package org.manuel.teambuilting.matches.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = CustomEvent.CustomEventBuilder.class)
@Immutable
@Data
@lombok.Builder
@AllArgsConstructor
/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
public class CustomEvent implements MatchEvent{

    private final Date when;

    /**
     * Id of the PlayerInfo who scored the goal
     */
    @JsonPropertyDescription("Title of the event")
    private final String title;

    @JsonPropertyDescription("Description of the goal")
    private final String description;

    @JsonPOJOBuilder(withPrefix = "")
    public static class CustomEventBuilder {

    }

}