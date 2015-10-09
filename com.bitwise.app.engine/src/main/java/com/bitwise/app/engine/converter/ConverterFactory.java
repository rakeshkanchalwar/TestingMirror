package com.bitwise.app.engine.converter;

import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.exceptions.ConverterNotFoundException;
import com.bitwise.app.engine.exceptions.EngineException;
import com.bitwise.app.graph.model.Component;

public class ConverterFactory {

	public static final ConverterFactory INSTANCE = new ConverterFactory();
	Logger logger = new LogFactory(getClass().getName()).getLogger();
	private ConverterFactory() {
	}

	public Converter getConverter(Component component) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, EngineException {
		try{
		logger.debug("getConverter - Getting converter for "+component);
			return (Converter) Class.forName(component.getConverter()).getDeclaredConstructor(Component.class).newInstance(component);
		}
		catch (ClassNotFoundException e) {
			logger.error("Exception Occured getting Converter for ::"+component,e);
			throw new ConverterNotFoundException(component.getBasename());
		}
	}
}
