package com.bitwise.app.propertywindow.widgets.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.ui.actions.OpenNewClassWizardAction;
import org.eclipse.jdt.ui.wizards.NewClassWizardPage;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public class FilterOperationClassUtility {
	private static String filePath = "";

	public static void createNewClassWizard(Text fileName) {
		OpenNewClassWizardAction wizard = new OpenNewClassWizardAction();
		wizard.setOpenEditorOnFinish(false);
		NewClassWizardPage page = new NewClassWizardPage();
		page.setSuperClass("java.lang.Object", true);
		List<String> interfaceList = new ArrayList<String>();
		interfaceList.add("com.bitwiseglobal.components.filter.FilterBase");
		page.setSuperInterfaces(interfaceList, true);
		wizard.setConfiguredWizardPage(page);
		wizard.run();
		if (page.isPageComplete())
			fileName.setText("/" + page.getPackageFragmentRootText() + "/"
					+ page.getPackageText().replace(".", "/") + "/"
					+ page.getTypeName() + ".java");
	}

	public static void browseFile(String filterExtension, Text fileName) {
		ResourceFileSelectionDialog dialog = new ResourceFileSelectionDialog(
				"Project", "Select Java Class (.java)", new String[] { "java" });
		if (dialog.open() == IDialogConstants.OK_ID) {
			IResource resource = (IResource) dialog.getFirstResult();
			filePath = resource.getRawLocation().toOSString();
			String arg = resource.getFullPath().toOSString();
			fileName.setText(arg);
		}
	} 

	public static boolean openFileEditor(String fileName) {
		try {
			File fileToOpen = new File(fileName);
			if(!fileToOpen.isFile())
			{
			Path path = new Path(fileName);
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			filePath = file.getRawLocation().toOSString();
			}
			else
			filePath=fileName;
			File fileToEditor = new File(filePath);
			if (fileToEditor.exists()) {
				IFileStore fileStore = EFS.getLocalFileSystem().getStore(
						fileToEditor.toURI());
				IWorkbenchPage page = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				IDE.openEditorOnFileStore(page, fileStore);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;

	}

	public static void errorMessage(Shell shell) {
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
		messageBox.setText("Error");
		messageBox.setMessage("File Not Found");
		messageBox.open();
	}

}
