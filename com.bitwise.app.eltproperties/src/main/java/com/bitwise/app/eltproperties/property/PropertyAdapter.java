package com.bitwise.app.eltproperties.property;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 03, 2015
 * 
 */

public class PropertyAdapter {
	LinkedHashMap<String,ArrayList<Property>> properties;
	
	public ArrayList<Property> getProperties(String componentName){
		return properties.get(componentName);
	}
}
