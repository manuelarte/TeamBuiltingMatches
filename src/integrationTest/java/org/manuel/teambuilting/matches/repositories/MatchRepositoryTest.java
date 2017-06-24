package org.manuel.teambuilting.matches.repositories;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.manuel.teambuilting.matches.model.Match;
import org.manuel.teambuilting.matches.model.MatchImpl;
import org.manuel.teambuilting.matches.model.TeamInMatch;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertTrue;

/**
 * @author Manuel Doncel Martos
 * @since 24/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchRepositoryTest {

    @Inject
    private MatchRepository matchRepository;

    @After
    public void after() {
        matchRepository.deleteAll();
    }

    @Test
    public void testRetrieveMatchesBetweenTwoSameInstantsNoPreviousRecords() {
        final Set<Match> matchesBetweenTwoInstants = matchRepository.findByEndingTimeIsBetween(Instant.now(), Instant.now());
        assertTrue(matchesBetweenTwoInstants.isEmpty());
    }

    @Test
    public void testRetrieveMatchesBetweenTwoInstantsOneMatchInBetween() {
        final Instant startingTime = Instant.now();
        final Duration duration = Duration.ofMinutes(45);
        createAndSaveMatch(startingTime, duration);

        final Instant startingLookingDate = startingTime;
        final Instant endingLookingDate = startingLookingDate.plus(Duration.ofMinutes(90));
        final Set<Match> matchesBetweenTwoInstants = matchRepository.findByEndingTimeIsBetween(startingLookingDate, endingLookingDate);
        assertTrue(matchesBetweenTwoInstants.size() == 1);
    }

    private Match createAndSaveMatch(final Instant startingTime, Duration duration) {
        final String homeTeamInfoId = UUID.randomUUID().toString();
        final TeamInfo homeTeamInfo = RegisteredTeamInfo.builder().id(homeTeamInfoId).teamId("teamId").build();

        final String homePlayerInfoId = UUID.randomUUID().toString();
        final PlayerInfo homePlayerOne = RegisteredPlayerInfo.builder().id(homePlayerInfoId).playerId(BigInteger.ONE).build();
        final TeamInMatch homeTeam = TeamInMatch.builder().teamInfo(homeTeamInfo).selectedPlayer(homePlayerOne).build();

        final String awayTeamInfoId = UUID.randomUUID().toString();
        final String awayPlayerInfoId = UUID.randomUUID().toString();
        final TeamInfo awayTeamInfo = UnRegisteredTeamInfo.builder().id(awayTeamInfoId).name("UnRegistered Team").build();
        final PlayerInfo awayPlayerOne = UnRegisteredPlayerInfo.builder().id(awayPlayerInfoId).name("UnRegistered Player").build();
        final TeamInMatch awayTeam = TeamInMatch.builder().teamInfo(awayTeamInfo).selectedPlayer(awayPlayerOne).build();

        final MatchEvent firstGoalEvent = GoalEvent.builder().when(startingTime.plus(Duration.ofMinutes(5)))
                .teamWhoScored(homeTeam.getTeamInfo().getId()).build();
        final List<MatchEvent> matchEvents = Arrays.asList(firstGoalEvent);

        final MatchPart part = MatchPartImpl.builder().startingTime(startingTime).duration(duration).events(matchEvents).build();

        final Match match = MatchImpl.builder().homeTeam(homeTeam).awayTeam(awayTeam).matchPart(part).build();
        matchRepository.save(match);
        return match;
    }

}
