package com.bitwise.app.graph.figure.factory;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.List;

import org.eclipse.draw2d.IFigure;

import com.bitwise.app.common.component.config.PortSpecification;
import com.bitwise.app.common.util.XMLConfigUtil;

public class ModelFigureFactory {
	 
		public IFigure createFigureForComponent(String componentName ) 
		{
		String shapeName=	XMLConfigUtil.INSTANCE.getComponent(componentName).getShape().value();
		List<PortSpecification> portspecification=XMLConfigUtil.INSTANCE.getComponent(componentName).getPort().getPortSpecification();
		IFigure ifigure = null;
		try {
			
			ifigure=(IFigure)Class.forName("com.bitwise.app.graph.figure."+shapeName).getDeclaredConstructor(List.class).newInstance(portspecification);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return ifigure;
		}	
}

