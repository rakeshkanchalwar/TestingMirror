package com.bitwise.app.engine.converter.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.converter.PropertyNameConstants;
import com.bitwise.app.engine.converter.TransformConverter;
import com.bitwise.app.engine.exceptions.PhaseException;
import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;
import com.bitwiseglobal.graph.commontypes.TypeInputField;
import com.bitwiseglobal.graph.commontypes.TypeOperationInputFields;
import com.bitwiseglobal.graph.commontypes.TypeTransformOperation;
import com.bitwiseglobal.graph.commontypes.TypeTransformOutSocket;
import com.bitwiseglobal.graph.transformtypes.Filter;

/**
 * Converter implementation for Filter component
 */
public class FilterConverter extends TransformConverter {

	Logger LOGGER = LogFactory.INSTANCE.getLogger(FilterConverter.class);
	
	public FilterConverter(Component component) {
		super();	
		this.baseComponent = new Filter();
		this.component = component;
		this.properties = component.getProperties();
	}

	@Override
	public void prepareForXML() throws PhaseException, SchemaException {
		LOGGER.debug("Genrating XML for :{}", properties.get(NAME));
		super.prepareForXML();
	}


	@Override
	protected List<TypeTransformOutSocket> getOutSocket() {
		LOGGER.debug("Genrating TypeTransformOutSocket data :{}", properties.get(NAME));
		List<TypeTransformOutSocket> outSocketList = new ArrayList<>();
		for (Link link : component.getSourceConnections()) {
			TypeTransformOutSocket outSocket = new TypeTransformOutSocket();
			outSocket.setId((String) link.getTarget().getProperties().get(NAME));
			outSocket.setType("");
			outSocket.getOtherAttributes();
			outSocketList.add(outSocket);

		}
		return outSocketList;
	}

	@Override
	protected List<TypeTransformOperation> getOperations() {
		LOGGER.debug("Genrating TypeTransformOperation data :{}", properties.get(NAME));
		List<TypeTransformOperation> operationList = new ArrayList<>();
		TypeTransformOperation operation = new TypeTransformOperation();
		TypeOperationInputFields operationInputFields=new TypeOperationInputFields();
		operationInputFields.getField().addAll(getOperationField());
		operation.setInputFields(operationInputFields);
		operation.setClazz(properties.get(PropertyNameConstants.OPERATION_CLASS.value()).toString());
		operationList.add(operation);
		return operationList;
	}

	private List<TypeInputField> getOperationField() {
		LOGGER.debug("Genrating TypeInputField data :{}", properties.get(NAME));
		List<TypeInputField> operationFiledList=new ArrayList<>();
		Set<String> componentOperationFileds = (HashSet<String>) component.getProperties().get(PropertyNameConstants.OPERATION_FILEDS.value());
		if(componentOperationFileds!=null){
			for(String object:componentOperationFileds){
				TypeInputField operationFiled=new TypeInputField();
				operationFiled.setName(object);
				operationFiledList.add(operationFiled);
			}
		}
		return operationFiledList;
	}
}
