package org.manuel.teambuilting.matches.services.query;

import org.manuel.teambuilting.core.services.query.BaseQueryService;
import org.manuel.teambuilting.matches.model.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author Manuel Doncel Martos
 * @since 15/07/2017.
 */
public interface MatchQueryService extends BaseQueryService<Match, String> {

    /**
     *
     * @param pageable
     * @param from
     * @param to
     * @return
     */
    Page<Match> findMatchesBetweenDatesForPlayer(Pageable pageable, Date from, Date to, BigInteger playerId);

    Page<Match> findMatchesBetweenDates(Pageable pageable, Date from, Date to);
}
