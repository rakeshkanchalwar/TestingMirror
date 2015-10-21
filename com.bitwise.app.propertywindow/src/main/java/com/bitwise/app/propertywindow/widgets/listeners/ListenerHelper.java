package com.bitwise.app.propertywindow.widgets.listeners;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 24, 2015
 * 
 */

public class ListenerHelper {
	public enum HelperType{
		CONTROL_DECORATION,
		VALIDATION_STATUS,
		SCHEMA_GRID;
	}
	
	private Map<HelperType, Object> helpers;
	
	public ListenerHelper() {
		this.helpers = new HashMap<>();
	}
	
	public void put(HelperType key, Object value){
		helpers.put(key, value);
	}
	
	public Object get(HelperType key){
		return helpers.get(key);
	}
	
	
	//TODO : remove this code once all of its references are remove
	String type;
	Object object;
	
	public ListenerHelper(String type, Object object) {
		super();
		this.type = type;
		this.object = object;
	}
	public String getType() {
		return type;
	}
	public Object getObject() {
		return object;
	}
}
