package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.StraightPullCategory;

// TODO: Auto-generated Javadoc
/**
 * The Class Replicate.
 * 
 * @author Bitwise
 */
public class CloneComponent extends StraightPullCategory {

	/**
	 * Instantiates a new replicate.
	 */
	public CloneComponent() {
		super();
	}

	public String getConverter() {
		return "com.bitwise.app.engine.converter.impl.ReplicateConverter";

	}
}
