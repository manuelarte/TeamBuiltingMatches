package org.manuel.teambuilting.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author manuel.doncel.martos
 * @since 13-1-2017
 */
@Data
@AllArgsConstructor
public class ExceptionMessage {

	private final HttpStatus status;

	private final String errorCode;

	private final String message;

	private final String detailedMessage;

}
