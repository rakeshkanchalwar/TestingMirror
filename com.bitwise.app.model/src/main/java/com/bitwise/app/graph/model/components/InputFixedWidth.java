package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.InputCategory;

public class InputFixedWidth extends InputCategory{

	public InputFixedWidth() {
		super();
	}

	public String getConverter() {
		return "com.bitwise.app.engine.converter.impl.InputFileFixedWidthConverter";

	}
}
