package com.bitwise.app.menus.handlers;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import com.bitwise.app.graph.controller.PortEditPart;
import com.bitwise.app.graph.editor.ELTGraphicalEditor;
import com.bitwise.app.graph.figure.PortFigure;
public class ShowPortLabelsHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart editor = HandlerUtil.getActiveEditor(event);
		if(editor instanceof ELTGraphicalEditor)
		{
			GraphicalViewer gv =(GraphicalViewer) ((GraphicalEditor)editor).getAdapter(GraphicalViewer.class);
			for (Iterator<PortEditPart> ite = gv.getEditPartRegistry().values().iterator(); 
					ite.hasNext();)
			{
				EditPart ep = (EditPart) ite.next();
				if(ep instanceof PortEditPart) 
				{
					PortFigure portFigure=((PortEditPart)ep).getPortFigure();
					portFigure.setValue(true);
					portFigure.repaint();
				}
			}
		}

		return null;
	}

}
