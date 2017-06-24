package org.manuel.teambuilting.matches.model.parts.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.manuel.teambuilting.matches.model.player.PlayerInfo;
import org.manuel.teambuilting.matches.model.player.RegisteredPlayerInfo;
import org.manuel.teambuilting.matches.model.team.RegisteredTeamInfo;
import org.manuel.teambuilting.matches.model.team.TeamInfo;

import java.io.IOException;
import java.math.BigInteger;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Manuel Doncel Martos
 * @since 23/06/2017.
 */
public class MatchEventTest {

    private static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    @Test
    public void testSeserializeGoalEvent() throws JsonProcessingException {
        final Instant when = Instant.now();

        final String playerInfoId = UUID.randomUUID().toString();
        final PlayerInfo who = RegisteredPlayerInfo.builder().id(playerInfoId).playerId(BigInteger.ONE).build();

        final String teamInfoId = UUID.randomUUID().toString();
        final TeamInfo teamWhoScored = RegisteredTeamInfo.builder().id(teamInfoId).teamId("teamId").build();
        final MatchEvent matchEvent = GoalEvent.builder().when(when).who(who.getId()).teamWhoScored(teamWhoScored.getId()).build();

        final String matchEventJson = mapper.writeValueAsString(matchEvent);
        //assertTrue(goalEvent instanceof GoalEvent);
        //final GoalEvent actual = (GoalEvent) goalEvent;
        //assertEquals(who, actual.getWho());
        //assertEquals(when, actual.getWhen());
        //assertEquals(teamWhoScored, actual.getTeamWhoScored());
    }

    @Test
    public void testDeserializeGoalEvent() throws IOException {
        final Instant when = Instant.now();
        final String playerInfoId = UUID.randomUUID().toString();
        final PlayerInfo who = RegisteredPlayerInfo.builder().id(playerInfoId).playerId(BigInteger.ONE).build();

        final String teamInfoId = UUID.randomUUID().toString();
        final TeamInfo teamWhoScored = RegisteredTeamInfo.builder().id(teamInfoId).teamId("teamId").build();
        final GoalEvent goalEventExpected = GoalEvent.builder().when(when).teamWhoScored(teamWhoScored.getId()).who(who.getId()).build();

        final JSONObject json = createJsonObjectFrom(goalEventExpected);
        final MatchEvent actual = mapper.readValue(json.toString(), MatchEvent.class);
        assertEquals(goalEventExpected, actual);
    }

    private JSONObject createJsonObjectFrom(final GoalEvent goalEvent) {
        final Map<String, Object> map = new HashMap<>();
        map.put(MatchEventType.GOAL.value(), goalEvent);
        return new JSONObject(map);
    }

}
