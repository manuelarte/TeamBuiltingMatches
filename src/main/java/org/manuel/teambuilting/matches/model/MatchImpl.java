package org.manuel.teambuilting.matches.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.collect.Ordering;
import com.mongodb.annotations.Immutable;
import lombok.Data;
import lombok.Singular;
import org.hibernate.validator.constraints.NotEmpty;
import org.manuel.teambuilting.matches.model.parts.MatchPart;
import org.manuel.teambuilting.matches.model.team.RegisteredTeamInfo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static org.manuel.teambuilting.matches.util.Util.MAX_DURATION_OF_MATCH;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = MatchImpl.MatchImplBuilder.class)
@Immutable
@Document
@Data
@lombok.Builder
/**
 * @author Manuel Doncel Martos
 * @since 2017/06/17
 * 
 * The Match
 */
public class MatchImpl implements Match {
	
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

    public MatchImpl(final String id, final TeamInMatch homeTeam, final TeamInMatch awayTeam,
                     final String location, final List<MatchPart> matchParts, final String description) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.location = location;
        this.matchParts = Ordering.from(sortByStartingTime()).sortedCopy(matchParts);
        this.description = description;
    }

    @Override
	public List<MatchPart> getMatchParts() {
        return matchParts;
	}

	@Override
    public Date getStartingTime() {
	    return getMatchParts().get(0).getStartingTime();
    }

    @Override
    public Date getEndingTime() {
        return new Date(getStartingTime().getTime() + getDuration().toMillis());
    }

    @Override
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

    private Comparator<? super MatchPart> sortByStartingTime() {
	    return new Comparator<MatchPart>() {
            @Override
            public int compare(MatchPart o1, MatchPart o2) {
                return o1.getStartingTime().compareTo(o2.getStartingTime());
            }
        };
    }

    @JsonPOJOBuilder(withPrefix = "")
	public static class MatchImplBuilder {
		
	}

}
