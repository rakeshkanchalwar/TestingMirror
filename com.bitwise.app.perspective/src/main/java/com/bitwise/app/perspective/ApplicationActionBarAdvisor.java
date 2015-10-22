package com.bitwise.app.perspective;


import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;


// TODO: Auto-generated Javadoc
/**
 * The Class ApplicationActionBarAdvisor.
 * 
 * @author Bitwise
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	 private IWorkbenchAction openPerspectiveAction;
	 
	/**
	 * Instantiates a new application action bar advisor.
	 * 
	 * @param configurer
	 *            the configurer
	 */
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	openPerspectiveAction = ActionFactory.OPEN_PERSPECTIVE_DIALOG.create(window);
        register(openPerspectiveAction);
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	
    }
    
}
