package com.bitwise.app.engine.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.converter.OutputConverter;
import com.bitwise.app.engine.converter.PropertyNameConstants;
import com.bitwise.app.engine.exceptions.PhaseException;
import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;
import com.bitwiseglobal.graph.commontypes.TypeOutputInSocket;
import com.bitwiseglobal.graph.otfd.TypeOutputDelimitedInSocket;
import com.bitwiseglobal.graph.outputtypes.FileFixedWidth;

public class OutputFileFixedWidthConverter extends OutputConverter {

Logger logger = LogFactory.INSTANCE.getLogger(OutputFileDelimitedConverter.class);
	
	public OutputFileFixedWidthConverter(Component component) {
		super();
		this.component = component;
		this.properties = component.getProperties();
		this.baseComponent = new FileFixedWidth();
	}
	
	@Override
	public void prepareForXML() throws PhaseException, SchemaException{
		logger.debug("prepareForXML - Genrating XML data for "+component);
		super.prepareForXML();
		FileFixedWidth fileFixedWidth = (FileFixedWidth) baseComponent;
		FileFixedWidth.Path path = new FileFixedWidth.Path();
		path.setUri((String) properties.get(PropertyNameConstants.PATH.value()));
		FileFixedWidth.Charset charset = new FileFixedWidth.Charset();
		charset.setValue(getCharset());
		
		fileFixedWidth.setPath(path);
		fileFixedWidth.setStrict(getBoolean(PropertyNameConstants.HAS_HEADER.value()));
	    fileFixedWidth.setCharset(charset);
		fileFixedWidth.setRuntimeProperties(getRuntimeProperties());
	}

	@Override
	protected List<TypeOutputInSocket> getOutInSocket() throws SchemaException {
		logger.debug("getInOutSocket - Genrating TypeOutputInSocket data");
		List<TypeOutputInSocket> outputinSockets = new ArrayList<>();
		for (Link link : component.getTargetConnections()) {
			TypeOutputDelimitedInSocket outInSocket = new TypeOutputDelimitedInSocket();
			outInSocket.setId("");
			outInSocket.setType("");
			outInSocket.setSchema(getSchema());
			outInSocket.getOtherAttributes();
			outInSocket.setFromComponentId((String) link.getSource().getProperties().get(NAME));
			outputinSockets.add(outInSocket);
		}
		return outputinSockets;
	}
}
