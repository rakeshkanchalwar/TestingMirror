package com.bitwise.app.engine.converter;

import java.util.List;

import com.bitwiseglobal.graph.commontypes.TypeOutputComponent;
import com.bitwiseglobal.graph.commontypes.TypeOutputInSocket;

public abstract class OutputConverter extends Converter {
	
	@Override
	public void prepareForXML(){
		super.prepareForXML();
		((TypeOutputComponent)baseComponent).getInSocket().addAll(getInOutSocket());
	}

	protected abstract List<TypeOutputInSocket> getInOutSocket();

}
