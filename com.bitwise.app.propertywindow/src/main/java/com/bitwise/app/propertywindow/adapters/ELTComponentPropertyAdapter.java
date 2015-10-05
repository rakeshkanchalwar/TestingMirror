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
	
	private ELTComponentPropertyAdapter(){
		
	}
	
	public ELTComponentPropertyAdapter(Object rawProperties){
		this.rawProperties = rawProperties;
		properties = new ArrayList<>();
	}
	
	@Override
	public void transform() throws EmptyComponentPropertiesException {
		ArrayList<com.bitwise.app.common.component.config.Property> castedRawproperties = getCastedRawProperties();
		for(com.bitwise.app.common.component.config.Property property : castedRawproperties){
			Property tempProperty = transformProperty(property);
			this.properties.add(tempProperty);
		} 
	}


	private ArrayList<com.bitwise.app.common.component.config.Property> getCastedRawProperties()
			throws EmptyComponentPropertiesException {
		if(rawProperties == null)
			throw new EmptyComponentPropertiesException();
		
		ArrayList<com.bitwise.app.common.component.config.Property> castedRawproperties =  (ArrayList<com.bitwise.app.common.component.config.Property>) rawProperties;
		return castedRawproperties;
	}
	
	private Property transformProperty(
			com.bitwise.app.common.component.config.Property property) {
		Property tempProperty = new Property(property.getDataType().toString(), property.getName().toString(), property.getRenderer().toString());
		tempProperty.group(property.getGroup().toString());
		tempProperty.subGroup(property.getSubGroup().toString());
		
		return tempProperty;
	}

	@Override
	public ArrayList<Property> getProperties() throws EmptyComponentPropertiesException {
		if(properties == null )
			throw new EmptyComponentPropertiesException();
		
		return properties;
	}
	
}
