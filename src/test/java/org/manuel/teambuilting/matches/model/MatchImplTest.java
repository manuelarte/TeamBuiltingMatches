package org.manuel.teambuilting.matches.model;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
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
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
public class MatchImplTest {

    @Test
    public void testMatchDurationOnePart() {
        final TeamInfo homeTeamInfo = RegisteredTeamInfo.builder().teamId("teamId").build();
        final PlayerInfo homePlayerOne = RegisteredPlayerInfo.builder().playerId(BigInteger.ONE).build();
        final TeamInMatch homeTeam = TeamInMatch.builder().teamInfo(homeTeamInfo).selectedPlayer(homePlayerOne).build();

        final TeamInfo awayTeamInfo = UnRegisteredTeamInfo.builder().name("UnRegistered Team").build();
        final PlayerInfo awayPlayerOne = UnRegisteredPlayerInfo.builder().name("UnRegistered Player").build();
        final TeamInMatch awayTeam = TeamInMatch.builder().teamInfo(awayTeamInfo).selectedPlayer(awayPlayerOne).build();

        final Instant startingTime = Instant.now();
        final Duration duration = Duration.ofMinutes(45);
        final MatchPart part = MatchPartImpl.builder().startingTime(startingTime).events(new ArrayList<>()).duration(duration).build();

        final Match match = MatchImpl.builder().homeTeam(homeTeam).awayTeam(awayTeam).matchPart(part).build();
        assertTrue(part.getDuration().compareTo(match.getDuration()) == 0);
    }

    @Test
    public void testMatchDurationTwoParts() {
        final TeamInfo homeTeamInfo = RegisteredTeamInfo.builder().teamId("teamId").build();
        final PlayerInfo homePlayerOne = RegisteredPlayerInfo.builder().playerId(BigInteger.ONE).build();
        final TeamInMatch homeTeam = TeamInMatch.builder().teamInfo(homeTeamInfo).selectedPlayer(homePlayerOne).build();

        final TeamInfo awayTeamInfo = UnRegisteredTeamInfo.builder().name("UnRegistered Team").build();
        final PlayerInfo awayPlayerOne = UnRegisteredPlayerInfo.builder().name("UnRegistered Player").build();
        final TeamInMatch awayTeam = TeamInMatch.builder().teamInfo(awayTeamInfo).selectedPlayer(awayPlayerOne).build();

        final Instant startingTimePart = Instant.now();
        final Duration durationPart = Duration.ofMinutes(45);

        final Instant startingTimePart2 = startingTimePart.plus(durationPart.plus(Duration.ofMinutes(15)));
        final Duration durationPart2 = Duration.ofMinutes(45);

        final MatchPart part = MatchPartImpl.builder().startingTime(startingTimePart).duration(durationPart).events(new ArrayList<>()).build();
        final MatchPart part2 = MatchPartImpl.builder().startingTime(startingTimePart2).duration(durationPart2).events(new ArrayList<>()).duration(Duration.ofMinutes(45)).build();
        final List<MatchPart> parts = Lists.newArrayList(part, part2);

        final Match match = MatchImpl.builder().homeTeam(homeTeam).awayTeam(awayTeam).matchParts(parts).build();
        assertTrue(Duration.ofMinutes(90).compareTo(match.getDuration()) == 0);
    }

    @Test
    public void testConvertFromJson() {

    }
}
