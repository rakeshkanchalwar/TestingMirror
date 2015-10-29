package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.TransformCategory;

/**
 * Model class for Aggregate component
 * 
 * @author Bitwise
 */
public class Aggregate extends TransformCategory {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5221821689519010230L;

	public String getConverter(){
		return "com.bitwise.app.engine.converter.impl.AggregateConverter";
	}
}
