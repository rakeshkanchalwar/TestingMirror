package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.StraightPullCategory;

public class Replicate extends StraightPullCategory {

	public Replicate() {
		super();
	}

	public String getConverter() {
		return "com.bitwise.app.engine.converter.impl.ReplicateConverter";

	}
}
