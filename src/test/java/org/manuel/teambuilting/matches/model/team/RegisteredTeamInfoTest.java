package org.manuel.teambuilting.matches.model.team;

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

public class RegisteredTeamInfoTest {

	@Test
	public void testBuilder() {
		final String id = UUID.randomUUID().toString();
		final String teamId = UUID.randomUUID().toString();
		final RegisteredTeamInfo knownTeam = RegisteredTeamInfo.builder().id(id).teamId(teamId).build();
		assertEquals(teamId, knownTeam.getTeamId());
	}
	
	@Test
	public void testConvertToJson() throws JsonProcessingException {
		final ObjectMapper mapper = new ObjectMapper();
		final String id = UUID.randomUUID().toString();
		final String teamId = UUID.randomUUID().toString();
		final RegisteredTeamInfo knownPlayer = RegisteredTeamInfo.builder().id(id).teamId(teamId).build();
		final JSONObject json = new JSONObject(mapper.writeValueAsString(knownPlayer));
		assertTrue(json.has("teamId"));
		assertTrue(json.has("id"));
		assertEquals(teamId, json.get("teamId"));
		assertEquals(id, json.get("id"));
	}
	
	@Test
	public void testJsonToObject() throws IOException {
		final String id = UUID.randomUUID().toString();
		final String teamId = UUID.randomUUID().toString();
		final ObjectMapper mapper = new ObjectMapper();
		final Map<String, String> map = new HashMap<>();
		map.put("teamId", teamId);
		map.put("id", id);
		final JSONObject json = new JSONObject(map);
		
		final RegisteredTeamInfo knownTeam = mapper.readValue(json.toString(), RegisteredTeamInfo.class);
		assertEquals(teamId, knownTeam.getTeamId());
	}
	
}
