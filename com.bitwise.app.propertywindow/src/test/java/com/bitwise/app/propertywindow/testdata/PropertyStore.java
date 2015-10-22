package com.bitwise.app.propertywindow.testdata;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.bitwise.app.propertywindow.property.Property;

// TODO: Auto-generated Javadoc
/**
 * 
 * @author Bitwise
 * Sep 04, 2015
 * 
 */

public class PropertyStore {
	LinkedHashMap<String,ArrayList<Property>> properties;
	
	/**
	 * Instantiates a new property store.
	 */
	public PropertyStore(){
		properties=new LinkedHashMap<>();
		addInputComponentProperties();
	}
	
	/**
	 * Adds the input component properties.
	 */
	public void addInputComponentProperties(){
		ArrayList<Property> inputComponentProperties = new ArrayList<>();
		
		
		//--------------------
		
				Property name= new Property.Builder("String", "name", "ELT_COMPONENT_NAME_WIDGET")
				.group("TEXT_PROPERTIES").build();
				
				Property path= new Property.Builder("String", "path", "ELT_FILE_PATH_WIDGET")
				.group("TEXT_PROPERTIES").build();
				
				Property delimiter= new Property.Builder("String", "strict", "ELT_STRICT_CLASS_WIDGET")
				.group("TEXT_PROPERTIES").build();
				
				//------
				
				Property charset= new Property.Builder("String", "charset", "ELT_CHARACTER_SET_WIDGET")
				.group("TEXT_PROPERTIES")
				.subGroup("AAAA").build();
						
				Property phase= new Property.Builder("String", "phase", "ELT_PHASE_WIDGET")
				.group("TEXT_PROPERTIES")
				.subGroup("AAAA").build();
				
				//--------------------
				
				Property safe= new Property.Builder("boolean", "safe", "ELT_SAFE_PROPERTY_WIDGET")
				.group("RADIO_PROPERTIES")
				.subGroup("safe").build();
				
				Property has_header= new Property.Builder("boolean", "has_header", "ELT_HAS_HEADER_WIDGET")
				.group("RADIO_PROPERTIES")
				.subGroup("header").build();
				//--------------------
				
				//--------------------
		
				Property schema= new Property.Builder("boolean", "Schema", "ELT_SCHEMA_WIDGET")
				.group("Schema").build();
				
				Property runtimeProps= new Property.Builder("boolean", "RuntimeProps", "ELT_RUNTIME_PROPERTIES_WIDGET")
				.group("RUNTIME_PROP").build();
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
	
	/**
	 * Gets the properties.
	 * 
	 * @param componentName
	 *            the component name
	 * @return the properties
	 */
	public ArrayList<Property> getProperties(String componentName){
		return properties.get(componentName);
	}

	@Override
	public String toString() {
		return "PropertyStore [properties=" + properties + "]";
	}
	
	
}
