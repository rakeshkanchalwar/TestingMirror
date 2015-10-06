package com.bitwise.app.graph.editorfactory;

import org.eclipse.ui.IEditorInput;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.graph.editor.ETLGraphicalEditor;
import com.bitwise.app.graph.editor.ETLGraphicalEditorInput;
import com.bitwise.app.graph.model.Container;

public class GraphicalEditorContiner implements IGenrateContainerData {

	private ETLGraphicalEditorInput graphicaleditorInput;
	private ETLGraphicalEditor eltGraphicalEditorInstance;
	LogFactory eltLogger = new LogFactory(getClass().getName());
	
	public GraphicalEditorContiner(IEditorInput editorInput, ETLGraphicalEditor eltGraphicalEditorInstance) {
		this.graphicaleditorInput = (ETLGraphicalEditorInput) editorInput;
		this.eltGraphicalEditorInstance=eltGraphicalEditorInstance;
	}

	@Override
	public Container getEditorInput() {
		eltLogger.getLogger().info("getEditorInput - Setting GraphicalEditor Input");
		this.eltGraphicalEditorInstance.setPartName(this.graphicaleditorInput.getName());
		return new Container();
	}

	@Override
	public void storeEditorInput() {
		eltLogger.getLogger().info("storeEditorInput - Calling doSaveAs method");
		eltGraphicalEditorInstance.doSaveAs();
		
	}
}

