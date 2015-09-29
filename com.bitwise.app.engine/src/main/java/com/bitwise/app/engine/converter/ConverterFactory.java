package com.bitwise.app.engine.converter;

import com.bitwise.app.engine.converter.impl.InputFileDelimitedConverter;
import com.bitwise.app.engine.converter.impl.OutputFileDelimitedConverter;
import com.bitwise.app.engine.converter.impl.ReplicateConverter;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.custom.Gather;
import com.bitwise.app.graph.model.custom.Input;
import com.bitwise.app.graph.model.custom.Output;
import com.bitwise.app.graph.model.custom.Replicate;


public class ConverterFactory {

	public static final ConverterFactory INSTANCE = new ConverterFactory();

	private ConverterFactory() {
	}

	public Converter getConverter(Component component) {
		if(component instanceof Input){
			return new InputFileDelimitedConverter(component);
		}
		if(component instanceof Output){
			return new OutputFileDelimitedConverter(component);
		}
		if(component instanceof Gather || component instanceof Replicate){
			return new ReplicateConverter(component);
		}
		return null;		
	}
}
