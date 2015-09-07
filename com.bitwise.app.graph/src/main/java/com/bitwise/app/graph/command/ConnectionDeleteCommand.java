package com.bitwise.app.graph.command;

import org.eclipse.gef.commands.Command;

import com.bitwise.app.graph.model.Connection;

public class ConnectionDeleteCommand extends Command {
	/** Connection instance to disconnect. */
	private final Connection connection;
	
	/**
	 * Create a command that will disconnect a connection from its endpoints.
	 * @param connection the connection instance to disconnect (non-null)
	 * @throws IllegalArgumentException if conn is null
	 */
	public ConnectionDeleteCommand(Connection connection) {
		if (connection == null) {
			throw new IllegalArgumentException();
		}
		setLabel("connection deletion");
		this.connection = connection;
	}
	
	public void execute() {
		connection.disconnect();
	}

	public void undo() {
		connection.reconnect();
	}
}
