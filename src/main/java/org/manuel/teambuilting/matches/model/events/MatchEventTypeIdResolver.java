package org.manuel.teambuilting.matches.model.events;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author Manuel Doncel Martos
 * @since 24/06/2017.
 *
 * Custom id property for the polymorphic de/serialization of the MatchEvent interface
 */
public class MatchEventTypeIdResolver implements TypeIdResolver {

    private JavaType mBaseType;

    @Override
    public void init(JavaType baseType) {
        this.mBaseType = baseType;
    }

    @Override
    public String idFromValue(Object value) {
        return idFromValueAndType(value, value.getClass());
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        Optional<MatchEventType> result =
                Arrays.stream(MatchEventType.values()).filter(
                        matchEventType -> matchEventType.getEventClass().equals(suggestedType))
                        .findFirst();
        return result.isPresent() ? result.get().value() : "custom";
    }

    @Override
    public String idFromBaseType() {
        return idFromValueAndType(null, mBaseType.getRawClass());
    }

    @Override
    public JavaType typeFromId(DatabindContext context, String id) throws IOException {
        final MatchEventType matchEventType = MatchEventType.fromValue(id);
        return TypeFactory.defaultInstance().constructSpecializedType(mBaseType, matchEventType.getEventClass());
    }

    @Override
    public String getDescForKnownTypeIds() {
        return null;
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return null;
    }
}
