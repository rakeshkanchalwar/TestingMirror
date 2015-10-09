package com.bitwise.app.graph.editorfactory;

import org.eclipse.ui.IEditorInput;
import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.graph.editor.ETLGraphicalEditor;
import com.bitwise.app.graph.editor.ETLGraphicalEditorInput;
import com.bitwise.app.graph.model.Container;

public class GraphicalEditorContiner implements IGenrateContainerData {

	private ETLGraphicalEditorInput graphicaleditorInput;
	private ETLGraphicalEditor eltGraphicalEditorInstance;
	Logger logger = new LogFactory(getClass().getName()).getLogger();
	
	public GraphicalEditorContiner(IEditorInput editorInput, ETLGraphicalEditor eltGraphicalEditorInstance) {
		this.graphicaleditorInput = (ETLGraphicalEditorInput) editorInput;
		this.eltGraphicalEditorInstance=eltGraphicalEditorInstance;
	}

	@Override
	public Container getEditorInput() {
		logger.debug("getEditorInput - Setting GraphicalEditor Input");
		this.eltGraphicalEditorInstance.setPartName(this.graphicaleditorInput.getName());
		return new Container();
	}

	@Override
	public void storeEditorInput() {
		logger.debug("storeEditorInput - Calling doSaveAs method");
		eltGraphicalEditorInstance.doSaveAs();
		
	}
}

