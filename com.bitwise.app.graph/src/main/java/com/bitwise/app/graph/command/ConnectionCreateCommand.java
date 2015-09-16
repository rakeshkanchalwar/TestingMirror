package com.bitwise.app.graph.command;

import java.util.Iterator;

import org.eclipse.draw2d.Graphics;
import org.eclipse.gef.commands.Command;

import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.ComponentConnection;

public class ConnectionCreateCommand extends Command{
	
	private ComponentConnection connection;
	private Component source, target;
	private String sourceTerminal, targetTerminal;
	
	public ConnectionCreateCommand() {
		super("connection");
	}
	
	public ConnectionCreateCommand(Component source, int lineStyle) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		setLabel("connection creation");
		this.source = source;
		
	}

	public boolean canExecute() {
		
		// disallow source -> source connections
		if (source.equals(target)) {
			return false;
		}
		// return false, if the source -> target connection exists already
		for (Iterator<ComponentConnection> iter = source.getSourceConnections().iterator(); iter
				.hasNext();) {
			ComponentConnection conn = (ComponentConnection) iter.next();
			
			if (conn.getTarget().equals(target)) {
				return false;
			}
		}
		
		
		if(!source.allowMoreOutGoingLinks())	
			return false;
		if(target!=null)
			if(!target.allowMoreInComingLinks())
			return false;
		
		return true;
	}

	public void execute() {
		
		if(source!=null){
			
			connection.setSource(source);
			System.out.println("Setting sourceTerminal: "+sourceTerminal);
			connection.setSourceTerminal(sourceTerminal);
			connection.setLineStyle(Graphics.LINE_SOLID);
			connection.attachSource();
		}
		if(target!=null){
			
			connection.setTarget(target);
			System.out.println("Setting targetTerminal: "+targetTerminal);
			connection.setTargetTerminal(targetTerminal);
			connection.setLineStyle(Graphics.LINE_SOLID);
			connection.attachTarget();
		}
	}

	public void setTarget(Component target) {
		if (target == null) {
			throw new IllegalArgumentException();
		}
		this.target = target;
	}

	public void setSource(Component newSource) {
		source = newSource;
	}
	
	public void setSourceTerminal(String newSourceTerminal) {
		sourceTerminal = newSourceTerminal;
	}
	
	public void setTargetTerminal(String newTargetTerminal) {
		targetTerminal = newTargetTerminal;
	}
	
	public void setConnection(ComponentConnection w) {
		connection = w;
	}
}