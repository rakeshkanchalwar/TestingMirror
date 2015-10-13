package com.bitwise.app.propertywindow.adapters;

import java.util.ArrayList;
import java.util.List;

import com.bitwise.app.propertywindow.property.Property;
/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 04, 2015
 * 
 */

public class ELTComponentPropertyAdapter implements IPropertyAdapter{

	private List<Property> properties;
	private List<com.bitwise.app.common.component.config.Property> rawProperties;
	
	public ELTComponentPropertyAdapter(List<com.bitwise.app.common.component.config.Property> rawProperties){
		this.rawProperties = rawProperties;
		properties = new ArrayList<>();
	}
	
	@Override
	public void transform() throws ELTComponentPropertyAdapter.EmptyComponentPropertiesException {
		validateRawProperties();
		for(com.bitwise.app.common.component.config.Property property : rawProperties){
			Property tempProperty = transformProperty(property);
			this.properties.add(tempProperty);
		} 
	}

	private void validateRawProperties() {
		if(rawProperties == null)
			throw new ELTComponentPropertyAdapter.EmptyComponentPropertiesException();
	}
	
	private Property transformProperty(
			com.bitwise.app.common.component.config.Property property) {
		return new Property.Builder(property.getDataType().toString(), property.getName().toString(), property.getRenderer().toString())
					.group(property.getGroup().toString())
					.subGroup(property.getSubGroup().toString()).build();
	}

	@Override
	public ArrayList<Property> getProperties(){
		return (ArrayList<Property>) properties;
	}
	
	public static class EmptyComponentPropertiesException extends RuntimeException{

		private static final long serialVersionUID = 1229993313725505841L;

		public EmptyComponentPropertiesException(){
	        super("Found empty property list");
	    }	
	}
	
}
