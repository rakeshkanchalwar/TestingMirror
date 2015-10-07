package com.bitwise.app.engine.converter;

import java.util.List;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.exceptions.PhaseException;
import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwiseglobal.graph.commontypes.TypeBaseInSocket;
import com.bitwiseglobal.graph.commontypes.TypeTransformComponent;
import com.bitwiseglobal.graph.commontypes.TypeTransformOperation;
import com.bitwiseglobal.graph.commontypes.TypeTransformOutSocket;

public abstract class TransformConverter extends Converter {
	LogFactory eltLogger = new LogFactory(getClass().getName());
		
	@Override
	public void prepareForXML() throws PhaseException, SchemaException {
		eltLogger.getLogger().info("prepareForXML - Genrating XML");	
		super.prepareForXML();
		((TypeTransformComponent) baseComponent).getInSocket().addAll(getInSocket());
		((TypeTransformComponent)baseComponent).getOutSocket().addAll(getOutSocket());
		((TypeTransformComponent)baseComponent).getOperation().addAll(getOperations());
	}

	protected abstract List<TypeBaseInSocket> getInSocket();
	protected abstract  List<TypeTransformOutSocket> getOutSocket();
	protected abstract List<TypeTransformOperation> getOperations();

}
