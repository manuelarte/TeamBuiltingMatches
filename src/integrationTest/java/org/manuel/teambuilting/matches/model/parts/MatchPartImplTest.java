package org.manuel.teambuilting.matches.model.parts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchPartImplTest {

    @Test
    public void testAddEventNotBelongingToTheMatchPart() {
        final Instant startingTime = Instant.now();
        final Duration duration = Duration.ofMinutes(45);
        final MatchEvent matchEvent = createMatchEvent(startingTime.minus(1, ChronoUnit.MINUTES));
        final MatchPart matchPart = MatchPartImpl.builder().startingTime(startingTime).duration(duration).event(matchEvent).build();

    }

    private MatchEvent createMatchEvent(final Instant when) {
        return () -> when;
    }
}
