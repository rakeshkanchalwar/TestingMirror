package com.bitwise.app.graph.figure.factory;

import org.eclipse.draw2d.IFigure;

import com.bitwise.app.common.util.XMLConfigUtil;

public class ModelFigureFactory {
	 
		public IFigure createFigureForComponent(String componentName ) 
		{
		String shapeName=	XMLConfigUtil.INSTANCE.getComponent(componentName).getShape().value();
		IFigure ifigure = null;
		try {
			ifigure = (IFigure) Class.forName("com.bitwise.app.graph.figure."+shapeName).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ifigure;
		}	
}

