package com.bitwise.app.eltproperties.testdata;

import java.util.LinkedHashMap;


/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 08, 2015
 * 
 */

public class ComponentModel {
	
	private LinkedHashMap<String, Object> getInputComponenetProperties(){
		LinkedHashMap<String,Object> inputComponenetProperties = new LinkedHashMap<>();
		inputComponenetProperties.put("path", null);
		inputComponenetProperties.put("delimiter", null);
		inputComponenetProperties.put("charset", null);
		inputComponenetProperties.put("phase", null);
		inputComponenetProperties.put("safe", null);
		inputComponenetProperties.put("has_header", null);		
		inputComponenetProperties.put("Schema", null);
		inputComponenetProperties.put("RuntimeProps", null);
		inputComponenetProperties.put("name", null);
		
		return inputComponenetProperties;
	}
	
	public LinkedHashMap<String,Object> getProperties(String componentName){
		if(componentName.equals("Input")){
			return getInputComponenetProperties();
		}else{
			return null;
		}
	}
}
