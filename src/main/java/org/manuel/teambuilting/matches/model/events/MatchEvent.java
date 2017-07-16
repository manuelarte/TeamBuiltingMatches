package org.manuel.teambuilting.matches.model.events;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import org.manuel.teambuilting.matches.model.MatchItemValidable;

import java.util.Date;

/**
 * @author Manuel Doncel Martos
 * @since 2017/06/17
 * 
 * Interface to declare an event that can happen in a game
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.CUSTOM, include=JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonTypeIdResolver(MatchEventTypeIdResolver.class)
public interface MatchEvent extends MatchItemValidable {

	String getId();

	/**
	 * When the event happened
	 * @return
	 */
	Date getWhen();

	/**
	 * Description with more information about the event
	 * @return
	 */
	String getDescription();

}
