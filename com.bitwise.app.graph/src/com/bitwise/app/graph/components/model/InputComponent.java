package com.bitwise.app.graph.components.model;

import java.beans.PropertyChangeEvent;

import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class InputComponent extends Component {
	/** A 16x16 pictogram of an elliptical shape. */
	//private static final Image INPUT_ICON = createImage("icons/InputFile_Palette.png");
	private static final Image INPUT_ICON = createImage("icons/input2.png");
	
	private static final long serialVersionUID = 1;
	private static final String PATH_PROP = "Shape.Path";
	private static final String SCHEMA_PROP = "Shape.Schema";
	private static final String DELIMITER_PROP = "Shape.Delimiter";
	
	private static IPropertyDescriptor[] inputDescriptors;

	// private static IPropertyDescriptor[]
	// inputDescriptors=getPropertyDescriptors();

	private String path="";
	private String schema="";
	private String delimiter="";
	
	public String getPath() {
		return path;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
		firePropertyChange(DELIMITER_PROP, null, delimiter);
	}

	public void setPath(String value) {
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

	public void propertyChange(PropertyChangeEvent evt) {
		// ...
		if (evt.getPropertyName().equals(Component.RENAME_PROP)) 
			//refreshVisuals();
			System.out.println("propertyChange...");
	}

	static {
		inputDescriptors = new IPropertyDescriptor[] {

		new TextPropertyDescriptor(PATH_PROP, "Path"), 
		new TextPropertyDescriptor(SCHEMA_PROP, "Schema"),
		new TextPropertyDescriptor(DELIMITER_PROP, "Delimiter")
		};
		
		// use a custom cell editor validator for all four array entries
		/*for (int i = 0; i < inputDescriptors.length; i++) {
			((PropertyDescriptor) inputDescriptors[i])
					.setValidator(new ICellEditorValidator() {
						public String isValid(Object value) {
							int intValue = -1;
							try {
								intValue = Integer.parseInt((String) value);
							} catch (NumberFormatException exc) {
								return "Not a number";
							}
							return (intValue >= 0) ? null
									: "Value must be >=  0";
						}
					});
		}*/
	} // static

	public Image getIcon() {
		return INPUT_ICON;
	}

	public String toString() {
		return "Input " + hashCode();
	}

	public Object getPropertyValue(Object propertyId) {
		if (PATH_PROP.equals(propertyId)) {
			return path;
		}

		if (SCHEMA_PROP.equals(propertyId)) {
			return schema;
		}
		if (DELIMITER_PROP.equals(propertyId)) {
			return delimiter;
		}
		return super.getPropertyValue(propertyId);
	}

	public void setPropertyValue(Object propertyId, Object value) {
		if (PATH_PROP.equals(propertyId)) {
			// int x = Integer.parseInt((String) value);
			// setLocation(new Point(x, location.y));
			setPath((String) value);
			firePropertyChange(PATH_PROP, null, path);
		} else if (SCHEMA_PROP.equals(propertyId)) {
			// int width = Integer.parseInt((String) value);
			// setSize(new Dimension(width, size.height));
			setSchema((String) value);
			firePropertyChange(SCHEMA_PROP, null, schema);
		}else if (DELIMITER_PROP.equals(propertyId)) {
			// int width = Integer.parseInt((String) value);
			// setSize(new Dimension(width, size.height));
			setDelimiter((String) value);
			firePropertyChange(DELIMITER_PROP, null, delimiter);
		} 
		else {
			super.setPropertyValue(propertyId, value);
		}
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {

		IPropertyDescriptor[] parentDescriptors = super
				.getPropertyDescriptors();
		// System.arraycopy(src, srcPos, dest, destPos, length);
		
		int totalArraySize = parentDescriptors.length + inputDescriptors.length;
		
		IPropertyDescriptor[] compDescriptors = new IPropertyDescriptor[totalArraySize];
		
		System.arraycopy(parentDescriptors, 0, compDescriptors, 0, parentDescriptors.length);
		System.arraycopy(inputDescriptors, 0, compDescriptors, parentDescriptors.length, inputDescriptors.length);
		
		//inputDescriptors = compDescriptors;
		return compDescriptors;
	}
}
