package com.bitwise.app.propertywindow.widgets.customwidgets.schema;


/**
 * 
 * @author rahulma
 * 
 */

public class SchemaGrid{
	private String fieldName;

	private String dateFormat;

	private Integer dataType;

	private String scale;
	
	private String dataTypeValue;

	public String getDataTypeValue() {
		return dataTypeValue;
	}

	public void setDataTypeValue(String dataTypeValue) {
		this.dataTypeValue = dataTypeValue;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getFieldName() {
		return fieldName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 2;
		result = prime * result;
		return result;
	}


	@Override
	public String toString() {
		return "SchemaGrid [fieldName=" + fieldName + ", dateFormat="
				+ dateFormat + ", dataType=" + dataType + ", scale=" + scale
				+ ", dataTypeValue=" + dataTypeValue + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchemaGrid other = (SchemaGrid) obj;
		if (fieldName == null) {
			if (other.fieldName != null)
				return false;
		} else if (!fieldName.equals(other.fieldName))
			return false;
		return true;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public SchemaGrid copy() {
		// TODO Auto-generated method stub
		SchemaGrid tempschemaGrid= new SchemaGrid();
		tempschemaGrid.setDataType(this.dataType);
		tempschemaGrid.setDateFormat(dateFormat);
		tempschemaGrid.setFieldName(fieldName);
		tempschemaGrid.setScale(scale);
		tempschemaGrid.setDataTypeValue(dataTypeValue);
		return tempschemaGrid;
	}

}