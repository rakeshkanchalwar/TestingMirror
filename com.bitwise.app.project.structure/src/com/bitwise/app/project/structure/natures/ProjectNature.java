package com.bitwise.app.project.structure.natures;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

/**
 * The Class ProjectNature.
 * 
 * @author Bitwise
 */
public class ProjectNature implements IProjectNature {
	public static final String NATURE_ID = "com.bitwise.app.project.structure.projectNature"; //$NON-NLS-1$ 

	private IProject project;
	
	@Override
	public void configure() throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deconfigure() throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public IProject getProject() {
		return project;
	}

	@Override
	public void setProject(IProject project) {
		this.project = project;
	}

}
