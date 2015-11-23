package com.bitwise.app.engine.converter.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;

import com.bitwise.app.common.datastructure.property.NameValueProperty;
import com.bitwise.app.common.datastructure.property.OperationField;
import com.bitwise.app.common.datastructure.property.OperationSystemProperties;
import com.bitwise.app.common.datastructure.property.TransformOperation;
import com.bitwise.app.common.datastructure.property.TransformPropertyGrid;
import com.bitwise.app.common.util.Constants;
import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.converter.PortTypeConstant;
import com.bitwise.app.engine.converter.TransformConverter;
import com.bitwise.app.engine.exceptions.PhaseException;
import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;
import com.bitwiseglobal.graph.aggregate.TypePrimaryKeyFields;
import com.bitwiseglobal.graph.aggregate.TypeSecondaryKeyFields;
import com.bitwiseglobal.graph.aggregate.TypeSecondayKeyFieldsAttributes;
import com.bitwiseglobal.graph.commontypes.TypeBaseField;
import com.bitwiseglobal.graph.commontypes.TypeBaseInSocket;
import com.bitwiseglobal.graph.commontypes.TypeFieldName;
import com.bitwiseglobal.graph.commontypes.TypeInputField;
import com.bitwiseglobal.graph.commontypes.TypeMapField;
import com.bitwiseglobal.graph.commontypes.TypeOperationField;
import com.bitwiseglobal.graph.commontypes.TypeOperationInputFields;
import com.bitwiseglobal.graph.commontypes.TypeOperationOutputFields;
import com.bitwiseglobal.graph.commontypes.TypeOperationsOutSocket;
import com.bitwiseglobal.graph.commontypes.TypeOutSocketAsInSocket;
import com.bitwiseglobal.graph.commontypes.TypeProperties;
import com.bitwiseglobal.graph.commontypes.TypeProperties.Property;
import com.bitwiseglobal.graph.commontypes.TypeSortOrder;
import com.bitwiseglobal.graph.commontypes.TypeTransformOperation;
import com.bitwiseglobal.graph.operationstypes.Aggregate;

public class AggregateConverter extends TransformConverter {
	Logger logger = LogFactory.INSTANCE.getLogger(AggregateConverter.class);
	TransformPropertyGrid transformPropertyGrid;
	
	public AggregateConverter(Component component){
		super();	
		this.baseComponent = new Aggregate();
		this.component = component;
		this.properties = component.getProperties();
		transformPropertyGrid = (TransformPropertyGrid) properties.get("operation");
	}
	
	@Override
	public void prepareForXML() throws PhaseException, SchemaException {
		logger.debug("Generating XML for :{}", properties.get(NAME));
		super.prepareForXML();
		
		Aggregate aggregate = (Aggregate) baseComponent;
		setPrimaryKeys(aggregate);
		setSecondaryKeys(aggregate);
	}
	
	@Override
	protected List<TypeTransformOperation> getOperations() {
		logger.debug("Generating TypeTransformOperation data :{}", properties.get(NAME));
		if(transformPropertyGrid != null){
			List<TransformOperation> transformOperations = transformPropertyGrid.getOperation();
			List<TypeTransformOperation> operationList = new ArrayList<>();
			for (TransformOperation transformOperation : transformOperations) {
				operationList.add(getOperation(transformOperation));
			}
			return operationList;
		}
		else{
			return new ArrayList<>();
		}
	}
	
	private TypeTransformOperation getOperation(TransformOperation transformOperation){
			TypeTransformOperation operation = new TypeTransformOperation();
			operation.setInputFields(getOperationInputFields(transformOperation.getInputFields()));
			operation.setOutputFields(getOperationOutputFields(transformOperation.getOutputFields()));
			operation.setProperties(getProperties(transformOperation.getNameValueProps()));
			operation.setId(""/*FILTER_OPERATION_ID*/);
			operation.setClazz(transformOperation.getOpClassProperty().getOperationClassPath());
			return operation;
	}
	
