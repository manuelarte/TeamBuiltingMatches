package org.manuel.teambuilting.matches.model.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import lombok.Getter;
import org.manuel.teambuilting.matches.model.events.schemas.SchemasCreator;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
@Getter
public enum MatchEventType {

    GOAL(GoalEvent.class, SchemasCreator.createSchemaFor(GoalEvent.class)),
    SUBSTITUTION(SubstitutionEvent.class, SchemasCreator.createSchemaFor(SubstitutionEvent.class)),
    INJURY(InjuryEvent.class, SchemasCreator.createSchemaFor(InjuryEvent.class)),
    CARD(CardEvent.class, SchemasCreator.createSchemaFor(CardEvent.class)),
    CUSTOM(CustomEvent.class, SchemasCreator.createSchemaFor(CustomEvent.class));

    private final Class<? extends MatchEvent> eventClass;
    private final JsonSchema schema;

    MatchEventType(final Class<? extends MatchEvent> clazz, final JsonSchema schema) {
        this.eventClass = clazz;
        this.schema = schema;
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
