package com.bitwise.app.graph.editorfactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.graph.editor.ETLGraphicalEditor;
import com.bitwise.app.graph.model.Container;

public class FileEditorContiner implements IGenrateContainerData {
	private IFileEditorInput ifileEditorInput;
	private ETLGraphicalEditor eltGraphicalEditorInstance;
	LogFactory eltLogger = new LogFactory(getClass().getName());
	
	public FileEditorContiner(IEditorInput editorInput, ETLGraphicalEditor eltGraphicalEditorInstance) {
		this.ifileEditorInput=(IFileEditorInput)editorInput;
		this.eltGraphicalEditorInstance=eltGraphicalEditorInstance;
	}

	@Override
	public Container getEditorInput() throws CoreException {
		eltLogger.getLogger().info("getEditorInput - Setting IFileEditor input");
		IFile Ifile = ((IFileEditorInput) ifileEditorInput).getFile();
		this.eltGraphicalEditorInstance.setPartName(Ifile.getName());
		return (Container) eltGraphicalEditorInstance.fromXMLToObject(Ifile.getContents());
	}

	@Override
	public void storeEditorInput() throws IOException, CoreException {
		eltLogger.getLogger().info("storeEditorInput - Storing IFileEditor input into Ifile");
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			eltGraphicalEditorInstance.createOutputStream(out);
			IFile ifile = ifileEditorInput.getFile();
			ifile.setContents(new ByteArrayInputStream(out.toByteArray()),true, false, null);
			this.eltGraphicalEditorInstance.getCommandStack().markSaveLocation();
			this.eltGraphicalEditorInstance.genrateTargetXml(ifile);
	
		
	}

	
	
}
