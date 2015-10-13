package com.bitwise.app.engine.converter;

import java.util.List;

import com.bitwise.app.engine.exceptions.PhaseException;
import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwise.app.engine.helper.ConverterHelper;
import com.bitwiseglobal.graph.commontypes.TypeTransformComponent;
import com.bitwiseglobal.graph.commontypes.TypeTransformOperation;
import com.bitwiseglobal.graph.commontypes.TypeTransformOutSocket;

public abstract class TransformConverter extends Converter {
		
	@Override
	public void prepareForXML() throws PhaseException, SchemaException {
		super.prepareForXML();
		((TypeTransformComponent) baseComponent).getInSocket().addAll(ConverterHelper.getInSocket(component));
		((TypeTransformComponent)baseComponent).getOutSocket().addAll(getOutSocket());
		((TypeTransformComponent)baseComponent).getOperation().addAll(getOperations());
	}
	
	/**
	 * Returns {@link List} of classes of type {@link TypeTransformOutSocket}
	 * @return {@link List}
	 */
	protected abstract  List<TypeTransformOutSocket> getOutSocket();
	
	/**
	 * Returns {@link List} of classes of type {@link TypeTransformOperation} 
	 * @return {@link List}
	 */
	protected abstract List<TypeTransformOperation> getOperations();
}
