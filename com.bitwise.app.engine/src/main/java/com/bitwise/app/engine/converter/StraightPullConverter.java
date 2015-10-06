package com.bitwise.app.engine.converter;

import java.util.Collection;
import java.util.List;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.exceptions.PhaseException;
import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwiseglobal.graph.commontypes.TypeBaseInSocket;
import com.bitwiseglobal.graph.commontypes.TypeStraightPullComponent;
import com.bitwiseglobal.graph.commontypes.TypeStraightPullOutSocket;

public abstract class StraightPullConverter extends Converter {
	LogFactory eltLogger = new LogFactory(getClass().getName());
	
	@Override
	public void prepareForXML() throws PhaseException, SchemaException {
		eltLogger.getLogger().info("prepareForXML - Genrating XML");	
		super.prepareForXML();
		((TypeStraightPullComponent) baseComponent).getInSocket().addAll(
				getInSocket());
		((TypeStraightPullComponent) baseComponent).getOutSocket().addAll(
				getOutSocket());
	}

	protected abstract List<TypeStraightPullOutSocket> getOutSocket();

	protected abstract List<TypeBaseInSocket> getInSocket();

}
