package com.bitwise.app.graph.figure;

import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;

import com.bitwise.app.common.component.config.PortSpecification;

// TODO: Auto-generated Javadoc
/**
 * The Class DedupFigure.
 * 
 * @author Bitwise
 */
public class DedupFigure extends ComponentFigure implements HandleBounds{

	/**
	 * Instantiates a new dedup figure.
	 * 
	 * @param portSpecification
	 *            the port specification
	 * @param canvasIconPath
	 *            the canvas icon path
	 */
	public DedupFigure(List<PortSpecification> portSpecification, String canvasIconPath) {
		super(portSpecification, canvasIconPath); 
	}

	@Override
	public Rectangle getHandleBounds() {
		return getBounds().getCopy();
	}
}
