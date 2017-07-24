package org.manuel.teambuilting.matches.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.manuel.teambuilting.matches.model.dto.ui.PropertyUiDto;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;

/**
 * @author Manuel Doncel Martos
 * @since 16/07/2017.
 */
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = UiDto.UiDtoBuilder.class)
@Immutable
@Data
@lombok.Builder
@AllArgsConstructor
public class UiDto {

    private final String iconName;

    private final String teamProperty;

    private final String playerProperty;

    @NotNull
    private final Set<String> tableProperties;

    private final Map<String, PropertyUiDto> properties;

}
