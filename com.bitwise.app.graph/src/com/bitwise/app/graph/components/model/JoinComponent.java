package com.bitwise.app.graph.components.model;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class JoinComponent extends Component{
	/** A 16x16 pictogram of an elliptical shape. */
	private static final Image JOIN_ICON = createImage("icons/Join_Palette.png");

	private static final long serialVersionUID = 1;
	
	private static final String KEYS_PROP = "Shape.Keys";
	private static final String TYPE_PROP = "Shape.Type";
	private static final String FIELDMAP_PROP = "Shape.FieldMap";
	
	private static IPropertyDescriptor[] joinDescriptors;
	
	private String keys="";
	private String type="";
	private String fieldMap="";

	
	static {
		joinDescriptors = new IPropertyDescriptor[] {

		new TextPropertyDescriptor(KEYS_PROP, "Keys"), 
		new TextPropertyDescriptor(TYPE_PROP, "Type"),
		new TextPropertyDescriptor(FIELDMAP_PROP, "FieldMap"),
		
		};
	}
		
	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
		firePropertyChange(KEYS_PROP, null, keys);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		firePropertyChange(TYPE_PROP, null, type);
	}

	public String getFieldMap() {
		return fieldMap;
	}

	public void setFieldMap(String fieldMap) {
		this.fieldMap = fieldMap;
		firePropertyChange(FIELDMAP_PROP, null, fieldMap);
	}

	public static String getFieldmapProp() {
		return FIELDMAP_PROP;
	}


	public Object getPropertyValue(Object propertyId) {
		if (KEYS_PROP.equals(propertyId)) {
			return keys;
		}

		if (TYPE_PROP.equals(propertyId)) {
			return type;
		}
		if (FIELDMAP_PROP.equals(propertyId)) {
			return fieldMap;
		}
		
		return super.getPropertyValue(propertyId);
	}

	
	public void setPropertyValue(Object propertyId, Object value) {
		if (KEYS_PROP.equals(propertyId)) {
			// int x = Integer.parseInt((String) value);
			// setLocation(new Point(x, location.y));
			setKeys((String) value);
			firePropertyChange(KEYS_PROP, null, keys);
		} else if (TYPE_PROP.equals(propertyId)) {
			// int width = Integer.parseInt((String) value);
			// setSize(new Dimension(width, size.height));
			setType((String) value);
			firePropertyChange(TYPE_PROP, null, type);
		}else if (FIELDMAP_PROP.equals(propertyId)) {
			// int width = Integer.parseInt((String) value);
			// setSize(new Dimension(width, size.height));
			setFieldMap((String) value);
			firePropertyChange(FIELDMAP_PROP, null, fieldMap);
		}
		else {
			super.setPropertyValue(propertyId, value);
		}
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {

		IPropertyDescriptor[] parentDescriptors = super
				.getPropertyDescriptors();
		// System.arraycopy(src, srcPos, dest, destPos, length);
		
		int totalArraySize = parentDescriptors.length + joinDescriptors.length;
		
		IPropertyDescriptor[] compDescriptors = new IPropertyDescriptor[totalArraySize];
		
		System.arraycopy(parentDescriptors, 0, compDescriptors, 0, parentDescriptors.length);
		System.arraycopy(joinDescriptors, 0, compDescriptors, parentDescriptors.length, joinDescriptors.length);
		
		//inputDescriptors = compDescriptors;
		return compDescriptors;
	}

	public Image getIcon() {
		return JOIN_ICON;
	}

	public String toString() {
		return "Join " + hashCode();
	}
}
