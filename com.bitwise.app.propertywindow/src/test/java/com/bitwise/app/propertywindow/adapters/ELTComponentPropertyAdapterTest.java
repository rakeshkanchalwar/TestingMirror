package com.bitwise.app.propertywindow.adapters;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.propertywindow.testdata.PropertyStore;
import com.bitwise.app.propertywindow.testdata.RawProperties;
import com.bitwise.app.propertywindow.adapters.ELTComponentPropertyAdapter;
import com.bitwise.app.propertywindow.property.Property;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 04, 2015
 * 
 */

public class ELTComponentPropertyAdapterTest {
	
	@Test
	public void itShouldTransformRowPropertiesToELTPropertyWindowFormat(){
		//Given
		RawProperties rawProps = new RawProperties();
		List<com.bitwise.app.common.component.config.Property> rawProperties = rawProps.getRawProperties();
		String expectedProperties = "[Property [propertyName=name, propertyRenderer=ELT_COMPONENT_NAME_WIDGET, propertyGroup=GENERAL, propertySubGroup=DISPLAY, propertyDataType=STRING, propertyType=USER], Property [propertyName=schema, propertyRenderer=ELT_SCHEMA_WIDGET, propertyGroup=SCHEMA, propertySubGroup=CONFIGURATION, propertyDataType=STRING, propertyType=USER], Property [propertyName=path, propertyRenderer=ELT_FILE_PATH_WIDGET, propertyGroup=GENERAL, propertySubGroup=CONFIGURATION, propertyDataType=STRING, propertyType=USER], Property [propertyName=delimiter, propertyRenderer=ELT_DELIMETER_WIDGET, propertyGroup=GENERAL, propertySubGroup=CONFIGURATION, propertyDataType=STRING, propertyType=USER], Property [propertyName=safe, propertyRenderer=ELT_SAFE_PROPERTY_WIDGET, propertyGroup=GENERAL, propertySubGroup=CONFIGURATION, propertyDataType=BOOLEAN, propertyType=USER], Property [propertyName=has_header, propertyRenderer=ELT_HAS_HEADER_WIDGET, propertyGroup=GENERAL, propertySubGroup=CONFIGURATION, propertyDataType=BOOLEAN, propertyType=USER], Property [propertyName=charset, propertyRenderer=ELT_CHARACTER_SET_WIDGET, propertyGroup=GENERAL, propertySubGroup=CONFIGURATION, propertyDataType=STRING, propertyType=USER], Property [propertyName=runtime_properties, propertyRenderer=ELT_RUNTIME_PROPERTIES_WIDGET, propertyGroup=GENERAL, propertySubGroup=CONFIGURATION, propertyDataType=STRING, propertyType=USER], Property [propertyName=phase, propertyRenderer=ELT_PHASE_WIDGET, propertyGroup=GENERAL, propertySubGroup=CONFIGURATION, propertyDataType=STRING, propertyType=USER]]";
		
		//when
		ELTComponentPropertyAdapter eltComponentPropertyAdapter = new ELTComponentPropertyAdapter(rawProperties);
		eltComponentPropertyAdapter.transform();
		ArrayList<Property> transformedProperties = eltComponentPropertyAdapter.getProperties();
		
		//then		
		assertEquals(expectedProperties,transformedProperties.toString());
	}
	
	@Test(expected = ELTComponentPropertyAdapter.EmptyComponentPropertiesException.class)
	public void itShouldThrowEmptyComponentPropertiesExceptionIfRawPropertiesAreEmptyWhileTransformation() {
		//Given
		
		//when
		//try{
			ELTComponentPropertyAdapter eltComponentPropertyAdapter = new ELTComponentPropertyAdapter(null);
			eltComponentPropertyAdapter.transform();	
		/*}catch(Exception e){
			e.printStackTrace();
		}*/
		
		
		//Then - expect EmptyComponentPropertiesException
	}
}
