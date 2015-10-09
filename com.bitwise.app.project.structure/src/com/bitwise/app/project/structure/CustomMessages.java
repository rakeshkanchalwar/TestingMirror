package com.bitwise.app.project.structure;

import org.eclipse.osgi.util.NLS;

public class CustomMessages extends NLS {
	private static final String BUNDLE_NAME = "com.bitwise.app.project.structure.messages"; //$NON-NLS-1$
	public static String CustomWizard_CREATE_ETL_PROJECT;
	public static String CustomWizard_ENTER_PROJECT_NAME;
	public static String ProjectSupport_BIN;
	public static String ProjectSupport_LIB;
	public static String ProjectSupport_SCRIPTS;
	public static String ProjectSupport_SRC;
	public static String ProjectSupport_XML;
	public static String ProjectSupport_BUILD_FOLDER;
	public static String ProjectSupport_CONFIG_FOLDER;
	public static String GradleNature_ID;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, CustomMessages.class);
	}

	private CustomMessages() {
	}
}
