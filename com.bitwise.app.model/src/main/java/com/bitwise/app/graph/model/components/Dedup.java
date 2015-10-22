package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.StraightPullCategory;

// TODO: Auto-generated Javadoc
/**
 * The Class Dedup.
 * 
 * @author Bitwise
 */
public class Dedup extends StraightPullCategory{

	/**
	 * Instantiates a new dedup.
	 */
	public Dedup(){
		super();
	}
	@Override
	public String getConverter()
	{
		return "com.bitwise.app.engine.converter.impl.DedupConverter";
		
	}
}
