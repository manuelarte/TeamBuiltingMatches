package org.manuel.teambuilting.matches.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mongodb.annotations.Immutable;
import lombok.Data;
import lombok.Singular;
import org.manuel.teambuilting.matches.model.player.PlayerInfo;
import org.manuel.teambuilting.matches.model.team.TeamInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = TeamInMatch.TeamInMatchBuilder.class)
@Immutable
@Data
@lombok.Builder
/**
 * @author Manuel Doncel Martos
 * @since 2017/06/17
 * 
 * The team in match info
 */
public class TeamInMatch {
	
	/**
	 * Info of the team
	 */
	@NotNull
    @Valid
	private final TeamInfo teamInfo;
	
	/**
	 * The players that are called up for the game 
	 */
	@Singular
    @Valid
	private final Set<PlayerInfo> selectedPlayers;

	@JsonPOJOBuilder(withPrefix = "")
	public static class TeamInMatchBuilder {
		
	}

}
