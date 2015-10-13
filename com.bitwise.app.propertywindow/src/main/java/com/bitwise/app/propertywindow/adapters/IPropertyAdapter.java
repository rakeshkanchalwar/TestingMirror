package com.bitwise.app.propertywindow.adapters;

import java.util.ArrayList;
import com.bitwise.app.propertywindow.property.Property;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 04, 2015
 * 
 */

public interface IPropertyAdapter {
	public void transform();
	public ArrayList<Property> getProperties();
}
