package com.bitwise.app.common.datastructure.property;

public class NameValueProperty extends PropertyField{

	public NameValueProperty(boolean isOpInputField, boolean isPropInner) {
		super(isOpInputField, isPropInner);
	}

	private String propertyName;
	private String propertyValue;

	
	
	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	
	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NameValueProperty [propertyName=");
		builder.append(propertyName);
		builder.append(", propertyValue=");
		builder.append(propertyValue);
		builder.append("]");
		return builder.toString();
	}

}