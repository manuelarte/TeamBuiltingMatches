package org.manuel.teambuilting.matches.model.parts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mongodb.annotations.Immutable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.List;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize
@Immutable
@Data
@lombok.Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
/**
 * @author Manuel Doncel Martos
 * @since 2017/06/17
 * 
 * The match part
 */
public class MatchPartImpl implements MatchPart {

	@NotNull
	private final Duration duration;
	
	@NotNull
	private final List<MatchEvent> events;
	
	@AssertTrue
	public boolean durationHasLength() {
		return duration.getSeconds() > 0;
	}
	
}
