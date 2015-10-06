package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;

/**
 * 
 * @author Shrirang S. Kumbhar 
 * Sep 08, 2015
 * 
 */

public abstract class AbstractWidget {

	protected PropertyDialogButtonBar propertyDialogButtonBar;

	protected LinkedHashMap<String, Object> componentMiscellaneousProperties;

	public void setComponentMiscellaneousProperties(
			LinkedHashMap<String, Object> componentMiscellaneousProperties) {
		this.componentMiscellaneousProperties = componentMiscellaneousProperties;
	}

	public abstract void attachToPropertySubGroup(AbstractELTContainerWidget subGroup);

	public abstract void setProperties(String propertyName, Object properties);

	public abstract LinkedHashMap<String, Object> getProperties();

	public void setpropertyDialogButtonBar(PropertyDialogButtonBar propertyDialogButtonBar){
		this.propertyDialogButtonBar = propertyDialogButtonBar;
	}
}
