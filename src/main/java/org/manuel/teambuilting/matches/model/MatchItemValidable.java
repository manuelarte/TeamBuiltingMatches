package org.manuel.teambuilting.matches.model;

/**
 * @author Manuel Doncel Martos
 * @since 26/06/2017.
 */
public interface MatchItemValidable {

    /**
     * Check whether the event is valid for the match context
     * @param match
     * @return
     */
    boolean validInContext(Match match);
}
