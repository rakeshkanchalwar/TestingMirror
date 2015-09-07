package com.bitwise.app.graph.command;

import org.eclipse.gef.commands.Command;

import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Connection;

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
	 * @param source the source endpoint (a non-null Shape instance)
	 * @param lineStyle the desired line style. See Connection#setLineStyle(int) for details
	 * @throws IllegalArgumentException if source is null
	 * @see Connection#setLineStyle(int)
	 */
	public ConnectionCreateCommand(Component source, int lineStyle) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		setLabel("connection creation");
		this.source = source;
		this.lineStyle = lineStyle;
	}

	public boolean canExecute() {
		if (source.equals(target)) {
			return false;
		}
		// return false, if the source -> target connection exists already
		for (Connection connection : source.getSourceConnections()) {
			if (connection.getTarget().equals(target)) {
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
		connection = new Connection(source, target);
		// use the supplied line style
		connection.setLineStyle(lineStyle);
	}

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

	public void undo() {
		connection.disconnect();
	}
}