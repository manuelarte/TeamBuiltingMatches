package org.manuel.teambuilting.matches.model.dto.ui;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonDeserialize(builder = PropertyWidgetDto.WidgetDtoBuilder.class)
@Immutable
@Data
@lombok.Builder
@AllArgsConstructor
public class PropertyWidgetDto {

    /**
     * Id of the Widget that should be used in the Front End
     */
    private final String id;

    @JsonIgnore
    private final Map<String, String> widgetProperties;

    @JsonPOJOBuilder(withPrefix = "")
    public static class WidgetDtoBuilder {

    }

}
