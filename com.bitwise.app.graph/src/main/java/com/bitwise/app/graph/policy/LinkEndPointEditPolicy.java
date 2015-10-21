package com.bitwise.app.graph.policy;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.swt.graphics.Color;

/**
 * The Class LinkEndPointEditPolicy.
 */
public class LinkEndPointEditPolicy extends ConnectionEndpointEditPolicy{

	@Override
	protected void addSelectionHandles() {
		super.addSelectionHandles();
		getLinkFigure().setForegroundColor(new Color(null, 22, 169, 199));
	}

	protected PolylineConnection getLinkFigure() {
		return (PolylineConnection) ((GraphicalEditPart) getHost()).getFigure();
	}

	@Override
	protected void removeSelectionHandles() {
		super.removeSelectionHandles();
		getLinkFigure().setForegroundColor(ColorConstants.black);
	}
}
