package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.StraightPullCategory;

// TODO: Auto-generated Javadoc
/**
 * The Class Gather.
 * 
 * @author Bitwise
 */
public class UnionallComponent extends StraightPullCategory {

		
	/**
	 * Instantiates a new gather.
	 */
	public UnionallComponent() {
		super();
	}
	
	public String getConverter()
	{
		return "com.bitwise.app.engine.converter.impl.UnionAllConverter";
		
	}

}
