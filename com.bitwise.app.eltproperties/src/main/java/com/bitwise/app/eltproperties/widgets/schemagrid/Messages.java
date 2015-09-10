package com.bitwise.app.eltproperties.widgets.schemagrid;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.bitwise.app.eltproperties.widgets.schemagrid.messages"; //$NON-NLS-1$
	public static String DATATYPELIST ;
	public static String FIELDNAME;
	public static String DATATYPE;
	public static String LIMIT;
	public static String FIELDNAMEERROR;
	public static String FIELDPHASE;
	public static String CHARACTERSET;
	public static String FIELDSAFE;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
 
