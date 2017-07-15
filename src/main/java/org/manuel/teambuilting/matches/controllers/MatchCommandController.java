package org.manuel.teambuilting.matches.controllers;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.matches.model.Match;
import org.manuel.teambuilting.matches.services.command.MatchCommandService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
@RestController
@RequestMapping("/matches")
@AllArgsConstructor
public class MatchCommandController {

    private final MatchCommandService matchCommandService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Match saveMatch(@Valid @RequestBody final Match match) {
        final Match saved = matchCommandService.save(match);
        return saved;
    }

}
