package com.bitwise.app.engine.converter;

import java.lang.reflect.InvocationTargetException;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.graph.model.Component;

public class ConverterFactory {

	public static final ConverterFactory INSTANCE = new ConverterFactory();
	LogFactory eltLogger = new LogFactory(getClass().getName());
	private ConverterFactory() {
	}

	public Converter getConverter(Component component) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		
		eltLogger.getLogger().info("getConverter - Getting converter for "+component);
			return (Converter) Class.forName(component.getConverter()).getDeclaredConstructor(Component.class).newInstance(component);
	
	}
}
