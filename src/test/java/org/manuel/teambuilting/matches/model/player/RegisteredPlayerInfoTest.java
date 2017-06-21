package org.manuel.teambuilting.matches.model.player;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisteredPlayerInfoTest {

	@Test
	public void testBuilder() {
		final String id = UUID.randomUUID().toString();
		final BigInteger playerId = BigInteger.ONE;
		final RegisteredPlayerInfo knownPlayer = RegisteredPlayerInfo.builder().id(id).playerId(playerId).build();
		assertEquals(playerId, knownPlayer.getPlayerId());
	}
	
	@Test
	public void testConvertToJson() throws JsonProcessingException {
		final ObjectMapper mapper = new ObjectMapper();
		final String id = UUID.randomUUID().toString();
		final BigInteger playerId = BigInteger.ONE;
		final RegisteredPlayerInfo knownPlayer = RegisteredPlayerInfo.builder().id(id).playerId(playerId).build();
		final JSONObject json = new JSONObject(mapper.writeValueAsString(knownPlayer));
		assertTrue(json.has("playerId"));
		assertEquals(json.get("playerId").toString(), playerId.toString());
	}
	
	@Test
	public void testJsonToObject() throws IOException {
		final String id = UUID.randomUUID().toString();
		final BigInteger playerId = BigInteger.ONE;
		final ObjectMapper mapper = new ObjectMapper();
		final Map<String, Object> map = new HashMap<>();
		map.put("playerId", playerId);
		map.put("id", id);
		final JSONObject json = new JSONObject(map);
		
		final RegisteredPlayerInfo knownPlayer = mapper.readValue(json.toString(), RegisteredPlayerInfo.class);
		assertEquals(playerId, knownPlayer.getPlayerId());
		assertEquals(id, knownPlayer.getId());
	}
	
}
