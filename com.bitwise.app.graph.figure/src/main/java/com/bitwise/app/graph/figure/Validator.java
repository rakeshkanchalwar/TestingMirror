package com.bitwise.app.graph.figure;

import com.bitwise.app.graph.model.Component;

public interface Validator {
	
	public void setStatus(Component.ValidityStatus status);
	
	public Component.ValidityStatus getStatus();
}
