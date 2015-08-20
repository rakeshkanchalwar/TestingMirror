package com.bitwise.app.adapters;

import java.io.Serializable;

public class ComponentProperty implements Serializable{
	
	private static final long serialVersionUID = -3592506476403282358L;
	private String propName;
	private String propID;
	private String propValue;
	
	public ComponentProperty( String propID, String propName, String propValue) {
		this.propName = propName;
		this.propID = propID;
		this.propValue = propValue;
	}

	public String getPropName() {
		return propName;
	}

	public String getPropID() {
		return propID;
	}

	public String getPropValue() {
		return propValue;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public void setPropID(String propID) {
		this.propID = propID;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

	@Override
	public String toString() {
		return "ComponentProperty [propName=" + propName + ", propID=" + propID
				+ ", propValue=" + propValue + "]";
	}
	
	
}
