package com.bitwise.app.graph.policy;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.bitwise.app.graph.command.LinkCommand;
import com.bitwise.app.graph.command.LinkReconnectCommand;
import com.bitwise.app.graph.command.LinkReconnectTargetCommand;
import com.bitwise.app.graph.controller.ComponentEditPart;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;

public class NodeConnectionEditPolicy extends GraphicalNodeEditPolicy{

	protected Command getConnectionCreateCommand(
			CreateConnectionRequest request) {

		LinkCommand command = new LinkCommand();
		command.setConnection(new Link());
		command.setSource(getComponentEditPart().getCastedModel());
		ConnectionAnchor ctor = getComponentEditPart().getSourceConnectionAnchor(
				request);
		if (ctor == null)
			return null;

		command.setSourceTerminal(getComponentEditPart()
				.mapConnectionAnchorToTerminal(ctor));

		request.setStartCommand(command);
		return command;

	}

	protected Command getConnectionCompleteCommand(
			CreateConnectionRequest request) {

		LinkCommand command = (LinkCommand) request
				.getStartCommand();
		command.setTarget(getComponentEditPart().getCastedModel());
		ConnectionAnchor ctor = getComponentEditPart().getTargetConnectionAnchor(
				request);
		if (ctor == null)
			return null;


		command.setTargetTerminal(getComponentEditPart()
				.mapConnectionAnchorToTerminal(ctor));

		return command;
	}

	protected Command getReconnectSourceCommand(
			ReconnectRequest request) {
		
		Link link=(Link)request.getConnectionEditPart().getModel();
		Component comp=getComponentEditPart().getCastedModel();
		
		LinkReconnectCommand cmd=new LinkReconnectCommand(link);
		cmd.setOldSource(link);
		cmd.setNewSource(comp);
		ConnectionAnchor anchor=getComponentEditPart().getSourceConnectionAnchor(request);
		if(anchor==null){
			return null;
		}
		cmd.setNewSourceTerminal(getComponentEditPart().mapConnectionAnchorToTerminal(anchor));
		
	
		return cmd;
	}
	protected Command getReconnectTargetCommand(
			ReconnectRequest request) {
		Link link=(Link)request.getConnectionEditPart().getModel();
		Component component=getComponentEditPart().getCastedModel();
		ConnectionAnchor anchor=getComponentEditPart().getTargetConnectionAnchor(request);
		if(anchor==null){
			return null;
		}
		LinkReconnectTargetCommand command=new LinkReconnectTargetCommand(link);
		command.setOldTarget(link);
		command.setNewTarget(component);
		command.setNewTargetTerminal(getComponentEditPart().mapConnectionAnchorToTerminal(anchor));
		
		return command;
	}


	protected ComponentEditPart getComponentEditPart() {
		return (ComponentEditPart) getHost();
	}


}
