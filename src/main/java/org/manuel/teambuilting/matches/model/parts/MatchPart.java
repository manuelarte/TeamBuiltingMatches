package org.manuel.teambuilting.matches.model.parts;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.manuel.teambuilting.matches.model.MatchItemValidable;

import java.time.Duration;
import java.util.Date;

@JsonDeserialize(as = MatchPartImpl.class)
public interface MatchPart extends MatchItemValidable {

	Date getStartingTime();

	Duration getDuration();

	Date getEndingTime();

}
