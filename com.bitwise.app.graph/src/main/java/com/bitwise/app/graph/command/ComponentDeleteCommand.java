package com.bitwise.app.graph.command;

import org.eclipse.gef.commands.Command;

import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Container;

public class ComponentDeleteCommand extends Command {
	private final Component child;
	private final Container parent;
	private boolean wasRemoved;

	public ComponentDeleteCommand(Container parent, Component child) {
		this.parent = parent;
		this.child = child;
	}

	@Override
	public boolean canUndo() {
		return wasRemoved;
	}

	@Override
	public void execute() {
		redo();
	}

	@Override
	public void redo() {
		wasRemoved = parent.removeChild(child);
	}

	@Override
	public void undo() {
		parent.addChild(child);
	}

}
