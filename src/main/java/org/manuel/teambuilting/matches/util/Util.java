package org.manuel.teambuilting.matches.util;

import lombok.experimental.UtilityClass;
import org.manuel.teambuilting.matches.model.Match;
import org.manuel.teambuilting.matches.model.team.TeamInfo;
import org.springframework.data.util.Pair;
import org.springframework.util.Assert;

import java.time.Duration;
import java.util.Optional;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
@UtilityClass
public class Util {

    /**
     * A Match cannot last longer than MAX DURATION OF MATCH
     */
    public static final Duration MAX_DURATION_OF_MATCH = Duration.ofMinutes(300);

    public static boolean homeTeamIsPresent(final Match match) {
        Assert.notNull(match, "Match cannot be null");
        return Optional.ofNullable(match.getHomeTeam()).isPresent()
                && Optional.ofNullable(match.getHomeTeam().getTeamInfo()).isPresent();
    }

    public static boolean awayTeamIsPresent(final Match match) {
        Assert.notNull(match, "Match cannot be null");
        return Optional.ofNullable(match.getAwayTeam()).isPresent()
                && Optional.ofNullable(match.getAwayTeam().getTeamInfo()).isPresent();
    }

    public static Pair<TeamInfo, TeamInfo> getTeams(final Match match) {
        return Pair.of(match.getHomeTeam().getTeamInfo(), match.getAwayTeam().getTeamInfo());
    }

}
