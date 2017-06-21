package org.manuel.teambuilting.matches.model.parts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
public class MatchPartImplTest {

    @Test
    public void testBuilder() {
        final Instant startingTime = Instant.now();
        final Duration duration = Duration.ofMinutes(45);
        final List<MatchEvent> events = new ArrayList<>();
        final MatchPart matchPart = MatchPartImpl.builder().startingTime(startingTime).duration(duration).events(events).build();
        assertEquals(startingTime, matchPart.getStartingTime());
        assertEquals(duration, matchPart.getDuration());
    }

    @Test
    public void testConvertToJsonNoEvents() throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        final Instant startingTime = Instant.now();
        final Duration duration = Duration.ofMinutes(45);
        final List<MatchEvent> events = new ArrayList<>();
        final MatchPart matchPart = MatchPartImpl.builder().startingTime(startingTime).duration(duration).events(events).build();
        final JSONObject json = new JSONObject(mapper.writeValueAsString(matchPart));
        assertTrue(json.has("startingTime"));
        assertTrue(json.has("duration"));
    }


}
