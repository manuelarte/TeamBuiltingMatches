package org.manuel.teambuilting.matches.model.parts;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public interface MatchPart {

	Instant getStartingTime();

	Duration getDuration();

	Instant getEndingTime();
	
	List<MatchEvent> getEvents();

}
