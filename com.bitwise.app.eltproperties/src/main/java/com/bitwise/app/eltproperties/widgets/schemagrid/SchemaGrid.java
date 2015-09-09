package com.bitwise.app.eltproperties.widgets.schemagrid;
/**
 * 
 * @author rahulma
 *
 */

public class SchemaGrid{
  private String fieldName;

  private String limit;

  private Integer dataType;

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
	return "SchemaGrid [fieldName=" + fieldName + ", limit=" + limit
			+ ", dataType=" + dataType + "]";
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

public String getLimit() {
	return limit;
} 

public void setLimit(String limit) {
	this.limit = limit;
}

public Integer getDataType() {
	return dataType;
}

public void setDataType(Integer dataType) {
	this.dataType = dataType;
} 

}