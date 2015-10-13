package com.bitwise.app.engine.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.converter.StraightPullConverter;
import com.bitwise.app.engine.exceptions.PhaseException;
import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;
import com.bitwiseglobal.graph.commontypes.TypeOutSocketAsInSocket;
import com.bitwiseglobal.graph.commontypes.TypeStraightPullOutSocket;
import com.bitwiseglobal.graph.straightpulltypes.Replicate;

/**
 * Converter implementation for Replicate component
 */
public class ReplicateConverter extends StraightPullConverter {

	Logger logger = LogFactory.INSTANCE.getLogger(ReplicateConverter.class);

	public ReplicateConverter(Component component) {
		super();
		this.baseComponent = new Replicate();
		this.component = component;
		this.properties = component.getProperties();
	}

	@Override
	public void prepareForXML() throws PhaseException, SchemaException {
		logger.debug("Genrating XML for :{}", properties.get(NAME));
		super.prepareForXML();

	}

	@Override
	protected List<TypeStraightPullOutSocket> getOutSocket() {
		logger.debug("getOutSocket - Genrating TypeStraightPullOutSocket data for :{}", properties.get(NAME));
		List<TypeStraightPullOutSocket> outSockectList = new ArrayList<TypeStraightPullOutSocket>();
		for (Link link : component.getSourceConnections()) {
			TypeStraightPullOutSocket outSocket = new TypeStraightPullOutSocket();
			TypeOutSocketAsInSocket outSocketAsInsocket = new TypeOutSocketAsInSocket();
			outSocketAsInsocket.setInSocketId(link.getSource().getProperties().get(NAME).toString());
			outSocketAsInsocket.getOtherAttributes();
			outSocket.setCopyOfInsocket(outSocketAsInsocket);
			outSocket.setId((String) link.getTarget().getProperties().get(NAME));
			outSocket.setType("");
			outSocket.getOtherAttributes();
			outSockectList.add(outSocket);
		}

		return outSockectList;
	}


}
