package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.InputCategory;

// TODO: Auto-generated Javadoc
/**
 * The Class IFDelimited.
 * 
 * @author Bitwise
 */
public class IFDelimited extends InputCategory {

	/**
	 * Instantiates a new IF delimited.
	 */
	public IFDelimited() {
	super();
	}
	
	public String getConverter()
	{
		return "com.bitwise.app.engine.converter.impl.InputFileDelimitedConverter";
		
	}
}
