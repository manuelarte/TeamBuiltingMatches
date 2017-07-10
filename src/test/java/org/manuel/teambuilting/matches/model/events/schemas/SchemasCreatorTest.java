package org.manuel.teambuilting.matches.model.events.schemas;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.manuel.teambuilting.matches.model.dto.WidgetDto;
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
        final WidgetDto widgetForGoalEvent = SchemasCreator.createWidgetFor(GoalEvent.class);
        assertTrue(widgetForGoalEvent.getSchemaProperties().containsKey("who"));
        assertTrue("player".equals(widgetForGoalEvent.getSchemaProperties().get("who").getId()));
    }

}
