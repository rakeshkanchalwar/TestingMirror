package com.bitwise.app.graph.components.handlers;

import org.eclipse.core.commands.AbstractHandler; 
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import com.bitwise.app.graph.editor.ComponentGraphEditor;
import com.bitwise.app.graph.editor.GraphEditorInput;


public class NewGraphHandler1 extends AbstractHandler{
	
	public NewGraphHandler1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPage page =
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		//page.openEditor(new MyEditorInput("com.bitwise.app.graph"), CompanyGraphEditor.ID, false);
		try {
			page.openEditor(new GraphEditorInput("com.bitwise.app.graph"), ComponentGraphEditor.ID, false);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
