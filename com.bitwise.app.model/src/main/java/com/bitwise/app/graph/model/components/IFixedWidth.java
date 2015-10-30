package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.InputCategory;

// TODO: Auto-generated Javadoc
/**
 * The Class InputFixedWidth.
 * 
 * @author Bitwise
 */
public class IFixedWidth extends InputCategory{

	/**
	 * Instantiates a new input fixed width.
	 */
	public IFixedWidth() {
		super();
	}

	public String getConverter() {
		return "com.bitwise.app.engine.converter.impl.InputFileFixedWidthConverter";

	}
}
