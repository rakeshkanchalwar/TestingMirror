package com.bitwise.app.project.structure.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

import com.bitwise.app.project.structure.CustomMessages;

public class WizardNewGraphFileCreationPage extends WizardNewFileCreationPage {

	public WizardNewGraphFileCreationPage(String pageName, IStructuredSelection selection) {
		super(pageName, selection);
		setTitle(CustomMessages.WizardNewGraphFileCreationPage_GRAPH_FILE_WIZARD);
        setDescription(CustomMessages.WizardNewGraphFileCreationPage_DESCRIPTION);
        setFileExtension(CustomMessages.WizardNewGraphFileCreationPage_FILE_EXTENTION);
	}
}
