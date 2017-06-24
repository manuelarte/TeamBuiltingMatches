package org.manuel.teambuilting.matches.model.parts;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.manuel.teambuilting.matches.model.parts.events.MatchEvent;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@JsonDeserialize(as = MatchPartImpl.class)
public interface MatchPart {

	Instant getStartingTime();

	Duration getDuration();

	Instant getEndingTime();
	
	List<MatchEvent> getEvents();

}
