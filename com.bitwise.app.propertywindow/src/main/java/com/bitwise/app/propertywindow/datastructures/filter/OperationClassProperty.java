package com.bitwise.app.propertywindow.datastructures.filter;

// TODO: Auto-generated Javadoc
/**
 * The Class OperationClassProperty.
 * 
 * @author Bitwise
 */
public class OperationClassProperty implements Cloneable{
	
	private String operationClassPath;
	private boolean isParameter;
	
	/**
	 * Instantiates a new operation class property.
	 * 
	 * @param operationClassPath
	 *            the operation class path
	 * @param isParameter
	 *            the is parameter
	 */
	public OperationClassProperty(String operationClassPath, boolean isParameter) {
		this.operationClassPath = operationClassPath;
		this.isParameter = isParameter;
	}
	public String getOperationClassPath() {
		return operationClassPath;
	}
	public boolean isParameter() {
		return isParameter;
	}
		
	@Override
	public OperationClassProperty clone(){
		return new OperationClassProperty(operationClassPath,isParameter);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isParameter ? 1231 : 1237);
		result = prime
				* result
				+ ((operationClassPath == null) ? 0 : operationClassPath
						.hashCode());
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
		OperationClassProperty other = (OperationClassProperty) obj;
		if (isParameter != other.isParameter)
			return false;
		if (operationClassPath == null) {
			if (other.operationClassPath != null)
				return false;
		} else if (!operationClassPath.equals(other.operationClassPath))
			return false;
		return true;
	}
  
}
