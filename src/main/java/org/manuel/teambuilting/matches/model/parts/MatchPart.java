package org.manuel.teambuilting.matches.model.parts;

import java.time.Duration;
import java.util.List;

public interface MatchPart {
	
	Duration getDuration();
	
	List<MatchEvent> getEvents();

}
