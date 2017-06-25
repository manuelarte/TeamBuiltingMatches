package org.manuel.teambuilting.matches.model.parts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.manuel.teambuilting.matches.model.parts.events.MatchEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchPartImplTest {

    @Test
    public void testAddEventNotBelongingToTheMatchPart() {
        final Date startingTime = new Date();
        final Duration duration = Duration.ofMinutes(45);
        final List<MatchEvent> matchEvents = Arrays.asList(createMatchEvent(new Date(startingTime.getTime() - 100)));
        final MatchPart matchPart = MatchPartImpl.builder().startingTime(startingTime).duration(duration).events(matchEvents).build();

    }

    private MatchEvent createMatchEvent(final Date when) {
        return () -> when;
    }
}
