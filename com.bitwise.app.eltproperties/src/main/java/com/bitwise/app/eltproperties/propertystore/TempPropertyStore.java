package com.bitwise.app.eltproperties.propertystore;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.bitwise.app.eltproperties.property.Property;

public class TempPropertyStore {
	LinkedHashMap<String,ArrayList<Property>> properties;
	
	public TempPropertyStore(){
		properties=new LinkedHashMap<>();
		addInputComponentProperties();
	}
	
	public void addInputComponentProperties(){
		ArrayList<Property> inputComponentProperties = new ArrayList<>();
		
		Property path= new Property("String", "path", "TEXT");
		Property delimiter= new Property("String", "delimiter", "TEXT");
		Property safe= new Property("boolean", "safe", "RADIO");
		Property has_header= new Property("boolean", "has_header", "RADIO");
		Property charset= new Property("String", "charset", "TEXT");
		Property phase= new Property("String", "phase", "TEXT");
		
		inputComponentProperties.add(path);
		inputComponentProperties.add(delimiter);
		inputComponentProperties.add(safe);
		inputComponentProperties.add(has_header);
		inputComponentProperties.add(charset);
		inputComponentProperties.add(phase);
		properties.put("Input", inputComponentProperties);
	}
	
	public ArrayList<Property> getProperties(String componentName){
		return properties.get(componentName);
	}
}
