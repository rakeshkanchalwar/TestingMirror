package com.bitwise.app.engine.converter;


public enum PropertyNameConstants {
	
		PATH("path"),
		IS_SAFE("safe"),
		CHAR_SET("charset"),
		SCHEMA ("schema"),
		DELIMITER("delimiter"),
		RUNTIME_PROPERTIES("runtime_properties"),
		HAS_HEADER("HAS_HEADER_WIDGET"),
		STRICT("strict"),
		OPERATION_CLASS("OPERATION_CLASS"),
		RETENTION_LOGIC_KEEP("retention_logic"),
		OPERATION_FILEDS("filter"),
		DEDUP_FILEDS("COLUMN_NAME");
		private final String value;

	PropertyNameConstants(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

	public static PropertyNameConstants fromValue(String value) {
		for (PropertyNameConstants propertyNameConstant : PropertyNameConstants.values()) {
			if (propertyNameConstant.value.equals(value)) {
				return propertyNameConstant;
			}
		}
		return null;
	}
	
	
}
