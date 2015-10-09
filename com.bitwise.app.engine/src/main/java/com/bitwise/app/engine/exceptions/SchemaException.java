package com.bitwise.app.engine.exceptions;

public class SchemaException extends EngineException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6430837268655544239L;
	static String messagesuffix = " component is having invalid Schema data ";

	public SchemaException(String id) {
		super("\n"+id + messagesuffix);
	}

}
