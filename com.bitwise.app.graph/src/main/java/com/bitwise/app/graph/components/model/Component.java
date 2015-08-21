package com.bitwise.app.graph.components.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.bitwise.app.adapters.ComponentProperty;
import com.bitwise.app.graph.components.ComponentsPlugin;
import com.bitwise.app.graph.components.model.Connection;
import com.bitwise.app.graph.components.model.ModelElement;

public abstract class Component extends ModelElement{
	/**
	 * A static array of property descriptors. There is one IPropertyDescriptor
	 * entry per editable property.
	 * 
	 * @see #getPropertyDescriptors()
	 * @see #getPropertyValue(Object)
	 * @see #setPropertyValue(Object, Object)
	 */
	private static IPropertyDescriptor[] descriptors;
	/**
	 * ID for the Height property value (used for by the corresponding property
	 * descriptor).
	 */
	public static final String NAME_PROP = "Shape.Name";
	//private static final String DELIMITER_PROP = "Shape.Delimiter";
	
	/** Property ID to use when the location of this shape is modified. */
	private static final String HEIGHT_PROP = "Shape.Height";
	/** Property ID to use when the location of this shape is modified. */
	public static final String LOCATION_PROP = "Shape.Location";
	private static final long serialVersionUID = 1;
	/** Property ID to use then the size of this shape is modified. */
	public static final String SIZE_PROP = "Shape.Size";
	/** Property ID to use when the list of outgoing connections is modified. */
	public static final String SOURCE_CONNECTIONS_PROP = "Shape.SourceConn";
	/** Property ID to use when the list of incoming connections is modified. */
	public static final String TARGET_CONNECTIONS_PROP = "Shape.TargetConn";
	/**
	 * ID for the Width property value (used for by the corresponding property
	 * descriptor).
	 */
	private static final String WIDTH_PROP = "Shape.Width";

	/**
	 * ID for the X property value (used for by the corresponding property
	 * descriptor).
	 */
	private static final String XPOS_PROP = "Shape.xPos";
	/**
	 * ID for the Y property value (used for by the corresponding property
	 * descriptor).
	 */
	private static final String YPOS_PROP = "Shape.yPos";
	public static final String RENAME_PROP = "Shape.Rename";

	/*
	 * Initializes the property descriptors array.
	 * 
	 * @see #getPropertyDescriptors()
	 * 
	 * @see #getPropertyValue(Object)
	 * 
	 * @see #setPropertyValue(Object, Object)
	 */
	static {
		descriptors = new IPropertyDescriptor[] {
				new TextPropertyDescriptor(NAME_PROP, "Name"),};
				
	} 

	protected static Image createImage(String name) {
		InputStream stream = ComponentsPlugin.class.getResourceAsStream(name);
		Image image = new Image(null, stream);
		try {
			stream.close();
		} catch (IOException ioe) {
		}
		return image;
	}

		
	/** Location of this shape. */
	private Point location = new Point(0, 0);
	/** Size of this shape. */
	private Dimension size = new Dimension(95, 105);
	private String componentName = ""; 
	private String delimiter = "";
	/** List of outgoing Connections. */
	private List sourceConnections = new ArrayList();
	/** List of incoming Connections. */
	private List targetConnections = new ArrayList();
	private LinkedHashMap<String,ComponentProperty> componentProperties=null;

	/**
	 * Add an incoming or outgoing connection to this shape.
	 * 
	 * @param conn
	 *            a non-null connection instance
	 * @throws IllegalArgumentException
	 *             if the connection is null or has not distinct endpoints
	 */
	void addConnection(Connection conn) {
		if (conn == null || conn.getSource() == conn.getTarget()) {
			throw new IllegalArgumentException();
		}
		if (conn.getSource() == this) {
			sourceConnections.add(conn);
			firePropertyChange(SOURCE_CONNECTIONS_PROP, null, conn);
		} else if (conn.getTarget() == this) {
			targetConnections.add(conn);
			firePropertyChange(TARGET_CONNECTIONS_PROP, null, conn);
		}
	}

	/**
	 * Return a pictogram (small icon) describing this model element. Children
	 * should override this method and return an appropriate Image.
	 * 
	 * @return a 16x16 Image or null
	 */
	public abstract Image getIcon();

	/**
	 * Return the Location of this shape.
	 * 
	 * @return a non-null location instance
	 */
	public Point getLocation() {
		return location.getCopy();
	}

