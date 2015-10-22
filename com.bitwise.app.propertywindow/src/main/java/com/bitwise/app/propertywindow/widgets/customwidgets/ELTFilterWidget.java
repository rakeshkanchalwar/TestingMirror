package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

import com.bitwise.app.propertywindow.property.ComponentConfigrationProperty;
import com.bitwise.app.propertywindow.property.ComponentMiscellaneousProperties;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.filterproperty.ELTFilterPropertyWizard;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultButton;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;

// TODO: Auto-generated Javadoc
/**
 * The Class ELTFilterWidget.
 * 
 * @author Bitwise
 */
public class ELTFilterWidget extends AbstractWidget {




	private LinkedHashSet<String> tempPropertyMap;
	private String propertyName;
	private List<String> propertyLst;
	private HashSet<String> set;
	private String componentName;

	/**
	 * Instantiates a new ELT filter widget.
	 * 
	 * @param componentConfigrationProperty
	 *            the component configration property
	 * @param componentMiscellaneousProperties
	 *            the component miscellaneous properties
	 * @param propertyDialogButtonBar
	 *            the property dialog button bar
	 */
	public ELTFilterWidget(
			ComponentConfigrationProperty componentConfigrationProperty,
			ComponentMiscellaneousProperties componentMiscellaneousProperties,
			PropertyDialogButtonBar propertyDialogButtonBar) {
		super(componentConfigrationProperty, componentMiscellaneousProperties,
				propertyDialogButtonBar);
		
		setProperties(componentConfigrationProperty.getPropertyName(), componentConfigrationProperty.getPropertyValue());
		validationStatus.setIsValid(true);
	}
	
	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {

		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite = new ELTDefaultSubgroupComposite(
				container.getContainerControl());
		eltSuDefaultSubgroupComposite.createContainerWidget();

		AbstractELTWidget eltDefaultLable = new ELTDefaultLable("Operation\n Fields");
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultLable);

		AbstractELTWidget eltDefaultButton = new ELTDefaultButton("Edit");
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultButton);
		Button button=(Button)eltDefaultButton.getSWTWidgetControl();
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ELTFilterPropertyWizard filterWizardObj=new ELTFilterPropertyWizard();
				
				if(getProperties().get(propertyName)==null){
					setProperties(propertyName, new HashSet<String>());
				}
					filterWizardObj.setRuntimePropertySet((HashSet<String>) getProperties().get(propertyName));
					setProperties(propertyName,filterWizardObj.launchRuntimeWindow());
			
			}
		});
	}
	private void setProperties(String propertyName, Object properties) {
		this.propertyName = propertyName;
		this.set = (HashSet<String>) properties;

	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		LinkedHashMap<String, Object> property=new LinkedHashMap<>();
		property.put(propertyName,this.set);
		return property;
	}

	/*@Override
	public void setComponentName(String componentName) {
			this.componentName=componentName;
	}
*/
}
