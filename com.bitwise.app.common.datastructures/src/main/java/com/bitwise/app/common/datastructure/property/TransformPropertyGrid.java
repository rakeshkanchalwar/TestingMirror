package com.bitwise.app.common.datastructure.property;

import java.util.List;

public class TransformPropertyGrid {
	private List<OperationSystemProperties> opSysProperties;
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

	public List<OperationSystemProperties> getOpSysProperties() {
		return opSysProperties;
	}

	public void setOpSysProperties(List<OperationSystemProperties> opSysProperties) {
		this.opSysProperties = opSysProperties;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TransformPropertyGrid [opSysProperties=");
		builder.append(opSysProperties);
		builder.append(", nameValueProps=");
		builder.append(nameValueProps);
		builder.append(", outputTreeFields=");
		builder.append(outputTreeFields);
		builder.append(", operation=");
		builder.append(operation);
		builder.append("]");
		return builder.toString();
	}

	
}
