package com.bitwise.app.propertywindow.property;

import java.util.LinkedHashMap;

public class ComponentConfigrationProperty {
	private String propertyName;
	private Object propertyValue;
		
	private ComponentConfigrationProperty(){
		
	}
	
	public ComponentConfigrationProperty(String propertyName, Object propertyValue) {
		super();
		this.propertyName = propertyName;
		this.propertyValue = propertyValue;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public Object getPropertyValue() {
		return propertyValue;
	}
	
	
}
