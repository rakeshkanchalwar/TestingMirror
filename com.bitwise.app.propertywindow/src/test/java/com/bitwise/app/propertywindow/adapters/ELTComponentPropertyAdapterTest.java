package com.bitwise.app.propertywindow.adapters;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.propertywindow.testdata.PropertyStore;
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
		List<com.bitwise.app.common.component.config.Property> rawProperties = XMLConfigUtil.INSTANCE.getComponent("Input").getProperty();
		String expectedProperties = "[Property [propertyName=name, propertyRenderer=ELT_COMPONENT_NAME_WIDGET, propertyGroup=GENERAL, propertySubGroup=NAME, propertyDataType=STRING, propertyType=USER, propertyListeners=[]], Property [propertyName=schema, propertyRenderer=ELT_SCHEMA_WIDGET, propertyGroup=SCHEMA, propertySubGroup=SCHEMA, propertyDataType=STRING, propertyType=USER, propertyListeners=[]], Property [propertyName=path, propertyRenderer=ELT_FILE_PATH_WIDGET, propertyGroup=GENERAL, propertySubGroup=FILE_PATH, propertyDataType=STRING, propertyType=USER, propertyListeners=[]], Property [propertyName=delimiter, propertyRenderer=ELT_DELIMETER_WIDGET, propertyGroup=GENERAL, propertySubGroup=DELIMITER, propertyDataType=STRING, propertyType=USER, propertyListeners=[]], Property [propertyName=safe, propertyRenderer=ELT_SAFE_PROPERTY_WIDGET, propertyGroup=GENERAL, propertySubGroup=SAFE, propertyDataType=BOOLEAN, propertyType=USER, propertyListeners=[]], Property [propertyName=has_header, propertyRenderer=ELT_HAS_HEADER_WIDGET, propertyGroup=GENERAL, propertySubGroup=HEADER, propertyDataType=BOOLEAN, propertyType=USER, propertyListeners=[]], Property [propertyName=charset, propertyRenderer=ELT_CHARACTER_SET_WIDGET, propertyGroup=GENERAL, propertySubGroup=CHARSET, propertyDataType=STRING, propertyType=USER, propertyListeners=[]], Property [propertyName=runtime_properties, propertyRenderer=ELT_RUNTIME_PROPERTIES_WIDGET, propertyGroup=RUNTIME_PROPERTIES, propertySubGroup=RUNTIME_PROPERTIES, propertyDataType=STRING, propertyType=USER, propertyListeners=[]], Property [propertyName=phase, propertyRenderer=ELT_PHASE_WIDGET, propertyGroup=GENERAL, propertySubGroup=PHASE, propertyDataType=STRING, propertyType=USER, propertyListeners=[]]]";
		
		//when
		ELTComponentPropertyAdapter eltComponentPropertyAdapter = new ELTComponentPropertyAdapter(rawProperties);
		eltComponentPropertyAdapter.transform();
		ArrayList<Property> transformedProperties = eltComponentPropertyAdapter.getProperties();
		System.out.println("XXXX " + transformedProperties.toString());
		
		//then		
		assertEquals(expectedProperties,transformedProperties.toString());
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
}
