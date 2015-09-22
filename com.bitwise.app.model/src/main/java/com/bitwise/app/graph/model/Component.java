package com.bitwise.app.graph.model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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
	
	private Point location = new Point(0, 0);
	private Dimension size = new Dimension(80, 60);
	private Map<String, Object> properties = new LinkedHashMap<>();

	private Container parent;

	protected Hashtable<String, Link> inputs = new Hashtable<String, Link>(7);
	protected List<Link> outputs = new ArrayList<Link>();

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

	public List<Link> getSourceConnections() {
		return outputs;
	}

	public List<Link> getTargetConnections() {

		Enumeration<Link> elements = inputs.elements();
		Vector<Link> v = new Vector<Link>(inputs.size());
		while (elements.hasMoreElements())
			v.addElement(elements.nextElement());
		return v;
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
		return null;
	}

	/**
	 * Set the property value for the given property id.
	 */
	public void setPropertyValue(Object propertyId, Object value) {
		if (properties.containsKey(propertyId)) {
			properties.put((String) propertyId, (String) value);
		}
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
		if (newLocation == null) {
			throw new IllegalArgumentException();
		}
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
		// TODO Auto-generated method stub
		return (LinkedHashMap<String, Object>) properties;
	}

	public boolean isUniqueCompName(String componentName) {

		return getParent().isUniqueCompName(componentName);
	}

	public ArrayList<String> getComponentNames() {
		return getParent().getComponentNames();
	}

	public void setComponentNames(ArrayList<String> componentNames) {
		getParent().setComponentNames(componentNames);
	}

}
