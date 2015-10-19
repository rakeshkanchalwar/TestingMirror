package com.bitwise.app.engine.converter;

import java.util.List;

import com.bitwise.app.engine.exceptions.PhaseException;
import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwiseglobal.graph.commontypes.TypeInputComponent;
import com.bitwiseglobal.graph.commontypes.TypeInputOutSocket;

public abstract class InputConverter extends Converter {
	
	@Override
	public void prepareForXML() throws PhaseException, SchemaException{
		super.prepareForXML();
		((TypeInputComponent)baseComponent).getOutSocket().addAll(getInOutSocket());
		((TypeInputComponent)baseComponent).getDependsOn().add(getDependsOn());
		
	}

	/**
	 * Returs the {@link List} of classes of type {@link TypeInputOutSocket}
	 * @return {@link List}
	 * @throws SchemaException
	 */
	protected abstract List<TypeInputOutSocket> getInOutSocket() throws SchemaException;

}
