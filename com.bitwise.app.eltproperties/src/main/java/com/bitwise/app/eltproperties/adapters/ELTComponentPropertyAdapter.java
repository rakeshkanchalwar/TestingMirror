package com.bitwise.app.eltproperties.adapters;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.bitwise.app.eltproperties.exceptions.EmptyComponentPropertiesException;
import com.bitwise.app.eltproperties.property.Property;
import com.bitwise.app.eltproperties.testdata.PropertyStore;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 04, 2015
 * 
 */

public class ELTComponentPropertyAdapter implements IPropertyAdapter{

	private ArrayList<Property> properties;
	private ArrayList<Property> rawProperties;
	
	public ELTComponentPropertyAdapter(ArrayList<Property> rawProperties){
		this.rawProperties = rawProperties;
	}
	
	
	@Override
	public void transform() throws EmptyComponentPropertiesException {
		
		if(rawProperties == null)
			throw new EmptyComponentPropertiesException();
		
		//TODO - Write actual transformation logic 
		properties = rawProperties;
	}

	@Override
	public ArrayList<Property> getProperties() throws EmptyComponentPropertiesException {
		
		if(properties == null )
			throw new EmptyComponentPropertiesException();
		
		return properties;
	}
	
}
