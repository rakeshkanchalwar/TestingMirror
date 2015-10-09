package com.bitwise.app.graph.figure.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.slf4j.Logger;

import com.bitwise.app.common.component.config.PortSpecification;
import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.common.util.XMLConfigUtil;

public class ModelFigureFactory {
	 
	private static final Logger log = LogFactory.INSTANCE.getLogger(ModelFigureFactory.class);
	private static final String COM_BITWISE_APP_GRAPH_FIGURE = "com.bitwise.app.graph.figure.";
	
	public static class ExpectedFigureNotFoundException extends RuntimeException {
		private static final long serialVersionUID = 8587432523081088051L;
		public ExpectedFigureNotFoundException(Exception e) {
			super(e);
		}
	}

	public static IFigure createFigureForComponent(String componentName ) {
		String shapeName = XMLConfigUtil.INSTANCE.getComponent(componentName).getShape().value();
		List<PortSpecification> portSpecification = XMLConfigUtil.INSTANCE.getComponent(componentName).getPort().getPortSpecification();
		try {
			
			return (IFigure)Class.forName(COM_BITWISE_APP_GRAPH_FIGURE + shapeName).getDeclaredConstructor(List.class).newInstance(portSpecification);
		} catch (InstantiationException | IllegalAccessException 
				| ClassNotFoundException | IllegalArgumentException 
				| InvocationTargetException | NoSuchMethodException | SecurityException exception) {
			log.warn("Figure creation failed for {}", componentName, exception);
			throw new ExpectedFigureNotFoundException(exception);
		}
	}	
}

