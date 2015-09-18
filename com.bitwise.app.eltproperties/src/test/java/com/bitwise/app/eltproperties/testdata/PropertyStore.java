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
		
		Property name= new Property("String", "name", "ELTComponentNameWidget");
		name.group("TextProperties");
		
		Property path= new Property("String", "path", "ELTFilePathWidget");
		path.group("TextProperties");
		
		Property delimiter= new Property("String", "delimiter", "ELTDelimeterWidget");
		delimiter.group("TextProperties");
		
		//------
		
		Property charset= new Property("String", "charset", "ELTCharacterSetWidget");
		charset.group("TextProperties");
		charset.subGroup("Opetional");
				
		Property phase= new Property("String", "phase", "ELTPhaseWidget");
		phase.group("TextProperties");
		phase.subGroup("Opetional");
		
		//--------------------
		
		Property safe= new Property("boolean", "safe", "ELTSafePropertyWidget");
		safe.group("RadioProperties");
		safe.subGroup("safe");
		
		Property has_header= new Property("boolean", "has_header", "ELTHasHeaderWidget");
		has_header.group("RadioProperties");
		has_header.subGroup("header");
		//--------------------
		
				//--------------------
		
				Property schema= new Property("boolean", "Schema", "ELTSchemaWidget");
				schema.group("Schema");
				
				Property runtimeProps= new Property("boolean", "RuntimeProps", "ELTRuntimePropertiesWidget");
				runtimeProps.group("RuntimeProps");
				//--------------------
		
		inputComponentProperties.add(name);
		inputComponentProperties.add(path);
		inputComponentProperties.add(delimiter);
		inputComponentProperties.add(safe);
		inputComponentProperties.add(has_header);
		inputComponentProperties.add(charset);
		inputComponentProperties.add(phase);
		inputComponentProperties.add(schema);
		inputComponentProperties.add(runtimeProps);
		
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
