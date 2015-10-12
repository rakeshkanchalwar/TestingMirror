package com.bitwise.app.propertywindow.property;

import java.util.LinkedHashMap;

public class ComponentMiscellaneousProperties {
	private LinkedHashMap<String, Object> componentMiscellaneousProperties;

	public ComponentMiscellaneousProperties(
			LinkedHashMap<String, Object> componentMiscellaneousProperties) {
		super();
		this.componentMiscellaneousProperties = componentMiscellaneousProperties;
	}
	
	public LinkedHashMap<String, Object> getComponentMiscellaneousProperties() {
		return componentMiscellaneousProperties;
	}
	
	public Object getComponentMiscellaneousProperty(String propertyName) {
		return componentMiscellaneousProperties.get(propertyName);
	}

	public void setComponentMiscellaneousProperties(
			LinkedHashMap<String, Object> componentMiscellaneousProperties) {
		this.componentMiscellaneousProperties = componentMiscellaneousProperties;
	}
	
	public void setComponentMiscellaneousProperty(String propertyName,Object propertyValue) {
		this.componentMiscellaneousProperties.put(propertyName, propertyValue);
	}
}
