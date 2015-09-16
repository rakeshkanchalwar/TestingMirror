package com.bitwise.app.graph.model;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;


public class Component extends Model {
	private static final long serialVersionUID = 2587870876576884352L;
	
	public static final String LOCATION_PROP = "Location";
	public static final String SIZE_PROP = "Size";
	
	private Point location = new Point(0, 0);
	private Dimension size = new Dimension(80, 60);
	private Map<String, Object> properties = new LinkedHashMap<>();
	private Container parent;
	
	protected Hashtable<String, ComponentConnection> inputs = new Hashtable<String, ComponentConnection>(7);
	protected Hashtable<String, ComponentConnection> outputs = new Hashtable<String, ComponentConnection>(7);
	
	public static final String INPUTS = "inputs", 
			OUTPUTS = "outputs"; 
	
	protected int numberOfOutGoingLinks=0;
	protected int numberOfInComingLinks=0;
	
	//-------------------------------------
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
	
	public void setAllowMultipleLinks(boolean allowMultipleLinks) {
		this.allowMultipleLinks = allowMultipleLinks;
	}
	//-------------------------------------
	
	public void connectInput(ComponentConnection c) {
		inputs.put(c.getTargetTerminal(), c);
		numberOfInComingLinks++;
		fireStructureChange(INPUTS, c);
	}

	public void connectOutput(ComponentConnection c) {
		outputs.put(c.getSourceTerminal(), c);
		numberOfOutGoingLinks++;
		fireStructureChange(OUTPUTS, c);
	}
	
	public List<ComponentConnection> getSourceConnections() {
		Enumeration<ComponentConnection> elements = outputs.elements();
		Vector<ComponentConnection> v = new Vector<ComponentConnection>(outputs.size());
		while (elements.hasMoreElements())
			v.addElement(elements.nextElement());
		return v;
	}

	public List<ComponentConnection> getTargetConnections() {
		
		Enumeration<ComponentConnection> elements = inputs.elements();
		Vector<ComponentConnection> v = new Vector<ComponentConnection>(inputs.size());
		while (elements.hasMoreElements())
			v.addElement(elements.nextElement());
		return v;
	}
		
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
	
	public Object getPropertyValue(Object propertyId) {
		if(properties.containsKey(propertyId)){
			return properties.get(propertyId);
		}
		return null;
	}
	
	public void setPropertyValue(Object propertyId, Object value) {
		if(properties.containsKey(propertyId)){
			properties.put((String)propertyId, (String)value);
		}
	}
	
	public void setLocation(Point newLocation) {
		if (newLocation == null) {
			throw new IllegalArgumentException();
		}
		location.setLocation(newLocation);
		firePropertyChange(LOCATION_PROP, null, location);
	}
	
	
	public Point getLocation() {
		return location.getCopy();
	}

	public void setSize(Dimension newSize) {
		if (newSize != null) {
			size.setSize(newSize);
			firePropertyChange(SIZE_PROP, null, size);
		}
	}
	
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
		return  (LinkedHashMap<String, Object>) properties;
	}
	
}
