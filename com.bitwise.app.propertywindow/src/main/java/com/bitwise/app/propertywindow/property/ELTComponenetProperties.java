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
	private LinkedHashMap<String, Object> ComponentMiscellaneousProperties;
	
	private ELTComponenetProperties(){
		
	}
	
	public ELTComponenetProperties(
			LinkedHashMap<String, Object> componentConfigurationProperties,
			LinkedHashMap<String, Object> componentMiscellaneousProperties) {
		super();
		this.componentConfigurationProperties = componentConfigurationProperties;
		this.ComponentMiscellaneousProperties = componentMiscellaneousProperties;
	}

	public LinkedHashMap<String, Object> getComponentConfigurationProperties() {
		return componentConfigurationProperties;
	}

	public void setComponentConfigurationProperties(
			LinkedHashMap<String, Object> componentConfigurationProperties) {
		this.componentConfigurationProperties = componentConfigurationProperties;
	}

	public LinkedHashMap<String, Object> getComponentMiscellaneousProperties() {
		return ComponentMiscellaneousProperties;
	}

	public void setComponentMiscellaneousProperties(
			LinkedHashMap<String, Object> componentMiscellaneousProperties) {
		ComponentMiscellaneousProperties = componentMiscellaneousProperties;
	}
}
