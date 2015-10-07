package com.bitwise.app.engine.converter;

import java.util.List;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.exceptions.PhaseException;
import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwiseglobal.graph.commontypes.TypeOutputComponent;
import com.bitwiseglobal.graph.commontypes.TypeOutputInSocket;

public abstract class OutputConverter extends Converter {
	LogFactory eltLogger = new LogFactory(getClass().getName());
	
	@Override
	public void prepareForXML() throws PhaseException, SchemaException{
		eltLogger.getLogger().info("prepareForXML - Genrating XML");	
		super.prepareForXML();
		((TypeOutputComponent)baseComponent).getInSocket().addAll(getInOutSocket());
	}

	protected abstract List<TypeOutputInSocket> getInOutSocket() throws SchemaException;

}
