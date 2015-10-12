package com.bitwise.app.engine.converter;

import java.util.List;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.exceptions.PhaseException;
import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwise.app.engine.helper.ConverterHelper;
import com.bitwiseglobal.graph.commontypes.TypeStraightPullComponent;
import com.bitwiseglobal.graph.commontypes.TypeStraightPullOutSocket;

public abstract class StraightPullConverter extends Converter {
	Logger logger = LogFactory.INSTANCE.getLogger(StraightPullConverter.class);
	
	@Override
	public void prepareForXML() throws PhaseException, SchemaException {
		logger.debug("prepareForXML - Genrating XML");	
		super.prepareForXML();
		((TypeStraightPullComponent) baseComponent).getInSocket().addAll(
				ConverterHelper.getInSocket(component));
		((TypeStraightPullComponent) baseComponent).getOutSocket().addAll(
				getOutSocket());
	}

	protected abstract List<TypeStraightPullOutSocket> getOutSocket();

//	protected abstract List<TypeBaseInSocket> getInSocket();

}
