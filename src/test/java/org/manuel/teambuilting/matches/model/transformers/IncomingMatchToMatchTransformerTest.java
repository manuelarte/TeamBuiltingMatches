package org.manuel.teambuilting.matches.model.transformers;

import com.auth0.Auth0User;
import com.auth0.authentication.result.UserIdentity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.manuel.teambuilting.core.utils.Util;
import org.manuel.teambuilting.matches.model.IncomingMatch;
import org.manuel.teambuilting.matches.model.Match;
import org.manuel.teambuilting.matches.model.TeamInMatch;
import org.manuel.teambuilting.matches.model.events.MatchEvent;
import org.manuel.teambuilting.matches.model.parts.MatchPart;
import org.manuel.teambuilting.matches.model.parts.MatchPartImpl;
import org.manuel.teambuilting.matches.model.player.PlayerInfo;
import org.manuel.teambuilting.matches.model.player.RegisteredPlayerInfo;
import org.manuel.teambuilting.matches.model.player.UnRegisteredPlayerInfo;
import org.manuel.teambuilting.matches.model.team.RegisteredTeamInfo;
import org.manuel.teambuilting.matches.model.team.TeamInfo;
import org.manuel.teambuilting.matches.model.team.UnRegisteredTeamInfo;
import org.manuel.teambuilting.matches.transformers.IncomingMatchToMatchTransformer;

import java.math.BigInteger;
import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Manuel Doncel Martos
 * @since 06/08/2017.
 */
public class IncomingMatchToMatchTransformerTest {

    private Util util;

    private IncomingMatchToMatchTransformer transformer;

    @BeforeEach
    public void beforeEach() {
        util = mock(Util.class);
        transformer = new IncomingMatchToMatchTransformer(util);
    }

    @Test
    public void testTransformMatchWithAllFields() {
        final String createdBy = "userId";
        final Optional<Auth0User> userProfile = createUserProfile(createdBy);
        when(util.getUserProfile()).thenReturn(userProfile);
        final IncomingMatch incomingMatch = createIncomingMatch();
        final Match apply = transformer.apply(incomingMatch);
        assertEquals(incomingMatch.getId(), apply.getId());
        assertEquals(createdBy, apply.getCreatedBy());
        assertEquals(incomingMatch.getHomeTeam(), apply.getHomeTeam());
        assertEquals(incomingMatch.getAwayTeam(), apply.getAwayTeam());
        assertEquals(incomingMatch.getMatchParts(), apply.getMatchParts());
        assertEquals(incomingMatch.getLocation(), apply.getLocation());
        assertEquals(incomingMatch.getDescription(), apply.getDescription());

    }

    private Optional<Auth0User> createUserProfile(final String userId) {
        final Map<String, Object> userMetadata = new HashMap<>();
        final Map<String, Object> appMetadata = new HashMap<>();
        final Map<String, Object> extraInfo = new HashMap<>();
        final List<UserIdentity> identities = new ArrayList<>();
        final Date createdAt = new Date();
        final boolean emailVerified= true;
        return Optional.of(new Auth0User(userId, "name", "nickname", "picture", "email",
        emailVerified, "givenName", "familyName", createdAt, identities,
        userMetadata, appMetadata, extraInfo));
    }

    private IncomingMatch createIncomingMatch() {
        final TeamInfo homeTeamInfo = RegisteredTeamInfo.builder().id("homeTeamInfoId").teamId("teamId").build();
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
        return IncomingMatch.builder().id("id").location("location")
                .homeTeam(homeTeam).awayTeam(awayTeam).matchPart(part)
                .events(matchEvents).tag("test").build();

    }
}
