package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.StraightPullCategory;

public class Filter extends StraightPullCategory {

	public Filter() {
		super();
	}
	
	public String getConverter()
	{
		return "com.bitwise.app.engine.converter.impl.FilterConverter";
		
	}
}
