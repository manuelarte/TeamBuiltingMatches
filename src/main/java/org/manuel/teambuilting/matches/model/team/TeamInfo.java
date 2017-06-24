package org.manuel.teambuilting.matches.model.team;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.manuel.teambuilting.matches.deserializers.TeamInfoDeserializer;

/**
 * @author Manuel Doncel Martos
 * @since 2017/06/17
 * 
 * The team info for the game
 */
@JsonDeserialize(using = TeamInfoDeserializer.class)
public interface TeamInfo {

    String getId();
	
}
