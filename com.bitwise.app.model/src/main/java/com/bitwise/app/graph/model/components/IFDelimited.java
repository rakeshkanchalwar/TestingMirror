package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.InputCategory;

public class IFDelimited extends InputCategory {

	public IFDelimited() {
	super();
	}
	
	public String getConverter()
	{
		return "com.bitwise.app.engine.converter.impl.InputFileDelimitedConverter";
		
	}
}
