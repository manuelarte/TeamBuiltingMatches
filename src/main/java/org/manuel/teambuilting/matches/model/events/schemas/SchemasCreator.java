package org.manuel.teambuilting.matches.model.events.schemas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import lombok.SneakyThrows;
import org.manuel.teambuilting.matches.model.events.GoalEvent;
import org.springframework.stereotype.Component;

/**
 * @author Manuel Doncel Martos
 * @since 01/07/2017.
 */
@Component
public class SchemasCreator {

    private static final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
    private static final JsonSchemaGenerator generator = new JsonSchemaGenerator(objectMapper);

    @SneakyThrows
    public static JsonSchema createSchemaFor(final Class<?> clazz) {
        return generator.generateSchema(clazz);
    }

    @SneakyThrows
    public static JsonSchema createGoalEventSchema() {
        final JsonSchema goalEventSchema = generator.generateSchema(GoalEvent.class);

        /*
        final ObjectSchema whoSchema = goalEventSchema.asObjectSchema().getProperties().get("who").asObjectSchema();
        whoSchema.asObjectSchema().setEnums(players.stream().map(PlayerInfo::getId).collect(Collectors.toSet()))
        */
        /*
        final ObjectSchema teamThatScoredPropertySchema = goalEventSchema.asObjectSchema().getProperties().get("teamThatScored").asObjectSchema();
        teamThatScoredPropertySchema.asObjectSchema().setEnums(Sets.newHashSet(match.getHomeTeam().getTeamInfo().getId(), match.getAwayTeam().getTeamInfo().getId()))
        */

        return goalEventSchema;

    }

}
