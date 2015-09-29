package com.bitwise.app.propertywindow.widgets.customwidgets.runtimeproperty;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.widgets.customwidgets.AbstractWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultButton;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper;

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
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {
		ListenerFactory listenerFactory = new ListenerFactory();

		ELTDefaultSubgroupComposite runtimeComposite = new ELTDefaultSubgroupComposite(
				container.getContainerControl());
		runtimeComposite.createContainerWidget();
		runtimeComposite.numberOfBasicWidgets(2);
		shell = runtimeComposite.getContainerControl().getShell();

		
		ELTDefaultLable defaultLable1 = new ELTDefaultLable("Runtime\nProperties"); 
		runtimeComposite.attachWidget(defaultLable1);
		
		ELTDefaultButton eltDefaultButton = new ELTDefaultButton(
				"Edit").buttonWidth(90).grabExcessHorizontalSpace(false);
		//eltDefaultButton.buttonWidth(120);
		runtimeComposite.attachWidget(eltDefaultButton);

		try {
			eltDefaultButton.attachListener(listenerFactory
					.getListener("ELTRuntimeButtonClickListener"),
					propertyDialogButtonBar, new ListenerHelper(this.getClass()
							.getName(), (Object) this), eltDefaultButton
							.getSWTWidgetControl());

		} catch (Exception e1) {

			e1.printStackTrace();
		}

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

	/*@Override
	public void setComponentName(String componentName) {

		this.componentName = componentName;

	}*/

	public void newWindowLauncher() {
		RunTimePropertyWizard runTimeWizardObj = new RunTimePropertyWizard();
		runTimeWizardObj.setComponentName(componentName);
		if (getProperties().get(propertyName) == null) {
			setProperties(propertyName, new TreeMap<String, String>());

		}

		runTimeWizardObj
				.setRuntimePropertyMap((TreeMap<String, String>) getProperties()
						.get(propertyName));
		setProperties(propertyName, runTimeWizardObj.launchRuntimeWindow(shell,propertyDialogButtonBar));

	}

}
