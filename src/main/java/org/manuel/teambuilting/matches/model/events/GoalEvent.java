package org.manuel.teambuilting.matches.model.events;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mongodb.annotations.Immutable;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.manuel.teambuilting.matches.config.Ui;
import org.manuel.teambuilting.matches.config.Widget;
import org.manuel.teambuilting.matches.model.Match;
import org.manuel.teambuilting.matches.model.team.TeamInfo;
import org.manuel.teambuilting.matches.util.Util;
import org.springframework.data.util.Pair;

import java.util.Date;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = GoalEvent.GoalEventBuilder.class)
@Immutable
@Data
/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
public class GoalEvent extends AbstractMatchEvent {

    /**
     * Id of the PlayerInfo who scored the goal
     */
    @JsonPropertyDescription("Player that scored the goal")
    @Ui(widget = @Widget(id = "player"), tableProperty = true)
    private final String who;

    /**
     * The team that scored the goal
     */
    @JsonProperty(required = true)
    @JsonPropertyDescription("Team that scored the goal")
    @Ui(widget = @Widget(id = "team"), tableProperty = true)
    @NotEmpty
    private final String teamThatScored;

    @lombok.Builder
    public GoalEvent(final String id, final Date when, final String description, final String who, final String teamThatScored) {
      super(id, when, description);
      this.who = who;
      this.teamThatScored = teamThatScored;
    }

    @JsonIgnore
    @Override
    public boolean validInContext(final Match match) {
        // Check that teamThatScored exists
        if (Util.homeTeamIsPresent(match) || Util.awayTeamIsPresent(match)) {
            final Pair<TeamInfo, TeamInfo> teams = Util.getTeams(match);
            return teams.getFirst().getId().equals(teamThatScored) || teams.getSecond().getId().equals(teamThatScored);
        }

        return false;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class GoalEventBuilder {

    }

}
