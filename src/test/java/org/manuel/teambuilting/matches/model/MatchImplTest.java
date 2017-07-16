package org.manuel.teambuilting.matches.model;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.manuel.teambuilting.matches.model.events.GoalEvent;
import org.manuel.teambuilting.matches.model.events.MatchEvent;
import org.manuel.teambuilting.matches.model.events.SubstitutionEvent;
import org.manuel.teambuilting.matches.model.parts.MatchPart;
import org.manuel.teambuilting.matches.model.parts.MatchPartImpl;
import org.manuel.teambuilting.matches.model.player.PlayerInfo;
import org.manuel.teambuilting.matches.model.player.RegisteredPlayerInfo;
import org.manuel.teambuilting.matches.model.player.UnRegisteredPlayerInfo;
import org.manuel.teambuilting.matches.model.team.RegisteredTeamInfo;
import org.manuel.teambuilting.matches.model.team.TeamInfo;
import org.manuel.teambuilting.matches.model.team.UnRegisteredTeamInfo;

import java.math.BigInteger;
import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
public class MatchImplTest {

    @Test
    public void testMatchDurationOnePart() {
        final String teamInfoId = UUID.randomUUID().toString();
        final TeamInfo homeTeamInfo = RegisteredTeamInfo.builder().id(teamInfoId).teamId("teamId").build();
        final String playerInfoId = UUID.randomUUID().toString();
        final PlayerInfo homePlayerOne = RegisteredPlayerInfo.builder().id(playerInfoId).playerId(BigInteger.ONE).build();
        final TeamInMatch homeTeam = TeamInMatch.builder().teamInfo(homeTeamInfo).selectedPlayer(homePlayerOne).build();

        final TeamInfo awayTeamInfo = UnRegisteredTeamInfo.builder().name("UnRegistered Team").build();
        final PlayerInfo awayPlayerOne = UnRegisteredPlayerInfo.builder().name("UnRegistered Player").build();
        final TeamInMatch awayTeam = TeamInMatch.builder().teamInfo(awayTeamInfo).selectedPlayer(awayPlayerOne).build();

        final Date startingTime = new Date();
        final Duration duration = Duration.ofMinutes(45);
        final List<MatchEvent> matchEvents = new ArrayList<>();
        final MatchPart part = MatchPartImpl.builder().startingTime(startingTime)
                .endingTime(new Date(startingTime.getTime() + duration.toMillis())).build();

        final Match match = MatchImpl.builder().homeTeam(homeTeam).awayTeam(awayTeam).matchPart(part).events(matchEvents).build();
        assertTrue(part.getDuration().compareTo(match.getDuration()) == 0);
    }

    @Test
    public void testMatchDurationTwoParts() {
        final String homeTeamInfoId = UUID.randomUUID().toString();
        final TeamInfo homeTeamInfo = RegisteredTeamInfo.builder().id(homeTeamInfoId).teamId("teamId").build();
        final String homePlayerInfoId = UUID.randomUUID().toString();
        final PlayerInfo homePlayerOne = RegisteredPlayerInfo.builder().id(homePlayerInfoId).playerId(BigInteger.ONE).build();
        final TeamInMatch homeTeam = TeamInMatch.builder().teamInfo(homeTeamInfo).selectedPlayer(homePlayerOne).build();

        final String awayTeamInfoId = UUID.randomUUID().toString();
        final TeamInfo awayTeamInfo = UnRegisteredTeamInfo.builder().id(awayTeamInfoId).name("UnRegistered Team").build();
        final String awayPlayerInfoId = UUID.randomUUID().toString();
        final PlayerInfo awayPlayerOne = UnRegisteredPlayerInfo.builder().id(awayPlayerInfoId).name("UnRegistered Player").build();
        final TeamInMatch awayTeam = TeamInMatch.builder().teamInfo(awayTeamInfo).selectedPlayer(awayPlayerOne).build();

        final Date startingTimePart = new Date();
        final Duration durationPart = Duration.ofMinutes(45);

        final Date startingTimePart2 = new Date(startingTimePart.getTime() + (durationPart.plus(Duration.ofMinutes(15))).toMillis());
        final Duration durationPart2 = Duration.ofMinutes(45);

        final List<MatchEvent> matchEvents = new ArrayList<>();
        final MatchPart part = MatchPartImpl.builder().startingTime(startingTimePart)
                .endingTime(new Date(startingTimePart.getTime() + durationPart.toMillis()))
                .build();
        final MatchPart part2 = MatchPartImpl.builder().startingTime(startingTimePart2)
                .endingTime(new Date(startingTimePart2.getTime() + durationPart2.toMillis())).build();
        final List<MatchPart> parts = Lists.newArrayList(part, part2);

        final Match match = MatchImpl.builder().homeTeam(homeTeam).awayTeam(awayTeam).matchParts(parts).events(matchEvents).build();
        assertTrue(Duration.ofMinutes(90).compareTo(match.getDuration()) == 0);
        assertTrue(match.getEvents().isEmpty());
    }

    @Test
    public void testEvents() {
        final String teamInfoId = UUID.randomUUID().toString();
        final TeamInfo homeTeamInfo = RegisteredTeamInfo.builder().id(teamInfoId).teamId("teamId").build();
        final String playerInfoId = UUID.randomUUID().toString();
        final PlayerInfo homePlayerOne = RegisteredPlayerInfo.builder().id(playerInfoId).playerId(BigInteger.ONE).build();
        final TeamInMatch homeTeam = TeamInMatch.builder().teamInfo(homeTeamInfo).selectedPlayer(homePlayerOne).build();

        final TeamInfo awayTeamInfo = UnRegisteredTeamInfo.builder().name("UnRegistered Team").build();
        final PlayerInfo awayPlayerOne = UnRegisteredPlayerInfo.builder().name("UnRegistered Player").build();
        final TeamInMatch awayTeam = TeamInMatch.builder().teamInfo(awayTeamInfo).selectedPlayer(awayPlayerOne).build();

        final Date startingTime = new Date();
        final Duration duration = Duration.ofMinutes(45);
        final MatchPart part = MatchPartImpl.builder().startingTime(startingTime)
                .endingTime(new Date(startingTime.getTime() + duration.toMillis())).build();

        final MatchEvent goalEvent = GoalEvent.builder().when(new Date()).teamThatScored(teamInfoId).build();

        final MatchEvent substitutionEvent = SubstitutionEvent.builder().when(new Date()).in(playerInfoId).build();
        final List<MatchEvent> matchEvents = Arrays.asList(goalEvent, substitutionEvent);

        final Match match = MatchImpl.builder().homeTeam(homeTeam).awayTeam(awayTeam).matchPart(part).events(matchEvents).build();
        assertTrue(part.getDuration().compareTo(match.getDuration()) == 0);
        assertTrue(match.getEvents().size() == 2);
        assertMatchEvents(match.getEvents(), goalEvent, substitutionEvent);
    }

    @Test
    public void testConvertFromJson() {

    }

    private void assertMatchEvents(final List<MatchEvent> actualEvents, final MatchEvent... expectedEvents) {
        for (int i = 0; i < expectedEvents.length; i++) {
            final MatchEvent actualEvent = actualEvents.get(i);
            assertTrue(actualEvent.equals(expectedEvents[i]));
        }
    }

}
