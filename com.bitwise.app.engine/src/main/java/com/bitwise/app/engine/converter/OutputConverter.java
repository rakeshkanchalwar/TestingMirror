package com.bitwise.app.engine.converter;

import java.util.List;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.exceptions.PhaseException;
import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwiseglobal.graph.commontypes.TypeOutputComponent;
import com.bitwiseglobal.graph.commontypes.TypeOutputInSocket;

public abstract class OutputConverter extends Converter {
	Logger logger = LogFactory.INSTANCE.getLogger(OutputConverter.class);
	
	@Override
	public void prepareForXML() throws PhaseException, SchemaException{
		logger.debug("prepareForXML - Genrating XML");	
		super.prepareForXML();
		((TypeOutputComponent)baseComponent).getInSocket().addAll(getOutInSocket());
	}
	
	protected abstract List<TypeOutputInSocket> getOutInSocket() throws SchemaException;

}
