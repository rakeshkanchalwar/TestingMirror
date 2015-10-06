package com.bitwise.app.engine.converter.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.converter.TransformConverter;
import com.bitwise.app.engine.exceptions.PhaseException;
import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;
import com.bitwiseglobal.graph.commontypes.TypeBaseInSocket;
import com.bitwiseglobal.graph.commontypes.TypeInputField;
import com.bitwiseglobal.graph.commontypes.TypeOperationInputFields;
import com.bitwiseglobal.graph.commontypes.TypeTransformOperation;
import com.bitwiseglobal.graph.commontypes.TypeTransformOutSocket;
import com.bitwiseglobal.graph.transformtypes.Filter;

public class FilterConverter extends TransformConverter {

	LogFactory eltLogger = new LogFactory(getClass().getName());
	
	public FilterConverter(Component component) {
		super();	
		this.baseComponent = new Filter();
		this.component = component;
		this.properties = component.getProperties();
	}

	@Override
	public void prepareForXML() throws PhaseException, SchemaException {
		eltLogger.getLogger().info("prepareForXML - Genrating XML data");	
		super.prepareForXML();
		Filter filter = (Filter) baseComponent;

	}

	@Override
	protected List<TypeBaseInSocket> getInSocket() {
		eltLogger.getLogger().info("getInSocket - Genrating TypeBaseInSocket data "+component);
		List<TypeBaseInSocket> inSocketsList = new ArrayList<>();
		for (Link link : component.getTargetConnections()) {
			TypeBaseInSocket inSocket = new TypeBaseInSocket();
			inSocket.setId((String) link.getSource().getProperties().get(NAME));
			inSocket.setType("");
			inSocket.getOtherAttributes();
			inSocketsList.add(inSocket);
		}
		return inSocketsList;
	}

	@Override
	protected List<TypeTransformOutSocket> getOutSocket() {
		eltLogger.getLogger().info("getOutSocket - Genrating TypeTransformOutSocket data "+component);
		List<TypeTransformOutSocket> outSocketList = new ArrayList<>();
		for (Link link : component.getSourceConnections()) {
			TypeTransformOutSocket outSocket = new TypeTransformOutSocket();
			outSocket
					.setId((String) link.getTarget().getProperties().get(NAME));
			outSocket.setType("");
			outSocket.getOtherAttributes();
			outSocketList.add(outSocket);

		}
		return outSocketList;
	}

	@Override
	protected List<TypeTransformOperation> getOperations() {
		eltLogger.getLogger().info("getOperations - Genrating TypeTransformOperation data "+component);
		List<TypeTransformOperation> operationList = new ArrayList<>();
		TypeTransformOperation operation = new TypeTransformOperation();
		TypeOperationInputFields operationInputFields=new TypeOperationInputFields();
		operationInputFields.getField().addAll(getOperationFiled());
		operation.setInputFields(operationInputFields);
		operationList.add(operation);
		return operationList;
	}

	private List<TypeInputField> getOperationFiled() {
		eltLogger.getLogger().info("getOperationFiled - Genrating TypeInputField data "+component);
		List<TypeInputField> operationFiledList=new ArrayList<>();
		HashSet<String> componentOperationFileds = (HashSet<String>) component.getProperties().get("filter");
		for(String object:componentOperationFileds){
			TypeInputField operationFiled=new TypeInputField();
			operationFiled.setName(object);
			operationFiledList.add(operationFiled);
		}
		return operationFiledList;
	}

}
