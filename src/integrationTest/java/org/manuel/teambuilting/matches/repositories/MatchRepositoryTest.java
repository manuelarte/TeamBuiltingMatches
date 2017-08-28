package org.manuel.teambuilting.matches.repositories;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.manuel.teambuilting.matches.model.Match;
import org.manuel.teambuilting.matches.model.MatchImpl;
import org.manuel.teambuilting.matches.model.TeamInMatch;
import org.manuel.teambuilting.matches.model.events.GoalEvent;
import org.manuel.teambuilting.matches.model.events.MatchEvent;
import org.manuel.teambuilting.matches.model.parts.MatchPart;
import org.manuel.teambuilting.matches.model.parts.MatchPartImpl;
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
import java.util.*;

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
        final Date date = new Date();
        final Set<Match> matchesBetweenTwoInstants = matchRepository.findByMatchPartsStartingTimeIsBetween(date, date);
        assertTrue(matchesBetweenTwoInstants.isEmpty());
    }

    @Test
    public void testRetrieveMatchesBetweenTwoInstantsOneMatchInBetween() {
        final Date startingTime = new Date();
        final Duration duration = Duration.ofMinutes(45);
        final Match match = createAndSaveMatch(startingTime, duration);

        final Date startingLookingDate = new Date(startingTime.getTime() - 100);
        final Date endingLookingDate = new Date(startingLookingDate.getTime() + Duration.ofMinutes(90).toMillis());
        final Set<Match> matchesBetweenTwoInstants = matchRepository.findByMatchPartsStartingTimeIsBetween(
                startingLookingDate, endingLookingDate);
        assertTrue(matchesBetweenTwoInstants.size() == 1);
    }

    private Match createAndSaveMatch(final Date startingTime, Duration duration) {
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

        final Date firstGoalEventWhen = new Date(startingTime.getTime() + Duration.ofMinutes(5).toMillis());
        final MatchEvent firstGoalEvent = GoalEvent.builder().when(firstGoalEventWhen)
                .teamThatScored(homeTeam.getTeamInfo().getId()).build();
        final List<MatchEvent> matchEvents = Arrays.asList(firstGoalEvent);

        final MatchPart part = MatchPartImpl.builder().startingTime(startingTime)
                .endingTime(new Date(startingTime.getTime() + duration.toMillis())).build();

        final Match match = MatchImpl.builder().homeTeam(homeTeam).awayTeam(awayTeam).matchPart(part).events(matchEvents).build();
        matchRepository.save(match);
        return match;
    }

}
