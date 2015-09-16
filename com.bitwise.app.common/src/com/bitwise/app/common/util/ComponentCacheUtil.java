package com.bitwise.app.common.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.bitwise.app.common.component.config.Component;
import com.bitwise.app.common.component.config.Property;

public class ComponentCacheUtil {
	public static final ComponentCacheUtil INSTANCE = new ComponentCacheUtil();
	
	private ComponentCacheUtil(){}
	
	private final Map<String, Map<String, Object>> propertyCache = new LinkedHashMap<>();
	
	
	//TODO : refine for nested properties
	public Map<String, Object> getProperties(String componentName) {
		Map<String, Object> propertyMap = null;
		if (propertyCache.containsKey(componentName)) {
			propertyMap = propertyCache.get(componentName);
		} else {
			Component component = XMLConfigUtil.INSTANCE.getComponent(componentName);
			propertyMap = new LinkedHashMap<>();
			for (Property property : component.getProperty()) {
				propertyMap.put(property.getName(), property.getValue());
			}
			propertyCache.put(componentName, propertyMap);
		}
		return cloneTheMap(propertyMap);
	}

	private Map<String, Object> cloneTheMap(Map<String, Object> propertyMap) {
		Map<String, Object> clonedMap = new LinkedHashMap<>();
		for (Map.Entry<String, Object> mapEntry : propertyMap.entrySet()) {
			clonedMap.put(mapEntry.getKey(), mapEntry.getValue());
		}
		return clonedMap;
	}
}