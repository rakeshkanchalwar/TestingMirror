package com.bitwise.app.perspective.config;

import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;

import com.bitwise.app.perspective.utils.PerspectiveManager;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Aug 26, 2015
 * 
 */

public class ELTWorkbenchConfig {
	private static void dockPrespectiveBarToRightTop(){
		PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.DOCK_PERSPECTIVE_BAR, IWorkbenchPreferenceConstants.TOP_RIGHT);
	}
	
	public static void setDefaultELTPrespectiveConfigurations(){
		dockPrespectiveBarToRightTop();
		PerspectiveManager.removeUnWantedPerspectives();
	}
}
