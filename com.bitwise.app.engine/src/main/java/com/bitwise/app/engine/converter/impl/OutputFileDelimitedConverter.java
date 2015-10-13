package com.bitwise.app.engine.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.converter.OutputConverter;
import com.bitwise.app.engine.exceptions.PhaseException;
import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;
import com.bitwiseglobal.graph.commontypes.TypeOutputInSocket;
import com.bitwiseglobal.graph.otfd.TypeOutputDelimitedInSocket;
import com.bitwiseglobal.graph.outputtypes.FileDelimited;

public class OutputFileDelimitedConverter extends OutputConverter {
	
	Logger logger = LogFactory.INSTANCE.getLogger(OutputFileDelimitedConverter.class);
	
	public OutputFileDelimitedConverter(Component component) {
		super();
		this.component = component;
		this.properties = component.getProperties();
		this.baseComponent = new FileDelimited();
	}
	
	@Override
	public void prepareForXML() throws PhaseException, SchemaException{
		logger.debug("Genrating XML data for {}", properties.get(NAME));
		super.prepareForXML();
		
		FileDelimited fileDelimited = (FileDelimited) baseComponent;
		
		FileDelimited.Path path = new FileDelimited.Path();
		path.setUri((String) properties.get(PATH));
		
		FileDelimited.Charset charset = new FileDelimited.Charset();
		charset.setValue(getCharset());
		
		FileDelimited.Delimiter delimiter = new FileDelimited.Delimiter();
		delimiter.setValue((String) properties.get(DELIMITER));
		
		fileDelimited.setPath(path);
		fileDelimited.setDelimiter(delimiter);
		fileDelimited.setHasHeader(getBoolean(HAS_HEADER));
		fileDelimited.setCharset(charset);
	}

	@Override
	protected List<TypeOutputInSocket> getOutInSocket() throws SchemaException {
		logger.debug("Genrating TypeOutputInSocket data");
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
