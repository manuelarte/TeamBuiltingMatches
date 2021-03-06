package org.manuel.teambuilting.matches.model.team;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = UnRegisteredTeamInfo.UnRegisteredTeamInfoBuilder.class)
@Immutable
@Data
@lombok.Builder
@AllArgsConstructor

/**
 * @author Manuel Doncel Martos
 * @since 2017/06/17
 * 
 * The team is not registered
 */
public class UnRegisteredTeamInfo implements TeamInfo {

	@NotEmpty
	private final String id;

	@NotEmpty
	private final String name;

	private final String picture;

	@JsonPOJOBuilder(withPrefix = "")
	public static class UnRegisteredTeamInfoBuilder {

	}

}
