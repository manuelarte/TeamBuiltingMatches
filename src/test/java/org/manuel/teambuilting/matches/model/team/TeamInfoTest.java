package org.manuel.teambuilting.matches.model.team;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
public class TeamInfoTest {

    @Test
    public void testDeserializeRegisteredTeamInfo() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final String teamId = UUID.randomUUID().toString();
        final Map<String, String> map = new HashMap<>();
        map.put("teamId", teamId);
        final JSONObject json = new JSONObject(map);
        final TeamInfo teamInfo = mapper.readValue(json.toString(), TeamInfo.class);
        assertTrue(teamInfo instanceof RegisteredTeamInfo);
    }

    @Test
    public void testDeserializeUnRegisteredTeamInfo() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final String name = UUID.randomUUID().toString();
        final Map<String, String> map = new HashMap<>();
        map.put("name", name);
        final JSONObject json = new JSONObject(map);
        final TeamInfo teamInfo = mapper.readValue(json.toString(), TeamInfo.class);
        assertTrue(teamInfo instanceof UnRegisteredTeamInfo);
    }
}
