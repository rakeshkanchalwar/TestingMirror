package com.bitwise.app.graph.components.model;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class TransformComponent extends Component{
	/** A 16x16 pictogram of an elliptical shape. */
	private static final Image TRANSFORM_ICON = createImage("icons/Transform_Palette.png");

	private static final long serialVersionUID = 1;
	
	private static final String INPUT_FIELDS_PROP = "Shape.InputFields";
	private static final String OPERATION_CLASS_PROP = "Shape.OperationClass";
	private static final String PROPERTIES_PROP = "Shape.Properties";
	
	private static IPropertyDescriptor[] transformDescriptors;
	
	private String inputFields="";
	private String operationClass="";
	private String properties="";

	
	static {
		transformDescriptors = new IPropertyDescriptor[] {

		new TextPropertyDescriptor(INPUT_FIELDS_PROP, "Input Fields"), 
		new TextPropertyDescriptor(OPERATION_CLASS_PROP, "Operation Class"),
		new TextPropertyDescriptor(PROPERTIES_PROP, "Properties"),
		
		};
	}
	
		
	public String getInputFields() {
		return inputFields;
	}

	public void setInputFields(String inputFields) {
		this.inputFields = inputFields;
		firePropertyChange(INPUT_FIELDS_PROP, null, inputFields);
	}

	public String getOperationClass() {
		return operationClass;
	}

	public void setOperationClass(String operationClass) {
		this.operationClass = operationClass;
		firePropertyChange(OPERATION_CLASS_PROP, null, operationClass);
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
		firePropertyChange(PROPERTIES_PROP, null, properties);
	}

	public Object getPropertyValue(Object propertyId) {
		if (INPUT_FIELDS_PROP.equals(propertyId)) {
			return inputFields;
		}

		if (OPERATION_CLASS_PROP.equals(propertyId)) {
			return operationClass;
		}
		if (PROPERTIES_PROP.equals(propertyId)) {
			return properties;
		}
		
		return super.getPropertyValue(propertyId);
	}

	public void setPropertyValue(Object propertyId, Object value) {
		if (INPUT_FIELDS_PROP.equals(propertyId)) {
			// int x = Integer.parseInt((String) value);
			// setLocation(new Point(x, location.y));
			setInputFields((String) value);
			firePropertyChange(INPUT_FIELDS_PROP, null, inputFields);
		} else if (OPERATION_CLASS_PROP.equals(propertyId)) {
			// int width = Integer.parseInt((String) value);
			// setSize(new Dimension(width, size.height));
			setOperationClass((String) value);
			firePropertyChange(OPERATION_CLASS_PROP, null, operationClass);
		}else if (PROPERTIES_PROP.equals(propertyId)) {
			// int width = Integer.parseInt((String) value);
			// setSize(new Dimension(width, size.height));
			setProperties((String) value);
			firePropertyChange(PROPERTIES_PROP, null, properties);
		}
		else {
			super.setPropertyValue(propertyId, value);
		}
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {

		IPropertyDescriptor[] parentDescriptors = super
				.getPropertyDescriptors();
		// System.arraycopy(src, srcPos, dest, destPos, length);
		
		int totalArraySize = parentDescriptors.length + transformDescriptors.length;
		
		IPropertyDescriptor[] compDescriptors = new IPropertyDescriptor[totalArraySize];
		
		System.arraycopy(parentDescriptors, 0, compDescriptors, 0, parentDescriptors.length);
		System.arraycopy(transformDescriptors, 0, compDescriptors, parentDescriptors.length, transformDescriptors.length);
		
		//inputDescriptors = compDescriptors;
		return compDescriptors;
	}

	public Image getIcon() {
		return TRANSFORM_ICON;
	}

	public String toString() {
		return "Transform " + hashCode();
	}
}
