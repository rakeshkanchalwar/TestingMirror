package com.bitwise.app.engine.converter.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.converter.PropertyNameConstants;
import com.bitwise.app.engine.converter.StraightPullConverter;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;
import com.bitwiseglobal.graph.commontypes.KeepValue;
import com.bitwiseglobal.graph.commontypes.KeyfieldDescriptionType;
import com.bitwiseglobal.graph.commontypes.KeyfieldDescriptionType.KeyFields;
import com.bitwiseglobal.graph.commontypes.KeyfieldDescriptionType.KeyFields.Field;
import com.bitwiseglobal.graph.commontypes.TypeBaseInSocket;
import com.bitwiseglobal.graph.commontypes.TypeOutSocketAsInSocket;
import com.bitwiseglobal.graph.commontypes.TypeStraightPullOutSocket;
import com.bitwiseglobal.graph.straightpulltypes.Dedup;
import com.bitwiseglobal.graph.straightpulltypes.Dedup.Keep;

public class DedupConverter extends StraightPullConverter {

	Logger LOGGER = LogFactory.INSTANCE.getLogger(DedupConverter.class);

	public DedupConverter(Component component) {
		super();
		this.baseComponent = new Dedup();
		this.component = component;
		this.properties = component.getProperties();
	}

	@Override
	public void prepareForXML(){
		LOGGER.debug("Genrating XML for : {}", properties.get(NAME));
		super.prepareForXML();
		Dedup dedup = (Dedup) baseComponent;
		dedup.setRuntimeProperties(getRuntimeProperties());
		dedup.setKeep(getKeep());
		dedup.setKeyDescription(getKeyDescriptionValue());
	}

	private KeyfieldDescriptionType getKeyDescriptionValue() {

		HashSet<String> fieldValueSet = ((HashSet<String>) properties.get(PropertyNameConstants.OPERATION_FILEDS.value()));

		KeyfieldDescriptionType keyfieldDescriptionType = null;
		if (fieldValueSet != null) {
			keyfieldDescriptionType = new KeyfieldDescriptionType();
			KeyFields keyFields = new KeyFields();
			List<Field> keyFiledList = keyFields.getField();
			for (String value : fieldValueSet) {
				Field field = new Field();
				field.setName(value);
				keyFiledList.add(field);
			}
			keyfieldDescriptionType.setKeyFields(keyFields);
		}
		return keyfieldDescriptionType;
	}

	private Keep getKeep() {
		LOGGER.debug("Genrating Retention Logic for ::{}", componentName);
		String keepValue = properties.get(
				PropertyNameConstants.RETENTION_LOGIC_KEEP.value()).toString();
		Keep keep = new Keep();
		keep.setValue(KeepValue.fromValue(keepValue.toLowerCase().replace(" ", "")));
		return keep;
	}

	@Override
	protected List<TypeStraightPullOutSocket> getOutSocket() {
		LOGGER.debug("Genrating TypeStraightPullOutSocket data for : {}",
				properties.get(NAME));
		List<TypeStraightPullOutSocket> outSockectList = new ArrayList<TypeStraightPullOutSocket>();
		for (Link link : component.getSourceConnections()) {
			TypeStraightPullOutSocket outSocket = new TypeStraightPullOutSocket();
			TypeOutSocketAsInSocket outSocketAsInsocket = new TypeOutSocketAsInSocket();
			outSocketAsInsocket.setInSocketId(link.getSource().getProperties()
					.get(NAME).toString());
			outSocketAsInsocket.getOtherAttributes();
			outSocket.setCopyOfInsocket(outSocketAsInsocket);
			outSocket
					.setId((String) link.getTarget().getProperties().get(NAME));
			outSocket.setType(OUT_SOCKET_TYPE);
			outSocket.getOtherAttributes();
			outSockectList.add(outSocket);
		}
		return outSockectList;
	}

	@Override
	public List<TypeBaseInSocket> getInSocket() {
		LOGGER.debug("Genrating TypeBaseInSocket data for :{}", component
				.getProperties().get(NAME));
		List<TypeBaseInSocket> inSocketsList = new ArrayList<>();
		for (Link link : component.getTargetConnections()) {
			TypeBaseInSocket inSocket = new TypeBaseInSocket();
			inSocket.setFromComponentId((String) link.getSource()
					.getProperties().get(NAME));
			inSocket.setFromSocketId(DEFAULT_OUT_SOCKET_ID);
			inSocket.setId(IN_SOCKET_ID_PREFIX);
			inSocket.setType(IN_SOCKET_TYPE);
			inSocket.getOtherAttributes();
			inSocketsList.add(inSocket);
			
		}
		return inSocketsList;
	}

}
