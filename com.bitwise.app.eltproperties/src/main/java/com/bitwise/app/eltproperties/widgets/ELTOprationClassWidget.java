package com.bitwise.app.eltproperties.widgets;

import java.util.LinkedHashMap;

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

import com.bitwise.app.eltproperties.Messages;
import com.bitwise.app.eltproperties.widgets.AbstractELTWidget;
import com.bitwise.app.eltproperties.widgets.utility.FilterOprationalClassUtility;
import com.bitwise.app.eltproperties.widgets.utility.WidgetUtility;

public class ELTOprationClassWidget extends AbstractELTWidget {
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
				 filePath=FilterOprationalClassUtility.browseFile("java", filename);

			} 
		});
 
		Button btnNew = new Button(composite, SWT.NONE);
		FormData fd_btnNew = new FormData();
		fd_btnNew.left = new FormAttachment(0, 339);
		btnNew.setLayoutData(fd_btnNew);
		Listener listener = new Listener() {

			public void handleEvent(Event event) {
				filePath=FilterOprationalClassUtility.createNewClassWizard(filename);
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
				FilterOprationalClassUtility.openFileEditor(filePath);
							
			}
		};
		btnedit.addListener(SWT.Selection, edit1);
		btnedit.setText("Edit");

		btnCheckButton = new Button(composite, SWT.CHECK);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
					if(btnCheckButton.getSelection())
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
