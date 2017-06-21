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

public class UnRegisteredTeamInfoTest {

	@Test
	public void testBuilder() {
		final String id = UUID.randomUUID().toString();
		final String teamName = UUID.randomUUID().toString();
		final UnRegisteredTeamInfo knownTeam = UnRegisteredTeamInfo.builder().id(id).name(teamName).build();
		assertEquals(teamName, knownTeam.getName());
	}
	
	@Test
	public void testConvertToJson() throws JsonProcessingException {
		final ObjectMapper mapper = new ObjectMapper();
		final String id = UUID.randomUUID().toString();
		final String teamName = UUID.randomUUID().toString();
		final UnRegisteredTeamInfo knownTeam = UnRegisteredTeamInfo.builder().id(id).name(teamName).build();
		final JSONObject json = new JSONObject(mapper.writeValueAsString(knownTeam));
		assertTrue(json.has("name"));
		assertEquals(teamName, json.get("name"));
	}
	
	@Test
	public void testJsonToObject() throws IOException {
		final String id = UUID.randomUUID().toString();
		final String teamName = UUID.randomUUID().toString();
		final ObjectMapper mapper = new ObjectMapper();
		final Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("name", teamName);
		final JSONObject json = new JSONObject(map);
		
		final UnRegisteredTeamInfo knownTeam = mapper.readValue(json.toString(), UnRegisteredTeamInfo.class);
		assertEquals(teamName, knownTeam.getName());
		assertEquals(id, knownTeam.getId());
	}
	
}
