package org.manuel.teambuilting.matches.model;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.manuel.teambuilting.matches.model.parts.MatchPart;
import org.manuel.teambuilting.matches.model.parts.MatchPartImpl;
import org.manuel.teambuilting.matches.model.parts.events.MatchEvent;
import org.manuel.teambuilting.matches.model.player.PlayerInfo;
import org.manuel.teambuilting.matches.model.player.RegisteredPlayerInfo;
import org.manuel.teambuilting.matches.model.player.UnRegisteredPlayerInfo;
import org.manuel.teambuilting.matches.model.team.RegisteredTeamInfo;
import org.manuel.teambuilting.matches.model.team.TeamInfo;
import org.manuel.teambuilting.matches.model.team.UnRegisteredTeamInfo;

import java.math.BigInteger;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        final MatchPart part = MatchPartImpl.builder().startingTime(startingTime).events(matchEvents).duration(duration).build();

        final Match match = MatchImpl.builder().homeTeam(homeTeam).awayTeam(awayTeam).matchPart(part).build();
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
        final MatchPart part = MatchPartImpl.builder().startingTime(startingTimePart).duration(durationPart).events(matchEvents).build();
        final MatchPart part2 = MatchPartImpl.builder().startingTime(startingTimePart2).duration(durationPart2).events(matchEvents).duration(Duration.ofMinutes(45)).build();
        final List<MatchPart> parts = Lists.newArrayList(part, part2);

        final Match match = MatchImpl.builder().homeTeam(homeTeam).awayTeam(awayTeam).matchParts(parts).build();
        assertTrue(Duration.ofMinutes(90).compareTo(match.getDuration()) == 0);
    }

    @Test
    public void testConvertFromJson() {

    }
}
