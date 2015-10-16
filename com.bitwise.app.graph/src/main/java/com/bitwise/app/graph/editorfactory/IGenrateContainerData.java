package com.bitwise.app.graph.editorfactory;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.core.runtime.CoreException;

import com.bitwise.app.graph.model.Container;

// TODO: Auto-generated Javadoc
/**
 * The Interface IGenrateContainerData.
 */
public interface IGenrateContainerData {

	public Container getEditorInput() throws CoreException, FileNotFoundException, IOException;
	
	/**
	 * Store editor input.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws CoreException
	 *             the core exception
	 */
	public void storeEditorInput() throws IOException, CoreException;
}
