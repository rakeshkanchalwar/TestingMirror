package com.bitwise.app.engine.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.converter.InputConverter;
import com.bitwise.app.engine.converter.PropertyNameConstants;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;
import com.bitwiseglobal.graph.commontypes.TypeInputOutSocket;
import com.bitwiseglobal.graph.inputtypes.FileFixedWidth;
import com.bitwiseglobal.graph.itfd.TypeInputDelimitedOutSocket;

public class InputFileFixedWidthConverter extends InputConverter {

	Logger logger = LogFactory.INSTANCE.getLogger(InputFileFixedWidthConverter.class);

	public InputFileFixedWidthConverter(Component component) {
		super();
		this.baseComponent = new FileFixedWidth();
		this.component = component;
		this.properties = component.getProperties();
	}
	
	@Override
	public void prepareForXML(){
		logger.debug("prepareForXML - Genrating XML data for "+component);
		super.prepareForXML();
		FileFixedWidth fileFixedWidth = (FileFixedWidth) baseComponent;
		FileFixedWidth.Path path = new FileFixedWidth.Path();
		path.setUri((String) properties.get(PropertyNameConstants.PATH.value()));
		FileFixedWidth.Charset charset = new FileFixedWidth.Charset();
		charset.setValue(getCharset());
		
		fileFixedWidth.setPath(path);
		fileFixedWidth.setStrict(getBoolean(PropertyNameConstants.STRICT.value()));
		fileFixedWidth.setSafe(getBoolean(PropertyNameConstants.IS_SAFE.value()));
		fileFixedWidth.setCharset(charset);
		fileFixedWidth.setRuntimeProperties(getRuntimeProperties());
	}
	@Override
	protected List<TypeInputOutSocket> getInOutSocket(){
		logger.debug("getInOutSocket - Genrating TypeInputOutSocket data for "+component);
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
