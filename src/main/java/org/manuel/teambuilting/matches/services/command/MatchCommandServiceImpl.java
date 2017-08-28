package org.manuel.teambuilting.matches.services.command;

import org.manuel.teambuilting.core.exceptions.ValidationRuntimeException;
import org.manuel.teambuilting.core.services.command.AbstractCommandService;
import org.manuel.teambuilting.core.utils.Util;
import org.manuel.teambuilting.matches.model.Match;
import org.manuel.teambuilting.matches.repositories.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

import static org.manuel.teambuilting.core.exceptions.ErrorCode.ENTRY_OVERLAPS;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
@Service
class MatchCommandServiceImpl extends AbstractCommandService<Match, String, MatchRepository> implements MatchCommandService {

    private final Util util;

    public MatchCommandServiceImpl(final MatchRepository repository, final Util util) {
        super(repository);
        this.util = util;
    }

    @Override
    protected void beforeSave(final Match matchToStore) {
        // check that there is no overlap with a previously stored game
        final Set<Match> overlappedMatches = repository.findByMatchPartsStartingTimeIsBetween(matchToStore.getStartingTime(),
                matchToStore.getEndingTime());
        if (!overlappedMatches.isEmpty()) {
            throw new ValidationRuntimeException(ENTRY_OVERLAPS, matchToStore);
        }
    }

}
