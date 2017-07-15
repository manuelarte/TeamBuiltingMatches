package org.manuel.teambuilting.matches.services.query;

import org.manuel.teambuilting.core.services.query.AbstractQueryService;
import org.manuel.teambuilting.matches.model.Match;
import org.manuel.teambuilting.matches.repositories.MatchRepository;
import org.springframework.stereotype.Service;

/**
 * @author Manuel Doncel Martos
 * @since 15/07/2017.
 */
@Service
class MatchQueryServiceImpl extends AbstractQueryService<Match, String, MatchRepository> implements MatchQueryService {

    public MatchQueryServiceImpl(final MatchRepository matchRepository) {
        super(matchRepository);
    }

}
