package com.bitwise.app.engine.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.converter.StraightPullConverter;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;
import com.bitwiseglobal.graph.commontypes.TypeBaseInSocket;
import com.bitwiseglobal.graph.commontypes.TypeOutSocketAsInSocket;
import com.bitwiseglobal.graph.commontypes.TypeStraightPullOutSocket;
import com.bitwiseglobal.graph.straightpulltypes.Replicate;

/**
 * Converter implementation for Replicate component
 */
public class ReplicateConverter extends StraightPullConverter {

	Logger LOGGER = LogFactory.INSTANCE.getLogger(ReplicateConverter.class);

	public ReplicateConverter(Component component) {
		super();
		this.baseComponent = new Replicate();
		this.component = component;
		this.properties = component.getProperties();
	}

	@Override
	public void prepareForXML() {
		LOGGER.debug("Genrating XML for :{}", properties.get(NAME));
		super.prepareForXML();

	}

	@Override
	protected List<TypeStraightPullOutSocket> getOutSocket() {
		LOGGER.debug(
				"getOutSocket - Genrating TypeStraightPullOutSocket data for :{}",
				properties.get(NAME));
		List<TypeStraightPullOutSocket> outSockectList = new ArrayList<TypeStraightPullOutSocket>();
		int outSocketCounter = 0;
		for (Link link : component.getSourceConnections()) {
			TypeStraightPullOutSocket outSocket = new TypeStraightPullOutSocket();
			TypeOutSocketAsInSocket outSocketAsInsocket = new TypeOutSocketAsInSocket();
			outSocketAsInsocket.setInSocketId(DEFAULT_IN_SOCKET_ID);
			outSocketAsInsocket.getOtherAttributes();
			outSocket.setCopyOfInsocket(outSocketAsInsocket);
			outSocket.setId(OUT_SOCKET_ID_PREFIX + outSocketCounter);
			outSocket.setType(OUT_SOCKET_TYPE);
			outSocket.getOtherAttributes();
			outSockectList.add(outSocket);
			outSocketCounter++;
		}

		return outSockectList;
	}

	public List<TypeBaseInSocket> getInSocket() {
		LOGGER.debug("Genrating TypeBaseInSocket data for :{}", component
				.getProperties().get(NAME));
		List<TypeBaseInSocket> inSocketsList = new ArrayList<>();
		for (Link link : component.getTargetConnections()) {
			TypeBaseInSocket inSocket = new TypeBaseInSocket();
			inSocket.setFromComponentId((String) link.getSource()
					.getProperties().get(NAME));
			inSocket.setFromSocketId(DEFAULT_IN_SOCKET_ID);
			inSocket.setId(DEFAULT_IN_SOCKET_ID);
			inSocket.setType(IN_SOCKET_TYPE);
			inSocket.getOtherAttributes();
			inSocketsList.add(inSocket);
		}
		return inSocketsList;
	}

}
