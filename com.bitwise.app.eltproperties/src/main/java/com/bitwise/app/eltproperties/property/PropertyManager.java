package com.bitwise.app.eltproperties.property;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.bitwise.app.eltproperties.adapters.PropertyAdapter;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 03, 2015
 * 
 */

public class PropertyManager {
	private ArrayList<Property> properties;
	private ArrayList<String> subGroupNameList;
	private ArrayList<String> groupNameList;
		
	/*public PropertyManager(){
		PropertyAdapter propertyAdapter = new PropertyAdapter();
		properties = propertyAdapter.getProperties("Input");
	}*/
	
	public PropertyManager(ArrayList<Property> properties){
		this.properties=properties;
		initGroupAndSubgroupNameList();
	}
	
	private void initGroupAndSubgroupNameList(){
		subGroupNameList= new ArrayList<>();
		groupNameList= new ArrayList<>();						
		for(Property property : properties){
			groupNameList.add(property.getPropertyGroup());
			subGroupNameList.add(property.getPropertyGroup() + "." + property.getPropertySubGroup());
		}
	}
	
	private ArrayList<Property> getPropertiesByGroupName(String groupName){
		ArrayList<Property> propertiesByGroup = new ArrayList<>();
		
		for(Property property: properties){
			if(groupName.equalsIgnoreCase(property.getPropertyGroup())){
				propertiesByGroup.add(property);
			}
		}
		
		return propertiesByGroup;
	}
	
	private ArrayList<Property> getPropertiesBySubGroupName(String groupName, String subGroupName){
		ArrayList<Property> propertiesBySubGroup = new ArrayList<>();
		for(Property property: properties){
			if(groupName.equalsIgnoreCase(property.getPropertyGroup()) &&
					subGroupName.equalsIgnoreCase(property.getPropertySubGroup())){
				propertiesBySubGroup.add(property);
			}
		}
		
		return propertiesBySubGroup;
	}
	
	
	
}
 