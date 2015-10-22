package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.StraightPullCategory;

// TODO: Auto-generated Javadoc
/**
 * The Class Replicate.
 * 
 * @author Bitwise
 */
public class Replicate extends StraightPullCategory {

	/**
	 * Instantiates a new replicate.
	 */
	public Replicate() {
		super();
	}

	public String getConverter() {
		return "com.bitwise.app.engine.converter.impl.ReplicateConverter";

	}
}
