package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.InputCategory;

// TODO: Auto-generated Javadoc
/**
 * The Class InputFixedWidth.
 * 
 * @author Bitwise
 */
public class InputFixedWidth extends InputCategory{

	/**
	 * Instantiates a new input fixed width.
	 */
	public InputFixedWidth() {
		super();
	}

	public String getConverter() {
		return "com.bitwise.app.engine.converter.impl.InputFileFixedWidthConverter";

	}
}
