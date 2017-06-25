package org.manuel.teambuilting.matches.repositories;

import org.manuel.teambuilting.matches.model.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
@Repository
public interface MatchRepository extends MongoRepository<Match, String> {

    /**
     * Find The Matches between two instants
     * @param from
     * @param to
     * @return
     */
    Set<Match> findByEndingTimeIsBetween(Date from, Date to);

}
