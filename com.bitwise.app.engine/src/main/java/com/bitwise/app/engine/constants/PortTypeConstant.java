package com.bitwise.app.engine.constants;

public class PortTypeConstant {

	private static final String INPUT_PORT_TYPE = "in";
	private static final String OUTPUT_PORT_TYPE = "out";
	private static final String UNUSED_PORT_TYPE = "unused";

	public static String getPortType(String PortName) {
		if (PortName.startsWith("in"))
			return INPUT_PORT_TYPE;
		if (PortName.startsWith("out"))
			return OUTPUT_PORT_TYPE;
		if (PortName.startsWith("unused"))
			return UNUSED_PORT_TYPE;

		return null;

	}

}
