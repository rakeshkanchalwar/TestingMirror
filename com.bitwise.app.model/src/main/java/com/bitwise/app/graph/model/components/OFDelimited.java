package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.OutputCategory;

// TODO: Auto-generated Javadoc
/**
 * The Class OFDelimited.
 * 
 * @author Bitwise
 */
public class OFDelimited extends OutputCategory {

	/**
	 * Instantiates a new OF delimited.
	 */
	public OFDelimited() {
		super();
	}

	public String getConverter() {
		return "com.bitwise.app.engine.converter.impl.OutputFileDelimitedConverter";

	}
}
