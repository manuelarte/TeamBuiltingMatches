package org.manuel.teambuilting.matches.model.dto.ui;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Manuel Doncel Martos
 * @since 10/07/2017.
 */
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = PropertyUiDto.PropertyUiDtoBuilder.class)
@Immutable
@Data
@lombok.Builder
@AllArgsConstructor
public class PropertyUiDto {

    private final PropertyWidgetDto widget;

    @JsonPOJOBuilder(withPrefix = "")
    public static class PropertyUiDtoBuilder {

    }

}
