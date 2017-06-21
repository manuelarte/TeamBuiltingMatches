package org.manuel.teambuilting.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Instant;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Manuel Doncel Martos
 * @since 07/12/2016.
 */
@AllArgsConstructor
@Data
public class TeamVisitedEvent {

    public static final String ROUTING_KEY = "team.behaviour.visited";

    @NotNull
    private final String teamId;

    private final String userId;

    @NotNull
    private final Instant date;

    @JsonIgnore
    public String getRoutingKey() {
        return ROUTING_KEY;
    }
}
