package com.bitwise.app.graph.model.components;

import com.bitwise.app.graph.model.categories.OutputCategory;

public class OFDelimited extends OutputCategory {

	public OFDelimited() {
		super();
	}

	public String getConverter() {
		return "com.bitwise.app.engine.converter.impl.OutputFileDelimitedConverter";

	}
}
