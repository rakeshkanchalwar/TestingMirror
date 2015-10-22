package com.bitwise.app.graph.figure;

import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;

import com.bitwise.app.common.component.config.PortSpecification;

// TODO: Auto-generated Javadoc
/**
 * The Class InputFixedWidthFigure.
 * 
 * @author Bitwise
 */
public class InputFixedWidthFigure extends ComponentFigure{

	

	/**
	 * Instantiates a new input fixed width figure.
	 * 
	 * @param portSpecification
	 *            the port specification
	 * @param canvasIconPath
	 *            the canvas icon path
	 */
	public InputFixedWidthFigure(List<PortSpecification> portSpecification, String canvasIconPath) {
		super(portSpecification, canvasIconPath);
 
	}
	

	public Rectangle getHandleBounds() {
		return getBounds().getCopy();
	}

}
