package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.StraightPullCategory;

// TODO: Auto-generated Javadoc
/**
 * The Class Dedup.
 * 
 * @author Bitwise
 */
public class RemovedupsComponent extends StraightPullCategory{

	/**
	 * Instantiates a new dedup.
	 */
	public RemovedupsComponent(){
		super();
	}
	@Override
	public String getConverter()
	{
		return "com.bitwise.app.engine.converter.impl.DedupConverter";
		
	}
}
