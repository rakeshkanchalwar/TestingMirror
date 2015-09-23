package com.bitwise.app.eltproperties.widgets.configure;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.bitwise.app.eltproperties.Messages;
import com.bitwise.app.eltproperties.widgets.AbstractELTWidget;
import com.bitwise.app.eltproperties.widgets.utility.WidgetUtility;

public class Filewidget extends AbstractELTWidget {
	private Text filename;
	private Button btnCheckButton;
	private String filePath;
	public ControlDecoration fieldNameDecorator;
	private FormData fd_btnedit_1;
	private Object properties;
	private String propertyName;
private boolean oprationClassIsParameter;
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void attachToPropertySubGroup(Group subGroup) {
		// TODO Auto-generated method stub

		Composite composite = new Composite(subGroup, SWT.NONE);
		composite.setLayout(new FormLayout());
		final Shell shell = composite.getShell();
		filename = new Text(composite, SWT.BORDER);
		FormData fd_filename = new FormData();
		fd_filename.left = new FormAttachment(0, 111);
		fd_filename.top = new FormAttachment(0, 21);
		filename.setLayoutData(fd_filename);
		filename.setBounds(137, 83, 200, 21);
		// Adding the decorator to show error message when field name same.
		fieldNameDecorator = WidgetUtility.addDecorator(filename,
				Messages.OperationClassBlank);
		filename.addModifyListener(new ModifyListener() {
 
			@Override
			public void modifyText(ModifyEvent e) {
				if (filename.getText().isEmpty()) {
					fieldNameDecorator.show();
					filename.setBackground(new Color(Display.getDefault(), 255,
							255, 204));
				} else {
					filename.setBackground(new Color(Display.getDefault(), 255,
							255, 255));
					fieldNameDecorator.hide();
				}
			}
		});
		Button btnNewButton = new Button(composite, SWT.PUSH);
		fd_filename.right = new FormAttachment(btnNewButton, -6);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.left = new FormAttachment(0, 269);
		fd_btnNewButton.top = new FormAttachment(0, 19);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setBounds(350, 83, 20, 21);
		btnNewButton.setText("Browse...");
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ResourceFileSelectionDialog dialog = new ResourceFileSelectionDialog("Title", "Message", new String[] { "java" });
				 if (dialog.open() == IDialogConstants.OK_ID){
					 IResource resource=(IResource)dialog.getFirstResult();
					 	filePath=resource.getRawLocation().toOSString();
					    String arg=resource.getFullPath().toOSString();
					    filename.setText(arg);
				 } 
				 
				
			} 
		});
 
		Button btnNew = new Button(composite, SWT.NONE);
		FormData fd_btnNew = new FormData();
		fd_btnNew.left = new FormAttachment(0, 339);
		btnNew.setLayoutData(fd_btnNew);
		Listener listener = new Listener() {

			public void handleEvent(Event event) {
				String fileName;
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
				filename.setText("/"+page.getPackageFragmentRootText()+"/"+page.getPackageText().replace(".", "/")+"/"+page.getTypeName()+".java");
				Path path = new Path("/"+page.getPackageFragmentRootText()+"/"+page.getPackageText().replace(".", "/")+"/"+page.getTypeName()+".java");
			    IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			    filePath = file.getRawLocation().toOSString();
				}
			} 
		};
		btnNew.addListener(SWT.Selection, listener);
		btnNew.setText("Create New");
		Button btnedit = new Button(composite, SWT.NONE);
		FormData fd_btnedit = new FormData();
		fd_btnedit.right = new FormAttachment(100, -38);
		fd_btnedit.left = new FormAttachment(btnNew, 6);
		btnedit.setLayoutData(fd_btnedit);
		fd_btnedit_1 = new FormData();
		fd_btnedit_1.right = new FormAttachment(btnNewButton, 9, SWT.RIGHT);
		fd_btnedit_1.top = new FormAttachment(btnNewButton, 22);
		fd_btnedit_1.left = new FormAttachment(btnNewButton, -64);
		btnNew.setLayoutData(fd_btnedit_1);
		Listener edit1 = new Listener() {

			public void handleEvent(Event event) {
				 
				System.out.println("filePath :"+filePath);
					File fileToOpen = new File(filePath);
				    IFileStore fileStore = EFS.getLocalFileSystem().getStore(fileToOpen.toURI());
				    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				    try {
				        IDE.openEditorOnFileStore( page, fileStore );
				    } catch ( PartInitException e ) {
				        //Put your exception handler here if you wish to
				    } 
					
			}
		};
		btnedit.addListener(SWT.Selection, edit1);
		btnedit.setText("Edit");

		btnCheckButton = new Button(composite, SWT.CHECK);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnCheckButton.getEnabled())
				oprationClassIsParameter=true;
			}
		});
		fd_btnedit.top = new FormAttachment(btnCheckButton, 27);
		fd_btnNewButton.right = new FormAttachment(btnCheckButton, -6);
		fd_btnNew.top = new FormAttachment(btnCheckButton, 6);
		fd_btnNew.right = new FormAttachment(btnCheckButton, 0, SWT.RIGHT);
		FormData fd_btnCheckButton = new FormData();
		fd_btnCheckButton.left = new FormAttachment(0, 339);
		fd_btnCheckButton.top = new FormAttachment(0, 23);
		btnCheckButton.setLayoutData(fd_btnCheckButton);
		btnCheckButton.setText("Is_Parameter"); 
		Label lblOprationalClass = new Label(composite, SWT.NONE);
		FormData fd_lblOprationalClass = new FormData();
		fd_lblOprationalClass.top = new FormAttachment(filename, 0, SWT.TOP);
		fd_lblOprationalClass.left = new FormAttachment(0, 10);
		lblOprationalClass.setLayoutData(fd_lblOprationalClass);
		lblOprationalClass.setText("Oprational Class:");

	}

	@Override
	public void setProperties(String propertyName, Object properties) {
		this.properties =  properties;
		this.propertyName = propertyName;
		if(properties != null)
			filename.setText((String) properties);
		else
			filename.setText("");

	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		LinkedHashMap<String, Object> property=new LinkedHashMap<>();
		property.put(propertyName, filename.getText());
		property.put("prationClassIsParameter", oprationClassIsParameter);
		return property;
	}

	@Override
	public void setComponentName(String componentName) {
		// TODO Auto-generated method stub

	}

}
