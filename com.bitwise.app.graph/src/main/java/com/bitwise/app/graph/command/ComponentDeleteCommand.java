package com.bitwise.app.graph.command;

import java.util.List;

import org.eclipse.gef.commands.Command;

import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Connection;
import com.bitwise.app.graph.model.Container;

public class ComponentDeleteCommand extends Command{
	/** Shape to remove. */
	private final Component child;

	/** ShapeDiagram to remove from. */
	private final Container parent;
	/** Holds a copy of the outgoing connections of child. */
	private List<Connection> sourceConnections;
	/** Holds a copy of the incoming connections of child. */
	private List<Connection> targetConnections;
	/** True, if child was removed from its parent. */
	private boolean wasRemoved;

	/**
	 * Create a command that will remove the shape from its parent.
	 * 
	 * @param parent
	 *            the ShapesDiagram containing the child
	 * @param child
	 *            the Shape to remove
	 * @throws IllegalArgumentException
	 *             if any parameter is null
	 */
	public ComponentDeleteCommand(Container parent, Component child) {
		if (parent == null || child == null) {
			throw new IllegalArgumentException();
		}
		setLabel("component deletion");
		this.parent = parent;
		this.child = child;
	}

	/**
	 * Reconnects a List of Connections with their previous endpoints.
	 * @param connections
	 *            a non-null List of connections
	 */
	private void addConnections(List<Connection> connections) {
		for (Connection conn : connections) {
			conn.reconnect();
		}
	}

	public boolean canUndo() {
		return wasRemoved;
	}

	public void execute() {
		// store a copy of incoming & outgoing connections before proceeding
		sourceConnections = child.getSourceConnections();
		targetConnections = child.getTargetConnections();
		redo();
	}

	public void redo() {
		// remove the child and disconnect its connections
		wasRemoved = parent.removeChild(child);
		if (wasRemoved) {
			removeConnections(sourceConnections);
			removeConnections(targetConnections);
		}
	}

	/**
	 * Disconnects a List of Connections from their endpoints.
	 * 
	 * @param connections
	 *            a non-null List of connections
	 */
	private void removeConnections(List<Connection> connections) {
		for (Connection connection :connections) {
			connection.disconnect();
		}
	}

	public void undo() {
		// add the child and reconnect its connections
		if (parent.addChild(child)) {
			addConnections(sourceConnections);
			addConnections(targetConnections);
		}
	}
}
