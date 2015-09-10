package com.bitwise.app.common.util;

import java.util.HashMap;
import java.util.Map;

import com.bitwise.app.common.component.config.Component;
import com.bitwise.app.common.component.config.Property;

public class ComponentCacheUtil {
	public static final ComponentCacheUtil INSTANCE = new ComponentCacheUtil();
	
	private ComponentCacheUtil(){}
	
	private final Map<String, Map<String, String>> propertyCache = new HashMap<>();
	
	
	//TODO : refine for nested properties
	public Map<String, String> getProperties(String componentName) {
		Map<String, String> propertyMap = null;
		if (propertyCache.containsKey(componentName)) {
			propertyMap = propertyCache.get(componentName);
		} else {
			Component component = XMLConfigUtil.INSTANCE.getComponent(componentName);
			propertyMap = new HashMap<>();
			for (Property property : component.getProperty()) {
				propertyMap.put(property.getName(), property.getValue());
			}
			propertyCache.put(componentName, propertyMap);
		}
		return cloneTheMap(propertyMap);
	}

	private Map<String, String> cloneTheMap(Map<String, String> propertyMap) {
		Map<String, String> clonedMap = new HashMap<>();
		for (Map.Entry<String, String> mapEntry : propertyMap.entrySet()) {
			clonedMap.put(mapEntry.getKey(), mapEntry.getValue());
		}
		return clonedMap;
	}
}