	private TypeOperationInputFields getOperationInputFields(List<OperationField> operationFields) {
		TypeOperationInputFields inputFields = null;
		if(operationFields != null && !operationFields.isEmpty()){
			inputFields = new TypeOperationInputFields();
			for (OperationField operationField : operationFields) {
				 TypeInputField typeInputField = new TypeInputField();
				 typeInputField.setInSocketId(DEFAULT_IN_SOCKET_ID);
				 typeInputField.setName(operationField.getName());
				 inputFields.getField().add(typeInputField);
			}
		}
		return inputFields;
	}
	
	private TypeProperties getProperties(List<NameValueProperty> nameValueProps) {
		TypeProperties typeProperties = null;
		if(nameValueProps != null && !nameValueProps.isEmpty()){
			typeProperties = new TypeProperties();
			for (NameValueProperty nameValueProperty : nameValueProps) {
				Property property = new Property();
				property.setName(nameValueProperty.getPropertyName());
				property.setValue(nameValueProperty.getPropertyValue());
				typeProperties.getProperty().add(property);
			}
		}
		return typeProperties;
	}

	private TypeOperationOutputFields getOperationOutputFields(List<OperationField> operationFields) {
		TypeOperationOutputFields outputFields = null;
		if(operationFields != null && !operationFields.isEmpty()){
			outputFields = new TypeOperationOutputFields();
			for (OperationField operationField : operationFields) {
				 TypeBaseField typeBaseField = new TypeBaseField();
				 typeBaseField.setName(operationField.getName());
				 typeBaseField.setDescription(null);
				 typeBaseField.setFormat(null);
				 typeBaseField.setPrecision(null);
				 typeBaseField.setScale(null);
				 typeBaseField.setScaleType(null);
				 typeBaseField.setType(null);
				 outputFields.getField().add(typeBaseField);
			}
		}
		return outputFields;
	}

	
	
	@Override
	protected List<TypeOperationsOutSocket> getOutSocket() {
		logger.debug("Generating TypeOperationsOutSocket data for : {}", properties.get(NAME));
		List<TypeOperationsOutSocket> outSocketList = new ArrayList<TypeOperationsOutSocket>();

		for (Link link : component.getSourceConnections()) {
			TypeOperationsOutSocket outSocket = new TypeOperationsOutSocket();
			setOutSocketProperties(outSocket);
			TypeOutSocketAsInSocket outSocketAsInsocket = new TypeOutSocketAsInSocket();
			outSocketAsInsocket.setInSocketId(link.getTarget().getPort(link.getTargetTerminal()).getNameOfPort());
			
			outSocket.setCopyOfInsocket(outSocketAsInsocket);
		
			outSocket.setId(link.getSource().getPort(link.getSourceTerminal()).getNameOfPort());
			outSocket.setType(PortTypeConstant.getPortType(link.getSource().getPort(link.getSourceTerminal()).getNameOfPort()));
			
			outSocket.getOtherAttributes();
			outSocketList.add(outSocket);
		}
		return outSocketList;
	}
	
	private void setOutSocketProperties(TypeOperationsOutSocket outSocket) {
		outSocket.setCopyOfInsocket(null);
		outSocket.setId("");
		outSocket.setType("");
		outSocket.getPassThroughfieldOrOperationFieldOrMapField().add(addPassThroughFields());
		outSocket.getPassThroughfieldOrOperationFieldOrMapField().add(addMapFields());
		outSocket.getPassThroughfieldOrOperationFieldOrMapField().add(addOperationFields());
	}

	private List<TypeInputField> addPassThroughFields() {
		if(transformPropertyGrid != null){
			List<TypeInputField> typeOperationFieldsList = null; 
			if(transformPropertyGrid.getOpSysProperties() != null &&
					!transformPropertyGrid.getOpSysProperties().isEmpty()){
				typeOperationFieldsList = new ArrayList<>();
				for(OperationSystemProperties systemProperties : transformPropertyGrid.getOpSysProperties()){
					if(systemProperties.isChecked()){
						TypeInputField typeInputField = new TypeInputField(); 
						typeInputField.setInSocketId(DEFAULT_IN_SOCKET_ID);
						typeInputField.setName(systemProperties.getOpSysValue());
						typeOperationFieldsList.add(typeInputField);
					}
				}
			}
			return typeOperationFieldsList;
		}
		else{
			return new ArrayList<>();
		}
	}

