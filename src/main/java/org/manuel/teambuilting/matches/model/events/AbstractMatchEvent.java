package org.manuel.teambuilting.matches.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.manuel.teambuilting.matches.config.UiProperty;
import org.manuel.teambuilting.matches.config.Widget;

import java.util.Date;

/**
 * @author Manuel Doncel Martos
 * @since 16/07/2017.
 */
@AllArgsConstructor
@Data
public abstract class AbstractMatchEvent implements MatchEvent {

    protected final String id;

    @JsonProperty(required = true)
    @JsonPropertyDescription("When did it happen?")
    @UiProperty(widget = @Widget(id = "time"))
    protected final Date when;

    @JsonPropertyDescription("Description")
    protected final String description;

}
