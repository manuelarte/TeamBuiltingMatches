package org.manuel.teambuilting.matches.model;

import org.manuel.teambuilting.matches.model.parts.MatchPart;

import java.time.Duration;
import java.util.List;

/**
 * @author Manuel Doncel Martos
 * @since 2017/06/17
 * 
 * Interface for the Match
 */
public interface Match {
	
	String getId();
	
	String getLocation();
	
	List<MatchPart> getMatchParts();
	
	/**
	 * The home team of the game
	 * @return the home team
	 */
	TeamInMatch getHomeTeam();
	
	/**
	 * The away team of the game
	 * @return the home team
	 */
	TeamInMatch getAwayTeam();
	
	String getDescription();

	/**
	 * The duration of the game
	 * @return The duration of the game
	 */
	Duration getDuration();
	

}
