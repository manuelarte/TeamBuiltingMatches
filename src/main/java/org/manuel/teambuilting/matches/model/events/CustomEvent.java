package org.manuel.teambuilting.matches.model.events;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mongodb.annotations.Immutable;
import lombok.Data;
import org.manuel.teambuilting.matches.model.Match;

import java.util.Date;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = CustomEvent.CustomEventBuilder.class)
@Immutable
@Data
/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
public class CustomEvent extends AbstractMatchEvent {

    /**
     * Title of the event
     */
    @JsonProperty(required = true)
    @JsonPropertyDescription("Title of the event")
    private final String title;

    @lombok.Builder
    public CustomEvent(final String id, final Date when, final String description, final String title) {
        super(id, when, description);
        this.title = title;
    }

    @JsonIgnore
    @Override
    public boolean validInContext(final Match match) {
        return true;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class CustomEventBuilder {

    }

}
