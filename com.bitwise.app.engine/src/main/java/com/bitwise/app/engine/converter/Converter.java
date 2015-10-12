package com.bitwise.app.engine.converter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.exceptions.PhaseException;
import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.propertywindow.widgets.customwidgets.schema.SchemaGrid;
import com.bitwiseglobal.graph.commontypes.BooleanValueType;
import com.bitwiseglobal.graph.commontypes.FieldDataTypes;
import com.bitwiseglobal.graph.commontypes.ScaleTypeList;
import com.bitwiseglobal.graph.commontypes.StandardCharsets;
import com.bitwiseglobal.graph.commontypes.TypeBaseComponent;
import com.bitwiseglobal.graph.commontypes.TypeBaseField;
import com.bitwiseglobal.graph.commontypes.TypeBaseRecord;
import com.bitwiseglobal.graph.commontypes.TypeDependsOn;

public abstract class Converter {

	protected static final String DEPENDS_ON = "dependsOn";
	protected static final String PHASE = "phase";
	protected static final String NAME = "name";
	protected static final String HAS_HEADER = "has_header";
	protected static final String PATH = "path";
	protected static final String IS_SAFE = "safe";
	protected static final Object CHAR_SET = "charset";
	protected static final Object SCHEMA = "schema";
	protected static final Object DELIMITER = "delimiter";

	protected LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
	protected Component component = null;
	protected TypeBaseComponent baseComponent = null;
	Logger logger = LogFactory.INSTANCE.getLogger(Converter.class);

	public void prepareForXML() throws PhaseException, SchemaException {
		logger.debug("prepareForXML - Genrating XML for " + component);
		baseComponent.setId((String) properties.get(NAME));
		try {
			baseComponent.setPhase(new BigInteger((String) properties
					.get(PHASE)));
		} catch (NullPointerException | NumberFormatException nfe) {
			logger.error(nfe.getMessage());
			throw new PhaseException("\n" + baseComponent.getId());
		}
	}

	protected BooleanValueType getBoolean(String propertyName) {
		logger.debug("getBoolean - Getting boolean Value for " + propertyName
				+ "=" + properties.get(propertyName));
		BooleanValueType booleanValue = new BooleanValueType();
		booleanValue.setValue(Boolean.valueOf((String) properties
				.get(propertyName)));
		return booleanValue;
	}

	protected StandardCharsets getCharset() {
		logger.debug("getCharset - Getting StandardCharsets for " + component);
		String charset = (String) properties.get(CHAR_SET);
		StandardCharsets targetCharset = null;
		for (StandardCharsets standardCharsets : StandardCharsets.values()) {
			if (standardCharsets.value().equalsIgnoreCase(charset)) {
				targetCharset = standardCharsets;
				break;
			}
		}
		return targetCharset;
	}

	protected TypeDependsOn getDependsOn() {
		logger.debug("getDependsOn - Getting DependsOn for " + component);
		TypeDependsOn dependsOn = new TypeDependsOn();
		dependsOn.setComponentId((String) properties.get(DEPENDS_ON));
		return dependsOn;
	}

	protected TypeBaseRecord getSchema() throws SchemaException {
		logger.debug("getSchema - Genrating TypeBaseRecord data for "
				+ component);
		TypeBaseRecord typeBaseRecord = new TypeBaseRecord();
		typeBaseRecord.setName("");
		typeBaseRecord.getFieldOrRecord().addAll(getFieldOrRecord());
		return typeBaseRecord;
	}

	protected List getFieldOrRecord() throws SchemaException {
		logger.debug("getFieldOrRecord - Genrating data for "+component+ " for property "+SCHEMA);
		List<SchemaGrid> schemaList = (List) properties.get(SCHEMA);
		List<TypeBaseField> typeBaseFields = new ArrayList<>();
		if(schemaList!=null){
		try{
		for (SchemaGrid object : schemaList) {
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
		}}
		catch (Exception e) {
			logger.error("Exception while creating schema for component"+component,e);
			throw new SchemaException(baseComponent.getId());
		}
		}
		return typeBaseFields;
	}

	public TypeBaseComponent getComponent() {
		return baseComponent;
	}
}
