package com.bitwise.app.engine.converter;

import java.util.List;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.exceptions.PhaseException;
import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwiseglobal.graph.commontypes.TypeInputComponent;
import com.bitwiseglobal.graph.commontypes.TypeInputOutSocket;

public abstract class InputConverter extends Converter {
	LogFactory eltLogger = new LogFactory(getClass().getName());
	
	@Override
	public void prepareForXML() throws PhaseException, SchemaException{
	eltLogger.getLogger().info("prepareForXML - Genrating XML");	
		super.prepareForXML();
		((TypeInputComponent)baseComponent).getOutSocket().addAll(getInOutSocket());
		((TypeInputComponent)baseComponent).getDependsOn().add(getDependsOn());
	}

	protected abstract List<TypeInputOutSocket> getInOutSocket() throws SchemaException;

}
