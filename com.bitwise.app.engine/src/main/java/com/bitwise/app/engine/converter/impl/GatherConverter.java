package com.bitwise.app.engine.converter.impl;

import java.util.ArrayList;
import java.util.List;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.converter.StraightPullConverter;
import com.bitwise.app.engine.exceptions.PhaseException;
import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;
import com.bitwiseglobal.graph.commontypes.TypeBaseInSocket;
import com.bitwiseglobal.graph.commontypes.TypeOutSocketAsInSocket;
import com.bitwiseglobal.graph.commontypes.TypeStraightPullOutSocket;
import com.bitwiseglobal.graph.straightpulltypes.Gather;
import com.bitwiseglobal.graph.transformtypes.Filter;

public class GatherConverter extends StraightPullConverter {

	LogFactory eltLogger = new LogFactory(getClass().getName());
	
	public GatherConverter(Component component) {
		super();
		this.baseComponent = new Gather();
		this.component = component;
		this.properties = component.getProperties();
	}

	@Override
	public void prepareForXML() throws PhaseException, SchemaException {
		eltLogger.getLogger().info("prepareForXML - Genrating XML");
		super.prepareForXML();
		Gather gather = (Gather) baseComponent;

	}

	@Override
	protected List<TypeStraightPullOutSocket> getOutSocket() {
		eltLogger.getLogger().info("getOutSocket - Genrating TypeStraightPullOutSocket data for "+component);
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

	@Override
	protected List<TypeBaseInSocket> getInSocket() {
		eltLogger.getLogger().info("getInSocket - Genrating TypeBaseInSocket data for "+component);
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

}
