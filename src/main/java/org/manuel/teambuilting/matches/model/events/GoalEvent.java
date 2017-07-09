package org.manuel.teambuilting.matches.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = GoalEvent.GoalEventBuilder.class)
@Immutable
@Data
@lombok.Builder
@AllArgsConstructor
/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
public class GoalEvent implements MatchEvent{

    @JsonProperty(required = true)
    @JsonPropertyDescription("When did the Goal happen?")
    private final Date when;

    /**
     * Id of the PlayerInfo who scored the goal
     */
    @JsonPropertyDescription("Player that scored the goal")
    private final String who;

    /**
     * The team that scored the goal
     */
    @JsonProperty(required = true)
    @JsonPropertyDescription("Team that scored the goal")
    @NotEmpty
    private final String teamThatScored;

    @JsonPropertyDescription("Description of the goal")
    private final String description;

    @JsonPOJOBuilder(withPrefix = "")
    public static class GoalEventBuilder {

    }

}
