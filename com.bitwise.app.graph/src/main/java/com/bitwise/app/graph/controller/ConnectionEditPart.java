package com.bitwise.app.graph.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.bitwise.app.graph.command.ConnectionDeleteCommand;
import com.bitwise.app.graph.model.Connection;

public class ConnectionEditPart extends AbstractConnectionEditPart implements PropertyChangeListener {
	/**
	 * Upon activation, attach to the model element as a property change
	 * listener.
	 */
	public void activate() {
		if (!isActive()) {
			super.activate();
			((Connection) getModel()).addPropertyChangeListener(this);
		}
	}


	/**
	 * Upon deactivation, detach from the model element as a property change
	 * listener.
	 */
	public void deactivate() {
		if (isActive()) {
			super.deactivate();
			((Connection) getModel()).removePropertyChangeListener(this);
		}
	}
	@Override
	protected IFigure createFigure() {
		PolylineConnection connection = (PolylineConnection) super.createFigure();
		
		// arrow at target endpoint
		connection.setTargetDecoration(new PolygonDecoration());
		
		// line drawing style
		connection.setLineStyle(((Connection)getModel()).getLineStyle()); 
		return connection;
	}

	@Override
	protected void createEditPolicies() {
	// Selection handle edit policy. Makes the connection show a feedback, when selected by the user.
	installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,	new ConnectionEndpointEditPolicy());
	
	// Allows the removal of the connection model element
	installEditPolicy(EditPolicy.CONNECTION_ROLE, new ConnectionEditPolicy() {
		protected Command getDeleteCommand(GroupRequest request) {
			return new ConnectionDeleteCommand((Connection) getModel());
		}
	});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.
	 * PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent event) {
		String property = event.getPropertyName();
		if (Connection.LINESTYLE_PROP.equals(property)) {
			((PolylineConnection) getFigure()).setLineStyle(((Connection) getModel()).getLineStyle());
		}
	}
}
