package org.manuel.teambuilting.matches.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.manuel.teambuilting.matches.model.team.RegisteredTeamInfo;
import org.manuel.teambuilting.matches.model.team.TeamInfo;
import org.manuel.teambuilting.matches.model.team.UnRegisteredTeamInfo;

import java.io.IOException;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
public class TeamInfoDeserializer extends JsonDeserializer<TeamInfo> {

    private static final String TEAM_ID = "teamId";

    @Override
    public TeamInfo deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        final ObjectMapper mapper = (ObjectMapper) p.getCodec();
        final ObjectNode root = (ObjectNode) mapper.readTree(p);
        TeamInfo teamInfo = null;
        if (root.has(TEAM_ID)) {
            teamInfo = mapper.readValue(root.toString(), RegisteredTeamInfo.class);
        } else {
            teamInfo = mapper.readValue(root.toString(), UnRegisteredTeamInfo.class);
        }
        return teamInfo;
    }
}
