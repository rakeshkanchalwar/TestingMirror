package com.bitwise.app.perspective.config;

import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;

/**
 * 
 * @author Bitwise
 * Aug 26, 2015
 * 
 */

public class ELTPerspectiveConfig {
	IWorkbenchWindowConfigurer configurer;	
	PerspectiveRemover perspectiveRemover;
		
	public ELTPerspectiveConfig(IWorkbenchWindowConfigurer configurer){
		this.configurer=configurer;
		perspectiveRemover=new PerspectiveRemover();
	}
	
	private void dockPrespectiveBarToRightTop(){
		PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.DOCK_PERSPECTIVE_BAR, IWorkbenchPreferenceConstants.TOP_RIGHT);
	}
	
	public void setDefaultELTPrespectiveConfigurations(){
		
		configurer.setInitialSize(configurer.getInitialSize());
        configurer.setShowCoolBar(true);
        configurer.setShowStatusLine(false);
        configurer.setTitle("ELT Tool"); //$NON-NLS-1$
        configurer.setShowPerspectiveBar(true);
        
        dockPrespectiveBarToRightTop();
        
        perspectiveRemover.removeUnWantedPerspectives();
	}
}
