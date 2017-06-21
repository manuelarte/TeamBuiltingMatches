package org.manuel.teambuilting.matches.model.team;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = RegisteredTeamInfo.RegisteredTeamInfoBuilder.class)
@Immutable
@Data
@lombok.Builder
@AllArgsConstructor
/**
 * @author Manuel Doncel Martos
 * @since 2017/06/17
 * 
 * The team is registered and the info is available
 */
public class RegisteredTeamInfo implements TeamInfo {

	private final String id;
	
	@NotNull
	private final String teamId;

	@JsonPOJOBuilder(withPrefix = "")
	public static class RegisteredTeamInfoBuilder {

	}
	
}
