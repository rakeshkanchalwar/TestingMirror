package com.bitwise.app.graph.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.bitwise.app.graph.command.ComponentDeleteCommand;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Container;

/**
 * The Class GraphComponentEditPolicy.
 */
public class GraphComponentEditPolicy extends ComponentEditPolicy {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.ComponentEditPolicy#createDeleteCommand(
	 * org.eclipse.gef.requests.GroupRequest)
	 */
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		Object parent = getHost().getParent().getModel();
		Object child = getHost().getModel();
		if (parent instanceof Container && child instanceof Component) {
			return new ComponentDeleteCommand((Container) parent,
					(Component) child);
		}
		return super.createDeleteCommand(deleteRequest);
	}

}
