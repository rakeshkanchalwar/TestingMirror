package com.bitwise.app.graph.figure;

import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;

import com.bitwise.app.common.component.config.PortSpecification;

// TODO: Auto-generated Javadoc
/**
 * The Class OutputFixedWidthFigure.
 * 
 * @author Bitwise
 */
public class OutputFixedWidthFigure extends ComponentFigure implements HandleBounds
{

	/**
	 * Instantiates a new output fixed width figure.
	 * 
	 * @param portSpecification
	 *            the port specification
	 * @param canvasIconPath
	 *            the canvas icon path
	 */
	public OutputFixedWidthFigure(List<PortSpecification> portSpecification, String canvasIconPath) {
		super(portSpecification, canvasIconPath);

	}
	
	
	@Override
	public Rectangle getHandleBounds() {
		
		return getBounds().getCopy();
	}

	

}
