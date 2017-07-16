package org.manuel.teambuilting.matches.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Manuel Doncel Martos
 * @since 10/07/2017.
 */
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = SchemaAndUiDto.SchemaAndUiDtoBuilder.class)
@Immutable
@Data
@lombok.Builder
@AllArgsConstructor
public class SchemaAndUiDto {

    private final JsonSchema schema;
    private final UiDto ui;

    @JsonPOJOBuilder(withPrefix = "")
    public static class SchemaAndWidgetDtoBuilder {

    }

}
