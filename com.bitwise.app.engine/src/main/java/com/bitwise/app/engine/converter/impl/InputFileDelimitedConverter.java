package com.bitwise.app.engine.converter.impl;




import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.converter.InputConverter;
import com.bitwise.app.engine.converter.PropertyNameConstants;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;
import com.bitwise.app.propertywindow.widgets.customwidgets.schema.SchemaGrid;
import com.bitwiseglobal.graph.commontypes.FieldDataTypes;
import com.bitwiseglobal.graph.commontypes.ScaleTypeList;
import com.bitwiseglobal.graph.commontypes.TypeBaseField;
import com.bitwiseglobal.graph.commontypes.TypeInputOutSocket;
import com.bitwiseglobal.graph.inputtypes.FileDelimited;
import com.bitwiseglobal.graph.itfd.TypeInputDelimitedOutSocket;

public class InputFileDelimitedConverter extends InputConverter {

	Logger LOGGER = LogFactory.INSTANCE.getLogger(InputFileDelimitedConverter.class);
	
	public InputFileDelimitedConverter(Component component) {
		super();
		this.baseComponent = new FileDelimited();
		this.component = component;
		this.properties = component.getProperties();
	}
	
	@Override
	public void prepareForXML(){
		LOGGER.debug("Genrating XML for {}", properties.get(NAME));	
		super.prepareForXML();
		FileDelimited fileDelimited = (FileDelimited) baseComponent;
		FileDelimited.Path path = new FileDelimited.Path();
		path.setUri((String) properties.get(PropertyNameConstants.PATH.value()));
		FileDelimited.Charset charset = new FileDelimited.Charset();
		charset.setValue(getCharset());
		FileDelimited.Delimiter delimiter = new FileDelimited.Delimiter();
		delimiter.setValue((String) properties.get(PropertyNameConstants.DELIMITER.value()));
		fileDelimited.setPath(path);
		fileDelimited.setDelimiter(delimiter);
		fileDelimited.setHasHeader(getBoolean(PropertyNameConstants.HAS_HEADER.value()));
		fileDelimited.setSafe(getBoolean(PropertyNameConstants.IS_SAFE.value()));
		fileDelimited.setCharset(charset);
		fileDelimited.setRuntimeProperties(getRuntimeProperties());
	}

	@Override
	protected List<TypeInputOutSocket> getInOutSocket(){
		LOGGER.debug("Genrating TypeInputOutSocket data for {}", properties.get(NAME));
		List<TypeInputOutSocket> outSockets = new ArrayList<>();
		for (Link link : component.getSourceConnections()) {
			TypeInputDelimitedOutSocket outSocket = new TypeInputDelimitedOutSocket();
			outSocket.setId(DEFAULT_OUT_SOCKET_ID);
			outSocket.setType(OUT_SOCKET_TYPE);
			outSocket.setSchema(getSchema());
			outSocket.getOtherAttributes();
			outSockets.add(outSocket);
		}
		return outSockets;
	}

	@Override
	protected List<TypeBaseField> getFieldOrRecord() {
		LOGGER.debug("Genrating data for {} for property {}", new Object[]{properties.get(NAME),PropertyNameConstants.SCHEMA.value()});
		List<SchemaGrid> schemaList = (List) properties.get(PropertyNameConstants.SCHEMA.value());
		List<TypeBaseField> typeBaseFields = new ArrayList<>();
		if(schemaList!=null){
			try{
				for (SchemaGrid object : schemaList ) {
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
