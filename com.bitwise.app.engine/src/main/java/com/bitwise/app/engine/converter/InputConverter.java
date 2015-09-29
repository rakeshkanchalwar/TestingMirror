package com.bitwise.app.engine.converter;

import java.util.List;

import com.bitwiseglobal.graph.commontypes.TypeInputComponent;
import com.bitwiseglobal.graph.commontypes.TypeInputOutSocket;

public abstract class InputConverter extends Converter {
	
	@Override
	public void prepareForXML(){
		super.prepareForXML();
		((TypeInputComponent)baseComponent).getOutSocket().addAll(getInOutSocket());
		((TypeInputComponent)baseComponent).getDependsOn().add(getDependsOn());
	}

	protected abstract List<TypeInputOutSocket> getInOutSocket();

}
