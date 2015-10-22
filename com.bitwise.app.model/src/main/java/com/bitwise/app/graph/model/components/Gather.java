package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.StraightPullCategory;

// TODO: Auto-generated Javadoc
/**
 * The Class Gather.
 * 
 * @author Bitwise
 */
public class Gather extends StraightPullCategory {

		
	/**
	 * Instantiates a new gather.
	 */
	public Gather() {
		super();
	}
	
	public String getConverter()
	{
		return "com.bitwise.app.engine.converter.impl.GatherConverter";
		
	}

}
