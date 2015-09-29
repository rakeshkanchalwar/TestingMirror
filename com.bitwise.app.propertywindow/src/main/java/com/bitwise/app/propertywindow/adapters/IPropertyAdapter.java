package com.bitwise.app.propertywindow.adapters;

import java.util.ArrayList;

import com.bitwise.app.propertywindow.exceptions.EmptyComponentPropertiesException;
import com.bitwise.app.propertywindow.property.Property;

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
