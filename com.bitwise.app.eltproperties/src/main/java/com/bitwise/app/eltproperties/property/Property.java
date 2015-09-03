package com.bitwise.app.eltproperties.property;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 03, 2015
 * 
 */


public class Property {
	String propertyName;
	String propertyRenderer;
	String propertyGroup;
	String propertySubGroup;
	String propertyDataType;
	String propertyType;
		
	private Property(){}
	
	public Property(String propertyName, String propertyRenderer,
			String propertyGroup, String propertySubGroup,
			String propertyDataType, String propertyType) {
		this.propertyName = propertyName;
		this.propertyRenderer = propertyRenderer;
		this.propertyGroup = propertyGroup;
		this.propertySubGroup = propertySubGroup;
		this.propertyDataType = propertyDataType;
		this.propertyType = propertyType;
	}
	
	public String getPropertyName() {
		return propertyName;
	}
	
	public String getPropertyRenderer() {
		return propertyRenderer;
	}

	public String getPropertyGroup() {
		return propertyGroup;
	}

	public String getPropertySubGroup() {
		return propertySubGroup;
	}

	public String getPropertyDataType() {
		return propertyDataType;
	}

	public String getPropertyType() {
		return propertyType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((propertyDataType == null) ? 0 : propertyDataType.hashCode());
		result = prime * result
				+ ((propertyGroup == null) ? 0 : propertyGroup.hashCode());
		result = prime * result
				+ ((propertyName == null) ? 0 : propertyName.hashCode());
		result = prime
				* result
				+ ((propertyRenderer == null) ? 0 : propertyRenderer.hashCode());
		result = prime
				* result
				+ ((propertySubGroup == null) ? 0 : propertySubGroup.hashCode());
		result = prime * result
				+ ((propertyType == null) ? 0 : propertyType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Property other = (Property) obj;
		if (propertyDataType == null) {
			if (other.propertyDataType != null)
				return false;
		} else if (!propertyDataType.equals(other.propertyDataType))
			return false;
		if (propertyGroup == null) {
			if (other.propertyGroup != null)
				return false;
		} else if (!propertyGroup.equals(other.propertyGroup))
			return false;
		if (propertyName == null) {
			if (other.propertyName != null)
				return false;
		} else if (!propertyName.equals(other.propertyName))
			return false;
		if (propertyRenderer == null) {
			if (other.propertyRenderer != null)
				return false;
		} else if (!propertyRenderer.equals(other.propertyRenderer))
			return false;
		if (propertySubGroup == null) {
			if (other.propertySubGroup != null)
				return false;
		} else if (!propertySubGroup.equals(other.propertySubGroup))
			return false;
		if (propertyType == null) {
			if (other.propertyType != null)
				return false;
		} else if (!propertyType.equals(other.propertyType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Property [propertyName=" + propertyName + ", propertyRenderer="
				+ propertyRenderer + ", propertyGroup=" + propertyGroup
				+ ", propertySubGroup=" + propertySubGroup
				+ ", propertyDataType=" + propertyDataType + ", propertyType="
				+ propertyType + "]";
	}
	
	
	
}
