package org.manuel.teambuilting.matches.model.parts.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Manuel Doncel Martos
 * @since 23/06/2017.
 */
public class GoalEventTest {

    private static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    @Test
    public void testSerializeGoalEvent() throws JsonProcessingException {
        final String teamWhoScored = UUID.randomUUID().toString();
        final GoalEvent goalEvent = GoalEvent.builder().when(new Date()).teamWhoScored(teamWhoScored).build();
        final JSONObject json = new JSONObject(mapper.writeValueAsString(goalEvent));
        final String goalFieldName = MatchEventType.GOAL.value();
        assertTrue(json.has(goalFieldName));
    }

    @Test
    public void testDeserializeGoalEvent() throws IOException {
        final Date when = new Date();
        final String who = UUID.randomUUID().toString();
        final String teamWhoScored = UUID.randomUUID().toString();
        final GoalEvent goalEventExpected = GoalEvent.builder().when(when).teamWhoScored(teamWhoScored).who(who).build();

        final String json = mapper.writeValueAsString(goalEventExpected);
        final MatchEvent matchEventActual = mapper.readValue(json.toString(), GoalEvent.class);
        assertTrue(matchEventActual instanceof GoalEvent);
        final GoalEvent actual = (GoalEvent) matchEventActual;
        assertEquals(who, actual.getWho());
        assertEquals(when, actual.getWhen());
        assertEquals(teamWhoScored, actual.getTeamWhoScored());
    }

}
