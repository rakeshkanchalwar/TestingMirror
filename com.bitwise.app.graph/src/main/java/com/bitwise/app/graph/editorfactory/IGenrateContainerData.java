package com.bitwise.app.graph.editorfactory;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.core.runtime.CoreException;

import com.bitwise.app.graph.model.Container;

public interface IGenrateContainerData {

	public Container getEditorInput() throws CoreException, FileNotFoundException, IOException;
	public void storeEditorInput() throws IOException, CoreException;
}
