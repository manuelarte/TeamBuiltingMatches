package org.manuel.teambuilting.matches.services.query;

import org.manuel.teambuilting.core.services.query.AbstractQueryService;
import org.manuel.teambuilting.matches.model.Match;
import org.manuel.teambuilting.matches.model.player.PlayerInfo;
import org.manuel.teambuilting.matches.model.player.RegisteredPlayerInfo;
import org.manuel.teambuilting.matches.repositories.MatchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Manuel Doncel Martos
 * @since 15/07/2017.
 */
@Service
class MatchQueryServiceImpl extends AbstractQueryService<Match, String, MatchRepository> implements MatchQueryService {

    public MatchQueryServiceImpl(final MatchRepository matchRepository) {
        super(matchRepository);
    }

    @Override
    public Page<Match> findMatchesBetweenDates(final Pageable pageable, final Date from, final Date to) {
        return repository.getByMatchPartsStartingTimeIsBetween(from, to, pageable);
    }

    @Override
    public Page<Match> findMatchesBetweenDatesForPlayer(final Pageable pageable, final Date from, final Date to,
            final BigInteger playerId) {
        return findByMatchPartsStartingTimeIsBetweenAndPlayerInfoPlayerId(pageable, from, to, playerId);
    }

    private Page<Match> findByMatchPartsStartingTimeIsBetweenAndPlayerInfoPlayerId(final Pageable pageable, final Date from, final Date to,
            final BigInteger playerId) {
        // TODO - Create a repository call that filter per player id, now I do not know how to do it
        final Set<Match> byMatchPartsStartingTimeIsBetween = repository.findByMatchPartsStartingTimeIsBetween(from, to);
        final Set<Match> matchesForPlayer = playerPlayed(byMatchPartsStartingTimeIsBetween, playerId);
        final Set<String> ids = matchesForPlayer.stream().map(Match::getId).collect(Collectors.toSet());
        // since I do not know how to make a direct call, I retrieve all the matches for that period, and then filter in java
        // using the player id, then I look for all the matches whose id is on the list
        return repository.findByIdIn(pageable, ids);

    }

    private Set<Match> playerPlayed(final Set<Match> matches, final BigInteger playerId) {
        return matches.parallelStream().filter(
                  match -> !(getAllPlayers(match)
                      .stream()
                      .filter(playerInfo -> playerInfo instanceof RegisteredPlayerInfo &&
                          ((RegisteredPlayerInfo) playerInfo).getPlayerId().equals(playerId))
                      .collect(Collectors.toSet()))
                  .isEmpty())
               .collect(Collectors.toSet());
    }

    private Set<PlayerInfo> getAllPlayers(final Match match) {
        final Set<PlayerInfo> set = new HashSet<>();
        set.addAll(match.getHomeTeam().getSelectedPlayers());
        set.addAll(match.getAwayTeam().getSelectedPlayers());
        return set;
    }

}
