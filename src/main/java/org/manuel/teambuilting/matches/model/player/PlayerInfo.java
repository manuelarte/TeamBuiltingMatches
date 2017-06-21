package org.manuel.teambuilting.matches.model.player;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.manuel.teambuilting.matches.deserializers.PlayerInfoDeserializer;

/**
 * @author Manuel Doncel Martos
 * @since 2017/06/17
 * 
 * The player info in a game
 */
@JsonDeserialize(using = PlayerInfoDeserializer.class)
public interface PlayerInfo {
	
	String getId();

}
