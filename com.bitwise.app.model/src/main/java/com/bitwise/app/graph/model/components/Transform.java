package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.TransformCategory;

/**
 * Model class for Transform component
 * 
 * @author Bitwise
 */
public class Transform extends TransformCategory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7251671460714273114L;

	public String getConverter(){
		return "com.bitwise.app.engine.converter.impl.TransformConverter";
	}
}
