package org.manuel.teambuilting.matches.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.manuel.teambuilting.matches.model.player.PlayerInfo;
import org.manuel.teambuilting.matches.model.player.RegisteredPlayerInfo;
import org.manuel.teambuilting.matches.model.player.UnRegisteredPlayerInfo;

import java.io.IOException;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
public class PlayerInfoDeserializer extends JsonDeserializer<PlayerInfo> {

    private static final String PLAYER_ID = "playerId";

    @Override
    public PlayerInfo deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        final ObjectMapper mapper = (ObjectMapper) p.getCodec();
        final ObjectNode root = mapper.readTree(p);
        PlayerInfo playerInfo = null;
        if (root.has(PLAYER_ID)) {
            playerInfo = mapper.readValue(root.toString(), RegisteredPlayerInfo.class);
        } else {
            playerInfo = mapper.readValue(root.toString(), UnRegisteredPlayerInfo.class);
        }
        return playerInfo;
    }
}
