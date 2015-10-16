package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.StraightPullCategory;

public class Dedup extends StraightPullCategory{

	public Dedup(){
		super();
	}
	@Override
	public String getConverter()
	{
		return "com.bitwise.app.engine.converter.impl.DedupConverter";
		
	}
}
