package com.bitwise.app.graph.command;

import java.util.List;

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
		// remove the child
		wasRemoved = parent.removeChild(child);
	}

	@Override
	public void undo() {
		parent.addChild(child);
	}

}
