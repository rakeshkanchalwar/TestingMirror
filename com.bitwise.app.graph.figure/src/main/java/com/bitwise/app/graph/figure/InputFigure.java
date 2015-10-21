package com.bitwise.app.graph.figure;


import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;

import com.bitwise.app.common.component.config.PortSpecification;

public class InputFigure extends ComponentFigure
implements HandleBounds{
	

	
	public InputFigure(List<PortSpecification> portSpecification, String canvasIconPath) {
		super(portSpecification, canvasIconPath);
		
 
	}
	

	@Override
	public Rectangle getHandleBounds() {
		return getBounds().getCopy();
	}

}
