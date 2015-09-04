package com.bitwise.app.eltproperties.adapters;

import java.util.ArrayList;

import com.bitwise.app.eltproperties.property.Property;

public interface IPropertyTransformer {
	public ArrayList<Property> getProperties(String componentName);
}
