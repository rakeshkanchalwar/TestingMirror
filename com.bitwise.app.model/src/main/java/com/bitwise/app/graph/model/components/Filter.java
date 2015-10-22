package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.StraightPullCategory;

// TODO: Auto-generated Javadoc
/**
 * The Class Filter.
 * 
 * @author Bitwise
 */
public class Filter extends StraightPullCategory {

	/**
	 * Instantiates a new filter.
	 */
	public Filter() {
		super();
	}
	
	public String getConverter()
	{
		return "com.bitwise.app.engine.converter.impl.FilterConverter";
		
	}
}
