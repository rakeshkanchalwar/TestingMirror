package com.bitwise.app.eltproperties.widgets;

import org.eclipse.swt.widgets.Group;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 08, 2015
 * 
 */

public interface IELTWidget {

	public void attachToPropertySubGroup(Group subGroup);
	
	public void setProperties(Object properties);
	
	public Object getProperties();
}
