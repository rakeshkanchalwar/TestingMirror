package com.bitwise.app.graph.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;

/**
 * The Class ComponentCutCommand represents Cut command for Component.
 * 
 * @author Bitwise
 */
public class ComponentCutCommand extends Command {
	private ArrayList<Component> list = new ArrayList<Component>();
	private boolean wasRemoved;
	private final List<Link> sourceConnections = new ArrayList<Link>();
	private final List<Link> targetConnections = new ArrayList<Link>();

	private void deleteConnections(Component component) {
		sourceConnections.addAll(component.getSourceConnections());
		for (int i = 0; i < sourceConnections.size(); i++) {
			Link link = sourceConnections.get(i);
			link.detachSource();
			link.detachTarget();
			if (link.getSource() != null)
				link.getSource().freeOutputPort(link.getSourceTerminal());
			if (link.getTarget() != null)
				link.getTarget().freeInputPort(link.getTargetTerminal());
		}

		targetConnections.addAll(component.getTargetConnections());
		for (int i = 0; i < targetConnections.size(); i++) {
			Link link = targetConnections.get(i);
			link.detachSource();
			link.detachTarget();
			if (link.getSource() != null)
				link.getSource().freeOutputPort(link.getSourceTerminal());
			if (link.getTarget() != null)
				link.getTarget().freeInputPort(link.getTargetTerminal());
		}
	}

	private void restoreConnections() {
		for (int i = 0; i < sourceConnections.size(); i++) {
			Link link = sourceConnections.get(i);
			link.attachSource();
			link.getSource().engageOutputPort(link.getSourceTerminal());
			link.attachTarget();
			link.getTarget().engageInputPort(link.getTargetTerminal());
		}
		sourceConnections.clear();
		for (int i = 0; i < targetConnections.size(); i++) {
			Link link = targetConnections.get(i);
			link.attachSource();
			link.getSource().engageOutputPort(link.getSourceTerminal());
			link.attachTarget();
			link.getTarget().engageInputPort(link.getTargetTerminal());
		}
		targetConnections.clear();
	}

	@Override
	public boolean canUndo() {
		return wasRemoved;
	}

	/**
	 * Adds the element.
	 * 
	 * @param node
	 *            the node
	 * @return true, if successful
	 */

	public boolean addElement(Component node) {
		if (!list.contains(node)) {

			return list.add(node);
		}
		return false;
	}

	@Override
	public void undo() {
		Iterator<Component> it = list.iterator();
		while (it.hasNext()) {
			Component node = it.next();
			node.getParent().addChild(node);
			restoreConnections();

		}

	}

	@Override
	public boolean canExecute() {
		if (list == null || list.isEmpty())
			return false;
		Iterator<Component> it = list.iterator();
		while (it.hasNext()) {
			if (!isCutNode(it.next()))
				return false;
		}
		return true;
	}

	@Override
	public void redo() {
		Iterator<Component> it = list.iterator();
		while (it.hasNext()) {
			Component node = it.next();
			deleteConnections(node);
			wasRemoved = node.getParent().removeChild(node);
		}
	}

	@Override
	public void execute() {
		Clipboard.getDefault().setContents(list);
		redo();
	}

	/**
	 * Checks if is copyable node.
	 * 
	 * @param node
	 *            the node
	 * @return true, if is copyable node
	 */
	public boolean isCutNode(Component node) {
		if (node instanceof Component)
			return true;
		return false;
	}

}
