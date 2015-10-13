package com.bitwise.app.engine.exceptions;

public class ConverterNotFoundException extends EngineException {
	
	private static final long serialVersionUID = -7050865517559484599L;
	private static String MSG_SUFFIX = "No converter found for - ";

	public ConverterNotFoundException(String message, Throwable cause) {
		super(MSG_SUFFIX + message, cause);
	}
}
