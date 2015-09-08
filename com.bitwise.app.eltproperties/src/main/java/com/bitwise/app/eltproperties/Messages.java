package com.bitwise.app.eltproperties;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.bitwise.app.eltproperties.messages"; //$NON-NLS-1$
	public static String DATATYPELIST ;
	public static String FIELDNAME;
	public static String DATATYPE;
	public static String LIMIT;

	 
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
 