package org.manuel.teambuilting.matches.model.events.schemas;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.manuel.teambuilting.matches.model.dto.UiDto;
import org.manuel.teambuilting.matches.model.events.GoalEvent;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Manuel Doncel Martos
 * @since 01/07/2017.
 */
public class SchemasCreatorTest {

    @Test
    public void testSerializeGoalEvent() throws JsonProcessingException {
        SchemasCreator.createSchemaFor(GoalEvent.class);
    }

    @Test
    public void testCreateWidgetForGoalEvent() {
        final UiDto uiForGoalEvent = SchemasCreator.createUiFor(GoalEvent.class);
        assertTrue(uiForGoalEvent.getProperties().containsKey("who"));
        assertTrue("player".equals(uiForGoalEvent.getProperties().get("who").getWidget().getId()));
    }

}
