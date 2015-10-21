package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import com.bitwise.app.propertywindow.property.ComponentConfigrationProperty;
import com.bitwise.app.propertywindow.property.ComponentMiscellaneousProperties;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;

/**
 * 
 * @author Shrirang S. Kumbhar 
 * Sep 08, 2015
 * 
 */

public abstract class AbstractWidget {
	protected ComponentConfigrationProperty componentConfigrationProperty;
	protected ComponentMiscellaneousProperties componentMiscellaneousProperties;
	protected PropertyDialogButtonBar propertyDialogButtonBar;
	protected ValidationStatus validationStatus = new ValidationStatus();
	
	public AbstractWidget(
			ComponentConfigrationProperty componentConfigrationProperty,
			ComponentMiscellaneousProperties componentMiscellaneousProperties,
			PropertyDialogButtonBar propertyDialogButtonBar) {
		
		this.componentConfigrationProperty = componentConfigrationProperty;
		this.componentMiscellaneousProperties = componentMiscellaneousProperties;
		this.propertyDialogButtonBar = propertyDialogButtonBar;
	}

	public abstract void attachToPropertySubGroup(AbstractELTContainerWidget subGroup);

	public abstract LinkedHashMap<String, Object> getProperties();
	
	/**
	 * Indicates if all the mandatory data is available and valid in the property window. 
	 */
	public class ValidationStatus{
		private Boolean isValid = false;
		
		public void setIsValid(Boolean isValid) {
			this.isValid = isValid;
		}
		
		public boolean getIsValid() {
			return isValid;
		}
	}
	
	public boolean isDataValid() {
		return validationStatus.isValid;
	}
}
