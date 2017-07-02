package org.manuel.teambuilting.matches.model.parts.events.schemas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import com.fasterxml.jackson.module.jsonSchema.types.ObjectSchema;
import com.fasterxml.jackson.module.jsonSchema.types.ValueTypeSchema;
import com.google.common.collect.Sets;
import lombok.SneakyThrows;
import org.manuel.teambuilting.matches.model.Match;
import org.manuel.teambuilting.matches.model.parts.events.GoalEvent;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author Manuel Doncel Martos
 * @since 01/07/2017.
 */
@Component
public class SchemasCreator {

    private static final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
    private static final JsonSchemaGenerator generator = new JsonSchemaGenerator(objectMapper);

    @SneakyThrows
    public String createGoalEventSchema(final Match match) {
        final JsonSchema goalEventSchema = generator.generateSchema(GoalEvent.class);

        final JsonSchema whoPropertySchema = new ValueTypeSchema() {
            @Override
            public JsonFormatTypes getType() {
                return JsonFormatTypes.STRING;
            }

            public Set<String> getEnums() {
                return Sets.newHashSet("playerInfoId1", "playerInfoId2", "playerInfoId3");
            }

        };

        final JsonSchema teamThatScoredPropertySchema = new ValueTypeSchema() {
            @Override
            public JsonFormatTypes getType() {
                return JsonFormatTypes.STRING;
            }

            public Set<String> getEnums() {
                return Sets.newHashSet(match.getHomeTeam().getTeamInfo().getId(), match.getAwayTeam().getTeamInfo().getId());
            }

        };

        ((ObjectSchema)goalEventSchema).getProperties().put("who", whoPropertySchema);
        ((ObjectSchema)goalEventSchema).getProperties().put("teamThatScored", teamThatScoredPropertySchema);
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(goalEventSchema);

    }

}
