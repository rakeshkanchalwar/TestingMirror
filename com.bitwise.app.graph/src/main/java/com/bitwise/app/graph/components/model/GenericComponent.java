package com.bitwise.app.graph.components.model;

import java.util.LinkedHashMap;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.bitwise.app.adapters.ComponentAdapter;
import com.bitwise.app.adapters.ComponentProperty;

public class GenericComponent extends Component{
	
	//private static final long serialVersionUID = 9104772738878171710L;
	private IPropertyDescriptor[] propertyDescriptors;
	private LinkedHashMap<String,ComponentProperty> componentProperties;
	//private String compName;
	
	
	/*public  String getComponentName() {
		return compName;
	}

	public void setComponentName(String componentName) {
		compName = componentName;
	}*/

	/*public void ReInitPropertyDescriptors(String compName){
		
	}*/
	
	//public void initPropertyDescriptors(LinkedHashMap<String,ComponentProperty> componentProperties,String compName){
	public void initPropertyDescriptors(String compName){
		//this.compName = compName;
		//ComponentAdapter.getComponentProperty(compName);
		//this.componentProperties = componentProperties;
		this.componentProperties = ComponentAdapter.getComponentProperty(compName);
		
		propertyDescriptors = new IPropertyDescriptor[componentProperties.size()];
		int index=0;
		for(String key : componentProperties.keySet()){
			propertyDescriptors[index] = new TextPropertyDescriptor(
					componentProperties.get(key).getPropID(),
					componentProperties.get(key).getPropName()
					);
			index++;
		}
	}

	@Override
	public Object getPropertyValue(Object propertyId) {
		initPropertyDescriptors((String)super.getPropertyValue("Shape.Name"));
		if(componentProperties.containsKey(propertyId)){
			return componentProperties.get(propertyId).getPropValue();
		}else{
			return super.getPropertyValue(propertyId);
		}
	}
	
	@Override
	public void setPropertyValue(Object propertyId, Object value) {
		if(componentProperties.containsKey(propertyId)){	
			componentProperties.get(propertyId).setPropValue((String) value);
			firePropertyChange(componentProperties.get(propertyId).getPropID(), null, value);
		}else{
			super.setPropertyValue(propertyId, value);
		}
	}

	@Override
	public Image getIcon() {
		return createImage(componentProperties.get("Shape.icon").getPropValue());
	}
	
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		initPropertyDescriptors((String)super.getPropertyValue("Shape.Name"));
		IPropertyDescriptor[] parentDescriptors = super
				.getPropertyDescriptors();
		int totalArraySize = parentDescriptors.length + propertyDescriptors.length;
		IPropertyDescriptor[] compDescriptors = new IPropertyDescriptor[totalArraySize];
		System.arraycopy(parentDescriptors, 0, compDescriptors, 0, parentDescriptors.length);
		System.arraycopy(propertyDescriptors, 0, compDescriptors, parentDescriptors.length, propertyDescriptors.length);
		return compDescriptors;
	}
}
