package org.manuel.teambuilting.matches.model.events;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.manuel.teambuilting.matches.config.Ui;
import org.manuel.teambuilting.matches.config.UiProperty;
import org.manuel.teambuilting.matches.config.Widget;
import org.manuel.teambuilting.matches.model.Match;

import java.util.Date;

@Ui(iconName = "card", teamProperty = "team", playerProperty = "who")
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = CardEvent.CardEventBuilder.class)
@Data
/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
public class CardEvent extends AbstractMatchEvent {

    /**
     * Id of the PlayerInfo who scored the goal
     */
    @JsonProperty(required = true)
    @JsonPropertyDescription("Player who got the card")
    @UiProperty(widget = @Widget(id = "player"), tableProperty = true)
    private final String who;

    @JsonProperty(required = true)
    @JsonPropertyDescription("Team that received the card")
    @UiProperty(widget = @Widget(id = "team"), tableProperty = true)
    @NotEmpty
    private final String team;

    @JsonProperty(required = true)
    @JsonPropertyDescription("Card color")
    @UiProperty(widget = @Widget(id = "string"), tableProperty = true)
    private final String card;

    @lombok.Builder
    public CardEvent(final String id, final Date when, final String description, final String who,
                     final String team, final String card) {
        super(id, when, description);
        this.who = who;
        this.team = team;
        this.card = card;
    }

    @JsonIgnore
    @Override
    public boolean validInContext(final Match match) {
        return true;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class CardEventBuilder {

    }

}
