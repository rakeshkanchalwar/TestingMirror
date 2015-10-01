package com.bitwise.app.perspective;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import com.bitwise.app.perspective.config.ELTPerspectiveConfig;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        ELTPerspectiveConfig eltPerspectiveConfig = new ELTPerspectiveConfig(configurer);
        
        eltPerspectiveConfig.setDefaultELTPrespectiveConfigurations();
        PlatformUI.getWorkbench().getThemeManager().setCurrentTheme("de.spiritlink.custom.ui.theme");
    }
    @Override
    public void createWindowContents(Shell shell) {
    	
    	super.createWindowContents(shell);
  
    }

	@Override
	public void postWindowOpen() {
		super.postWindowOpen();
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().setMaximized(true);
	}
    
    
    
}
