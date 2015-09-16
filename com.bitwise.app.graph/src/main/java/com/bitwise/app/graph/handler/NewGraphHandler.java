package com.bitwise.app.graph.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.bitwise.app.graph.editor.ETLGraphicalEditor;
import com.bitwise.app.graph.editor.ETLGraphicalEditorInput;

public class NewGraphHandler extends AbstractHandler {
	private int graphCounter=1; 
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		try {
			ETLGraphicalEditorInput input = new ETLGraphicalEditorInput("Graph "+graphCounter++);
			page.openEditor(input, ETLGraphicalEditor.ID, false);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
