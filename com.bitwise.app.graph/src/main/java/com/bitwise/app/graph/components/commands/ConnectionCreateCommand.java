package com.bitwise.app.graph.components.commands;

import java.util.Iterator;

import org.eclipse.gef.commands.Command;

import com.bitwise.app.graph.components.model.Component;
import com.bitwise.app.graph.components.model.Connection;


public class ConnectionCreateCommand extends Command{
	/** The connection instance. */
	private Connection connection;
	/** The desired line style for the connection (dashed or solid). */
	private final int lineStyle;

	/** Start endpoint for the connection. */
	private final Component source;
	/** Target endpoint for the connection. */
	private Component target;

	/**
	 * Instantiate a command that can create a connection between two shapes.
	 * 
	 * @param source
	 *            the source endpoint (a non-null Shape instance)
	 * @param lineStyle
	 *            the desired line style. See Connection#setLineStyle(int) for
	 *            details
	 * @throws IllegalArgumentException
	 *             if source is null
	 * @see Connection#setLineStyle(int)
	 */
	public ConnectionCreateCommand(Component source, int lineStyle) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		setLabel("connection creation");
		this.source = source;
		this.lineStyle = lineStyle;
		System.out.println("ConnectionCreateCommand...");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		// disallow source -> source connections
		if (source.equals(target)) {
			return false;
		}
		// return false, if the source -> target connection exists already
		for (Iterator iter = source.getSourceConnections().iterator(); iter
				.hasNext();) {
			Connection conn = (Connection) iter.next();
			if (conn.getTarget().equals(target)) {
				return false;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		// create a new connection between source and target
		System.out.println("ConnectionCreateCommand:execuet()");
		System.out.println("source: "+source.getClass());
		System.out.println("target: "+target.getClass());
		connection = new Connection(source, target);
		// use the supplied line style
		connection.setLineStyle(lineStyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		connection.reconnect();
	}

	/**
	 * Set the target endpoint for the connection.
	 * 
	 * @param target
	 *            that target endpoint (a non-null Shape instance)
	 * @throws IllegalArgumentException
	 *             if target is null
	 */
	public void setTarget(Component target) {
		if (target == null) {
			throw new IllegalArgumentException();
		}
		this.target = target;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		connection.disconnect();
	}
}
