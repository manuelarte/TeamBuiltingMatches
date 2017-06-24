package org.manuel.teambuilting.matches.services;

import org.manuel.teambuilting.core.services.command.AbstractCommandService;
import org.manuel.teambuilting.exceptions.ValidationRuntimeException;
import org.manuel.teambuilting.matches.model.Match;
import org.manuel.teambuilting.matches.repositories.MatchRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

import static org.manuel.teambuilting.exceptions.ErrorCode.ENTRY_OVERLAPS;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
@Component
class MatchCommandServiceImpl extends AbstractCommandService<Match, String, MatchRepository> implements MatchCommandService {

    public MatchCommandServiceImpl(final MatchRepository repository) {
        super(repository);
    }

    @Override
    protected void beforeSave(final Match matchToStore) {
        // check that there is no overlap with a previously stored game
        final Set<Match> overlappedMatches = repository.findByEndingTimeIsBetween(Date.from(matchToStore.getStartingTime()), Date.from(matchToStore.getEndingTime()));
        if (!overlappedMatches.isEmpty()) {
            throw new ValidationRuntimeException(ENTRY_OVERLAPS, matchToStore);
        }
    }

}
