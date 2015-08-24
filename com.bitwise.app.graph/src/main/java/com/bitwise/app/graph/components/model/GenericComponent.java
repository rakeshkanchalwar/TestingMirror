package com.bitwise.app.graph.components.model;

import java.util.LinkedHashMap;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.bitwise.app.adapters.ComponentAdapter;
import com.bitwise.app.adapters.ComponentProperty;

public class GenericComponent extends Component{
	
	//private static final long serialVersionUID = 9104772738878171710L;
	public IPropertyDescriptor[] propertyDescriptors;
	public String IconPath;
	
	public GenericComponent() {
		// TODO Auto-generated constructor stub
		//System.out.println("Hello");
		
		}
		
	public void initPropertyDescriptors(String compName, GenericComponent tempModel){
			
		LinkedHashMap<String,ComponentProperty> componentProperties = ComponentAdapter.getComponentProperty(compName);
		
		propertyDescriptors = new IPropertyDescriptor[componentProperties.size()];
		int index=0;
		for(String key : componentProperties.keySet()){
			propertyDescriptors[index] = new TextPropertyDescriptor(
					componentProperties.get(key).getPropID(),
					componentProperties.get(key).getPropName()
					);
			super.setPropertyValue(componentProperties.get(key).getPropID(), componentProperties.get(key).getPropValue());
			index++;
		}
		IconPath=componentProperties.get("Shape.icon").getPropValue();
		super.setOtherPropertyNames(componentProperties);
	}

	@Override
	public Object getPropertyValue(Object propertyId) {
		return super.getPropertyValue(propertyId);
	}
	
	@Override
	public void setPropertyValue(Object propertyId, Object value) {
		super.setPropertyValue(propertyId, value);
		firePropertyChange((String) propertyId, null, value);
	}

	@Override
	public Image getIcon() {
		System.out.println("Executed ICON");
		return createImage(IconPath);
	}
	
	/*public IPropertyDescriptor[] getTempPropertyDescriptors() {		
		IPropertyDescriptor[] parentDescriptors = super
				.getPropertyDescriptors();
		int totalArraySize = parentDescriptors.length + propertyDescriptors.length;
		IPropertyDescriptor[] compDescriptors = new IPropertyDescriptor[totalArraySize];
		System.arraycopy(parentDescriptors, 0, compDescriptors, 0, parentDescriptors.length);
		System.arraycopy(propertyDescriptors, 0, compDescriptors, parentDescriptors.length, propertyDescriptors.length);
		return compDescriptors;
	}*/
	
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {		
		IPropertyDescriptor[] parentDescriptors = super
				.getPropertyDescriptors();
		int totalArraySize = parentDescriptors.length + propertyDescriptors.length;
		IPropertyDescriptor[] compDescriptors = new IPropertyDescriptor[totalArraySize];
		System.arraycopy(parentDescriptors, 0, compDescriptors, 0, parentDescriptors.length);
		System.arraycopy(propertyDescriptors, 0, compDescriptors, parentDescriptors.length, propertyDescriptors.length);
		return compDescriptors;
	}
}
