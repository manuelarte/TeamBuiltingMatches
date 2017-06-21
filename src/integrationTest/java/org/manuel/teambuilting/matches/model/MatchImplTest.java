package org.manuel.teambuilting.matches.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.manuel.teambuilting.matches.model.parts.MatchPart;
import org.manuel.teambuilting.matches.model.parts.MatchPartImpl;
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
import java.util.ArrayList;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchImplTest {

    @Inject
    private MatchRepository matchRepository;

    @Test
    public void testSaveSimpleGame() {
        final TeamInfo homeTeamInfo = RegisteredTeamInfo.builder().teamId("teamId").build();
        final PlayerInfo homePlayerOne = RegisteredPlayerInfo.builder().playerId(BigInteger.ONE).build();
        final TeamInMatch homeTeam = TeamInMatch.builder().teamInfo(homeTeamInfo).selectedPlayer(homePlayerOne).build();

        final TeamInfo awayTeamInfo = UnRegisteredTeamInfo.builder().name("UnRegistered Team").build();
        final PlayerInfo awayPlayerOne = UnRegisteredPlayerInfo.builder().name("UnRegistered Player").build();
        final TeamInMatch awayTeam = TeamInMatch.builder().teamInfo(awayTeamInfo).selectedPlayer(awayPlayerOne).build();

        final MatchPart part = MatchPartImpl.builder().events(new ArrayList<>()).duration(Duration.ofMinutes(45)).build();

        final Match match = MatchImpl.builder().homeTeam(homeTeam).awayTeam(awayTeam).matchPart(part).build();
        matchRepository.save(match);
    }
}
