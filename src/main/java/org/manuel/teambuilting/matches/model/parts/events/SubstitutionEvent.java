package org.manuel.teambuilting.matches.model.parts.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.manuel.teambuilting.matches.model.player.PlayerInfo;

import javax.validation.constraints.AssertTrue;
import java.time.Instant;

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

    private final Instant when;

    private final PlayerInfo in;

    private final PlayerInfo out;

    private final String description;

    @AssertTrue
    public boolean inOrOutDeclared() {
        return in != null || out != null;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class SubstitutionEventBuilder {

    }

}
