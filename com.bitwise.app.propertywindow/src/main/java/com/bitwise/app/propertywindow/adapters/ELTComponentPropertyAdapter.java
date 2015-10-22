package com.bitwise.app.propertywindow.adapters;

import java.util.ArrayList;
import java.util.List;

import com.bitwise.app.propertywindow.property.Property;
// TODO: Auto-generated Javadoc
/**
 * 
 * @author Bitwise
 * Sep 04, 2015
 * 
 */

public class ELTComponentPropertyAdapter implements IPropertyAdapter{

	private List<Property> properties;
	private List<com.bitwise.app.common.component.config.Property> rawProperties;
	
	/**
	 * Instantiates a new ELT component property adapter.
	 * 
	 * @param rawProperties
	 *            the raw properties
	 */
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
	
	/**
	 * The Class EmptyComponentPropertiesException.
	 * 
	 * @author Bitwise
	 */
	public static class EmptyComponentPropertiesException extends RuntimeException{

		private static final long serialVersionUID = 1229993313725505841L;

		/**
		 * Instantiates a new empty component properties exception.
		 */
		public EmptyComponentPropertiesException(){
	        super("Found empty property list");
	    }	
	}
	
}
