package com.bitwise.app.graph.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.bitwise.app.graph.editor.ELTGraphicalEditor;
import com.bitwise.app.graph.editor.ELTGraphicalEditorInput;

/**
 *	Handler class to create the graphical editor.
 */
public class NewGraphHandler extends AbstractHandler {
	private int graphCounter=1; 
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		try {
			ELTGraphicalEditorInput input = new ELTGraphicalEditorInput("Job_"+ graphCounter++);
			page.openEditor(input, ELTGraphicalEditor.ID, false);
			
			//For selecting the created editor so it will trigger the event to activate and load the Palette
			IWorkbench workbench = PlatformUI.getWorkbench();
		    IWorkbenchWindow activeWindow = workbench.getActiveWorkbenchWindow();
		    if (activeWindow != null) {
		        final IWorkbenchPage activePage = activeWindow.getActivePage();
		        if (activePage != null) {
		            activePage.activate(activePage.findEditor(input));
		        }
		    }
		} catch (PartInitException e) {
			//TODO : add logger
		}
		return null;
	}
}
