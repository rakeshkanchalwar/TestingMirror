package com.bitwise.app.engine.exceptions;

/**
 * Exception class thrown when Phase parameter is invalid
 */
public class PhaseException extends EngineException {
	private static final long serialVersionUID = -2836447374635141077L;
	private static final String MESSAGE_PREFIX = "Phase is empty or invalid - ";
	
	public PhaseException(String message, Throwable cause) {
		super(MESSAGE_PREFIX + message, cause);
	}
}
