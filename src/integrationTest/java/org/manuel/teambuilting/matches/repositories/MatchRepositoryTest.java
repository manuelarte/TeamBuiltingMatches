package org.manuel.teambuilting.matches.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.manuel.teambuilting.matches.model.Match;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * @author Manuel Doncel Martos
 * @since 24/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchRepositoryTest {

    @Inject
    private MatchRepository matchRepository;

    @Test
    public void testRetrieveMatchesBetweenTwoSameInstants() {
        final Set<Match> matchesBetweenTwoInstants = matchRepository.findByEndingTimeIsBetween(new Date(), new Date());
        assertTrue(matchesBetweenTwoInstants.isEmpty());
    }

}
