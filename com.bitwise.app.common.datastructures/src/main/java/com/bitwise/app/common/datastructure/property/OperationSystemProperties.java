package com.bitwise.app.common.datastructure.property;

public class OperationSystemProperties {
	private boolean isChecked;
	private String opSysValue;
	
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	public String getOpSysValue() {
		return opSysValue;
	}
	public void setOpSysValue(String opSysValue) {
		this.opSysValue = opSysValue;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OperationSystemProperties [isChecked=");
		builder.append(isChecked);
		builder.append(", opSysValue=");
		builder.append(opSysValue);
		builder.append("]");
		return builder.toString();
	}

	
}
