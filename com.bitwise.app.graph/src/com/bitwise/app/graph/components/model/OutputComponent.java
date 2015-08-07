package com.bitwise.app.graph.components.model;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class OutputComponent extends Component{
	/** A 16x16 pictogram of an elliptical shape. */
	private static final Image OUTPUT_ICON = createImage("icons/OutputFile_Palette.png");

	private static final long serialVersionUID = 1;
	
	private static final String PATH_PROP = "Shape.Path";
	private static final String SCHEMA_PROP = "Shape.Schema";
	
	private static IPropertyDescriptor[] outputDescriptors;
	
	private String path="";
	private String schema="";

	
	static {
		outputDescriptors = new IPropertyDescriptor[] {

		new TextPropertyDescriptor(PATH_PROP, "Path"), 
		new TextPropertyDescriptor(SCHEMA_PROP, "Schema"),
		
		};
	}
	
	public Image getIcon() {
		return OUTPUT_ICON;
	}

	public String toString() {
		return "Output " + hashCode();
	}
	
	public String getFile() {
		return path;
	}

	public void setFile(String value) {
		this.path = value;
		firePropertyChange(PATH_PROP, null, value);
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
		firePropertyChange(SCHEMA_PROP, null, schema);
	}
	public Object getPropertyValue(Object propertyId) {
		if (PATH_PROP.equals(propertyId)) {
			return path;
		}

		if (SCHEMA_PROP.equals(propertyId)) {
			return schema;
		}
		
		return super.getPropertyValue(propertyId);
	}

	public void setPropertyValue(Object propertyId, Object value) {
		if (PATH_PROP.equals(propertyId)) {
			// int x = Integer.parseInt((String) value);
			// setLocation(new Point(x, location.y));
			setFile((String) value);
			firePropertyChange(PATH_PROP, null, path);
		} else if (SCHEMA_PROP.equals(propertyId)) {
			// int width = Integer.parseInt((String) value);
			// setSize(new Dimension(width, size.height));
			setSchema((String) value);
			firePropertyChange(SCHEMA_PROP, null, schema);
		}
		else {
			super.setPropertyValue(propertyId, value);
		}
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {

		IPropertyDescriptor[] parentDescriptors = super
				.getPropertyDescriptors();
		// System.arraycopy(src, srcPos, dest, destPos, length);
		
		int totalArraySize = parentDescriptors.length + outputDescriptors.length;
		
		IPropertyDescriptor[] compDescriptors = new IPropertyDescriptor[totalArraySize];
		
		System.arraycopy(parentDescriptors, 0, compDescriptors, 0, parentDescriptors.length);
		System.arraycopy(outputDescriptors, 0, compDescriptors, parentDescriptors.length, outputDescriptors.length);
		
		//inputDescriptors = compDescriptors;
		return compDescriptors;
	}

}
