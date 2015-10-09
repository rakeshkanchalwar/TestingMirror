package com.bitwise.app.engine.helper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.converter.Converter;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;
import com.bitwiseglobal.graph.commontypes.TypeBaseInSocket;

public class ConverterHelper extends Converter{

	static Logger logger = LogFactory.INSTANCE.getLogger(ConverterHelper.class);
	
	
	public static List<TypeBaseInSocket> getInSocket(Component component) {
		logger.debug("getInSocket - Genrating TypeBaseInSocket data "+component);
		List<TypeBaseInSocket> inSocketsList = new ArrayList<>();
		for (Link link : component.getTargetConnections()) {
			TypeBaseInSocket inSocket = new TypeBaseInSocket();
			inSocket.setFromComponentId((String) link.getSource().getProperties().get(NAME));
			inSocket.setType("");
			inSocket.getOtherAttributes();
			inSocketsList.add(inSocket);
		}
		return inSocketsList;
	}

	
	
}
