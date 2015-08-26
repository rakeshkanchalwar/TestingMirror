package com.bitwise.app.perspective;


import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;

import org.eclipse.ui.IWorkbenchWindow;

import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;


public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	 private IWorkbenchAction openPerspectiveAction;
	 
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
