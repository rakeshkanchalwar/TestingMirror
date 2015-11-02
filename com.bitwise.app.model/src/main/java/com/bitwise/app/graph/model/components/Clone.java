package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.StraightPullCategory;

// TODO: Auto-generated Javadoc
/**
 * The Class Replicate.
 * 
 * @author Bitwise
 */
public class Clone extends StraightPullCategory {

	/**
	 * Instantiates a new replicate.
	 */
	public Clone() {
		super();
	}

	public String getConverter() {
		return "com.bitwise.app.engine.converter.impl.ReplicateConverter";

	}
}
