package org.manuel.teambuilting.matches.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mongodb.annotations.Immutable;
import lombok.Data;
import lombok.Singular;
import org.hibernate.validator.constraints.NotEmpty;
import org.manuel.teambuilting.matches.model.events.MatchEvent;
import org.manuel.teambuilting.matches.model.parts.MatchPart;
import org.manuel.teambuilting.matches.model.team.RegisteredTeamInfo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static org.manuel.teambuilting.matches.util.Util.MAX_DURATION_OF_MATCH;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = IncomingMatch.IncomingMatchBuilder.class)
@Immutable
@Document
@Data
@lombok.Builder(toBuilder = true)
/**
 * @author Manuel Doncel Martos
 * @since 2017/06/17
 * 
 * The Match
 */
public class IncomingMatch {

	@Id
	private final String id;

	@NotNull
    @Valid
	private final TeamInMatch homeTeam;

	@NotNull
    @Valid
	private final TeamInMatch awayTeam;

	private final String location;

	@NotEmpty
    @Valid
	@Singular
	private final List<MatchPart> matchParts;

	private final String description;

    @NotNull
    @Valid
    private final List<MatchEvent> events;

    @NotNull
    @Singular
    private final Set<String> tags;

    public List<MatchPart> getMatchParts() {
        return matchParts;
    }

    public Date getStartingTime() {
        return getMatchParts().get(0).getStartingTime();
    }

    public Date getEndingTime() {
        return new Date(getStartingTime().getTime() + getDuration().toMillis());
    }

    public Duration getDuration() {
        Duration totalDuration = Duration.ZERO;
        for (MatchPart part : matchParts) {
            totalDuration = totalDuration.plus(part.getDuration());
        }
        return totalDuration;
    }

	@AssertTrue
    public boolean matchPartsNoOverlap() {
	    for (int i = 1; i < getMatchParts().size(); i++) {
	        final MatchPart currentPart = getMatchParts().get(i);
	        final MatchPart previousPart = getMatchParts().get(i-1);
	        final Date startingOfPreviousPart = previousPart.getStartingTime();
            if (startingOfPreviousPart.after(currentPart.getEndingTime()) ) {
                return false;
            }
        }
        return true;
    }

    @AssertTrue
    public boolean checkMaxDuration() {
        return getDuration().plus(MAX_DURATION_OF_MATCH).isNegative();
    }

    @AssertTrue
    public boolean atLeastOneTeamIsRegistered() {
        return homeTeam.getTeamInfo() instanceof RegisteredTeamInfo || awayTeam.getTeamInfo() instanceof RegisteredTeamInfo;
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
        return when.before(getStartingTime()) || when.after(getEndingTime());
    }

    private Comparator<? super MatchPart> sortByStartingTime() {
	    return Comparator.comparing(MatchPart::getStartingTime);
    }

    @JsonPOJOBuilder(withPrefix = "")
	public static class IncomingMatchBuilder {
		
	}

}
