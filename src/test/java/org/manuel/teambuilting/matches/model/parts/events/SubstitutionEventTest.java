package org.manuel.teambuilting.matches.model.parts.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Manuel Doncel Martos
 * @since 23/06/2017.
 */
public class SubstitutionEventTest {

    private static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    @Test
    public void testDeserializeSubstitutionEvent() throws IOException {
        final Date when = new Date();

        final String in = UUID.randomUUID().toString();
        final String out = UUID.randomUUID().toString();
        final SubstitutionEvent substitutionEvent = SubstitutionEvent.builder().when(when).in(in).out(out).build();
        final String json = mapper.writeValueAsString(substitutionEvent);
        final MatchEvent actual = mapper.readValue(json, SubstitutionEvent.class);
        assertEquals(substitutionEvent, actual);

    }

}
