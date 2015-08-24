package com.bitwise.app.graph;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import com.bitwise.app.graph.editor.ComponentGraphEditor;
import com.bitwise.app.graph.editor.GraphEditorInput;


public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

		
	
	private static final String PERSPECTIVE_ID = "com.bitwise.app.graph.perspective"; //$NON-NLS-1$

    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new ApplicationWorkbenchWindowAdvisor(configurer);
    }

	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}
	@Override
	public void postStartup() {
		try {
			/*IWorkbenchPage page =
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			//page.openEditor(new MyEditorInput("com.bitwise.app.graph"), CompanyGraphEditor.ID, false);
			page.openEditor(new MyEditorInput("com.bitwise.app.graph"), ComponentGraphEditor.ID, false);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
