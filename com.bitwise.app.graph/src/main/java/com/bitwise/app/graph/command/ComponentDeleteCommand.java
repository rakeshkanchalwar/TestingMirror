package com.bitwise.app.graph.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Container;
import com.bitwise.app.graph.model.Link;

public class ComponentDeleteCommand extends Command {
	private final Component child;
	private final Container parent;
	private boolean wasRemoved;
	private List<Link> sourceConnections = new ArrayList<Link>();
	private List<Link> targetConnections = new ArrayList<Link>();

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
		deleteConnections(child);
		wasRemoved = parent.removeChild(child);
	}

	@Override
	public void undo() {
		parent.addChild(child);
	}
	
	private void deleteConnections(Component component) {
		System.out.println("DeleteCommand:deleteConnections");
		
		sourceConnections.addAll(component.getSourceConnections());
		for (int i = 0; i < sourceConnections.size(); i++) {
			Link link = (Link) sourceConnections.get(i);
			link.detachSource();
			link.detachTarget();
			if(link.getSource()!=null)
				link.getSource().removeOutputPort(link.getSourceTerminal());
			if(link.getTarget()!=null)
				link.getTarget().removeInputPort(link.getTargetTerminal());
		}
		
		targetConnections.addAll(component.getTargetConnections());
		for (int i = 0; i < targetConnections.size(); i++) {
			Link link = (Link) targetConnections.get(i);
			link.detachSource();
			link.detachTarget();
			if(link.getSource()!=null)
				link.getSource().removeOutputPort(link.getSourceTerminal());
			if(link.getTarget()!=null)
				link.getTarget().removeInputPort(link.getTargetTerminal());
		}
	}

}
