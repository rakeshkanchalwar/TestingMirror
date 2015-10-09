package com.bitwise.app.engine.converter;

import java.util.List;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.exceptions.PhaseException;
import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwise.app.engine.helper.ConverterHelper;
import com.bitwiseglobal.graph.commontypes.TypeTransformComponent;
import com.bitwiseglobal.graph.commontypes.TypeTransformOperation;
import com.bitwiseglobal.graph.commontypes.TypeTransformOutSocket;

public abstract class TransformConverter extends Converter {
	Logger logger = LogFactory.INSTANCE.getLogger(TransformConverter.class);
		
	@Override
	public void prepareForXML() throws PhaseException, SchemaException {
		logger.debug("prepareForXML - Genrating XML");	
		super.prepareForXML();
		((TypeTransformComponent) baseComponent).getInSocket().addAll(ConverterHelper.getInSocket(component));
		((TypeTransformComponent)baseComponent).getOutSocket().addAll(getOutSocket());
		((TypeTransformComponent)baseComponent).getOperation().addAll(getOperations());
	}

	
	protected abstract  List<TypeTransformOutSocket> getOutSocket();
	protected abstract List<TypeTransformOperation> getOperations();

}
