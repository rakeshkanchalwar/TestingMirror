package com.bitwise.app.propertywindow.property;

import java.util.LinkedHashMap;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 29, 2015
 * 
 */

public class ELTComponenetProperties {
	private LinkedHashMap<String, Object> componentConfigurationProperties;
	private LinkedHashMap<String, Object> componentMiscellaneousProperties;
	
	private ELTComponenetProperties(){
		
	}
	
	public ELTComponenetProperties(
			LinkedHashMap<String, Object> componentConfigurationProperties,
			LinkedHashMap<String, Object> componentMiscellaneousProperties) {
		super();
		this.componentConfigurationProperties = componentConfigurationProperties;
		this.componentMiscellaneousProperties = componentMiscellaneousProperties;
	}

	public LinkedHashMap<String, Object> getComponentConfigurationProperties() {
		return componentConfigurationProperties;
	}

	public Object getComponentConfigurationProperty(String propertyName) {
		return componentConfigurationProperties.get(propertyName);
	}
	
	public void setComponentConfigurationProperties(
			LinkedHashMap<String, Object> componentConfigurationProperties) {
		this.componentConfigurationProperties = componentConfigurationProperties;
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
}
