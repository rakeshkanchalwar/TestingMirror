package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;

/**
 * 
 * @author Shrirang S. Kumbhar Sep 08, 2015
 * 
 */

public abstract class AbstractWidget {

	protected PropertyDialogButtonBar propertyDialogButtonBar;

	protected ArrayList<String> names = new ArrayList<>();

	public abstract void attachToPropertySubGroup(AbstractELTContainerWidget subGroup);

	public abstract void setProperties(String propertyName, Object properties);

	public abstract LinkedHashMap<String, Object> getProperties();

	public abstract void setComponentName(String componentName);

	public ArrayList<String> getNames() {
		return names;
	}

	public void setNames(ArrayList<String> names) {
		this.names = names;
	}

	public void setpropertyDialogButtonBar(PropertyDialogButtonBar propertyDialogButtonBar){
		this.propertyDialogButtonBar = propertyDialogButtonBar;
	}
}
