package com.bitwise.app.graph.command;

import java.util.Iterator;

import java.util.List;

import org.eclipse.draw2d.Connection;
import org.eclipse.gef.commands.Command;

import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Container;

public class ComponentDeleteCommand extends Command {
	private final Component child;
	private final Container parent;
	private List sourceConnections;
	private List targetConnections;
	private boolean wasRemoved;

	public ComponentDeleteCommand(Container parent, Component child) {
		if (parent == null || child == null) {
			throw new IllegalArgumentException();
		}
		setLabel("component deletion");
		this.parent = parent;
		this.child = child;
	}

	private void addConnections(List connections) {
		for (Iterator iter = connections.iterator(); iter.hasNext();) {
			Connection conn = (Connection) iter.next();
			// conn.reconnect();
		}
	}

	@Override
	public boolean canUndo() {
		return wasRemoved;
	}

	@Override
	public void execute() {
		// store a copy of incoming & outgoing connections before proceeding
		sourceConnections = child.getSourceConnections();
		targetConnections = child.getTargetConnections();
		redo();
	}

	@Override
	public void redo() {
		// remove the child and disconnect its connections
		wasRemoved = parent.removeChild(child);
		if (wasRemoved) {
			removeConnections(sourceConnections);
			removeConnections(targetConnections);
		}
	}

	private void removeConnections(List connections) {
		for (Iterator iter = connections.iterator(); iter.hasNext();) {
			Connection conn = (Connection) iter.next();
			// conn.disconnect();
		}
	}

	@Override
	public void undo() {
		if (parent.addChild(child)) {
			addConnections(sourceConnections);
			addConnections(targetConnections);
		}
	}

}
