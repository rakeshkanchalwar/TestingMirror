package com.bitwise.app.propertywindow.widgets.customwidgets.config;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.bitwise.app.propertywindow.factory.ListenerFactory.Listners;

public class DropDownConfig implements WidgetConfig {
	private String name;
	private List<String> items = new LinkedList<>();
	private List<Listners> textBoxListeners = new ArrayList<>();
	private List<Listners> dropDownListeners = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getItems() {
		return items;
	}
	
	public void setItems(List<String> items) {
		this.items = items;
	}
	public List<Listners> getTextBoxListeners() {
		return textBoxListeners;
	}
	public void setTextBoxListeners(List<Listners> textBoxListeners) {
		this.textBoxListeners = textBoxListeners;
	}
	public List<Listners> getDropDownListeners() {
		return dropDownListeners;
	}
	public void setDropDownListeners(List<Listners> dropDownListeners) {
		this.dropDownListeners = dropDownListeners;
	}
}
