package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.TransformCategory;

/**
 * The Class Filter.
 * 
 * @author Bitwise
 */
public class Filter extends TransformCategory {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5885805870300509574L;

	
	public String getConverter(){
		return "com.bitwise.app.engine.converter.impl.FilterConverter";
	}
}
