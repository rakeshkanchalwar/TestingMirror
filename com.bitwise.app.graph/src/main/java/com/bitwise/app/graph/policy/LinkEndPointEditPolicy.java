package com.bitwise.app.graph.policy;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;

public class LinkEndPointEditPolicy extends ConnectionEndpointEditPolicy{

	protected void addSelectionHandles() {
		super.addSelectionHandles();
		getLinkFigure().setForegroundColor(ColorConstants.blue);
	}

	protected PolylineConnection getLinkFigure() {
		return (PolylineConnection) ((GraphicalEditPart) getHost()).getFigure();
	}

	protected void removeSelectionHandles() {
		super.removeSelectionHandles();
		getLinkFigure().setForegroundColor(ColorConstants.black);
	}
}
