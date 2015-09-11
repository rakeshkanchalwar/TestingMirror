package com.bitwise.app.eltproperties.widgets;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;

/**
 * 
 * @author Shrirang S. Kumbhar Sep 08, 2015
 * 
 */

public abstract class AbstractELTWidget {

	private Button okButton;
	protected ArrayList<String> names = new ArrayList<>();

	public void setOkButton(Button okButton) {

		this.okButton = okButton;

	}

	protected void toggleOkButton(boolean status) {
		if (this.okButton != null) {
			okButton.setEnabled(status);
		}
	}

	public abstract void attachToPropertySubGroup(Group subGroup);

	public abstract void setProperties(String propertyName, Object properties);

	public abstract LinkedHashMap<String, Object> getProperties();

	public abstract void setComponentName(String componentName);

	public ArrayList<String> getNames() {
		return names;
	}

	public void setNames(ArrayList<String> names) {
		this.names = names;
	}

}
