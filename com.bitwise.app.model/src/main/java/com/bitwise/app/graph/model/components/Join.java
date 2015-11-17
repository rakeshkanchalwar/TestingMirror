package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.TransformCategory;

public class Join extends TransformCategory{
	
	//private static final long serialVersionUID = -5885805870300509574L;

	@Override
	public String getConverter() {
		
		return "com.bitwise.app.engine.converter.impl.JoinConverter";
	}

}
