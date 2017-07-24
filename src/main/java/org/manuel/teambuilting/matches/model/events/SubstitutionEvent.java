package org.manuel.teambuilting.matches.model.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mongodb.annotations.Immutable;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.manuel.teambuilting.matches.config.Ui;
import org.manuel.teambuilting.matches.config.UiProperty;
import org.manuel.teambuilting.matches.config.Widget;
import org.manuel.teambuilting.matches.model.Match;

import javax.validation.constraints.AssertTrue;
import java.util.Date;

@Ui(iconName = "substitution", teamProperty = "team", playerProperty = "in")
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = SubstitutionEvent.SubstitutionEventBuilder.class)
@Immutable
@Data
/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
public class SubstitutionEvent extends AbstractMatchEvent {

    /**
     * Id of the playerInfo who comes in
     */
    @JsonPropertyDescription("Team that makes the substitution")
    @UiProperty(widget = @Widget(id = "team"), tableProperty = true)
    @NotEmpty
    private final String team;

    /**
     * Id of the playerInfo who comes in
     */
    @JsonPropertyDescription("Player who comes in")
    @UiProperty(widget = @Widget(id = "player"), tableProperty = true)
    private final String in;

    /**
     * Id of the PlayerInfo who comes out
     */
    @JsonPropertyDescription("Player who goes out")
    @UiProperty(widget = @Widget(id = "player"), tableProperty = true)
    private final String out;

    @lombok.Builder
    public SubstitutionEvent(final String id, final Date when, final String description, final String team, final String in, final String out) {
        super(id, when, description);
        this.team = team;
        this.in = in;
        this.out = out;
    }

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
