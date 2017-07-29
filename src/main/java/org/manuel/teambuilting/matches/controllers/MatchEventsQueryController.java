package org.manuel.teambuilting.matches.controllers;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.matches.model.dto.SchemaAndUiDto;
import org.manuel.teambuilting.matches.model.events.MatchEventType;
import org.manuel.teambuilting.matches.model.events.schemas.SchemasCreator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping(produces = "application/json")
    public Map<String, SchemaAndUiDto> getMatchEvents() {
        final Map<String, SchemaAndUiDto> matchEventsType = Stream.of(MatchEventType.values())
                .collect(Collectors.toMap(MatchEventType::value,
                matchEventType -> SchemaAndUiDto.builder().schema(matchEventType.getSchema())
                        .ui(SchemasCreator.createUiFor(matchEventType.getEventClass()))
                        .build()));
        return matchEventsType;
    }

}
