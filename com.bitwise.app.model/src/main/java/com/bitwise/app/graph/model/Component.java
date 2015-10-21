package com.bitwise.app.graph.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import com.bitwise.app.common.component.config.PortSpecification;
import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.graph.processor.DynamicClassProcessor;

public abstract class Component extends Model {
	private static final long serialVersionUID = 2587870876576884352L;

	public static enum Props {
		LOCATION_PROP("Location"),
		SIZE_PROP("Size"),
		INPUTS("inputs"),
		OUTPUTS("outputs"),
		STATUS("status");
		
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
	
	public static enum ValidityStatus {
		WARN,
		ERROR,
		VALID;
	}
	
	private final Point location;
	private final Dimension size;
	private Map<String, Object> properties;
	private Container parent;
	private ValidityStatus validityStatus;
	
	private final Hashtable<String, ArrayList<Link>> inputLinksHash;
	private final Hashtable<String, ArrayList<Link>> outputLinksHash;
    private ArrayList<Link> inputLinks = new ArrayList<Link>();
    private ArrayList<Link> outputLinks = new ArrayList<Link>();
	private final List<String> inputportTerminals;
	private final List<String> outputPortTerminals;
	private boolean newInstance;
	private String basename;
	private String category;
	private Hashtable<String, Port> ports;
	private String componentName;
	List<PortSpecification> portSpecification;

	public Component(){
		location = new Point(0, 0);
		size = new Dimension(100, 75);
		properties = new LinkedHashMap<>();
		inputLinksHash = new Hashtable<String, ArrayList<Link>>();
		inputLinks = new ArrayList<Link>();
		outputLinksHash = new Hashtable<String, ArrayList<Link>>();
		outputLinks = new ArrayList<Link>();
		inputportTerminals = new ArrayList<String>();
		outputPortTerminals = new ArrayList<String>();
		newInstance = true;
		validityStatus = ValidityStatus.WARN;
		
		initPortSettings();
		
	}
	
	private void initPortSettings(){
		componentName = DynamicClassProcessor.INSTANCE
				.getClazzName(this.getClass());
		portSpecification = XMLConfigUtil.INSTANCE.getComponent(componentName).getPort().getPortSpecification();
		
		ports = new Hashtable<String, Port>();
		
		for(PortSpecification p:portSpecification)
		{ 	
			String portTerminal = p.getTypeOfPort() + p.getSequenceOfPort();
			Port port = new Port(portTerminal, this, p.getNumberOfPorts(), p.getTypeOfPort(), p.getSequenceOfPort());
			ports.put(portTerminal, port);
		}
	}
	
	public List<PortSpecification> getPortSpecification() {
		return portSpecification;
	}

	public Port getPort(String terminal) {
		return ports.get(terminal);
	}

	public List<Port> getChildren() {
		return new ArrayList<Port>(ports.values());
		
	}
	
	private void updateConnectionProperty(String prop, Object newValue) {
		firePropertyChange(prop, null,newValue);
	}
	
	public void connectInput(Link c) {
		inputLinks.add(c);
		inputLinksHash.put(c.getTargetTerminal(), inputLinks);
		updateConnectionProperty(Props.INPUTS.getValue(), c);
	}

	public void connectOutput(Link c) {
		outputLinks.add(c);
		outputLinksHash.put(c.getSourceTerminal(), outputLinks);
		updateConnectionProperty(Props.OUTPUTS.getValue(), c);
	}
	
	public void disconnectInput(Link c) {
		inputLinks.remove(c);
		inputLinksHash.remove(c.getTargetTerminal());
		updateConnectionProperty(Props.INPUTS.getValue(), c);
	}

	public void disconnectOutput(Link c) {
		outputLinks.remove(c);
		outputLinksHash.remove(c.getSourceTerminal());
		updateConnectionProperty(Props.OUTPUTS.getValue(), c);
	}
	
	/* add comments as function called by gef*/
	public List<Link> getSourceConnections() {
		return outputLinks;
	}

	public List<Link> getTargetConnections() {
		return inputLinks;
	}

	
	public void engageInputPort(String terminal){
		inputportTerminals.add(terminal);
	}
	public void freeInputPort(String terminal){
		inputportTerminals.remove(terminal);
	}
	public boolean isInputPortEngaged(String terminal) {
		return inputportTerminals.contains(terminal);
		
	}

	
	public void engageOutputPort(String terminal){
		outputPortTerminals.add(terminal);
	}
	public void freeOutputPort(String terminal){
		outputPortTerminals.remove(terminal);
	}
	public boolean isOutputPortEngaged(String terminal) {
		return outputPortTerminals.contains(terminal);
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
		resetLocation(newLocation);		
		location.setLocation(newLocation);
		firePropertyChange(Props.LOCATION_PROP.getValue(), null, location);
	}

	/**
	 *  reset if x or y of components are negative
	 * @param newLocation
	 */
	private void resetLocation(Point newLocation) {
		if(newLocation.x < 0 ){
			newLocation.x = 0;			
		}
		
		if(newLocation.y < 0){
			newLocation.y = 0;
		}
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
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public ValidityStatus getValidityStatus() {
		return validityStatus;
	}
	
	public void setValidityStatus(ValidityStatus validityStatus) {
		this.validityStatus = validityStatus;
	}
	
	//For Target XML
	public abstract String getConverter();
	
	@Override
	public Component clone() throws CloneNotSupportedException {
		Component component = null;
		try {
			component = this.getClass().newInstance();
		} catch (Exception e) {
			//TODO : add logger
			e.printStackTrace();
		} 
		component.setBasename(getBasename());
		component.setCategory(getCategory());
		component.setParent(getParent());
		component.setProperties((Map<String, Object>) getProperties().clone());
		component.setPropertyValue("name", getBasename());
	    component.setSize(getSize());
	    component.setLocation(getLocation());	
	    return component;
	}

}
