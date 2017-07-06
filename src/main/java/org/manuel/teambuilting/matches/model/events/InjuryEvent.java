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
@JsonDeserialize(builder = InjuryEvent.InjuryEventBuilder.class)
@Immutable
@Data
@lombok.Builder
@AllArgsConstructor
/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
public class InjuryEvent implements MatchEvent{

    private final Date when;

    /**
     * Id of the PlayerInfo who scored the goal
     */
    @JsonPropertyDescription("Id of the PlayerInfo that got injured")
    private final String who;

    @JsonPropertyDescription("Description of the injury")
    private final String description;

    @JsonPOJOBuilder(withPrefix = "")
    public static class InjuryEventBuilder {

    }

}
