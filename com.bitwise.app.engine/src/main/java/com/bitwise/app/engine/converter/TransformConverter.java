package com.bitwise.app.engine.converter;

import java.util.List;

import com.bitwiseglobal.graph.commontypes.TypeBaseInSocket;
import com.bitwiseglobal.graph.commontypes.TypeTransformComponent;
import com.bitwiseglobal.graph.commontypes.TypeTransformOperation;
import com.bitwiseglobal.graph.commontypes.TypeTransformOutSocket;

public abstract class TransformConverter extends Converter {
		
	@Override
	public void prepareForXML(){
		super.prepareForXML();
		((TypeTransformComponent) baseComponent).getInSocket().addAll(getInSocket());
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
	public abstract List<TypeBaseInSocket> getInSocket();
}
