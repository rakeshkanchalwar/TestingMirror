package com.bitwise.app.eltproperties.property;

import java.util.ArrayList;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 03, 2015
 * 
 */

public class Property {
	private String propertyName;
	private String propertyRenderer;
	private String propertyGroup;
	private String propertySubGroup;
	private String propertyDataType;
	private String propertyType;
	ArrayList<String> propertyListeners;
		
	private Property(){}
	
	public Property(String propertyDataType,String propertyName,String propertyRenderer){
		this.propertyDataType = propertyDataType;
		this.propertyName = propertyName;
		this.propertyRenderer = propertyRenderer;
		this.propertyType = "USER";
		this.propertyGroup = "GENERAL";
		this.propertySubGroup = "GENERAL";
		propertyListeners = new ArrayList<>();
	}
	
	public Property group(String propertyGroup){
		this.propertyGroup = propertyGroup;
		return this;
	}
	
	public Property subGroup(String propertySubGroup){
		this.propertySubGroup = propertySubGroup;
		return this;
	}
	
	public Property type(String propertyType){
		this.propertyType = propertyType;
		return this;
	}
	
	public Property listener(String propertyListener){
		this.propertyListeners.add(propertyListener);
		return this;
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

	public String getPropertySubGroupID() {
		return propertyGroup + "." +propertySubGroup;
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

	public ArrayList<String> getPropertyListeners() {
		return propertyListeners;
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
		result = prime
				* result
				+ ((propertyListeners == null) ? 0 : propertyListeners
						.hashCode());
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
		if (propertyListeners == null) {
			if (other.propertyListeners != null)
				return false;
		} else if (!propertyListeners.equals(other.propertyListeners))
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
				+ propertyType + ", propertyListeners=" + propertyListeners
				+ "]";
	}
	
}