	/**
	 * Returns an array of IPropertyDescriptors for this shape.
	 * <p>
	 * The returned array is used to fill the property view, when the edit-part
	 * corresponding to this model element is selected.
	 * </p>
	 * 
	 * @see #descriptors
	 * @see #getPropertyValue(Object)
	 * @see #setPropertyValue(Object, Object)
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}

	/**
	 * Return the property value for the given propertyId, or null.
	 * <p>
	 * The property view uses the IDs from the IPropertyDescriptors array to
	 * obtain the value of the corresponding properties.
	 * </p>
	 * 
	 * @see #descriptors
	 * @see #getPropertyDescriptors()
	 */
	public Object getPropertyValue(Object propertyId) {
		if (XPOS_PROP.equals(propertyId)) {
			return Integer.toString(location.x);
		}
		if (YPOS_PROP.equals(propertyId)) {
			return Integer.toString(location.y);
		}
		if (HEIGHT_PROP.equals(propertyId)) {
			return Integer.toString(size.height);
		}
		if (WIDTH_PROP.equals(propertyId)) {
			return Integer.toString(size.width);
		}
		if (NAME_PROP.equals(propertyId)) {
			return componentName;
		}else{
			if(componentProperties.containsKey(propertyId)){
				return componentProperties.get(propertyId).getPropValue();
			}else{
				return super.getPropertyValue(propertyId);
			}
		}
//		if (DELIMITER_PROP.equals(propertyId)) {
//			return delimiter;
//		}
//		return super.getPropertyValue(propertyId);
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
		firePropertyChange(NAME_PROP, null, componentName);
		//getListeners().firePropertyChange(PROPERTY_RENAME, oldName, this.name);
	}
	public String getDelimiter() {
		return delimiter;
	}

//	public void setDelimiter(String delimiter) {
//		this.delimiter = delimiter;
//		firePropertyChange(DELIMITER_PROP, null, delimiter);
//	}

	/**
	 * Return the Size of this shape.
	 * 
	 * @return a non-null Dimension instance
	 */
	public Dimension getSize() {
		return size.getCopy();
	}

	/**
	 * Return a List of outgoing Connections.
	 */
	public List getSourceConnections() {
		return new ArrayList(sourceConnections);
	}

	/**
	 * Return a List of incoming Connections.
	 */
	public List getTargetConnections() {
		return new ArrayList(targetConnections);
	}

	/**
	 * Remove an incoming or outgoing connection from this shape.
	 * 
	 * @param conn
	 *            a non-null connection instance
	 * @throws IllegalArgumentException
	 *             if the parameter is null
	 */
	void removeConnection(Connection conn) {
		if (conn == null) {
			throw new IllegalArgumentException();
		}
		if (conn.getSource() == this) {
			sourceConnections.remove(conn);
			firePropertyChange(SOURCE_CONNECTIONS_PROP, null, conn);
		} else if (conn.getTarget() == this) {
			targetConnections.remove(conn);
			firePropertyChange(TARGET_CONNECTIONS_PROP, null, conn);
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
		firePropertyChange(LOCATION_PROP, null, location);
	}

	/**
	 * Set the property value for the given property id. If no matching id is
	 * found, the call is forwarded to the superclass.
	 * <p>
	 * The property view uses the IDs from the IPropertyDescriptors array to set
	 * the values of the corresponding properties.
	 * </p>
	 * 
	 * @see #descriptors
	 * @see #getPropertyDescriptors()
	 */
	public void setPropertyValue(Object propertyId, Object value) {
		if (XPOS_PROP.equals(propertyId)) {
			int x = Integer.parseInt((String) value);
			setLocation(new Point(x, location.y));
		} else if (YPOS_PROP.equals(propertyId)) {
			int y = Integer.parseInt((String) value);
			setLocation(new Point(location.x, y));
		} else if (HEIGHT_PROP.equals(propertyId)) {
			int height = Integer.parseInt((String) value);
			setSize(new Dimension(size.width, height));
		} else if (WIDTH_PROP.equals(propertyId)) {
			int width = Integer.parseInt((String) value);
			setSize(new Dimension(width, size.height));
		} else if (NAME_PROP.equals(propertyId)) {
			setComponentName((String)value);
		} 
//		else if (DELIMITER_PROP.equals(propertyId)) {
//			setDelimiter((String)value);
//		}
		else {
			super.setPropertyValue(propertyId, value);
		}
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

	public void addPropertyDescriptors(IPropertyDescriptor[] propertyDescriptors) {
		IPropertyDescriptor[] tempdescriptors = new IPropertyDescriptor[propertyDescriptors.length + descriptors.length]; 
		/*for(int i=0;i<propertyDescriptors.length;i++){
			tempdescriptors[i] = propertyDescriptors[i];
		}*/
		System.arraycopy(propertyDescriptors, 0, tempdescriptors, 0, propertyDescriptors.length);
		System.arraycopy(descriptors, 0, tempdescriptors, propertyDescriptors.length, descriptors.length);
		
	
		descriptors = tempdescriptors;
		
		// TODO Auto-generated method stub
		
	}

	public void addNewProperties(
			LinkedHashMap<String, ComponentProperty> componentProperties) {
		this.componentProperties=componentProperties;
		// TODO Auto-generated method stub		
	}
	
	public LinkedHashMap<String, ComponentProperty> getExtraProps(){
		return this.componentProperties;
	}
}
