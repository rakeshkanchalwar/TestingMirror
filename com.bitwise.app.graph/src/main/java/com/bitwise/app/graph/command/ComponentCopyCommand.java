package com.bitwise.app.graph.command;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

import com.bitwise.app.graph.model.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class ComponentCopyCommand.
 */
public class ComponentCopyCommand extends Command {
   
	private ArrayList<Component> list = new ArrayList<Component>();
   
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
	public boolean canExecute() {
		if (list == null || list.isEmpty())
			return false;
		Iterator<Component> it = list.iterator();
		while (it.hasNext()) {
			if (!isCopyableNode(it.next()))
				return false;
		}
		return true;
	}

	@Override
	public void execute() {
		if (canExecute()){
			Clipboard.getDefault().setContents(list);
		}
	}

	@Override
	public boolean canUndo() {
		return false;
	}

	/**
	 * Checks if is copyable node.
	 * 
	 * @param node
	 *            the node
	 * @return true, if is copyable node
	 */
	public boolean isCopyableNode(Component node) {
		if (node instanceof Component)
			return true;
		return false;
	}

}
