package com.bitwise.app.graph.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;

import com.bitwise.app.graph.command.LinkCommand;
import com.bitwise.app.graph.model.Link;

public class LinkEditPolicy extends org.eclipse.gef.editpolicies.ConnectionEditPolicy {
	protected Command getDeleteCommand(GroupRequest request) {
		LinkCommand c = new LinkCommand();
		c.setConnection((Link) getHost().getModel());
		return c;
	}
}


