package org.manuel.teambuilting.matches.controllers;

import org.manuel.teambuilting.core.controllers.query.AbstractQueryController;
import org.manuel.teambuilting.matches.model.Match;
import org.manuel.teambuilting.matches.services.query.MatchQueryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
@RestController
@RequestMapping("/matches")
public class MatchQueryController extends AbstractQueryController<Match, String, MatchQueryService> {

    public MatchQueryController(final MatchQueryService matchQueryService) {
        super(matchQueryService);
    }

}
