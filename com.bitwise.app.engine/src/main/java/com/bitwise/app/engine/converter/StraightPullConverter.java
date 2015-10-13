package com.bitwise.app.engine.converter;

import java.util.List;

import com.bitwise.app.engine.exceptions.PhaseException;
import com.bitwise.app.engine.exceptions.SchemaException;
import com.bitwise.app.engine.helper.ConverterHelper;
import com.bitwiseglobal.graph.commontypes.TypeStraightPullComponent;
import com.bitwiseglobal.graph.commontypes.TypeStraightPullOutSocket;

/**
 * Converter implementation for StraightPull type components
 */
public abstract class StraightPullConverter extends Converter {

	@Override
	public void prepareForXML() throws PhaseException, SchemaException {
		super.prepareForXML();
		((TypeStraightPullComponent) baseComponent).getInSocket().addAll(ConverterHelper.getInSocket(component));
		((TypeStraightPullComponent) baseComponent).getOutSocket().addAll(getOutSocket());
	}

	/**
	 *  Returns {@link List} of classes of type {@link TypeStraightPullOutSocket}
	 * @return {@link List}
	 */
	protected abstract List<TypeStraightPullOutSocket> getOutSocket();

//	protected abstract List<TypeBaseInSocket> getInSocket();

}
