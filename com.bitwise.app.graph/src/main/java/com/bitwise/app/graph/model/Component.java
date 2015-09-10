package com.bitwise.app.graph.model;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;


public class Component extends Model {
	private static final long serialVersionUID = 2587870876576884352L;
	
	public static final String LOCATION_PROP = "Location";
	/** Property ID to use then the size of this shape is modified. */
	public static final String SIZE_PROP = "Size";
	/** Property ID to use when the list of outgoing connections is modified. */
	//public static final String SOURCE_CONNECTIONS_PROP = "SourceConnection";
	/** Property ID to use when the list of incoming connections is modified. */
	//public static final String TARGET_CONNECTIONS_PROP = "TargetConnection";
	

	/** Location of this shape. */
	private Point location = new Point(0, 0);
	/** Size of this shape. */
	private Dimension size = new Dimension(80, 60);
	
	private Map<String, String> properties = new HashMap<>();
	
	private Container parent;
	
	protected Hashtable inputs = new Hashtable(7);
	protected Vector outputs = new Vector(4, 4);
	
	public static final String INPUTS = "inputs", 
			OUTPUTS = "outputs"; 
	
	protected int numberOfOutGoingLinks=0;
	protected int numberOfInComingLinks=0;
	
	//protected int numberOfOutGoingLinksLimit=1, numberOfInComingLinksLimit=1;
	protected boolean allowMultipleLinks=false;
	
	public boolean allowMoreOutGoingLinks(){
		
		if(numberOfOutGoingLinks<1 || allowMultipleLinks)
			return true;
		return false;

	}
	public boolean allowMoreInComingLinks(){
		
		if(numberOfInComingLinks<1 || allowMultipleLinks)
			return true;
		return false;
	}
	
	public void connectInput(ComponentConnection c) {
		inputs.put(c.getTargetTerminal(), c);
		numberOfInComingLinks++;
		fireStructureChange(INPUTS, c);
	}

	public void connectOutput(ComponentConnection c) {
		outputs.addElement(c);
		numberOfOutGoingLinks++;
		fireStructureChange(OUTPUTS, c);
	}
	
	public List getSourceConnections() {
		
		return (Vector) outputs.clone();
	}

	public List getTargetConnections() {
		
		Enumeration elements = inputs.elements();
		Vector v = new Vector(inputs.size());
		while (elements.hasMoreElements())
			v.addElement(elements.nextElement());
		return v;
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
	

	public boolean isAllowMultipleLinks() {
		return allowMultipleLinks;
	}
	
	public void setAllowMultipleLinks(boolean allowMultipleLinks) {
		this.allowMultipleLinks = allowMultipleLinks;
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

	public Container getParent() {
		return parent;
	}

	public void setParent(Container parent) {
		this.parent = parent;
	}
}
