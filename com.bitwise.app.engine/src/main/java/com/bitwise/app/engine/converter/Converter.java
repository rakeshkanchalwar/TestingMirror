package com.bitwise.app.engine.converter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.exceptions.PhaseException;
import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.propertywindow.widgets.customwidgets.schema.SchemaGrid;
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
	LogFactory eltLogger = new LogFactory(getClass().getName());
	
	public void prepareForXML() throws PhaseException, SchemaException{
		eltLogger.getLogger().info("prepareForXML - Genrating XML for "+component);	
		baseComponent.setId((String) properties.get(NAME));
		try{
			baseComponent.setPhase(new BigInteger((String)properties.get(PHASE)));
		}
		catch(NullPointerException | NumberFormatException nfe)
		{
			eltLogger.getLogger().error(nfe.getMessage());
			throw new PhaseException("\n"+baseComponent.getId());
		}
	}

	protected BooleanValueType getBoolean(String propertyName){
		eltLogger.getLogger().info("getBoolean - Getting boolean Value for "+propertyName);	
		BooleanValueType booleanValue = new BooleanValueType();
		booleanValue.setValue(Boolean.valueOf((String) properties.get(propertyName)));
		return booleanValue;
	}

	protected StandardCharsets getCharset() {
		eltLogger.getLogger().info("getCharset - Getting StandardCharsets for "+component);	
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
		eltLogger.getLogger().info("getDependsOn - Getting DependsOn for "+component);
		TypeDependsOn dependsOn = new TypeDependsOn();
		dependsOn.setComponentId((String) properties.get(DEPENDS_ON));
		return dependsOn;
	}
	
	protected TypeBaseRecord getSchema() throws SchemaException{
		eltLogger.getLogger().info("getSchema - Genrating TypeBaseRecord data for "+component);
		TypeBaseRecord typeBaseRecord = new TypeBaseRecord();
		typeBaseRecord.setName("");
		typeBaseRecord.getFieldOrRecord().addAll(getFieldOrRecord());
		return typeBaseRecord;
	}

	
	protected List getFieldOrRecord() throws SchemaException {
		eltLogger.getLogger().info("getFieldOrRecord - Genrating data for "+component+ " for property "+SCHEMA);
		List<SchemaGrid> schemaList = (List) properties.get(SCHEMA);
		List<TypeBaseField> typeBaseFields = new ArrayList<>();
		try{
		for (SchemaGrid object : schemaList) {
			TypeBaseField typeBaseField = new TypeBaseField();
			typeBaseField.setName(object.getFieldName());
			typeBaseField.setDescription("");
			typeBaseField.setFormat(object.getDateFormat());
			if(!object.getScale().trim().isEmpty())
				typeBaseField.setScale(Integer.parseInt(object.getScale()));
			typeBaseField.setScaleType(ScaleTypeList.IMPLICIT /*object.getScaleType()*/);
			typeBaseField.setType(null/*FieldDataTypes.fromValue("")object.getFromValue()*/);
			//FieldDataTypes.JAVA_LANG_SHORT);
//			for(FieldDataTypes fieldDataTypes:FieldDataTypes.values()){
//				fieldDataTypes.value().equalsIgnoreCase("UI data");
//			}
			typeBaseFields.add(typeBaseField);
		}}
		catch (Exception e) {
			eltLogger.getLogger().error(e.getMessage());
			throw new SchemaException(baseComponent.getId());
		}
		
		return typeBaseFields;
	}

	public TypeBaseComponent getComponent() {
		return baseComponent;
	}
}
