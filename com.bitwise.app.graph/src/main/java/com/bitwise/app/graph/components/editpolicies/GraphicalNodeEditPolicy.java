package com.bitwise.app.graph.components.editpolicies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.bitwise.app.graph.components.commands.ConnectionCreateCommand;
import com.bitwise.app.graph.components.commands.ConnectionReconnectCommand;
import com.bitwise.app.graph.components.model.Component;
import com.bitwise.app.graph.components.model.Connection;

public class GraphicalNodeEditPolicy extends AbstractEditPolicy {
	
	public GraphicalNodeEditPolicy(){
		
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#
	 * getConnectionCompleteCommand
	 * (org.eclipse.gef.requests.CreateConnectionRequest)
	 */
	protected Command getConnectionCompleteCommand(
			CreateConnectionRequest request) {
		ConnectionCreateCommand cmd = (ConnectionCreateCommand) request
				.getStartCommand();
		cmd.setTarget((Component) getHost().getModel());
		return cmd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#
	 * getConnectionCreateCommand
	 * (org.eclipse.gef.requests.CreateConnectionRequest)
	 */
	protected Command getConnectionCreateCommand(
			CreateConnectionRequest request) {
		Component source = (Component) getHost().getModel();
		int style = ((Integer) request.getNewObjectType())
				.intValue();
		ConnectionCreateCommand cmd = new ConnectionCreateCommand(
				source, style);
		request.setStartCommand(cmd);
		return cmd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#
	 * getReconnectSourceCommand
	 * (org.eclipse.gef.requests.ReconnectRequest)
	 */
	protected Command getReconnectSourceCommand(
			ReconnectRequest request) {
		Connection conn = (Connection) request
				.getConnectionEditPart().getModel();
		Component newSource = (Component) getHost().getModel();
		ConnectionReconnectCommand cmd = new ConnectionReconnectCommand(
				conn);
		cmd.setNewSource(newSource);
		return cmd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#
	 * getReconnectTargetCommand
	 * (org.eclipse.gef.requests.ReconnectRequest)
	 */
	protected Command getReconnectTargetCommand(
			ReconnectRequest request) {
		Connection conn = (Connection) request
				.getConnectionEditPart().getModel();
		Component newTarget = (Component) getHost().getModel();
		ConnectionReconnectCommand cmd = new ConnectionReconnectCommand(
				conn);
		cmd.setNewTarget(newTarget);
		return cmd;
	}
}
