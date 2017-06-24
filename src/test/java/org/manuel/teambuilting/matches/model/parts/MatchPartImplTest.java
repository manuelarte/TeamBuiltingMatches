package org.manuel.teambuilting.matches.model.parts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.manuel.teambuilting.matches.model.parts.events.GoalEvent;
import org.manuel.teambuilting.matches.model.parts.events.MatchEvent;
import org.manuel.teambuilting.matches.model.parts.events.MatchEventType;
import org.manuel.teambuilting.matches.model.parts.events.SubstitutionEvent;
import org.manuel.teambuilting.matches.model.player.PlayerInfo;
import org.manuel.teambuilting.matches.model.player.RegisteredPlayerInfo;
import org.manuel.teambuilting.matches.model.team.RegisteredTeamInfo;
import org.manuel.teambuilting.matches.model.team.TeamInfo;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

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
        final Instant startingTime = Instant.now();
        final Duration duration = Duration.ofMinutes(45);
        final List<MatchEvent> matchEvents = new ArrayList<>();
        final MatchPart matchPart = MatchPartImpl.builder().startingTime(startingTime).duration(duration).events(matchEvents).build();
        assertEquals(startingTime, matchPart.getStartingTime());
        assertEquals(duration, matchPart.getDuration());
    }

    @Test
    public void testConvertToJsonNoEvents() throws JsonProcessingException {
        final Instant startingTime = Instant.now();
        final Duration duration = Duration.ofMinutes(45);
        final List<MatchEvent> matchEvents = new ArrayList<>();
        final MatchPart matchPart = MatchPartImpl.builder().startingTime(startingTime).duration(duration).events(matchEvents).build();
        final JSONObject json = new JSONObject(mapper.writeValueAsString(matchPart));
        assertTrue(json.has("startingTime"));
        assertTrue(json.has("duration"));
    }

    @Test
    public void testConvertToJsonOneGoalEvent() throws JsonProcessingException {
        final Instant startingTime = Instant.now();
        final Duration duration = Duration.ofMinutes(45);

        final String teamInfoId = UUID.randomUUID().toString();
        final TeamInfo teamInfo = RegisteredTeamInfo.builder().id(teamInfoId).teamId("teamId").build();
        final GoalEvent goalEvent = GoalEvent.builder().when(Instant.now()).teamWhoScored(teamInfo.getId()).build();
        final List<MatchEvent> matchEvents = Arrays.asList(goalEvent);

        final MatchPart matchPart = MatchPartImpl.builder().startingTime(startingTime).duration(duration).events(matchEvents).build();
        final JSONObject json = new JSONObject(mapper.writeValueAsString(matchPart));
        assertTrue(json.has("startingTime"));
        assertTrue(json.has("duration"));
        assertTrue(json.has("events"));
        final JSONArray events = (JSONArray) json.get("events");
        assertTrue(events.length() == 1);
        assertMatchEvents(events, goalEvent);
    }

    @Test
    public void testConvertToJsonOneGoalAndOneSubstitutionEvent() throws JsonProcessingException {
        final Instant startingTime = Instant.now();
        final Duration duration = Duration.ofMinutes(45);

        final String teamInfoId = UUID.randomUUID().toString();
        final TeamInfo teamInfo = RegisteredTeamInfo.builder().teamId("teamId").build();
        final MatchEvent goalEvent = GoalEvent.builder().when(Instant.now()).teamWhoScored(teamInfo.getId()).build();

        final String playerInfoId = UUID.randomUUID().toString();
        final PlayerInfo playerInfo = RegisteredPlayerInfo.builder().playerId(BigInteger.ONE).build();
        final MatchEvent substitutionEvent = SubstitutionEvent.builder().when(Instant.now()).in(playerInfo.getId()).build();
        final List<MatchEvent> matchEvents = Arrays.asList(goalEvent, substitutionEvent);

        final MatchPart matchPart = MatchPartImpl.builder().startingTime(startingTime).duration(duration).events(matchEvents).build();
        final JSONObject json = new JSONObject(mapper.writeValueAsString(matchPart));
        assertTrue(json.has("startingTime"));
        assertTrue(json.has("duration"));
        assertTrue(json.has("events"));
        final JSONArray events = (JSONArray) json.get("events");
        assertTrue(events.length() == 2);
        assertMatchEvents(events, goalEvent, substitutionEvent);
    }

    @Test
    public void testConvertToJsonTwoGoalEvents() throws JsonProcessingException {
        final Instant startingTime = Instant.now();
        final Duration duration = Duration.ofMinutes(45);

        final String teamInfoId = UUID.randomUUID().toString();
        final TeamInfo teamInfo = RegisteredTeamInfo.builder().id(teamInfoId).teamId("teamId").build();
        final MatchEvent goalEvent = GoalEvent.builder().when(Instant.now()).teamWhoScored(teamInfo.getId()).build();
        final MatchEvent secondGoalEvent = GoalEvent.builder().when(Instant.now()).teamWhoScored(teamInfo.getId()).build();
        final List<MatchEvent> matchEvents = Arrays.asList(goalEvent, secondGoalEvent);

        final MatchPart matchPart = MatchPartImpl.builder().startingTime(startingTime).duration(duration).events(matchEvents).build();

        final JSONObject json = new JSONObject(mapper.writeValueAsString(matchPart));
        assertTrue(json.has("startingTime"));
        assertTrue(json.has("duration"));
        assertTrue(json.has("events"));
        final JSONArray events = (JSONArray) json.get("events");
        assertTrue(events.length() == 2);
        assertMatchEvents(events, goalEvent, secondGoalEvent);

    }

    private void assertMatchEvents(final JSONArray actualEvents, final MatchEvent... expectedEvents) {
        for (int i = 0; i < expectedEvents.length; i++) {
            final JSONObject actualEvent = (JSONObject) actualEvents.get(i);
            final Optional<MatchEventType> matchEventType = Arrays.stream(MatchEventType.values()).filter(
                    eventType -> eventType.value().equals(actualEvent.keySet().iterator().next().toString()))
                    .findFirst();
            assertTrue(matchEventType.isPresent());
            assertTrue(actualEvent.has(matchEventType.get().value()));
        }
    }


}
