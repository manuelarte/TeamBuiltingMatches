package org.manuel.teambuilting.matches.transformers;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.core.utils.Util;
import org.manuel.teambuilting.matches.model.IncomingMatch;
import org.manuel.teambuilting.matches.model.Match;
import org.manuel.teambuilting.matches.model.MatchImpl;
import org.springframework.stereotype.Component;

/**
 * @author Manuel Doncel Martos
 * @since 06/08/2017.
 */
@Component
@AllArgsConstructor
public class IncomingMatchToMatchTransformer {

    private Util util;

    public Match apply(final IncomingMatch incomingMatch) {
        final String createdBy = util.getUserProfile().get().getUserId();
        return MatchImpl.builder().id(incomingMatch.getId()).createdBy(createdBy)
                .homeTeam(incomingMatch.getHomeTeam())
                .awayTeam(incomingMatch.getAwayTeam()).matchParts(incomingMatch.getMatchParts())
                .description(incomingMatch.getDescription()).location(incomingMatch.getLocation())
                .tags(incomingMatch.getTags()).events(incomingMatch.getEvents()).build();
    }


}
