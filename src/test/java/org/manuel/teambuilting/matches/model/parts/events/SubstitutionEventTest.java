package org.manuel.teambuilting.matches.model.parts.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.manuel.teambuilting.matches.model.player.PlayerInfo;
import org.manuel.teambuilting.matches.model.player.RegisteredPlayerInfo;

import java.io.IOException;
import java.math.BigInteger;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Manuel Doncel Martos
 * @since 23/06/2017.
 */
public class SubstitutionEventTest {

    private static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    @Test
    public void testDeserializeSubstitutionEvent() throws IOException {
        final Instant when = Instant.now();
        final PlayerInfo in = RegisteredPlayerInfo.builder().playerId(BigInteger.ONE).build();
        final PlayerInfo out = RegisteredPlayerInfo.builder().playerId(BigInteger.TEN).build();
        final SubstitutionEvent substitutionEvent = SubstitutionEvent.builder().when(when).in(in).out(out).build();
        final JSONObject json = createJsonObjectFrom(substitutionEvent);
        final MatchEvent actual = mapper.readValue(json.toString(), SubstitutionEvent.class);
        assertEquals(substitutionEvent, actual);

    }

    private JSONObject createJsonObjectFrom(final SubstitutionEvent substitutionEvent) {
        final Map<String, Object> map = new HashMap<>();
        map.put(MatchEventType.SUBSTITUTION.value(), substitutionEvent);
        return new JSONObject(map);
    }

}