	private List<NameValueProperty> addMapFields() {
		if(transformPropertyGrid != null){
			List<NameValueProperty> nameValuePropertyList = null;
			if(transformPropertyGrid.getNameValueProps() != null &&
					!transformPropertyGrid.getNameValueProps().isEmpty()){
				nameValuePropertyList = new ArrayList<>();
				for(NameValueProperty nameValueProperty : transformPropertyGrid.getNameValueProps()){
					TypeMapField mapField = new TypeMapField();
					mapField.setSourceName(nameValueProperty.getPropertyName());
					mapField.setName(nameValueProperty.getPropertyValue());
					mapField.setInSocketId(DEFAULT_IN_SOCKET_ID);
					nameValuePropertyList.add(nameValueProperty);
				}
			}
			return nameValuePropertyList;
		}
		else{
			return new ArrayList<>();
		}
	}

	private List<TypeOperationField> addOperationFields() {
		if(transformPropertyGrid != null){
			List<TransformOperation> operations = transformPropertyGrid.getOperation();
			List<TypeOperationField> typeOperationFieldList = null;
			if(operations != null && !operations.isEmpty()){
				typeOperationFieldList = new ArrayList<>();
				for(TransformOperation operation : operations){
					List<OperationField> outputFields = operation.getOutputFields();
					if(outputFields != null && !outputFields.isEmpty()){
						for (OperationField operationField : outputFields) {
							TypeOperationField typeOperationField = new TypeOperationField();
							typeOperationField.setName(operationField.getName());
							typeOperationField.setOperationId("");
							typeOperationFieldList.add(typeOperationField);
						}
					}
				}
			}
			return typeOperationFieldList;
		}
		else{
			return new ArrayList<>();
		}
	}
	
	@Override
	public List<TypeBaseInSocket> getInSocket() {
		logger.debug("Generating TypeBaseInSocket data for :{}", component.getProperties().get(NAME));
		List<TypeBaseInSocket> inSocketsList = new ArrayList<>();
		for (Link link : component.getTargetConnections()) {
			TypeBaseInSocket inSocket = new TypeBaseInSocket();
			inSocket.setFromComponentId((String) link.getSource().getProperties().get(NAME));
			inSocket.setFromSocketId(link.getSource().getPort(link.getSourceTerminal()).getNameOfPort());
			inSocket.setId(link.getTarget().getPort(link.getTargetTerminal()).getNameOfPort());
			inSocket.setType(PortTypeConstant.getPortType(link.getTarget().getPort(link.getTargetTerminal()).getNameOfPort()));
			inSocket.getOtherAttributes();
			inSocketsList.add(inSocket);
		}
		return inSocketsList;
	}
	
	
	private void setPrimaryKeys(Aggregate aggregate){
		Set<String> columnNameProperties = (HashSet<String>) component.getProperties().get(Constants.PROPERTY_COLUMN_NAME);
		if(columnNameProperties != null){
		TypePrimaryKeyFields primaryKeyFields = new TypePrimaryKeyFields();
		aggregate.setPrimaryKeys(primaryKeyFields);
		List<TypeFieldName> field = primaryKeyFields.getField();
			for(String columnNameProperty : columnNameProperties){
				TypeFieldName fieldName = new TypeFieldName(); 
				fieldName.setName(columnNameProperty);
				field.add(fieldName);
			}
		}
	}
	
	private void setSecondaryKeys(Aggregate aggregate) {
		Map<String,String> gridRow = (Map<String,String>) component.getProperties().get(Constants.PROPERTY_SECONDARY_COLUMN_KEYS);
		if(gridRow != null){
		TypeSecondaryKeyFields secondaryKeyFields = new TypeSecondaryKeyFields();
		aggregate.setSecondaryKeys(secondaryKeyFields);
		List<TypeSecondayKeyFieldsAttributes> field = secondaryKeyFields.getField();
			for(Entry<String, String> gridRowEntry : gridRow.entrySet()){
				TypeSecondayKeyFieldsAttributes fieldsAttributes = new TypeSecondayKeyFieldsAttributes();
				TypeSortOrder order = TypeSortOrder.fromValue(gridRowEntry.getValue());
				fieldsAttributes.setName(gridRowEntry.getKey());
				fieldsAttributes.setOrder(order);
				field.add(fieldsAttributes);
			}
		}
	}
}
