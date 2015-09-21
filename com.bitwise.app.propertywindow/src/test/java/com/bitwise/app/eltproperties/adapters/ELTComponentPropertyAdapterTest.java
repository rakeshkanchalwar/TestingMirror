package com.bitwise.app.eltproperties.adapters;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.eltproperties.testdata.PropertyStore;
import com.bitwise.app.propertywindow.adapters.ELTComponentPropertyAdapter;
import com.bitwise.app.propertywindow.exceptions.EmptyComponentPropertiesException;
import com.bitwise.app.propertywindow.property.Property;

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
		//ArrayList<Property> rawProperties = propertyStore.getProperties("Input");
		//System.out.println("+++" + rawProperties.toString());
		try {
			XMLConfigUtil.INSTANCE.getComponentConfig();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//XMLConfigUtil.INSTANCE.
		List<com.bitwise.app.common.component.config.Property> rawProperties = XMLConfigUtil.INSTANCE.getComponent("Input").getProperty();
		//when
		ELTComponentPropertyAdapter eltComponentPropertyAdapter = new ELTComponentPropertyAdapter(rawProperties);
		eltComponentPropertyAdapter.transform();
		ArrayList<Property> transformedProperties = eltComponentPropertyAdapter.getProperties();
		System.out.println("XXXX " + transformedProperties.toString());
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
