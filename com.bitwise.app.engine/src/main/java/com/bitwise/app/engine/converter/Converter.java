package com.bitwise.app.engine.converter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.bitwise.app.graph.model.Component;
import com.bitwiseglobal.graph.commontypes.BooleanValueType;
import com.bitwiseglobal.graph.commontypes.ScaleTypeList;
import com.bitwiseglobal.graph.commontypes.StandardCharsets;
import com.bitwiseglobal.graph.commontypes.TypeBaseComponent;
import com.bitwiseglobal.graph.commontypes.TypeBaseField;
import com.bitwiseglobal.graph.commontypes.TypeBaseRecord;
import com.bitwiseglobal.graph.commontypes.TypeDependsOn;

public abstract class Converter{

	protected static final String DEPENDS_ON = "dependsOn";
	protected static final String PHASE = "phase";
	protected static final String NAME = "name";
	protected static final String HAS_HEADER = "hasHeader";
	protected static final String PATH = "path";
	protected static final String IS_SAFE = "isSafe";
	protected static final Object CHAR_SET = "charset";
	protected static final Object SCHEMA = "schema";
	protected static final Object DELIMITER = "delimiter";
	
	protected LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
	protected Component component = null;
	protected TypeBaseComponent baseComponent = null;
	
	public void prepareForXML(){
		baseComponent.setId((String) properties.get(NAME));
		baseComponent.setPhase(new BigInteger((String)properties.get(PHASE)));
	}

	protected BooleanValueType getBoolean(String propertyName){
		BooleanValueType booleanValue = new BooleanValueType();
		booleanValue.setValue(Boolean.valueOf((String) properties.get(propertyName)));
		return booleanValue;
	}

	protected StandardCharsets getCharset() {
		String charset = (String) properties.get(CHAR_SET);
		StandardCharsets targetCharset = null;
		for (StandardCharsets standardCharsets : StandardCharsets.values()) {
			if(standardCharsets.value().equalsIgnoreCase(charset)){
				targetCharset = standardCharsets;
				break;
			}
		}
		return targetCharset;
	}
	
	protected TypeDependsOn getDependsOn(){
		TypeDependsOn dependsOn = new TypeDependsOn();
		dependsOn.setComponentId((String) properties.get(DEPENDS_ON));
		return dependsOn;
	}
	
	protected TypeBaseRecord getSchema(){
		TypeBaseRecord typeBaseRecord = new TypeBaseRecord();
		typeBaseRecord.setName("");
		typeBaseRecord.getFieldOrRecord().addAll(getFieldOrRecord());
		return typeBaseRecord;
	}

	protected List getFieldOrRecord() {
		//List schemaList = (List) properties.get(SCHEMA);
		List<TypeBaseField> typeBaseFields = new ArrayList<>();
		//for (Object object : schemaList) {
			TypeBaseField typeBaseField = new TypeBaseField();
			typeBaseField.setName("" /*object.getName()*/);
			typeBaseField.setDescription("" /*object.getDescription()*/);
			typeBaseField.setFormat("" /*object.getFormat()*/);
			typeBaseField.setScale(0 /*object.getScale()*/);
			typeBaseField.setScaleType(ScaleTypeList.IMPLICIT /*object.getScaleType()*/);
			typeBaseField.setType(null/*FieldDataTypes.fromValue("")object.getFromValue()*/);
			
			typeBaseFields.add(typeBaseField);
		//}
		return typeBaseFields;
	}

	public TypeBaseComponent getComponent() {
		return baseComponent;
	}
}
