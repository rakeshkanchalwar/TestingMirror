package com.bitwise.app.common.datastructure.property;

public class PropertyField {
	private boolean isOpInputField;
	private boolean isPropInner;
	public PropertyField(boolean isOpInputField, boolean isPropInner) {
		super();
		this.isOpInputField = isOpInputField;
		this.isPropInner = isPropInner;
	}
	public boolean isOpInputField() {
		return isOpInputField;
	}
	public void setOpInputField(boolean isOpInputField) {
		this.isOpInputField = isOpInputField;
	}
	public boolean isPropInner() {
		return isPropInner;
	}
	public void setPropInner(boolean isPropInner) {
		this.isPropInner = isPropInner;
	}
	

}
