package com.bitwise.app.propertywindow.constants;

// TODO: Auto-generated Javadoc
/**
 * 
 * @author Bitwise
 * Oct 05, 2015
 * 
 */

public enum ELTProperties {
	NAME_PROPERTY("name");
	private String propertyName;
	
	private ELTProperties(String propertyName) {
		this.propertyName = propertyName;
	}
	
	/**
	 * Property name.
	 * 
	 * @return the string
	 */
	public String propertyName(){
		return propertyName;
	}
}
