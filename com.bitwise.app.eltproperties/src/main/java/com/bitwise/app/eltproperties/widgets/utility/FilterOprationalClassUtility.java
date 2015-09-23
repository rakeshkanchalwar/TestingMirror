package com.bitwise.app.eltproperties.widgets.utility;

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
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public class FilterOprationalClassUtility {
private static String filePath="";

	public static String createNewClassWizard(Text fileName){
		OpenNewClassWizardAction wizard = new OpenNewClassWizardAction();
		wizard.setOpenEditorOnFinish(false);
		NewClassWizardPage page = new NewClassWizardPage();
		page.setSuperClass("java.lang.Object", true);
		List<String> interfaceList= new ArrayList<String>();
		interfaceList.add("com.bitwiseglobal.components.filter.FilterBase");
		page.setSuperInterfaces(interfaceList, true);
		wizard.setConfiguredWizardPage(page); 
		wizard.run();
		if(page.isPageComplete()){
		fileName.setText("/"+page.getPackageFragmentRootText()+"/"+page.getPackageText().replace(".", "/")+"/"+page.getTypeName()+".java");
		Path path = new Path("/"+page.getPackageFragmentRootText()+"/"+page.getPackageText().replace(".", "/")+"/"+page.getTypeName()+".java");
	    IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
	    filePath  = file.getRawLocation().toOSString();
	}
		  return filePath; 
}
	
	public static String browseFile(String filterExtension,Text fileName){
		ResourceFileSelectionDialog dialog = new ResourceFileSelectionDialog("Title", "Message", new String[] { "java" });
	 if (dialog.open() == IDialogConstants.OK_ID){
		 IResource resource=(IResource)dialog.getFirstResult();
		 filePath=resource.getRawLocation().toOSString();
		    String arg=resource.getFullPath().toOSString();
		    fileName.setText(arg);
	 } 
	 return filePath;
	}
	
	public static void openFileEditor(String filePath){
		File fileToOpen = new File(filePath);
	    IFileStore fileStore = EFS.getLocalFileSystem().getStore(fileToOpen.toURI());
	    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	    try {
	        IDE.openEditorOnFileStore( page, fileStore );
	    } catch ( PartInitException e ) {
	        //Put your exception handler here if you wish to
	    } 

	}
}
