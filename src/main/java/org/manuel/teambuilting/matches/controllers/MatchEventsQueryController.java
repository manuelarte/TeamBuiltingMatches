package org.manuel.teambuilting.matches.controllers;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.matches.model.dto.SchemaAndWidgetDto;
import org.manuel.teambuilting.matches.model.events.MatchEventType;
import org.manuel.teambuilting.matches.model.events.schemas.SchemasCreator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
@RestController
@RequestMapping("/matches/events")
@AllArgsConstructor
public class MatchEventsQueryController {

    private final SchemasCreator schemasCreator;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Map<String, SchemaAndWidgetDto> getMatchEvents() {
        final Map<String, SchemaAndWidgetDto> matchEventsType = Stream.of(MatchEventType.values())
                .collect(Collectors.toMap(MatchEventType::value,
                matchEventType -> SchemaAndWidgetDto.builder().schema(matchEventType.getSchema())
                        .widget(SchemasCreator.createWidgetFor(matchEventType.getEventClass()))
                        .build()));
        return matchEventsType;
    }

}
