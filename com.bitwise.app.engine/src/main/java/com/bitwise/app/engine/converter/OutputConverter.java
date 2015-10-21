package com.bitwise.app.engine.converter;

import java.util.List;

import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwiseglobal.graph.commontypes.TypeOutputComponent;
import com.bitwiseglobal.graph.commontypes.TypeOutputInSocket;

public abstract class OutputConverter extends Converter {
	
	@Override
	public void prepareForXML(){
		super.prepareForXML();
		((TypeOutputComponent)baseComponent).getInSocket().addAll(getOutInSocket());
	}
	
	/**
	 * Returs the {@link List} of classes of type {@link TypeOutputInSocket}
	 * @return {@link TypeOutputInSocket}
	 * @throws SchemaException
	 */
	protected abstract List<TypeOutputInSocket> getOutInSocket() ;

}
