package com.bitwise.app.common.datastructure.property;

public class OperationField extends PropertyField{
	
	public OperationField(boolean isOpInputField, boolean isPropInner) {
		super(isOpInputField, isPropInner);
		// TODO Auto-generated constructor stub
	}

	private String operationField;

	public String getOperationField() {
		return operationField;
	}

	public void setOperationField(String operationField) {
		this.operationField = operationField;
	}


}
