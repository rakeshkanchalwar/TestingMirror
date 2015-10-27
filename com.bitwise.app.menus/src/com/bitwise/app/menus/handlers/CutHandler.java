package com.bitwise.app.menus.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;

public class CutHandler extends AbstractHandler implements IHandler {

	IHandler copy = new CopyHandler();
	IHandler cut = new DeleteHandler();

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		copy.execute(event);
		cut.execute(event);
		return null;
	}

	@Override
	public void dispose() {
		copy.dispose();
		cut.dispose();
		super.dispose();

	}
}
