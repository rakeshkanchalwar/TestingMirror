package com.bitwise.app.graph.components.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.bitwise.app.adapters.ComponentProperty;
import com.bitwise.app.graph.components.ComponentsPlugin;
import com.bitwise.app.graph.components.model.ModelElement;

public abstract class Component extends ModelElement{

	private static final long serialVersionUID = 1;

	private static IPropertyDescriptor[] descriptors;

	

	public static final String NAME_PROP = "Shape.Name";
	private static final String HEIGHT_PROP = "Shape.Height";
	public static final String LOCATION_PROP = "Shape.Location";
	public static final String SIZE_PROP = "Shape.Size";
	public static final String SOURCE_CONNECTIONS_PROP = "Shape.SourceConn";
	public static final String TARGET_CONNECTIONS_PROP = "Shape.TargetConn";
	private static final String WIDTH_PROP = "Shape.Width";
	private static final String XPOS_PROP = "Shape.xPos";
	private static final String YPOS_PROP = "Shape.yPos";
	public static final String RENAME_PROP = "Shape.Rename";
	private static final LinkedHashMap<String,String> propertyNames;

	private Point location = new Point(0, 0);
	private Dimension size = new Dimension(95, 105);
	private String componentName = ""; 
	private List sourceConnections = new ArrayList();
	private List targetConnections = new ArrayList();
	private LinkedHashMap<String, String> propertyValues = new LinkedHashMap<>();
	
	static {
		propertyNames= new LinkedHashMap<>();
		descriptors = new IPropertyDescriptor[] {
				new TextPropertyDescriptor(NAME_PROP, "Name"),};
	} 
	
	public static void setDescriptors(IPropertyDescriptor[] descriptors) {
		Component.descriptors = descriptors;
	}

	public void setOtherPropertyNames(LinkedHashMap<String,ComponentProperty> componentProperties){
		for(String propID : componentProperties.keySet()){
			propertyNames.put(componentProperties.get(propID).getPropID(), componentProperties.get(propID).getPropName());
		}
	}
	

	public void addPropertyDescriptors(IPropertyDescriptor[] propertyDescriptors) {
		IPropertyDescriptor[] tempdescriptors = new IPropertyDescriptor[propertyDescriptors.length + descriptors.length]; 
		System.arraycopy(propertyDescriptors, 0, tempdescriptors, 0, propertyDescriptors.length);
		System.arraycopy(descriptors, 0, tempdescriptors, propertyDescriptors.length, descriptors.length);
		descriptors = tempdescriptors;
	}

	public LinkedHashMap<String, String> getExtraProps(){
		return propertyNames;
	}

	public String getImagePath(String string) {
		return propertyValues.get(string);
	}
	

	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}

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
			if(propertyNames.containsKey(propertyId)){
				return propertyValues.get(propertyId);
			}else{
				return super.getPropertyValue(propertyId);
			}
		}
	}
	
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
		} else if (propertyNames.containsKey(propertyId)){
			propertyValues.put((String)propertyId, (String) value);
		}else {
			super.setPropertyValue(propertyId, value);
		}
	}

	
	public abstract Image getIcon();

	public Point getLocation() {
		return location.getCopy();
	}
	
	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
		firePropertyChange(NAME_PROP, null, componentName);
	}
	
	public Dimension getSize() {
		return size.getCopy();
	}

	public List getSourceConnections() {
		return new ArrayList(sourceConnections);
	}

	public List getTargetConnections() {
		return new ArrayList(targetConnections);
	}

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
	
	public void setLocation(Point newLocation) {
		if (newLocation == null) {
			throw new IllegalArgumentException();
		}
		location.setLocation(newLocation);
		firePropertyChange(LOCATION_PROP, null, location);
	}

	public void setSize(Dimension newSize) {
		if (newSize != null) {
			size.setSize(newSize);
			firePropertyChange(SIZE_PROP, null, size);
		}
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
}
