package com.bitwise.app.eltproperties.testdata;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.LinkedBlockingDeque;


/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 08, 2015
 * 
 */

public class ComponentModel {
	
	private LinkedHashMap<String, Object> getInputComponenetProperties(){
		LinkedHashMap<String,Object> inputComponenetProperties = new LinkedHashMap<>();
		inputComponenetProperties.put("path", new Object());
		inputComponenetProperties.put("delimiter", new Object());
		inputComponenetProperties.put("charset", new Object());
		inputComponenetProperties.put("phase", new Object());
		inputComponenetProperties.put("safe", new Object());
		inputComponenetProperties.put("has_header", new Object());		
		inputComponenetProperties.put("Schema", new Object());
		inputComponenetProperties.put("RuntimeProps", new Object());
		inputComponenetProperties.put("name", new Object());
		
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
