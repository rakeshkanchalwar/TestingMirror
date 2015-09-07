package com.bitwise.app.graph.command;

import org.eclipse.gef.commands.Command;

import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Connection;

public class ConnectionReconnectCommand extends Command{
	/** The connection instance to reconnect. */
	private Connection connection;
	/** The new source endpoint. */
	private Component newSource;
	/** The new target endpoint. */
	private Component newTarget;
	/** The original source endpoint. */
	private final Component oldSource;
	/** The original target endpoint. */
	private final Component oldTarget;

	/**
	 * Instantiate a command that can reconnect a Connection instance to a
	 * different source or target endpoint.
	 * @param connection the connection instance to reconnect (non-null)
	 * @throws IllegalArgumentException if conn is null
	 */
	public ConnectionReconnectCommand(Connection connection) {
		if (connection == null) {
			throw new IllegalArgumentException();
		}
		this.connection = connection;
		this.oldSource = connection.getSource();
		this.oldTarget = connection.getTarget();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		if (newSource != null) {
			return checkSourceReconnection();
		} else if (newTarget != null) {
			return checkTargetReconnection();
		}
		return false;
	}

	/**
	 * Return true, if reconnecting the connection-instance to newSource is
	 * allowed.
	 */
	private boolean checkSourceReconnection() {
		// connection endpoints must be different Shapes
		if (newSource.equals(oldTarget)) {
			return false;
		}
		// return false, if the connection exists already
		for (Connection connection : newSource.getSourceConnections()) {
			// return false if a newSource -> oldTarget connection exists
			// already
			// and it is a different instance than the connection-field
			if (connection.getTarget().equals(oldTarget) && !connection.equals(connection)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Return true, if reconnecting the connection-instance to newTarget is
	 * allowed.
	 */
	private boolean checkTargetReconnection() {
		// connection endpoints must be different Shapes
		if (newTarget.equals(oldSource)) {
			return false;
		}
		// return false, if the connection exists already
		for (Connection connection : newTarget.getTargetConnections()) {
			// return false if a oldSource -> newTarget connection exists
			// already
			// and it is a differenct instance that the connection-field
			if (connection.getSource().equals(oldSource) && !connection.equals(connection)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Reconnect the connection to newSource (if setNewSource(...) was invoked
	 * before) or newTarget (if setNewTarget(...) was invoked before).
	 */
	public void execute() {
		if (newSource != null) {
			connection.reconnect(newSource, oldTarget);
		} else if (newTarget != null) {
			connection.reconnect(oldSource, newTarget);
		} else {
			throw new IllegalStateException("Should not happen");
		}
	}

	/**
	 * Set a new source endpoint for this connection. When execute() is invoked,
	 * the source endpoint of the connection will be attached to the supplied
	 * Shape instance.
	 * <p>
	 * Note: Calling this method, deactivates reconnection of the <i>target</i>
	 * endpoint. A single instance of this command can only reconnect either the
	 * source or the target endpoint.
	 * </p>
	 * 
	 * @param connectionSource
	 *            a non-null Shape instance, to be used as a new source endpoint
	 * @throws IllegalArgumentException
	 *             if connectionSource is null
	 */
	public void setNewSource(Component connectionSource) {
		if (connectionSource == null) {
			throw new IllegalArgumentException();
		}
		setLabel("move connection startpoint");
		newSource = connectionSource;
		newTarget = null;
	}

	/**
	 * Set a new target endpoint for this connection When execute() is invoked,
	 * the target endpoint of the connection will be attached to the supplied
	 * Shape instance.
	 * <p>
	 * Note: Calling this method, deactivates reconnection of the <i>source</i>
	 * endpoint. A single instance of this command can only reconnect either the
	 * source or the target endpoint.
	 * </p>
	 * 
	 * @param connectionTarget
	 *            a non-null Shape instance, to be used as a new target endpoint
	 * @throws IllegalArgumentException
	 *             if connectionTarget is null
	 */
	public void setNewTarget(Component connectionTarget) {
		if (connectionTarget == null) {
			throw new IllegalArgumentException();
		}
		setLabel("move connection endpoint");
		newSource = null;
		newTarget = connectionTarget;
	}

	/**
	 * Reconnect the connection to its original source and target endpoints.
	 */
	public void undo() {
		connection.reconnect(oldSource, oldTarget);
	}


}
