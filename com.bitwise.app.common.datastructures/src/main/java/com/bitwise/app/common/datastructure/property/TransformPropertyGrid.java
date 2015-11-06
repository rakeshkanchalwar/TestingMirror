package com.bitwise.app.common.datastructure.property;

import java.util.List;

public class TransformPropertyGrid {
	private List<String> passThrough;
	private List<NameValueProperty> nameValueProps;
	private List<OperationField> outputTreeFields;
	private List<TransformOperation> operation;
	
	public List<NameValueProperty> getNameValueProps() {
		return nameValueProps;
	}

	public void setNameValueProps(List<NameValueProperty> nameValueProps) {
		this.nameValueProps = nameValueProps;
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

	public List<String> getPassThrough() {
		return passThrough;
	}

	public void setPassThrough(List<String> passThrough) {
		this.passThrough = passThrough;
	}

}
