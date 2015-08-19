package com.bitwise.app.common.util;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.bitwise.app.common.util.messages"; //$NON-NLS-1$
	public static String XMLConfigUtil_CONFIG_FOLDER;
	public static String XMLConfigUtil_FILE_EXTENTION;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
