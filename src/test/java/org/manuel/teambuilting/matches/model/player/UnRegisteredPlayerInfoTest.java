package org.manuel.teambuilting.matches.model.player;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnRegisteredPlayerInfoTest {

	@Test
	public void testBuilder() {
		final String id = UUID.randomUUID().toString();
		final String playerName = UUID.randomUUID().toString();
		final UnRegisteredPlayerInfo knownPlayer = UnRegisteredPlayerInfo.builder().id(id).name(playerName).build();
		assertEquals(playerName, knownPlayer.getName());
	}
	
	@Test
	public void testConvertToJson() throws JsonProcessingException {
		final ObjectMapper mapper = new ObjectMapper();
		final String id = UUID.randomUUID().toString();
		final String playerName = UUID.randomUUID().toString();
		final UnRegisteredPlayerInfo knownPlayer = UnRegisteredPlayerInfo.builder().id(id).name(playerName).build();
		final JSONObject json = new JSONObject(mapper.writeValueAsString(knownPlayer));
		assertTrue(json.has("name"));
		assertTrue(json.has("id"));
		assertEquals(playerName, json.get("name"));
	}
	
	@Test
	public void testJsonToObject() throws IOException {
		final String id = UUID.randomUUID().toString();
		final String playerName = UUID.randomUUID().toString();
		final ObjectMapper mapper = new ObjectMapper();
		final Map<String, String> map = new HashMap<>();
		map.put("name", playerName);
		map.put("id", id);
		final JSONObject json = new JSONObject(map);
		
		final UnRegisteredPlayerInfo knownPlayer = mapper.readValue(json.toString(), UnRegisteredPlayerInfo.class);
		assertEquals(playerName, knownPlayer.getName());
		assertEquals(id, knownPlayer.getId());
	}
	
}
