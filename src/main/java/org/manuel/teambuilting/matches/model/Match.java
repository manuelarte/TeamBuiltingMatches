package org.manuel.teambuilting.matches.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.manuel.teambuilting.matches.model.parts.MatchPart;
import org.manuel.teambuilting.matches.model.events.MatchEvent;

import java.time.Duration;
import java.util.Date;
import java.util.List;

/**
 * @author Manuel Doncel Martos
 * @since 2017/06/17
 * 
 * Interface for the Match
 */
@JsonDeserialize(as = MatchImpl.class)
public interface Match {
	
	String getId();

	Date getStartingTime();

    Date getEndingTime();

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

	List<MatchEvent> getEvents();

}
