package org.manuel.teambuilting.matches.model.events;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.manuel.teambuilting.matches.config.Ui;
import org.manuel.teambuilting.matches.config.Widget;
import org.manuel.teambuilting.matches.model.Match;

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

    private final String id;

    @JsonProperty(required = true)
    @JsonPropertyDescription("When did the Substitution happen?")
    @Ui(widget = @Widget(id = "time"))
    private final Date when;

    /**
     * Id of the playerInfo who comes in
     */
    @JsonPropertyDescription("Player who comes in")
    @Ui(widget = @Widget(id = "player"), tableProperty = true)
    private final String in;

    /**
     * Id of the PlayerInfo who comes out
     */
    @JsonPropertyDescription("Player who goes out")
    @Ui(widget = @Widget(id = "player"), tableProperty = true)
    private final String out;

    @JsonPropertyDescription("More information to add")
    private final String description;

    @JsonIgnore
    @Override
    public boolean validInContext(final Match match) {
        return true;
    }

    @JsonIgnore
    @AssertTrue
    public boolean inOrOutDeclared() {
        return in != null || out != null;
    }

    @JsonIgnore
    @AssertTrue
    public boolean isNotTheSamePlayer() {
        return in != null? !in.equals(out) : false;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class SubstitutionEventBuilder {

    }

}
