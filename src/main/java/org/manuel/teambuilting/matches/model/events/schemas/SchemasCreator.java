package org.manuel.teambuilting.matches.model.events.schemas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import lombok.SneakyThrows;
import org.manuel.teambuilting.matches.config.Ui;
import org.manuel.teambuilting.matches.model.dto.UiDto;
import org.manuel.teambuilting.matches.model.dto.ui.PropertyUiDto;
import org.manuel.teambuilting.matches.model.dto.ui.PropertyWidgetDto;
import org.manuel.teambuilting.matches.model.events.MatchEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static UiDto createUiFor(final Class<? extends MatchEvent> clazz) {
        final Field[] parentFields = !clazz.getSuperclass().equals(Object.class)?
                clazz.getSuperclass().getDeclaredFields() : new Field[]{};
        final Field[] fields = Stream.concat(Arrays.stream(clazz.getDeclaredFields()),
                Arrays.stream(parentFields))
                .toArray(Field[]::new);

        final Set<String> tableProperties = Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(Ui.class)
                        && field.getAnnotationsByType(Ui.class)[0].tableProperty()).map(Field::getName).collect(Collectors.toSet());

        final Map<String, PropertyUiDto> propertyUiDtoMap = Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(Ui.class))
                .collect(Collectors.toMap(Field::getName, SchemasCreator::createUiDto));

        return UiDto.builder().tableProperties(tableProperties).properties(propertyUiDtoMap).build();
    }

    private static PropertyUiDto createUiDto(final Field field) {
        final Ui ui = field.getAnnotationsByType(Ui.class)[0];
        return PropertyUiDto.builder()
                .widget(PropertyWidgetDto.builder().id(ui.widget().id()).build())
                .build();
    }

}
