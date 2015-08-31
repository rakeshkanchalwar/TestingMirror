package com.bitwise.app.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.bitwise.app.common.component.config.Component;
import com.bitwise.app.common.component.config.Property;
import com.bitwise.app.common.component.config.PropertyRenderer;

public class ComponentCacheUtil {
	public static final ComponentCacheUtil INSTANCE = new ComponentCacheUtil();
	
	private final Map<String, List<IPropertyDescriptor>> propertyDescriptorCache = new HashMap<>();
	private final Map<String, Map<String, String>> propertyCache = new HashMap<>();
	
	/**
	 * Prepares the property descriptor list as per the xml configuration in Component xml
	 * @param componentName
	 * @return
	 */
	public IPropertyDescriptor[] getPropertyDescriptors(String componentName) {
		List<IPropertyDescriptor> propertyDescriptorList = null;
		if(propertyDescriptorCache.containsKey(componentName)){
			propertyDescriptorList = propertyDescriptorCache.get(componentName);
		}
		else{
			Component component =  XMLConfigUtil.INSTANCE.getComponent(componentName);
			propertyDescriptorList = new ArrayList<>();
			for (Property property : component.getProperty()) {
				if(PropertyRenderer.FILE.equals(property.getRenderer())){
					
				}
				else if(PropertyRenderer.TEXT.equals(property.getRenderer())){
					propertyDescriptorList.add(new TextPropertyDescriptor(property.getName(), property.getName()));
				}
			}
			propertyDescriptorCache.put(componentName, propertyDescriptorList);
		}
		
		return propertyDescriptorList.toArray(new IPropertyDescriptor[propertyDescriptorList.size()]);
	}
	
	//TODO : refine for nested properties
	public Map<String, String> getProperties(String componentName){
		Map<String, String> propertyMap = null;
		if(propertyCache.containsKey(componentName)){
			propertyMap = propertyCache.get(componentName);
		}
		else{
			Component component =  XMLConfigUtil.INSTANCE.getComponent(componentName);
			propertyMap = new HashMap<>();
			for (Property property : component.getProperty()) {
				propertyMap.put(property.getName(), property.getValue());
			}
			propertyCache.put(componentName, propertyMap);
		}
		return propertyMap;
	}
}