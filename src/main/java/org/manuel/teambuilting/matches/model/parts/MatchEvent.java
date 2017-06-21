package org.manuel.teambuilting.matches.model.parts;

import java.time.Instant;

/**
 * @author Manuel Doncel Martos
 * @since 2017/06/17
 * 
 * Interface to declare an event that can happen in a game
 */
public interface MatchEvent {
	
	Instant getWhen();

}
