package com.bitwise.app.perspective;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.ide.IDE;

import theme.ThemeHelper;

/**
 * The Class ApplicationWorkbenchAdvisor.
 * 
 * @author Bitwise
 */
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private static final String PERSPECTIVE_ID = "com.bitwise.app.perspective.ETLPerspective"; //$NON-NLS-1$
	
	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
    	IDE.registerAdapters();
    	 PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.ENABLE_DETACHED_VIEWS, false);
        return new ApplicationWorkbenchWindowAdvisor(configurer);
    }

	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}

	@Override
	public IAdaptable getDefaultPageInput() {
		return ResourcesPlugin.getWorkspace().getRoot();
	}
	@Override
	public void initialize(IWorkbenchConfigurer configurer) {
		// TODO Auto-generated method stub
		super.initialize(configurer);
		
		ThemeHelper.getEngine().setTheme("com.bitwise.app.project.structure.rcp.theme.id",true);
		
		
		
	}
	
	
}
