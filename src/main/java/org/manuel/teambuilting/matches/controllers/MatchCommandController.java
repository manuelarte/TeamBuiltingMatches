package org.manuel.teambuilting.matches.controllers;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.matches.model.IncomingMatch;
import org.manuel.teambuilting.matches.model.Match;
import org.manuel.teambuilting.matches.services.command.MatchCommandService;
import org.manuel.teambuilting.matches.transformers.IncomingMatchToMatchTransformer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private final IncomingMatchToMatchTransformer transformer;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Match saveMatch(@Valid @RequestBody final IncomingMatch match) {
        final Match saved = matchCommandService.save(transformer.apply(match));
        return saved;
    }

}
