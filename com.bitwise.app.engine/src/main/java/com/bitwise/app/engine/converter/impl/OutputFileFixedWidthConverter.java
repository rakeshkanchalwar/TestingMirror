package com.bitwise.app.engine.converter.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.converter.OutputConverter;
import com.bitwise.app.engine.converter.PropertyNameConstants;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;
import com.bitwise.app.propertywindow.fixedwidthschema.FixedWidthGridRow;
import com.bitwiseglobal.graph.commontypes.FieldDataTypes;
import com.bitwiseglobal.graph.commontypes.ScaleTypeList;
import com.bitwiseglobal.graph.commontypes.TypeBaseField;
import com.bitwiseglobal.graph.commontypes.TypeOutputInSocket;
import com.bitwiseglobal.graph.otffw.TypeOutputFixedwidthInSocket;
import com.bitwiseglobal.graph.outputtypes.FileFixedWidth;

public class OutputFileFixedWidthConverter extends OutputConverter {

	Logger LOGGER = LogFactory.INSTANCE.getLogger(OutputFileDelimitedConverter.class);
	
	public OutputFileFixedWidthConverter(Component component) {
		super();
		this.component = component;
		this.properties = component.getProperties();
		this.baseComponent = new FileFixedWidth();
	}
	
	@Override
	public void prepareForXML(){
		LOGGER.debug("prepareForXML - Genrating XML data for "+component);
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
	protected List<TypeOutputInSocket> getOutInSocket(){
		LOGGER.debug("getInOutSocket - Genrating TypeOutputInSocket data");
		List<TypeOutputInSocket> outputinSockets = new ArrayList<>();
		for (Link link : component.getTargetConnections()) {
			TypeOutputFixedwidthInSocket outInSocket = new TypeOutputFixedwidthInSocket();
			outInSocket.setId(DEFAULT_IN_SOCKET_ID);
			outInSocket.setType(IN_SOCKET_TYPE);
			outInSocket.setSchema(getSchema());
			outInSocket.getOtherAttributes();
			outInSocket.setFromComponentId((String) link.getSource().getProperties().get(NAME));
			outInSocket.setFromSocketId(DEFAULT_OUT_SOCKET_ID);
			outputinSockets.add(outInSocket);
		}
		return outputinSockets;
	}

	@Override
	protected List<TypeBaseField> getFieldOrRecord() {
		{
			LOGGER.debug("Genrating data for {} for property {}", new Object[]{properties.get(NAME),PropertyNameConstants.SCHEMA.value()});
			List<FixedWidthGridRow> schemaList = (List) properties.get(PropertyNameConstants.SCHEMA.value());
			List<TypeBaseField> typeBaseFields = new ArrayList<>();
			if(schemaList!=null){
				try{
					for (FixedWidthGridRow object : schemaList ) {
						TypeBaseField typeBaseField = new TypeBaseField();
						typeBaseField.setName(object.getFieldName());
						typeBaseField.setDescription("");
						typeBaseField.setFormat(object.getDateFormat());
						if(!object.getScale().trim().isEmpty())
							typeBaseField.setScale(Integer.parseInt(object.getScale()));
						typeBaseField.setScaleType(ScaleTypeList.IMPLICIT );
						for(FieldDataTypes fieldDataType:FieldDataTypes.values()){
							if(fieldDataType.value().equalsIgnoreCase(object.getDataTypeValue()))
								typeBaseField.setType(fieldDataType);
						}
						if(object.getLength()!=null)
						{
							typeBaseField.getOtherAttributes().put(new QName("length"), object.getLength());
						}
						typeBaseFields.add(typeBaseField);
					}
				}
				catch (Exception exception) {
					LOGGER.warn("Exception while creating schema for component : {}{}", new Object[]{properties.get(NAME),exception});
					
				}
			}
			return typeBaseFields;
		}
	}

}
