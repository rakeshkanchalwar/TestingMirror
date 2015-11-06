package com.bitwise.app.common.datastructure.property;

import java.util.List;

public class TransformOperation {
	private List<OperationField> inputFields;
	private List<OperationField> outputFields;
	private OperationClassProperty opClassProperty;
	private List<NameValueProperty> nameValueProps;
	
	public List<NameValueProperty> getNameValueProps() {
		return nameValueProps;
	}
	public void setNameValueProps(List<NameValueProperty> nameValueProps) {
		this.nameValueProps = nameValueProps;
	}
	public List<OperationField> getInputFields() {
		return inputFields;
	}
	public void setInputFields(List<OperationField> inputFields) {
		this.inputFields = inputFields;
	}
	public List<OperationField> getOutputFields() {
		return outputFields;
	}
	public void setOutputFields(List<OperationField> outputFields) {
		this.outputFields = outputFields;
	}
	public OperationClassProperty getOpClassProperty() {
		return opClassProperty;
	}
	public void setOpClassProperty(OperationClassProperty opClassProperty) {
		this.opClassProperty = opClassProperty;
	}
	
	
}
