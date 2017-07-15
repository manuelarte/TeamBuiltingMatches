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

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.Date;

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
	private final Date endingTime;

    @JsonIgnore
	@Override
	public Duration getDuration() {
        return Duration.ofMillis(endingTime.getTime() - startingTime.getTime());
    }

	@AssertTrue
    @SuppressWarnings("unused")
	public boolean endingTimeIsGreaterThanStartingTime() {
		return Duration.ofMillis(endingTime.getTime() - startingTime.getTime()).toMillis() > 0;
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
