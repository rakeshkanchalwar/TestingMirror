package com.bitwise.app.perspective;

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
        
        //Initiate the com.bitwise.app.model jar updation
		DynamicClassProcessor.INSTANCE.initiate();
    }
}
