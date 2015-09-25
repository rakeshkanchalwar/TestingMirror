package com.bitwise.app.propertywindow.widgets.listeners;

public class ListenerHelper {
	String type;
	Object object;
	
	private ListenerHelper(){
		
	}
	
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
