package com.bitwise.app.eltproperties.adapters;

import static org.junit.Assert.assertEquals;


import java.util.ArrayList;

import org.junit.Test;

import com.bitwise.app.eltproperties.exceptions.EmptyComponentPropertiesException;
import com.bitwise.app.eltproperties.property.Property;
import com.bitwise.app.eltproperties.testdata.PropertyStore;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 04, 2015
 * 
 */

public class ELTComponentPropertyAdapterTest {
	
	@Test
	public void itShouldTransformRowPropertiesToELTPropertyWindowFormat() throws EmptyComponentPropertiesException{
		//Given
		PropertyStore propertyStore = new PropertyStore();
		ArrayList<Property> rawProperties = propertyStore.getProperties("Input");
		
		//when
		ELTComponentPropertyAdapter eltComponentPropertyAdapter = new ELTComponentPropertyAdapter(rawProperties);
		eltComponentPropertyAdapter.transform();
		ArrayList<Property> transformedProperties = eltComponentPropertyAdapter.getProperties();
		
		//then		
		assertEquals(rawProperties,transformedProperties);
	}
	
	@Test(expected = EmptyComponentPropertiesException.class)
	public void itShouldThrowEmptyComponentPropertiesExceptionIfRawPropertiesAreEmptyWhileTransformation() throws EmptyComponentPropertiesException{
		//Given
		PropertyStore propertyStore = new PropertyStore();
		ArrayList<Property> rawProperties = propertyStore.getProperties("TEST");
		
		//when
		ELTComponentPropertyAdapter eltComponentPropertyAdapter = new ELTComponentPropertyAdapter(rawProperties);
		eltComponentPropertyAdapter.transform();
		
		//Then - expect EmptyComponentPropertiesException
	}
	      
	@Test(expected = EmptyComponentPropertiesException.class)
	public void itShouldThrowEmptyComponentPropertiesExceptionIfELTComponentPropertyAdapterHasEmptyPropertyListForTheComponent() throws EmptyComponentPropertiesException{
		//Given
		PropertyStore propertyStore = new PropertyStore();
		ArrayList<Property> rawProperties = propertyStore.getProperties("TEST");
		
		//when
		ELTComponentPropertyAdapter eltComponentPropertyAdapter = new ELTComponentPropertyAdapter(rawProperties);
		eltComponentPropertyAdapter.getProperties();
		
		//Then - expect EmptyComponentPropertiesException
		
	}
}
