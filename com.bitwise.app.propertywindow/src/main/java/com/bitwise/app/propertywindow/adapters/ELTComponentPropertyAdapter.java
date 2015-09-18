package com.bitwise.app.propertywindow.adapters;

import java.util.ArrayList;

import com.bitwise.app.propertywindow.exceptions.EmptyComponentPropertiesException;
import com.bitwise.app.propertywindow.property.Property;
/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 04, 2015
 * 
 */

public class ELTComponentPropertyAdapter implements IPropertyAdapter{

	private ArrayList<Property> properties;
	private Object rawProperties;
	
	public ELTComponentPropertyAdapter(Object rawProperties){
		this.rawProperties = rawProperties;
		properties = new ArrayList<>();
	}
	
	
	@Override
	public void transform() throws EmptyComponentPropertiesException {
		
		if(rawProperties == null)
			throw new EmptyComponentPropertiesException();
		
		//TODO - Write actual transformation logic 
		//properties = rawProperties;
		ArrayList<com.bitwise.app.common.component.config.Property> properties =  (ArrayList<com.bitwise.app.common.component.config.Property>) rawProperties;
		for(com.bitwise.app.common.component.config.Property property : properties){
			Property tempProperty = new Property(property.getDataType().toString(), property.getName().toString(), property.getRenderer().toString());
			tempProperty.group(property.getGroup().toString());
			tempProperty.subGroup(property.getSubGroup().toString());
			this.properties.add(tempProperty);
			System.out.println(tempProperty.toString());
		} 
	}

	@Override
	public ArrayList<Property> getProperties() throws EmptyComponentPropertiesException {
		
		if(properties == null )
			throw new EmptyComponentPropertiesException();
		
		return properties;
	}
	
}
