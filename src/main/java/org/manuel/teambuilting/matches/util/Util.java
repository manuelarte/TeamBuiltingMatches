package org.manuel.teambuilting.matches.util;

import lombok.experimental.UtilityClass;

import java.time.Duration;

/**
 * @author Manuel Doncel Martos
 * @since 21/06/2017.
 */
@UtilityClass
public class Util {

    /**
     * A Match cannot last longer than MAX DURATION OF MATCH
     */
    public static final Duration MAX_DURATION_OF_MATCH = Duration.ofMinutes(300);

}
