package com.bitwise.app.engine.exceptions;

/**
 * Base class for converter related exceptions
 *
 */
public class EngineException extends RuntimeException {
	private static final long serialVersionUID = -8417780631291379598L;

	public EngineException(String message, Throwable cause) {
		super(message, cause);
	}
}

