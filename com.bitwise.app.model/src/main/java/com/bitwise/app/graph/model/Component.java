package com.bitwise.app.graph.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

public class Component extends Model {
	private static final long serialVersionUID = 2587870876576884352L;

	public static enum Props {
		LOCATION_PROP("Location"),
		SIZE_PROP("Size"),
		INPUTS("inputs"),
		OUTPUTS("outputs");
		
		private String value;
		private Props(String value){
			this.value = value;
		}
		
		public String getValue(){
			return this.value;
		}
		
		public boolean eq(String property){
			return this.value.equals(property);
		}
	}
	
	private Point location;
	private Dimension size;
	private Map<String, Object> properties;
	private Container parent;
	private Hashtable<String, Link> inputs;
	private List<Link> outputs;
	private boolean newInstance;
	private String basename;

	public Component(){
		location = new Point(0, 0);
		size = new Dimension(80, 60);
		properties = new LinkedHashMap<>();
		inputs = new Hashtable<String, Link>();
		outputs = new ArrayList<Link>();
		newInstance = true;
	}
	
	private void updateConnectionProperty(String prop, Object newValue) {
		firePropertyChange(prop, null,newValue);
	}
	
	public void connectInput(Link c) {
		inputs.put(c.getTargetTerminal(), c);
		updateConnectionProperty(Props.INPUTS.getValue(), c);
	}

	public void connectOutput(Link c) {
		outputs.add(c);
		updateConnectionProperty(Props.OUTPUTS.getValue(), c);
	}
	
	public void disconnectInput(Link c) {
		inputs.remove(c.getTargetTerminal());
		updateConnectionProperty(Props.INPUTS.getValue(), c);
	}

	public void disconnectOutput(Link c) {
		outputs.remove(c);
		updateConnectionProperty(Props.OUTPUTS.getValue(), c);
	}
	
	/* add comments as function called by gef*/
	public List<Link> getSourceConnections() {
		return outputs;
	}

	public List<Link> getTargetConnections() {
		return Arrays.asList((inputs.values().toArray(new Link[inputs.size()])));
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	/**
	 * Return the property value for the given propertyId, or null.
	 */
	public Object getPropertyValue(Object propertyId) {
		if (properties.containsKey(propertyId)) {
			return properties.get(propertyId);
		}
		throw new PropertyNotAvailableException();
	}

	/**
	 * Set the property value for the given property id.
	 */
	public void setPropertyValue(Object propertyId, Object value) {
		properties.put((String) propertyId, (String) value);
	}


	/**
	 * Set the Location of this shape.
	 * 
	 * @param newLocation
	 *            a non-null Point instance
	 * @throws IllegalArgumentException
	 *             if the parameter is null
	 */
	public void setLocation(Point newLocation) {
		location.setLocation(newLocation);
		firePropertyChange(Props.LOCATION_PROP.getValue(), null, location);
	}

	/**
	 * Return the Location of this shape.
	 * 
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
			firePropertyChange(Props.SIZE_PROP.getValue(), null, size);
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

	public LinkedHashMap<String, Object> getProperties() {
		return (LinkedHashMap<String, Object>) properties;
	}
	
	private class PropertyNotAvailableException extends RuntimeException {
		private static final long serialVersionUID = -7978238880803956846L;

	}

	public boolean isNewInstance() {
		return newInstance;
	}

	public void setNewInstance(boolean newInstance) {
		this.newInstance = newInstance;
	}

	public String getBasename() {
		return basename;
	}

	public void setBasename(String basename) {
		this.basename = basename;
	}

}
