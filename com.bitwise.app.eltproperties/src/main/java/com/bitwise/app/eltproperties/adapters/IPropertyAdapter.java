package com.bitwise.app.eltproperties.adapters;

import java.util.ArrayList;

import com.bitwise.app.eltproperties.exceptions.EmptyComponentPropertiesException;
import com.bitwise.app.eltproperties.property.Property;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 04, 2015
 * 
 */

public interface IPropertyAdapter {
	public void transform() throws EmptyComponentPropertiesException;
	public ArrayList<Property> getProperties() throws EmptyComponentPropertiesException;
}
