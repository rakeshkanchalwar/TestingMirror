package com.bitwise.app.graph;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.bitwise.app.graph.messages"; //$NON-NLS-1$
	public static String GRADLE_RUN;
	public static String XMLPATH;
	public static String PARAM_FILE;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
