package com.bitwise.app.propertywindow.widgets.customwidgets.runtimeproperties;

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

import com.bitwise.app.propertywindow.widgets.customwidgets.AbstractWidget;

public class ELTRuntimePropertiesWidget extends AbstractWidget {
	private TreeMap<String, String> InstializeMap;
	private String propertyName;
	private Shell shell;
	private String componentName;

	public ELTRuntimePropertiesWidget() {
		super();
		tempPropertyMap = new LinkedHashMap<String, Object>();
	}

	private LinkedHashMap<String, Object> tempPropertyMap;

	/**
	 * @wbp.parser.entryPoint
	 */
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

		// formToolkit.adapt(composite);
		// formToolkit.paintBordersFor(composite);
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		// formToolkit.adapt(btnNewButton_1, true, true);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Temp Code
				RunTimePropertyWizard runTimeWizardObj = new RunTimePropertyWizard();
				runTimeWizardObj.setComponentName(componentName);
				if (getProperties().get(propertyName) == null) {
					setProperties(propertyName, new TreeMap<String, String>());

				}
				runTimeWizardObj
						.setRuntimePropertyMap((TreeMap<String, String>) getProperties()
								.get(propertyName));
				setProperties(propertyName,
						runTimeWizardObj.launchRuntimeWindow(shell));
			}
		});
		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.right = new FormAttachment(0, 314);
		fd_btnNewButton_1.top = new FormAttachment(0, 10);
		fd_btnNewButton_1.left = new FormAttachment(0, 59);
		btnNewButton_1.setLayoutData(fd_btnNewButton_1);
		btnNewButton_1.setText("Open Runtime Property");

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

		this.componentName = componentName;

	}

}
