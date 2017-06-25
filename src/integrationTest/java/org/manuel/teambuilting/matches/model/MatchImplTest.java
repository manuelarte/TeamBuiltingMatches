package org.manuel.teambuilting.matches.model;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.manuel.teambuilting.matches.model.parts.MatchPart;
import org.manuel.teambuilting.matches.model.parts.MatchPartImpl;
import org.manuel.teambuilting.matches.model.parts.events.GoalEvent;
import org.manuel.teambuilting.matches.model.parts.events.MatchEvent;
import org.manuel.teambuilting.matches.model.player.PlayerInfo;
import org.manuel.teambuilting.matches.model.player.RegisteredPlayerInfo;
import org.manuel.teambuilting.matches.model.player.UnRegisteredPlayerInfo;
import org.manuel.teambuilting.matches.model.team.RegisteredTeamInfo;
import org.manuel.teambuilting.matches.model.team.TeamInfo;
import org.manuel.teambuilting.matches.model.team.UnRegisteredTeamInfo;
import org.manuel.teambuilting.matches.repositories.MatchRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.math.BigInteger;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchImplTest {

    @Inject
    private MatchRepository matchRepository;

    @After
    public void afterEach() {
        matchRepository.deleteAll();
    }

    @Test
    public void testSaveSimpleGame() {
        final String teamInfoId = UUID.randomUUID().toString();
        final TeamInfo homeTeamInfo = RegisteredTeamInfo.builder().id(teamInfoId).teamId("teamId").build();

        final String playerInfoId = UUID.randomUUID().toString();
        final PlayerInfo homePlayerOne = RegisteredPlayerInfo.builder().id(playerInfoId).playerId(BigInteger.ONE).build();
        final TeamInMatch homeTeam = TeamInMatch.builder().teamInfo(homeTeamInfo).selectedPlayer(homePlayerOne).build();

        final TeamInfo awayTeamInfo = UnRegisteredTeamInfo.builder().name("UnRegistered Team").build();
        final PlayerInfo awayPlayerOne = UnRegisteredPlayerInfo.builder().name("UnRegistered Player").build();
        final TeamInMatch awayTeam = TeamInMatch.builder().teamInfo(awayTeamInfo).selectedPlayer(awayPlayerOne).build();

        final Date startingTime = new Date();

        final MatchEvent firstGoalEvent = GoalEvent.builder().when(new Date(startingTime.getTime() + Duration.ofMinutes(5).toMillis()))
                .teamWhoScored(homeTeam.getTeamInfo().getId()).build();
        final List<MatchEvent> matchEvents = Arrays.asList(firstGoalEvent);


        final MatchPart part = MatchPartImpl.builder().startingTime(startingTime).duration(Duration.ofMinutes(45)).events(matchEvents).build();

        final Match match = MatchImpl.builder().homeTeam(homeTeam).awayTeam(awayTeam).matchPart(part).build();
        matchRepository.save(match);

        final Match actual = matchRepository.findOne(match.getId());
        assertEquals(match, actual);
    }
}
