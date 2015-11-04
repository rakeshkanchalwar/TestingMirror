package com.bitwise.app.propertywindow.widgets.customwidgets.config;

public class SingleColumnGridConfig implements WidgetConfig {
	String componentName;
	String labelName;
	
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String columnName) {
		this.labelName = columnName;
	}
}
