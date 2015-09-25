package com.bitwise.app.propertywindow.datastructures.filter;

public class OperationClassProperty {
	
	private String operationClassPath;
	private boolean isParameter;
	  
	public String getOperationClassPath() {
		return operationClassPath;
	}
	public boolean isParameter() {
		return isParameter;
	}
	public void setParameter(boolean isParameter) {
		this.isParameter = isParameter;
	}
	public void setOperationClassPath(String operationClassPath) {
		this.operationClassPath = operationClassPath;
	}
  
}
