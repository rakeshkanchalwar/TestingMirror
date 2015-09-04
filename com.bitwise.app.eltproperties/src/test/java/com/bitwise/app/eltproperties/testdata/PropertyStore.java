package com.bitwise.app.eltproperties.testdata;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.bitwise.app.eltproperties.property.Property;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 04, 2015
 * 
 */

public class PropertyStore {
	LinkedHashMap<String,ArrayList<Property>> properties;
	
	public PropertyStore(){
		properties=new LinkedHashMap<>();
		addInputComponentProperties();
	}
	
	public void addInputComponentProperties(){
		ArrayList<Property> inputComponentProperties = new ArrayList<>();
		
		//--------------------
		
		Property path= new Property("String", "path", "TEXT");
		path.group("TextProperties");
		
		Property delimiter= new Property("String", "delimiter", "TEXT");
		delimiter.group("TextProperties");
		
		//------
		
		Property charset= new Property("String", "charset", "TEXT");
		charset.group("TextProperties");
		charset.subGroup("Opetional");
				
		Property phase= new Property("String", "phase", "TEXT");
		phase.group("TextProperties");
		phase.subGroup("Opetional");
		
		//--------------------
		
		Property safe= new Property("boolean", "safe", "RADIO");
		safe.group("RadioProperties");
		safe.subGroup("safe");
		
		Property has_header= new Property("boolean", "has_header", "RADIO");
		has_header.group("RadioProperties");
		has_header.subGroup("header");
		//--------------------
		
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

	@Override
	public String toString() {
		return "PropertyStore [properties=" + properties + "]";
	}
	
	
}
