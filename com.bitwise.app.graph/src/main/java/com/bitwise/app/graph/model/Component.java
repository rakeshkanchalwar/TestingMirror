package com.bitwise.app.graph.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

public class Component extends Model {
	private static final long serialVersionUID = 2587870876576884352L;
	
	public static final String LOCATION_PROP = "Location";
	/** Property ID to use then the size of this shape is modified. */
	public static final String SIZE_PROP = "Size";
	/** Property ID to use when the list of outgoing connections is modified. */
	public static final String SOURCE_CONNECTIONS_PROP = "SourceConnection";
	/** Property ID to use when the list of incoming connections is modified. */
	public static final String TARGET_CONNECTIONS_PROP = "TargetConnection";
	
	private Map<String, String> properties = new HashMap<>();
	
	/** List of outgoing Connections. */
	private List<Connection> sourceConnections = new ArrayList<>();
	/** List of incoming Connections. */
	private List<Connection> targetConnections = new ArrayList<>();
	
	
	/** Location of this shape. */
	private Point location = new Point(0, 0);
	/** Size of this shape. */
	private Dimension size = new Dimension(95, 105);
	
	/**
	 * Return a List of outgoing Connections.
	 */
	public List<Connection> getSourceConnections() {
		return new ArrayList<Connection>(sourceConnections);
	}

	/**
	 * Return a List of incoming Connections.
	 */
	public List<Connection> getTargetConnections() {
		return new ArrayList<Connection>(targetConnections);
	}
	
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
	/**
	 * Return the property value for the given propertyId, or null.
	 */
	public Object getPropertyValue(Object propertyId) {
		if(properties.containsKey(propertyId)){
			return properties.get(propertyId);
		}
		return null;
	}
	
	/**
	 * Set the property value for the given property id. 
	 */
	public void setPropertyValue(Object propertyId, Object value) {
		if(properties.containsKey(propertyId)){
			properties.put((String)propertyId, (String)value);
		}
	}
	
	/**
	 * Add an incoming or outgoing connection to this shape.
	 * 
	 * @param connection
	 *            a non-null connection instance
	 * @throws IllegalArgumentException
	 *             if the connection is null or has not distinct endpoints
	 */
	void addConnection(Connection connection) {
		if (connection == null || connection.getSource() == connection.getTarget()) {
			throw new IllegalArgumentException();
		}
		if (connection.getSource() == this) {
			sourceConnections.add(connection);
			firePropertyChange(SOURCE_CONNECTIONS_PROP, null, connection);
		} else if (connection.getTarget() == this) {
			targetConnections.add(connection);
			firePropertyChange(TARGET_CONNECTIONS_PROP, null, connection);
		}
	}
	
	/**
	 * Remove an incoming or outgoing connection from this shape.
	 * 
	 * @param connection
	 *            a non-null connection instance
	 * @throws IllegalArgumentException
	 *             if the parameter is null
	 */
	void removeConnection(Connection connection) {
		if (connection == null) {
			throw new IllegalArgumentException();
		}
		if (connection.getSource() == this) {
			sourceConnections.remove(connection);
			firePropertyChange(SOURCE_CONNECTIONS_PROP, null, connection);
		} else if (connection.getTarget() == this) {
			targetConnections.remove(connection);
			firePropertyChange(TARGET_CONNECTIONS_PROP, null, connection);
		}
	}
	
	/**
	 * Set the Location of this shape.
	 * @param newLocation a non-null Point instance
	 * @throws IllegalArgumentException if the parameter is null
	 */
	public void setLocation(Point newLocation) {
		if (newLocation == null) {
			throw new IllegalArgumentException();
		}
		location.setLocation(newLocation);
		firePropertyChange(LOCATION_PROP, null, location);
	}
	
	/**
	 * Return the Location of this shape.
	 * @return a non-null location instance
	 */
	public Point getLocation() {
		return location.getCopy();
	}
	
	/**
	 * Set the Size of this shape. Will not modify the size if newSize is null.
	 * 
	 * @param newSize
	 *            a non-null Dimension instance or null
	 */
	public void setSize(Dimension newSize) {
		if (newSize != null) {
			size.setSize(newSize);
			firePropertyChange(SIZE_PROP, null, size);
		}
	}
	
	/**
	 * Return the Size of this shape.
	 * 
	 * @return a non-null Dimension instance
	 */
	public Dimension getSize() {
		return size.getCopy();
	}
}
