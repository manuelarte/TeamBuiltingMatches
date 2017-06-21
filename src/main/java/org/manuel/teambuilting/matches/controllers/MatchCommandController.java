package org.manuel.teambuilting.matches.controllers;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.matches.model.Match;
import org.manuel.teambuilting.matches.services.MatchCommandService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Match saveMatch(@Valid @RequestBody final Match match) {
        final Match saved = matchCommandService.save(match);
        return saved;
    }

}
