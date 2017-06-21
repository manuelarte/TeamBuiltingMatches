package org.manuel.teambuilting.matches.model.parts.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mongodb.annotations.Immutable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.manuel.teambuilting.matches.model.parts.MatchEvent;
import org.manuel.teambuilting.matches.model.player.PlayerInfo;
import org.manuel.teambuilting.matches.model.team.TeamInfo;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = GoalEvent.GoalEventBuilder.class)
@Immutable
@Data
@lombok.Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
public class GoalEvent implements MatchEvent{

    private final Instant when;

    private final PlayerInfo who;

    @NotNull
    private final TeamInfo teamWhoScored;

    private final String description;

    @JsonPOJOBuilder(withPrefix = "")
    public static class GoalEventBuilder {

    }

}
