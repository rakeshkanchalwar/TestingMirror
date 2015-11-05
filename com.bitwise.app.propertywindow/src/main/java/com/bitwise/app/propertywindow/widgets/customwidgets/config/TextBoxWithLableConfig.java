package com.bitwise.app.propertywindow.widgets.customwidgets.config;

import java.util.ArrayList;
import java.util.List;

import com.bitwise.app.propertywindow.factory.ListenerFactory.Listners;

public class TextBoxWithLableConfig implements WidgetConfig {
	private String name;
	private List<Listners> listeners = new ArrayList<>();
	private boolean grabExcessSpace = false;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Listners> getListeners() {
		return listeners;
	}
	public void setListeners(List<Listners> listeners) {
		this.listeners = listeners;
	}
	public boolean getGrabExcessSpace() {
		return grabExcessSpace;
	}
	public void setGrabExcessSpace(boolean grabExcessSpace) {
		this.grabExcessSpace = grabExcessSpace;
	}
}
