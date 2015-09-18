package com.bitwise.app.graph.command;

import java.util.Iterator;

import org.eclipse.draw2d.Graphics;
import org.eclipse.gef.commands.Command;

import com.bitwise.app.graph.models.Component;
import com.bitwise.app.graph.models.ComponentConnection;

public class ConnectionCreateCommand extends Command{
	/** The connection instance. */
	private ComponentConnection connection;
	/** The desired line style for the connection (dashed or solid). */
	private int lineStyle;

	
	private Component source, target;
	protected String sourceTerminal, targetTerminal;

	protected Component oldSource;
	protected String oldSourceTerminal;
	protected Component oldTarget;
	protected String oldTargetTerminal;
	/**
	 * Instantiate a command that can create a connection between two shapes.
	 * @param source the source endpoint (a non-null Shape instance)
	 * @param lineStyle the desired line style. See Connection#setLineStyle(int) for details
	 * @throws IllegalArgumentException if source is null
	 * @see ComponentConnection#setLineStyle(int)
	 */
	
	public ConnectionCreateCommand() {
		super("connection");
	}
	
	public ConnectionCreateCommand(Component source, int lineStyle) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		setLabel("connection creation");
		this.source = source;
		this.lineStyle = lineStyle;
	}

	public boolean canExecute() {
		
		// disallow source -> source connections
		if (source.equals(target)) {
			return false;
		}
		// return false, if the source -> target connection exists already
		for (Iterator iter = source.getSourceConnections().iterator(); iter
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		
		if(source!=null){
			
			connection.setSource(source);
			connection.setSourceTerminal(sourceTerminal);
			connection.setLineStyle(Graphics.LINE_SOLID);
			connection.attachSource();
		}
		if(target!=null){
			
			connection.setTarget(target);
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
		oldSource = w.getSource();
		oldTarget = w.getTarget();
		oldSourceTerminal = w.getSourceTerminal();
		oldTargetTerminal = w.getTargetTerminal();
	}
}