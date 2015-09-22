package com.bitwise.app.eltproperties.widget.filterproperties;

import java.util.LinkedHashMap;
import java.util.TreeMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import com.bitwise.app.eltproperties.widgets.AbstractELTWidget;
import com.bitwise.app.eltproperties.widgets.runtimeproperties.RunTimePropertyWizard;

public class ELTFilterPropertyWidget extends AbstractELTWidget{

	private Shell shell;

	@Override
	public void attachToPropertySubGroup(Group subGroup) {
		shell = subGroup.getShell();
		Composite composite = new Composite(subGroup, SWT.NONE);

		composite.setLayout(new FormLayout());
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(100, -27);
		fd_composite.right = new FormAttachment(0, 401);
		fd_composite.top = new FormAttachment(0, 2);
		fd_composite.left = new FormAttachment(0, 7);

		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.right = new FormAttachment(0, 314);
		fd_btnNewButton_1.top = new FormAttachment(0, 10);
		fd_btnNewButton_1.left = new FormAttachment(0, 59);
		btnNewButton_1.setLayoutData(fd_btnNewButton_1);
		btnNewButton_1.setText("Filter Property");
		
	}

	@Override
	public void setProperties(String propertyName, Object properties) {
		// TODO Auto-generated method stub
		
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
