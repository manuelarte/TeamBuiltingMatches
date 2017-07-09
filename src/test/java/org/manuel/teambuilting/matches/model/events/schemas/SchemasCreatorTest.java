package org.manuel.teambuilting.matches.model.events.schemas;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.manuel.teambuilting.matches.model.events.GoalEvent;

/**
 * @author Manuel Doncel Martos
 * @since 01/07/2017.
 */
public class SchemasCreatorTest {

    @Test
    public void testSerializeGoalEvent() throws JsonProcessingException {
        SchemasCreator.createSchemaFor(GoalEvent.class);
    }

}
