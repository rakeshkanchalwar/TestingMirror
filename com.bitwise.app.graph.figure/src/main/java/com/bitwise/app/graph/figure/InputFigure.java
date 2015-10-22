package com.bitwise.app.graph.figure;


import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;

import com.bitwise.app.common.component.config.PortSpecification;

// TODO: Auto-generated Javadoc
/**
 * The Class InputFigure.
 * 
 * @author Bitwise
 */
public class InputFigure extends ComponentFigure
implements HandleBounds{
	

	
	/**
	 * Instantiates a new input figure.
	 * 
	 * @param portSpecification
	 *            the port specification
	 * @param canvasIconPath
	 *            the canvas icon path
	 */
	public InputFigure(List<PortSpecification> portSpecification, String canvasIconPath) {
		super(portSpecification, canvasIconPath);
		
 
	}
	

	@Override
	public Rectangle getHandleBounds() {
		return getBounds().getCopy();
	}

}
