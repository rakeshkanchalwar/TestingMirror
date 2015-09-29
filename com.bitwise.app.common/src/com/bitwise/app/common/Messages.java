package com.bitwise.app.common;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.bitwise.app.common.messages"; //$NON-NLS-1$
	public static String XMLConfigUtil_CONFIG_FOLDER;
	public static String XMLConfigUtil_FILE_EXTENTION;
	public static String XMLConfigUtil_COMPONENTCONFIG_XSD_PATH;
	public static String XMLConfigUtil_POLICYCONFIG_XSD_PATH;
	public static String XMLConfigUtil_POLICY;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
