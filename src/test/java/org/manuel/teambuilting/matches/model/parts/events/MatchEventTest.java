package org.manuel.teambuilting.matches.model.parts.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.manuel.teambuilting.matches.model.player.PlayerInfo;
import org.manuel.teambuilting.matches.model.player.RegisteredPlayerInfo;
import org.manuel.teambuilting.matches.model.team.RegisteredTeamInfo;
import org.manuel.teambuilting.matches.model.team.TeamInfo;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
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
        final Date when = new Date();

        final String playerInfoId = UUID.randomUUID().toString();
        final PlayerInfo who = RegisteredPlayerInfo.builder().id(playerInfoId).playerId(BigInteger.ONE).build();

        final String teamInfoId = UUID.randomUUID().toString();
        final TeamInfo teamWhoScored = RegisteredTeamInfo.builder().id(teamInfoId).teamId("teamId").build();
        final MatchEvent matchEvent = GoalEvent.builder().when(when).who(who.getId()).teamThatScored(teamWhoScored.getId()).build();

        final String matchEventJson = mapper.writeValueAsString(matchEvent);
        //assertTrue(goalEvent instanceof GoalEvent);
        //final GoalEvent actual = (GoalEvent) goalEvent;
        //assertEquals(who, actual.getWho());
        //assertEquals(when, actual.getWhen());
        //assertEquals(teamWhoScored, actual.getTeamThatScored());
    }

    @Test
    public void testDeserializeGoalEvent() throws IOException {
        final Date when = new Date();
        final String playerInfoId = UUID.randomUUID().toString();
        final PlayerInfo who = RegisteredPlayerInfo.builder().id(playerInfoId).playerId(BigInteger.ONE).build();

        final String teamInfoId = UUID.randomUUID().toString();
        final TeamInfo teamWhoScored = RegisteredTeamInfo.builder().id(teamInfoId).teamId("teamId").build();
        final GoalEvent goalEventExpected = GoalEvent.builder().when(when).teamThatScored(teamWhoScored.getId()).who(who.getId()).build();

        final String json = mapper.writeValueAsString(goalEventExpected);
        final MatchEvent actual = mapper.readValue(json, MatchEvent.class);
        assertEquals(goalEventExpected, actual);
    }

}
