package com.bitwise.app.project.structure.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;

public class CustomWizardNewFile extends Wizard implements INewWizard {
	private static final String WINDOW_TITLE = "New Graph File"; //$NON-NLS-1$

	private IWorkbench _workbench;
	private IStructuredSelection _selection;
	private WizardNewFileCreationPage _pageOne;
	
	public CustomWizardNewFile() {
		setWindowTitle(WINDOW_TITLE);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		_workbench = workbench;
		_selection = selection;
	}

	@Override
	public boolean performFinish() {
		boolean result = false;
		 
	    IFile file = _pageOne.createNewFile();
	    result = file != null;
	 
	    if (result) {
	        try {
	            IDE.openEditor(_workbench.getActiveWorkbenchWindow().getActivePage(), file);
	        } catch (PartInitException e) {
	            e.printStackTrace();
	        }
	    } 	 
	    return result;
	}

	@Override
	public void addPages() {
		super.addPages();
		_pageOne = new WizardNewGraphFileCreationPage("File Creation Wizard", _selection);
		addPage(_pageOne);
	}
}
