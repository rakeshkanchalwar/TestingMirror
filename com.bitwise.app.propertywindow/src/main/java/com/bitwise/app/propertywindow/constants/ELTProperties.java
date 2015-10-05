package com.bitwise.app.propertywindow.constants;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Oct 05, 2015
 * 
 */

public enum ELTProperties {
	NAME_PROPERTY("name");
	private String propertyName;
	
	private ELTProperties(String propertyName) {
		this.propertyName = propertyName;
	}
	
	public String propertyName(){
		return propertyName;
	}
}
