package com.bitwise.app.parametergrid.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.bitwise.app.parametergrid.dialog.ParameterGridDialog;

public class ParameterGridOpenHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		/*IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window.getShell(),
				"Parameter Grid",
				"We will have parameter grid here");*/
		
		ParameterGridDialog parameterGrid = new ParameterGridDialog(Display.getDefault().getActiveShell());
		parameterGrid.open();
		
		return null;
	}

}
