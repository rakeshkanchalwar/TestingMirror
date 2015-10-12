package com.bitwise.app.menus.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;

public class ProjectExplorerHandler extends AbstractHandler implements IHandler {
	private Logger logger=LogFactory.INSTANCE.getLogger(ConsoleHandler.class);
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
			.showView("com.bitwise.app.project.structure.navigator");
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
