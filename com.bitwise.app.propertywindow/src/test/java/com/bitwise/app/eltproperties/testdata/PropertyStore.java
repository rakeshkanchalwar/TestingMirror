package com.bitwise.app.eltproperties.testdata;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.bitwise.app.propertywindow.property.Property;

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
		
		/*//--------------------
		
		Property name= new Property("String", "name", "ELT_PHASE_WIDGET");
		name.group("TextProperties");
		
		Property path= new Property("String", "path", "ELT_FILE_PATH_WIDGET");
		path.group("TextProperties");
		
		Property delimiter= new Property("String", "delimiter", "ELT_DELIMETER_WIDGET");
		delimiter.group("TextProperties");
		
		//------
		
		Property charset= new Property("String", "charset", "ELT_CHARACTER_SET_WIDGET");
		charset.group("TextProperties");
		charset.subGroup("Opetional");
				
		Property phase= new Property("String", "phase", "ELT_PHASE_WIDGET");
		phase.group("TextProperties");
		phase.subGroup("Opetional");
		
		//--------------------
		
		Property safe= new Property("boolean", "safe", "ELT_SAFE_PROPERTY_WIDGET");
		safe.group("RadioProperties");
		safe.subGroup("safe");
		
		Property has_header= new Property("boolean", "has_header", "ELT_HAS_HEADER_WIDGET");
		has_header.group("RadioProperties");
		has_header.subGroup("header");
		//--------------------
		
				//--------------------
		
				Property schema= new Property("boolean", "Schema", "ELT_SCHEMA_WIDGET");
				schema.group("Schema");
				
				Property runtimeProps= new Property("boolean", "RuntimeProps", "ELT_RUNTIME_PROPERTIES_WIDGET");
				runtimeProps.group("RuntimeProps");
				//--------------------
*/		
		
		
		
		//--------------------
		
				Property name= new Property("String", "name", "ELT_COMPONENT_NAME_WIDGET");
				name.group("TEXT_PROPERTIES");
				
				Property path= new Property("String", "path", "ELT_PHASE_WIDGET");
				path.group("TEXT_PROPERTIES");
				
				Property delimiter= new Property("String", "delimiter", "ELT_CHARACTER_SET_WIDGET");
				delimiter.group("TEXT_PROPERTIES");
				
				//------
				
				Property charset= new Property("String", "charset", "AAA");
				charset.group("TEXT_PROPERTIES");
				charset.subGroup("OPTIONAL_PROPERTIES");
						
				Property phase= new Property("String", "phase", "AAA");
				phase.group("TEXT_PROPERTIES");
				phase.subGroup("OPTIONAL_PROPERTIES");
				
				//--------------------
				
				Property safe= new Property("boolean", "safe", "ELT_SAFE_PROPERTY_WIDGET");
				safe.group("RADIO_PROPERTIES");
				safe.subGroup("safe");
				
				Property has_header= new Property("boolean", "has_header", "ELT_HAS_HEADER_WIDGET");
				has_header.group("RADIO_PROPERTIES");
				has_header.subGroup("header");
				//--------------------
				
				//--------------------
		
				Property schema= new Property("boolean", "Schema", "ELT_SCHEMA_WIDGET");
				schema.group("Schema");
				
				Property runtimeProps= new Property("boolean", "RuntimeProps", "AAA");
				runtimeProps.group("RUNTIME_PROP");
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
