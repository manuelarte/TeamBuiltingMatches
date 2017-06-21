package org.manuel.teambuilting.matches.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mongodb.annotations.Immutable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Singular;
import org.hibernate.validator.constraints.NotEmpty;
import org.manuel.teambuilting.matches.model.parts.MatchPart;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = MatchImpl.MatchImplBuilder.class)
@Immutable
@Document
@Data
@lombok.Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
	private final TeamInMatch homeTeam;
	
	@NotNull
	private final TeamInMatch awayTeam;
	
	private final String location;
	
	@NotEmpty
	@Singular
	private final List<MatchPart> matchParts;
	
	private final String description;

	@Override
	public List<MatchPart> getMatchParts() {
		return new ArrayList<>(matchParts);
	}

	@Override
	public Duration getDuration() {
		Duration totalDuration = Duration.ZERO;
		for (MatchPart part : matchParts) {
		    totalDuration = totalDuration.plus(part.getDuration());
        }
		return totalDuration;
	}

    @JsonPOJOBuilder(withPrefix = "")
	public static class MatchImplBuilder {
		
	}

}
