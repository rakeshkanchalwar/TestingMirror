package com.bitwise.app.graph.editorfactory;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.graph.editor.ETLGraphicalEditor;
import com.bitwise.app.graph.editor.ETLGraphicalEditorInput;
import com.bitwise.app.graph.model.Container;

public class GenrateContainerData {

	private IGenrateContainerData editorInput;
	Logger logger = new LogFactory(getClass().getName()).getLogger();
	
	public void setEditorInput(IEditorInput editorInput, ETLGraphicalEditor eltGraphicalEditorInstance) {
		logger.debug("setEditorInput - ");
		if((ETLGraphicalEditorInput.class).isAssignableFrom(editorInput.getClass()))
		{this.editorInput = new GraphicalEditorContiner(editorInput,eltGraphicalEditorInstance);}
		else{
			if((IFileEditorInput.class).isAssignableFrom(editorInput.getClass()))
				this.editorInput= new FileEditorContiner(editorInput,eltGraphicalEditorInstance);
			else
				if((FileStoreEditorInput.class).isAssignableFrom(editorInput.getClass()))
					this.editorInput=new FileStorageEditorContainer(editorInput,eltGraphicalEditorInstance);
		}
	}
	
	public Container getContainerData() throws CoreException, IOException
	{
		
		return editorInput.getEditorInput();
	}
	
	public void storeContainerData() throws IOException, CoreException
	{

		editorInput.storeEditorInput();
		
	}
	
}
