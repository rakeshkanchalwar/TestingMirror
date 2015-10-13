package com.bitwise.app.engine.exceptions;

/**
 * Exception class thrown when Schema parameter is invalid
 */
public class SchemaException extends EngineException {
	private static final long serialVersionUID = -6430837268655544239L;
	private static final String MESSAGE_PREFIX = "Component is having invalid Schema data - ";

	public SchemaException(String message, Throwable cause) {
		super(MESSAGE_PREFIX + message, cause);
	}

}
