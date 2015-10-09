package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.OutputCategory;

public class OutputFixedWidth extends OutputCategory {

	public OutputFixedWidth() {
		super();
	}

	public String getConverter() {
		return "com.bitwise.app.engine.converter.impl.OutputFixedWidthConverter";

	}
}
