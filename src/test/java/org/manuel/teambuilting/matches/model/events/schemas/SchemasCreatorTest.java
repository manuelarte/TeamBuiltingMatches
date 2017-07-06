package org.manuel.teambuilting.matches.model.events.schemas;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.manuel.teambuilting.matches.model.Match;
import org.manuel.teambuilting.matches.model.MatchImpl;
import org.manuel.teambuilting.matches.model.TeamInMatch;
import org.manuel.teambuilting.matches.model.parts.MatchPart;
import org.manuel.teambuilting.matches.model.team.RegisteredTeamInfo;
import org.manuel.teambuilting.matches.model.team.TeamInfo;
import org.manuel.teambuilting.matches.model.team.UnRegisteredTeamInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Manuel Doncel Martos
 * @since 01/07/2017.
 */
public class SchemasCreatorTest {

    @Test
    public void testSerializeGoalEvent() throws JsonProcessingException {
        final SchemasCreator schemasCreator = new SchemasCreator();

        final TeamInfo homeTeamInfo = new RegisteredTeamInfo("homeTeamInfoId", "homeTeamId");
        final TeamInMatch homeTeam = TeamInMatch.builder().teamInfo(homeTeamInfo).selectedPlayers(new ArrayList<>()).build();

        final TeamInfo awayTeamInfo = new UnRegisteredTeamInfo("awayTeamInfoId", "Away Team Name");
        final TeamInMatch awayTeam = TeamInMatch.builder().teamInfo(awayTeamInfo).selectedPlayers(new ArrayList<>()).build();

        List<MatchPart> parts = new ArrayList<>();
        final Match match = new MatchImpl(null, homeTeam, awayTeam, null, parts, null, new ArrayList<>());
        schemasCreator.createGoalEventSchema();
    }

}
