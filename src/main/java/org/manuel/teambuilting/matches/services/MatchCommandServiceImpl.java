package org.manuel.teambuilting.matches.services;

import org.manuel.teambuilting.core.services.command.AbstractCommandService;
import org.manuel.teambuilting.matches.model.Match;
import org.manuel.teambuilting.matches.repositories.MatchRepository;
import org.springframework.stereotype.Component;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
@Component
class MatchCommandServiceImpl extends AbstractCommandService<Match, String, MatchRepository> implements MatchCommandService {

    public MatchCommandServiceImpl(final MatchRepository repository) {
        super(repository);
    }

}
