package com.bitwise.app.engine.converter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.exceptions.PhaseException;
import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwise.app.engine.helper.ConverterHelper;
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

/**
 * Base class for converter implementation. Consists of common methods used by all components.
 * Functionalities specific to some of the converters can be found in {@link ConverterHelper} 
 *
 */
public abstract class Converter {
	private static final Logger logger = LogFactory.INSTANCE.getLogger(Converter.class);

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

	/**
	 * Prepares the class of type {@link TypeBaseComponent} for xml conversion
	 * @throws PhaseException
	 * @throws SchemaException
	 */
	public void prepareForXML() throws PhaseException, SchemaException {
		baseComponent.setId((String) properties.get(NAME));
		try {
			baseComponent.setPhase(new BigInteger((String) properties.get(PHASE)));
		} catch (NullPointerException | NumberFormatException nfe) {
			logger.error("Phase id Empty or Invalid for : {}", baseComponent.getId());
			throw new PhaseException(baseComponent.getId(), nfe);
		}
	}

	/**
	 * Converts the String to {@link BooleanValueType}
	 * @param propertyName
	 * @return {@link BooleanValueType}
	 */
	protected BooleanValueType getBoolean(String propertyName) {
		logger.debug("Getting boolean Value for {}={}", new Object[]{propertyName, properties.get(propertyName)});
		BooleanValueType booleanValue = new BooleanValueType();
		booleanValue.setValue(Boolean.valueOf((String) properties.get(propertyName)));
		return booleanValue;
	}

	/** Converts String value to {@link StandardCharsets} 
	 * @return {@link StandardCharsets}
	 */
	protected StandardCharsets getCharset() {
		logger.debug("Getting StandardCharsets for {}", properties.get(NAME));
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

	/** Converts String value to {@link TypeDependsOn} 
	 * @return {@link TypeDependsOn}
	 */
	protected TypeDependsOn getDependsOn() {
		logger.debug("Getting DependsOn for {}", properties.get(NAME));
		TypeDependsOn dependsOn = new TypeDependsOn();
		dependsOn.setComponentId((String) properties.get(DEPENDS_ON));
		return dependsOn;
	}

	/** Converts String value to {@link TypeBaseRecord}
	 * @return {@link TypeBaseRecord}
	 * @throws SchemaException
	 */
	protected TypeBaseRecord getSchema() throws SchemaException {
		logger.debug("Genrating TypeBaseRecord data for {}", properties.get(NAME));
		TypeBaseRecord typeBaseRecord = new TypeBaseRecord();
		typeBaseRecord.setName("");
		typeBaseRecord.getFieldOrRecord().addAll(getFieldOrRecord());
		return typeBaseRecord;
	}

	/**
	 * Prepare the Fields/Records for shcema
	 * @return {@link List}
	 * @throws SchemaException
	 */
	protected List<TypeBaseField> getFieldOrRecord() throws SchemaException {
		logger.debug("Genrating data for {} for property {}", new Object[]{properties.get(NAME),SCHEMA});
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
				}
			}
			catch (Exception exception) {
				logger.error("Exception while creating schema for component : {}{}", new Object[]{properties.get(NAME),exception});
				throw new SchemaException(baseComponent.getId(), exception);
			}
		}
		return typeBaseFields;
	}

	/**
	 * Returns the base type of the component
	 * @return {@link TypeBaseComponent}
	 */
	public TypeBaseComponent getComponent() {
		return baseComponent;
	}
}
