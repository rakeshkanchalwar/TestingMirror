package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.StraightPullCategory;

public class Gather extends StraightPullCategory {

		
	public Gather() {
		super();
	}
	
	public String getConverter()
	{
		return "com.bitwise.app.engine.converter.impl.GatherConverter";
		
	}

}
