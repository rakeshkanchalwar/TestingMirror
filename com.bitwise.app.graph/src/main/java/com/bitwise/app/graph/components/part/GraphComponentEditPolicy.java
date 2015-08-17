package com.bitwise.app.graph.components.part;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.bitwise.app.graph.components.commands.ComponentDeleteCommand;
import com.bitwise.app.graph.components.model.Component;
import com.bitwise.app.graph.components.model.ComponentsDiagram;


public class GraphComponentEditPolicy extends ComponentEditPolicy{
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
		if (parent instanceof ComponentsDiagram && child instanceof Component) {
			return new ComponentDeleteCommand((ComponentsDiagram) parent, (Component) child);
		}
		return super.createDeleteCommand(deleteRequest);
	}
}
