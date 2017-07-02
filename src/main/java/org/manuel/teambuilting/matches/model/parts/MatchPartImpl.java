package org.manuel.teambuilting.matches.model.parts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.manuel.teambuilting.matches.model.Match;
import org.manuel.teambuilting.matches.model.parts.events.MatchEvent;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = MatchPartImpl.MatchPartImplBuilder.class)
@Immutable
@Data
@lombok.Builder
@AllArgsConstructor
/**
 * @author Manuel Doncel Martos
 * @since 2017/06/17
 * 
 * The match part
 */
public class MatchPartImpl implements MatchPart {

    @NotNull
    private final Date startingTime;

	@NotNull
	private final Duration duration;
	
	@NotNull
    @Valid
	private final List<MatchEvent> events;

	@AssertTrue
    @SuppressWarnings("unused")
	public boolean durationHasLength() {
		return duration.getSeconds() > 0;
	}

    @AssertTrue
    @SuppressWarnings("unused")
    public boolean eventsAndDurationMatch() {
        final List<MatchEvent> eventNotInMatchTime = events.stream().filter(event -> Optional.ofNullable(event.getWhen()).isPresent()
                && eventIsNotBetweenMatchTime(event)).collect(Collectors.toList());
        return eventNotInMatchTime.isEmpty();
    }

    private boolean eventIsNotBetweenMatchTime(final MatchEvent event) {
        final Date when = event.getWhen();
        return when.before(startingTime) || when.after(getEndingTime());
    }

    @Override
    @JsonIgnore
    public Date getEndingTime() {
        return new Date(startingTime.getTime() + duration.toMillis());
    }

    @Override
    @AssertTrue
    public boolean validInContext(Match match) {
	    // validate events that the players exist
        return true;
    }

    @JsonPOJOBuilder(withPrefix = "")
	public static class MatchPartImplBuilder {

	}
	
}
