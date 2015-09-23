package com.bitwise.app.eltproperties.widget.filterproperties;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.bitwise.app.eltproperties.widgets.AbstractELTWidget;
import com.bitwise.app.eltproperties.widgets.runtimeproperties.RunTimePropertyWizard;

public class ELTFilterPropertyWidget extends AbstractELTWidget{

	private Shell shell;
	private LinkedHashMap<String, Object> tempPropertyMap;
	private String propertyName;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private List<String> propertyLst;
	private TreeMap<String, String> InstializeMap;
	private String componentName;
	

	public ELTFilterPropertyWidget(){
		super();
		tempPropertyMap = new LinkedHashMap<String, Object>();
	}
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

		Label lblAdesss = new Label(composite, SWT.READ_ONLY);
		lblAdesss.setAlignment(SWT.CENTER);
		FormData fd_lblAdesss = new FormData();
		fd_lblAdesss.top = new FormAttachment(0, 15);
		fd_lblAdesss.left = new FormAttachment(0, 10);
		fd_lblAdesss.right = new FormAttachment(0, 150);
		lblAdesss.setLayoutData(fd_lblAdesss);
		formToolkit.adapt(lblAdesss, true, true);
		lblAdesss.setText("Filter Operation Field");
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
		
			

			@Override
			public void widgetSelected(SelectionEvent e) {
				ELTFilterPropertyWizard filterWizardObj = new ELTFilterPropertyWizard();
				/*runTimeWizardObj.setComponentName(componentName);
				if (getProperties().get(propertyName) == null) {
					setProperties((String)propertyName, new TreeMap<String, String>());
						
				}
			runTimeWizardObj.setRuntimePropertyMap((TreeMap<String, String>) getProperties().get(propertyName));
				}*/	setProperties((String)propertyName,filterWizardObj.launchRuntimeWindow(shell));
			
			}
			});
		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.right = new FormAttachment(0, 314);
		fd_btnNewButton_1.top = new FormAttachment(0, 10);
		fd_btnNewButton_1.left = new FormAttachment(0, 200);
		btnNewButton_1.setLayoutData(fd_btnNewButton_1);
		btnNewButton_1.setText("Edit");
		
	}

	@Override
	public void setProperties(String propertyName, Object properties) {
		this.propertyName = propertyName;
		this.InstializeMap = (TreeMap<String, String>) properties;
		
	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		tempPropertyMap.put(this.propertyName, this.InstializeMap);
		return (tempPropertyMap);
	}

	@Override
	public void setComponentName(String componentName) {
		this.componentName=componentName;
		
	}

}
