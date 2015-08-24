package com.bitwise.app.graph.components.commands;

import org.eclipse.gef.commands.Command;

import com.bitwise.app.graph.components.model.Component;



public class RenameCommand extends Command{
	private Component model;
	private String oldName;
	private String newName;
	public void execute() {
		this.oldName = model.getComponentName();
		
		this.model.setComponentName(newName);
	}
	public void setModel(Object model) {
		this.model = (Component)model;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public void undo() {
		this.model.setComponentName(oldName);
	}

}
