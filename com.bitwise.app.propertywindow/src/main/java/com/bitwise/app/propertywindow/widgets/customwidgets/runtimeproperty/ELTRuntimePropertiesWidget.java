package com.bitwise.app.propertywindow.widgets.customwidgets.runtimeproperty;

import java.util.LinkedHashMap;
import java.util.TreeMap;

import org.eclipse.swt.widgets.Shell;

import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.property.ComponentConfigrationProperty;
import com.bitwise.app.propertywindow.property.ComponentMiscellaneousProperties;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.customwidgets.AbstractWidget;
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

	/*public ELTRuntimePropertiesWidget() {
		super();
		tempPropertyMap = new LinkedHashMap<String, Object>();
	}*/
	
	public ELTRuntimePropertiesWidget(
			ComponentConfigrationProperty componentConfigrationProperty,
			ComponentMiscellaneousProperties componentMiscellaneousProperties,
			PropertyDialogButtonBar propertyDialogButtonBar) {
		super(componentConfigrationProperty, componentMiscellaneousProperties,
				propertyDialogButtonBar);

		this.propertyName = componentConfigrationProperty.getPropertyName();
		this.InstializeMap = (TreeMap<String, String>) componentConfigrationProperty.getPropertyValue();
		
		tempPropertyMap = new LinkedHashMap<String, Object>();
	}
	
	
	/*public ELTRuntimePropertiesWidget(
			ComponentConfigrationProperty componentModelProperty,
			PropertyDialogButtonBar propertyDialogButtonBar) {
		super(componentModelProperty, propertyDialogButtonBar);

		this.propertyName = componentModelProperty.getPropertyName();
		this.InstializeMap = (TreeMap<String, String>) componentModelProperty.getPropertyValue();
		
		tempPropertyMap = new LinkedHashMap<String, Object>();
		
	}*/
	
	/*public ELTRuntimePropertiesWidget(
			ELTComponenetProperties eltComponenetProperties,
			PropertyDialogButtonBar propertyDialogButtonBar) {
		super(eltComponenetProperties, propertyDialogButtonBar);

		this.propertyName = componentProperties.get(key);
		this.InstializeMap = (TreeMap<String, String>) properties;
		
		tempPropertyMap = new LinkedHashMap<String, Object>();
		
	}*/

	/*public ELTRuntimePropertiesWidget(
			PropertyDialogButtonBar propertyDialogButtonBar,
			LinkedHashMap<String, Object> componentMiscellaneousProperties,
			LinkedHashMap<String, Object> componentProperties) {
		super(propertyDialogButtonBar, componentMiscellaneousProperties,
				componentProperties);
		
		
		this.propertyName = componentProperties.get(key);
		this.InstializeMap = (TreeMap<String, String>) properties;
		
		tempPropertyMap = new LinkedHashMap<String, Object>();
	}*/
		

	/*public ELTRuntimePropertiesWidget(
			ELTComponenetProperties eltComponenetProperties,
			PropertyDialogButtonBar propertyDialogButtonBar) {
		// TODO Auto-generated constructor stub
	}*/


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
				"Edit");
		//eltDefaultButton.buttonWidth(120);
		runtimeComposite.attachWidget(eltDefaultButton);

		try {
			eltDefaultButton.attachListener(ListenerFactory.Listners.RUNTIME_BUTTON_CLICK.getListener(),
					propertyDialogButtonBar, new ListenerHelper(this.getClass()
							.getName(), this), eltDefaultButton
							.getSWTWidgetControl());

		} catch (Exception e1) {

			e1.printStackTrace();
		}

	}

	
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
