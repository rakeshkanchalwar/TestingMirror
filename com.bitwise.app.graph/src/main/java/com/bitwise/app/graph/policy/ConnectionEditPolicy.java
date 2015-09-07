package com.bitwise.app.graph.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.bitwise.app.graph.command.ConnectionCreateCommand;
import com.bitwise.app.graph.command.ConnectionReconnectCommand;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Connection;


public class ConnectionEditPolicy extends GraphicalNodeEditPolicy {

	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
		ConnectionCreateCommand cmd = (ConnectionCreateCommand) request.getStartCommand();
		cmd.setTarget((Component) getHost().getModel());
		return cmd;
	}

	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		Component source = (Component) getHost().getModel();
		int style = ((Integer) request.getNewObjectType()).intValue();
		ConnectionCreateCommand cmd = new ConnectionCreateCommand(source, style);
		request.setStartCommand(cmd);
		return cmd;
	}

	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		Connection connection = (Connection) request.getConnectionEditPart().getModel();
		Component newSource = (Component) getHost().getModel();
		ConnectionReconnectCommand cmd = new ConnectionReconnectCommand(connection);
		cmd.setNewSource(newSource);
		return cmd;
	}

	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		Connection connection = (Connection) request.getConnectionEditPart().getModel();
		Component newTarget = (Component) getHost().getModel();
		ConnectionReconnectCommand cmd = new ConnectionReconnectCommand(connection);
		cmd.setNewTarget(newTarget);
		return cmd;
	}

}
