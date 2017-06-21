package org.manuel.teambuilting.matches.model.player;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
public class PlayerInfoTest {

    @Test
    public void testDeserializeRegisteredTeamInfo() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final BigInteger playerId = BigInteger.ONE;
        final Map<String, Object> map = new HashMap<>();
        map.put("playerId", playerId);
        final JSONObject json = new JSONObject(map);
        final PlayerInfo teamInfo = mapper.readValue(json.toString(), PlayerInfo.class);
        assertTrue(teamInfo instanceof RegisteredPlayerInfo);
    }

    @Test
    public void testDeserializeUnRegisteredTeamInfo() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final String name = UUID.randomUUID().toString();
        final Map<String, String> map = new HashMap<>();
        map.put("name", name);
        final JSONObject json = new JSONObject(map);
        final PlayerInfo teamInfo = mapper.readValue(json.toString(), PlayerInfo.class);
        assertTrue(teamInfo instanceof UnRegisteredPlayerInfo);
    }
}
