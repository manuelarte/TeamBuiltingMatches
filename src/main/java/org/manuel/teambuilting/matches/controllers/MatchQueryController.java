package org.manuel.teambuilting.matches.controllers;

import org.manuel.teambuilting.core.controllers.query.AbstractQueryController;
import org.manuel.teambuilting.matches.model.Match;
import org.manuel.teambuilting.matches.services.query.MatchQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;

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

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Match>> findMatchesBetweenDates(@PageableDefault(size = 20) final Pageable pageable,
            @RequestParam(value = "from", defaultValue = "1900-01-01", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final Date from,
            @RequestParam(value = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final Date to,
            @RequestParam(value = "playerId", required = false) final Optional<BigInteger> playerId) {
        final Date endDate = to != null ? to : new Date();
        Page<Match> matches;
        if (playerId.isPresent()) {
            matches = queryService.findMatchesBetweenDatesForPlayer(pageable, from, endDate, playerId.get());
        } else {
            matches = queryService.findMatchesBetweenDates(pageable, from, endDate);
        }
        return ResponseEntity.ok(matches);
    }

}
