package com.bitwise.app.propertywindow.messages;

import org.eclipse.osgi.util.NLS;

/**
 * The Class Messages.
 * 
 * @author Bitwise
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.bitwise.app.propertywindow.messages.messages";
	public static String LENGTHERROR;
	public static String DATATYPELIST;
	public static String FIELDNAME;
	public static String DATATYPE;
	public static String DATEFORMAT;
	public static String FIELDNAMEERROR;
	public static String FIELDPHASE;
	public static String CHARACTERSET;
	public static String SCALE;
	public static String SCALEERROR;
	public static String RuntimePropertAlreadyExists;
	public static String MessageBeforeClosingWindow;
	public static String SelectRowToDelete;
	public static String ConfirmToDeleteAllProperties;
	public static String PropertyAppliedNotification;
	public static String EmptyFiledNotification;
	public static String EmptyNameNotification;
	public static String EmptyValueNotification;
	public static String OperationClassBlank;
	public static String FIELD_LABEL_ERROR;
	public static String path;
	public static String INVALID_FILE;
	public static String EMPTYFIELDMESSAGE;
	public static String LENGTH;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
