package com.bitwise.app.engine.converter.impl;

import com.bitwise.app.engine.converter.Converter;
import com.bitwise.app.engine.converter.StraightPullConverter;
import com.bitwise.app.graph.model.Component;
import com.bitwiseglobal.graph.inputtypes.FileDelimited;
import com.bitwiseglobal.graph.straightpulltypes.Replicate;

public class ReplicateConverter extends StraightPullConverter{

	public ReplicateConverter() {
		// TODO Auto-generated constructor stub
	}
	
	public ReplicateConverter(Component component) {
		super();
		this.baseComponent = new FileDelimited();
		this.component = component;
		this.properties = component.getProperties();
	}
	
	@Override
	public void prepareForXML(){
		Replicate replicate = new Replicate();
		
	}

}
