package org.manuel.teambuilting.matches.model.parts.events;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

import java.time.Instant;

/**
 * @author Manuel Doncel Martos
 * @since 2017/06/17
 * 
 * Interface to declare an event that can happen in a game
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.CUSTOM, include=JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonTypeIdResolver(MatchEventTypeIdResolver.class)
public interface MatchEvent {

	/**
	 * When the event happened
	 * @return
	 */
	Instant getWhen();

}
