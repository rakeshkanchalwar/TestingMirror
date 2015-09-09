package com.bitwise.app.eltproperties.widgets;

import java.util.LinkedHashMap;

import org.eclipse.swt.widgets.Group;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 08, 2015
 * 
 */

public interface IELTWidget {

	public void attachToPropertySubGroup(Group subGroup);
	
	public void setProperties(String propertyName,Object properties);
	
	public LinkedHashMap<String,Object> getProperties();
	
	public void setComponentName(String componentName);	
}
