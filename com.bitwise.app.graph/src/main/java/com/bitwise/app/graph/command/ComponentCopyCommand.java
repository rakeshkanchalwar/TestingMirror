package com.bitwise.app.graph.command;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

import com.bitwise.app.graph.model.Component;

public class ComponentCopyCommand extends Command {
   
	private ArrayList<Component> list = new ArrayList<Component>();
   
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

	public boolean isCopyableNode(Component node) {
		if (node instanceof Component)
			return true;
		return false;
	}

}
