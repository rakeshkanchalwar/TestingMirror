package com.bitwise.app.engine.exceptions;

public class PhaseException extends RuntimeException {
private static String messagesuffix=" - Phase is empty or invalid";
	
	public PhaseException(String message) {
		super(message + messagesuffix);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2836447374635141077L;

}
