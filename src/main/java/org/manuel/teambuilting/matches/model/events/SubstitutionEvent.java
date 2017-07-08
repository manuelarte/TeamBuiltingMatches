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

import javax.validation.constraints.AssertTrue;
import java.util.Date;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = SubstitutionEvent.SubstitutionEventBuilder.class)
@Immutable
@Data
@lombok.Builder
@AllArgsConstructor
/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
public class SubstitutionEvent implements MatchEvent {

    @JsonProperty(required = true)
    private final Date when;

    /**
     * Id of the playerInfo who comes in
     */
    @JsonPropertyDescription("Player who comes in")
    private final String in;

    /**
     * Id of the PlayerInfo who comes out
     */
    @JsonPropertyDescription("Player who goes out")
    private final String out;

    @JsonPropertyDescription("More information to add")
    private final String description;

    @AssertTrue
    public boolean inOrOutDeclared() {
        return in != null || out != null;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class SubstitutionEventBuilder {

    }

}
