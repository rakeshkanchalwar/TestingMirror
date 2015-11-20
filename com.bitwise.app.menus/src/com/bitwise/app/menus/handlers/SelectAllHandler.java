package com.bitwise.app.menus.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import com.bitwise.app.graph.editor.ELTGraphicalEditor;

public class SelectAllHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IEditorPart editor = HandlerUtil.getActiveEditor(event);
		if (editor instanceof ELTGraphicalEditor)
			((ELTGraphicalEditor) editor).selectAllSelection();

		return null;
	}

}
