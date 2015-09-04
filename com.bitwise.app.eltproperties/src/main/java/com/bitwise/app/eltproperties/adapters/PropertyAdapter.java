package com.bitwise.app.eltproperties.adapters;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.bitwise.app.eltproperties.property.Property;
import com.bitwise.app.eltproperties.propertystore.TempPropertyStore;

public class PropertyAdapter implements IPropertyTransformer{

	ArrayList<Property> properties;
	ArrayList<Property> rawProperties;
	
	public PropertyAdapter(){
		TempPropertyStore  tempPropertyStore = new TempPropertyStore();
		rawProperties = tempPropertyStore.getProperties("Input");
	}
	
	public void transform() {
		properties = rawProperties;
	}

	@Override
	public ArrayList<Property> getProperties(String componentName) {
		// TODO Auto-generated method stub
		return properties;
	}
	
}
