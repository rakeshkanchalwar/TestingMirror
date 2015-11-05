package com.bitwise.app.common.datastructure.property;

import java.util.List;

public class TransformPropertyGrid {
	private NameValueProperty nameValueProperty;
	private List<OperationField> outputTreeFields;
	private List<TransformOperation> operation;

	public NameValueProperty getNameValueProperty() {
		return nameValueProperty;
	}

	public void setNameValueProperty(NameValueProperty nameValueProperty) {
		this.nameValueProperty = nameValueProperty;
	}

	public List<OperationField> getOutputTreeFields() {
		return outputTreeFields;
	}

	public void setOutputTreeFields(List<OperationField> outputTreeFields) {
		this.outputTreeFields = outputTreeFields;
	}

	public List<TransformOperation> getOperation() {
		return operation;
	}

	public void setOperation(List<TransformOperation> operation) {
		this.operation = operation;
	}

}
