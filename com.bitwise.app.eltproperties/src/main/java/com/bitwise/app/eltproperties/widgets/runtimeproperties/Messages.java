package com.bitwise.app.eltproperties.widgets.runtimeproperties;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.bitwise.app.eltproperties.widgets.runtimeproperties.messages"; //$NON-NLS-1$
	public static String RuntimePropertAlreadyExists;
	public static String MessageBeforeClosingWindow;
	public static String SelectRowToDelete;
	public static String ConfirmToDeleteAllProperties;
	public static String PropertyAppliedNotification;
	public static String EmptyFiledNotification;
	public static String EmptyNameNotification;
	public static String EmptyValueNotification;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
