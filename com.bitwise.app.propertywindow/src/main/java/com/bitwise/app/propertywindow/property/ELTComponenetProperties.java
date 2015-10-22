package com.bitwise.app.propertywindow.property;

import java.util.LinkedHashMap;

// TODO: Auto-generated Javadoc
/**
 * 
 * @author Bitwise
 * Sep 29, 2015
 * 
 */

public class ELTComponenetProperties {
	private LinkedHashMap<String, Object> componentConfigurationProperties;
	private LinkedHashMap<String, Object> componentMiscellaneousProperties;
	
	private ELTComponenetProperties(){
		
	}
	
	/**
	 * Instantiates a new ELT componenet properties.
	 * 
	 * @param componentConfigurationProperties
	 *            the component configuration properties
	 * @param componentMiscellaneousProperties
	 *            the component miscellaneous properties
	 */
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

	/**
	 * Gets the component configuration property.
	 * 
	 * @param propertyName
	 *            the property name
	 * @return the component configuration property
	 */
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
	
	/**
	 * Gets the component miscellaneous property.
	 * 
	 * @param propertyName
	 *            the property name
	 * @return the component miscellaneous property
	 */
	public Object getComponentMiscellaneousProperty(String propertyName) {
		return componentMiscellaneousProperties.get(propertyName);
	}

	public void setComponentMiscellaneousProperties(
			LinkedHashMap<String, Object> componentMiscellaneousProperties) {
		this.componentMiscellaneousProperties = componentMiscellaneousProperties;
	}
}
