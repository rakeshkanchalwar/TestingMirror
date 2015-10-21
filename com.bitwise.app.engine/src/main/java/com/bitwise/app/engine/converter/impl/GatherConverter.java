package com.bitwise.app.engine.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.converter.StraightPullConverter;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;
import com.bitwiseglobal.graph.commontypes.TypeOutSocketAsInSocket;
import com.bitwiseglobal.graph.commontypes.TypeStraightPullOutSocket;
import com.bitwiseglobal.graph.straightpulltypes.Gather;


/**
 * Converter implementation for Gather component
 */
public class GatherConverter extends StraightPullConverter {

	Logger LOGGER = LogFactory.INSTANCE.getLogger(GatherConverter.class);
	
	public GatherConverter(Component component) {
		super();
		this.baseComponent = new Gather();
		this.component = component;
		this.properties = component.getProperties();
	}

	@Override
	public void prepareForXML(){
		LOGGER.debug("Genrating XML for : {}", properties.get(NAME));
		super.prepareForXML();
	}

	@Override
	protected List<TypeStraightPullOutSocket> getOutSocket() {
		LOGGER.debug("Genrating TypeStraightPullOutSocket data for : {}", properties.get(NAME));
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
