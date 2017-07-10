package org.manuel.teambuilting.matches.model.events.schemas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import lombok.SneakyThrows;
import org.manuel.teambuilting.matches.config.Widget;
import org.manuel.teambuilting.matches.model.dto.PropertyWidgetDto;
import org.manuel.teambuilting.matches.model.dto.WidgetDto;
import org.manuel.teambuilting.matches.model.events.GoalEvent;
import org.manuel.teambuilting.matches.model.events.MatchEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

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

    public static WidgetDto createWidgetFor(final Class<? extends MatchEvent> clazz) {
        final Field[] fields = clazz.getDeclaredFields();

        final Map<String, PropertyWidgetDto> propertyWidgetDtoMap = Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(Widget.class))
                .collect(Collectors.toMap(Field::getName, SchemasCreator::createPropertyWidgetDto));

        return WidgetDto.builder().schemaProperties(propertyWidgetDtoMap).build();
    }

    private static PropertyWidgetDto createPropertyWidgetDto(final Field field) {
        return PropertyWidgetDto.builder().id(field.getAnnotationsByType(Widget.class)[0].id()).build();
    }

}
