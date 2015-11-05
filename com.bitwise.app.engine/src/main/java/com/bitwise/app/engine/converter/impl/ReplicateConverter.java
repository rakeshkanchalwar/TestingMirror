package com.bitwise.app.engine.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.converter.PortTypeConstant;
import com.bitwise.app.engine.converter.StraightPullConverter;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;
import com.bitwiseglobal.graph.commontypes.TypeBaseInSocket;
import com.bitwiseglobal.graph.commontypes.TypeOutSocketAsInSocket;
import com.bitwiseglobal.graph.commontypes.TypeStraightPullOutSocket;
import com.bitwiseglobal.graph.straightpulltypes.Clone;

/**
 * Converter implementation for Replicate component
 */
public class ReplicateConverter extends StraightPullConverter {

	Logger LOGGER = LogFactory.INSTANCE.getLogger(ReplicateConverter.class);

	public ReplicateConverter(Component component) {
		super();
		this.baseComponent = new Clone();
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
			outSocketAsInsocket.setInSocketId(link.getTarget().getPort(link.getTargetTerminal()).getNameOfPort());
			outSocketAsInsocket.getOtherAttributes();
			outSocket.setCopyOfInsocket(outSocketAsInsocket);
			outSocket.setId(PortTypeConstant.getPortType(link.getSource().getPort(link.getSourceTerminal()).getNameOfPort()) + outSocketCounter);
			outSocket.setType(PortTypeConstant.getPortType(link.getSource().getPort(link.getSourceTerminal()).getNameOfPort()));
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
			inSocket.setFromSocketId(link.getSource().getPort(link.getSourceTerminal()).getNameOfPort());
			inSocket.setId(link.getTarget().getPort(link.getTargetTerminal()).getNameOfPort());
			inSocket.setType(PortTypeConstant.getPortType(link.getTarget().getPort(link.getTargetTerminal()).getNameOfPort()));
			inSocket.getOtherAttributes();
			inSocketsList.add(inSocket);
		}
		return inSocketsList;
	}

}
