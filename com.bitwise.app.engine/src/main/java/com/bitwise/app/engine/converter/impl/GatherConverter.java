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
import com.bitwiseglobal.graph.straightpulltypes.Gather;

public class GatherConverter extends StraightPullConverter {

	Logger logger = new LogFactory(getClass().getName()).getLogger();
	
	public GatherConverter(Component component) {
		super();
		this.baseComponent = new Gather();
		this.component = component;
		this.properties = component.getProperties();
	}

	@Override
	public void prepareForXML() throws PhaseException, SchemaException {
		logger.debug("prepareForXML - Genrating XML");
		super.prepareForXML();
		Gather gather = (Gather) baseComponent;

	}

	@Override
	protected List<TypeStraightPullOutSocket> getOutSocket() {
		logger.debug("getOutSocket - Genrating TypeStraightPullOutSocket data for "+component);
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
