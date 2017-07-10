package org.manuel.teambuilting.matches.model.dto;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * @author Manuel Doncel Martos
 * @since 10/07/2017.
 */
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = WidgetDto.WidgetDtoBuilder.class)
@Immutable
@Data
@lombok.Builder
@AllArgsConstructor
public class WidgetDto {

    @JsonIgnore
    private final Map<String, PropertyWidgetDto> schemaProperties;

    @JsonAnyGetter
    public Map<String, PropertyWidgetDto> getSchemaProperties() {
        return this.schemaProperties;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class WidgetDtoBuilder {

    }

}
