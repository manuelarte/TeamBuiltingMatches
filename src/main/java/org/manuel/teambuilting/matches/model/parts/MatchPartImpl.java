package org.manuel.teambuilting.matches.model.parts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.manuel.teambuilting.matches.model.parts.events.MatchEvent;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.Duration;
import java.time.Instant;
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
    @Past
    private final Instant startingTime;

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
        final Instant when = event.getWhen();
        return when.isBefore(startingTime) || when.isAfter(getEndingTime());
    }

    @Override
    @JsonIgnore
    public Instant getEndingTime() {
        return startingTime.plus(duration);
    }

	@JsonPOJOBuilder(withPrefix = "")
	public static class MatchPartImplBuilder {

	}
	
}
