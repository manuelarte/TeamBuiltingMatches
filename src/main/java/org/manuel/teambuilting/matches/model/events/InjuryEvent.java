package org.manuel.teambuilting.matches.model.events;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Data;
import org.manuel.teambuilting.matches.config.Ui;
import org.manuel.teambuilting.matches.config.UiProperty;
import org.manuel.teambuilting.matches.config.Widget;
import org.manuel.teambuilting.matches.model.Match;

import java.util.Date;

@Ui(iconName = "injury", playerProperty = "who")
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = InjuryEvent.InjuryEventBuilder.class)
@Data
/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
public class InjuryEvent extends AbstractMatchEvent {

    /**
     * Id of the PlayerInfo who scored the goal
     */
    @JsonProperty(required = true)
    @JsonPropertyDescription("Id of the PlayerInfo that got injured")
    @UiProperty(widget = @Widget(id = "player"), tableProperty = true)
    private final String who;

    @lombok.Builder
    public InjuryEvent(final String id, final Date when, final String description, final String who) {
        super(id, when, description);
        this.who = who;
    }

    @JsonIgnore
    @Override
    public boolean validInContext(final Match match) {
        return true;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class InjuryEventBuilder {

    }

}
