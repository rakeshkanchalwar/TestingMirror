package com.bitwise.app.graph.figure.factory;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.slf4j.Logger;

import com.bitwise.app.common.component.config.PortSpecification;
import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.common.util.XMLConfigUtil;

public class ModelFigureFactory {
	
	 
	private static final Logger log = LogFactory.INSTANCE.getLogger(ModelFigureFactory.class);
	private static final String COM_BITWISE_APP_GRAPH_FIGURE = "com.bitwise.app.graph.figure.";
	
	private static class ExpectedFigureNotFoundException extends RuntimeException {
		private static final long serialVersionUID = 8587432523081088051L;
		public ExpectedFigureNotFoundException(Exception e) {
			super(e);
		}
	}

	public static IFigure createFigureForComponent(String componentName ) {
		String shapeName = XMLConfigUtil.INSTANCE.getComponent(componentName).getShape().value();
		String canvasIconPath = XMLConfigUtil.INSTANCE.getComponent(componentName).getCanvasIconPath();
		List<PortSpecification> portSpecification = XMLConfigUtil.INSTANCE.getComponent(componentName).getPort().getPortSpecification();
		try {
			
			return (IFigure)Class.forName(COM_BITWISE_APP_GRAPH_FIGURE + shapeName).getDeclaredConstructor(List.class,String.class).newInstance(portSpecification, canvasIconPath);
		} catch (Exception exception) {
			//TODO do we need a pop up...
			log.warn("Figure creation failed for {}", componentName, exception);
			throw new ModelFigureFactory.ExpectedFigureNotFoundException(exception);
		}
	}	
}

