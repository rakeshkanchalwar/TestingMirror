package com.bitwise.app.engine.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.converter.InputConverter;
import com.bitwise.app.engine.exceptions.PhaseException;
import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;
import com.bitwiseglobal.graph.commontypes.TypeInputOutSocket;
import com.bitwiseglobal.graph.inputtypes.FileDelimited;
import com.bitwiseglobal.graph.itfd.TypeInputDelimitedOutSocket;

public class InputFileDelimitedConverter extends InputConverter {

	Logger logger = LogFactory.INSTANCE.getLogger(InputFileDelimitedConverter.class);
	
	public InputFileDelimitedConverter(Component component) {
		super();
		this.baseComponent = new FileDelimited();
		this.component = component;
		this.properties = component.getProperties();
	}
	
	@Override
	public void prepareForXML() throws PhaseException, SchemaException{
		logger.debug("Genrating XML for {}", properties.get(NAME));	
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
		fileDelimited.setSafe(getBoolean(IS_SAFE));
		fileDelimited.setCharset(charset);
		fileDelimited.setRuntimeProperties(getRuntimeProperties());
	}

	@Override
	protected List<TypeInputOutSocket> getInOutSocket() throws SchemaException {
		logger.debug("Genrating TypeInputOutSocket data for {}", properties.get(NAME));
		List<TypeInputOutSocket> outSockets = new ArrayList<>();
		for (Link link : component.getSourceConnections()) {
			TypeInputDelimitedOutSocket outSocket = new TypeInputDelimitedOutSocket();
			outSocket.setId((String) link.getTarget().getProperties().get(NAME));
			outSocket.setType("");
			outSocket.setSchema(getSchema());
			outSocket.getOtherAttributes();
			outSockets.add(outSocket);
		}
		return outSockets;
	}
}
