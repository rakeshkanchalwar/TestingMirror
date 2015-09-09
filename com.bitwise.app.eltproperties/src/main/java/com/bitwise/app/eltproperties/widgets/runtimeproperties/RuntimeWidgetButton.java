package com.bitwise.app.eltproperties.widgets.runtimeproperties;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.bitwise.app.eltproperties.widgets.IELTWidget;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class RuntimeWidgetButton implements IELTWidget {

	private HashMap<String, String> InstializeMap;

	private Shell shell;

	@Override
	public void attachToPropertySubGroup(Group subGroup) {

		shell = subGroup.getShell();
		Composite composite = new Composite(subGroup, SWT.NONE);
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(100, -27);
		fd_composite.right = new FormAttachment(0, 401);
		fd_composite.top = new FormAttachment(0, 2);
		fd_composite.left = new FormAttachment(0, 7);
		composite.setLayoutData(fd_composite);
		composite.setLayout(new FormLayout());

		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Temp Code
				RunTimePropertyWizard runTimeWizardObj = new RunTimePropertyWizard();
				runTimeWizardObj.setComponentName("Component Name");
				if (getProperties() == null)
					//setProperties(new HashMap<String, String>());

				//runTimeWizardObj.setRuntimePropertyMap((HashMap<String, String>)getProperties());
				//setProperties(runTimeWizardObj.executeDemoWizard(shell));
				System.out.println(getProperties());
			}
		});
		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.right = new FormAttachment(0, 314);
		fd_btnNewButton_1.top = new FormAttachment(0, 10);
		fd_btnNewButton_1.left = new FormAttachment(0, 59);
		btnNewButton_1.setLayoutData(fd_btnNewButton_1);
		btnNewButton_1.setText("Open Runtime Property");

	}

	/**
	 * @wbp.parser.entryPoint
	 */
	
	public static void main(String[] args) {
		Display d = new Display();
		Shell shell = new Shell(d);
		shell.setLayout(new FormLayout());

		Group grpRuntimeProperty = new Group(shell, SWT.NONE);
		grpRuntimeProperty.setText("Runtime Property");
		grpRuntimeProperty.setLayout(new FormLayout());
		FormData fd_grpRuntimeProperty = new FormData();
		fd_grpRuntimeProperty.bottom = new FormAttachment(0, 101);
		fd_grpRuntimeProperty.top = new FormAttachment(0, 10);
		fd_grpRuntimeProperty.left = new FormAttachment(0, 10);
		fd_grpRuntimeProperty.right = new FormAttachment(0, 424);
		grpRuntimeProperty.setLayoutData(fd_grpRuntimeProperty);
		new RuntimeWidgetButton().attachToPropertySubGroup(grpRuntimeProperty);

		shell.open();

		while (!shell.isDisposed()) {
			if (!shell.getDisplay().readAndDispatch())
				shell.getDisplay().sleep();
		}

	}

	@Override
	public void setProperties(String aaa,Object properties) {
		
		InstializeMap =(HashMap<String, String>)properties;
		
	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setComponentName(String componentName) {
		// TODO Auto-generated method stub
		
	}
}
