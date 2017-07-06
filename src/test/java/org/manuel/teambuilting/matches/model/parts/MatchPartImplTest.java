package org.manuel.teambuilting.matches.model.parts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
public class MatchPartImplTest {

    private static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    @Test
    public void testBuilder() {
        final Date startingTime = new Date();
        final Duration duration = Duration.ofMinutes(45);
        final MatchPart matchPart = MatchPartImpl.builder().startingTime(startingTime).duration(duration).build();
        assertEquals(startingTime, matchPart.getStartingTime());
        assertEquals(duration, matchPart.getDuration());
    }

    @Test
    public void testConvertToJson() throws JsonProcessingException {
        final Date startingTime = new Date();
        final Duration duration = Duration.ofMinutes(45);
        final MatchPart matchPart = MatchPartImpl.builder().startingTime(startingTime).duration(duration).build();
        final JSONObject json = new JSONObject(mapper.writeValueAsString(matchPart));
        assertTrue(json.has("startingTime"));
        assertTrue(json.has("duration"));
    }

}
