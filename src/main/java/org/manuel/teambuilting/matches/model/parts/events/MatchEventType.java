package org.manuel.teambuilting.matches.model.parts.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
@Getter
public enum MatchEventType {

    GOAL(GoalEvent.class), SUBSTITUTION(SubstitutionEvent.class);

    private final Class<? extends MatchEvent> eventClass;

    MatchEventType(final Class<? extends MatchEvent> clazz) {
        this.eventClass = clazz;
    }

    @JsonValue
    public final String value() {
        return this.toString().toLowerCase();
    }

    @JsonCreator
    @SuppressWarnings("unused")
    public static MatchEventType fromValue(String v) {
        for (MatchEventType myEnum : values()) {
            if (myEnum.toString().equalsIgnoreCase(v)) {
                return myEnum;
            }
        }
        throw new IllegalArgumentException("invalid string value passed: " + v);
    }

}
