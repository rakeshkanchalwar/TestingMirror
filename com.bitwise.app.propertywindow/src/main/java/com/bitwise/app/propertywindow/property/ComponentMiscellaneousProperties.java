package com.bitwise.app.propertywindow.property;

import java.util.LinkedHashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class ComponentMiscellaneousProperties.
 * 
 * @author Bitwise
 */
public class ComponentMiscellaneousProperties {
	private LinkedHashMap<String, Object> componentMiscellaneousProperties;

	/**
	 * Instantiates a new component miscellaneous properties.
	 * 
	 * @param componentMiscellaneousProperties
	 *            the component miscellaneous properties
	 */
	public ComponentMiscellaneousProperties(
			LinkedHashMap<String, Object> componentMiscellaneousProperties) {
		super();
		this.componentMiscellaneousProperties = componentMiscellaneousProperties;
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
	
	/**
	 * Sets the component miscellaneous property.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param propertyValue
	 *            the property value
	 */
	public void setComponentMiscellaneousProperty(String propertyName,Object propertyValue) {
		this.componentMiscellaneousProperties.put(propertyName, propertyValue);
	}
}
