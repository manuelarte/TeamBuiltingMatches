package org.manuel.teambuilting.matches.model.parts;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.manuel.teambuilting.matches.model.parts.events.MatchEvent;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@JsonDeserialize(as = MatchPartImpl.class)
public interface MatchPart {

	Date getStartingTime();

	Duration getDuration();

	Date getEndingTime();
	
	List<MatchEvent> getEvents();

}
