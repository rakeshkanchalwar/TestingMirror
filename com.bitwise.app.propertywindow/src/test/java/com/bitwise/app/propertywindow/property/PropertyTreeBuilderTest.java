package com.bitwise.app.propertywindow.property;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.bitwise.app.propertywindow.testdata.PropertyStore;
import com.bitwise.app.propertywindow.property.Property;
import com.bitwise.app.propertywindow.property.PropertyTreeBuilder;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 04, 2015
 * 
 */

public class PropertyTreeBuilderTest {

	@Test
	public void itShouldBuildComponenetPropertyTree(){
		//Given
		PropertyStore propertyStore = new PropertyStore();
		ArrayList<Property> inputComponentProperties = propertyStore.getProperties("Input");
		//String expectedTree = "PropertyTreeBuilder [propertyTree={TextProperties={TextProperties.GENERAL=[Property [propertyName=path, propertyRenderer=TEXT, propertyGroup=TextProperties, propertySubGroup=GENERAL, propertyDataType=String, propertyType=USER, propertyListeners=[]], Property [propertyName=delimiter, propertyRenderer=TEXT, propertyGroup=TextProperties, propertySubGroup=GENERAL, propertyDataType=String, propertyType=USER, propertyListeners=[]]], TextProperties.Opetional=[Property [propertyName=charset, propertyRenderer=TEXT, propertyGroup=TextProperties, propertySubGroup=Opetional, propertyDataType=String, propertyType=USER, propertyListeners=[]], Property [propertyName=phase, propertyRenderer=TEXT, propertyGroup=TextProperties, propertySubGroup=Opetional, propertyDataType=String, propertyType=USER, propertyListeners=[]]]}, RadioProperties={RadioProperties.safe=[Property [propertyName=safe, propertyRenderer=RADIO, propertyGroup=RadioProperties, propertySubGroup=safe, propertyDataType=boolean, propertyType=USER, propertyListeners=[]]], RadioProperties.header=[Property [propertyName=has_header, propertyRenderer=RADIO, propertyGroup=RadioProperties, propertySubGroup=header, propertyDataType=boolean, propertyType=USER, propertyListeners=[]]]}}]";
		String expectedTree ="PropertyTreeBuilder [propertyTree={TEXT_PROPERTIES={TEXT_PROPERTIES.GENERAL=[Property [propertyName=name, propertyRenderer=ELT_COMPONENT_NAME_WIDGET, propertyGroup=TEXT_PROPERTIES, propertySubGroup=GENERAL, propertyDataType=String, propertyType=USER], Property [propertyName=path, propertyRenderer=ELT_FILE_PATH_WIDGET, propertyGroup=TEXT_PROPERTIES, propertySubGroup=GENERAL, propertyDataType=String, propertyType=USER], Property [propertyName=strict, propertyRenderer=ELT_STRICT_CLASS_WIDGET, propertyGroup=TEXT_PROPERTIES, propertySubGroup=GENERAL, propertyDataType=String, propertyType=USER]], TEXT_PROPERTIES.AAAA=[Property [propertyName=charset, propertyRenderer=ELT_CHARACTER_SET_WIDGET, propertyGroup=TEXT_PROPERTIES, propertySubGroup=AAAA, propertyDataType=String, propertyType=USER], Property [propertyName=phase, propertyRenderer=ELT_PHASE_WIDGET, propertyGroup=TEXT_PROPERTIES, propertySubGroup=AAAA, propertyDataType=String, propertyType=USER]]}, RADIO_PROPERTIES={RADIO_PROPERTIES.safe=[Property [propertyName=safe, propertyRenderer=ELT_SAFE_PROPERTY_WIDGET, propertyGroup=RADIO_PROPERTIES, propertySubGroup=safe, propertyDataType=boolean, propertyType=USER]], RADIO_PROPERTIES.header=[Property [propertyName=has_header, propertyRenderer=ELT_HAS_HEADER_WIDGET, propertyGroup=RADIO_PROPERTIES, propertySubGroup=header, propertyDataType=boolean, propertyType=USER]]}, Schema={Schema.GENERAL=[Property [propertyName=Schema, propertyRenderer=ELT_SCHEMA_WIDGET, propertyGroup=Schema, propertySubGroup=GENERAL, propertyDataType=boolean, propertyType=USER]]}, RUNTIME_PROP={RUNTIME_PROP.GENERAL=[Property [propertyName=RuntimeProps, propertyRenderer=ELT_RUNTIME_PROPERTIES_WIDGET, propertyGroup=RUNTIME_PROP, propertySubGroup=GENERAL, propertyDataType=boolean, propertyType=USER]]}}]";
		//When
		PropertyTreeBuilder propertyTreeBuilder = new PropertyTreeBuilder(inputComponentProperties);
		
		//Then
		assertEquals(expectedTree,propertyTreeBuilder.toString());
	}
	
	@Test
	public void itShouldProvidePropertyTree(){
		//Given
		PropertyStore propertyStore = new PropertyStore();
		ArrayList<Property> inputComponentProperties = propertyStore.getProperties("Input");
		String expectedTree="{TEXT_PROPERTIES={TEXT_PROPERTIES.GENERAL=[Property [propertyName=name, propertyRenderer=ELT_COMPONENT_NAME_WIDGET, propertyGroup=TEXT_PROPERTIES, propertySubGroup=GENERAL, propertyDataType=String, propertyType=USER], Property [propertyName=path, propertyRenderer=ELT_FILE_PATH_WIDGET, propertyGroup=TEXT_PROPERTIES, propertySubGroup=GENERAL, propertyDataType=String, propertyType=USER], Property [propertyName=strict, propertyRenderer=ELT_STRICT_CLASS_WIDGET, propertyGroup=TEXT_PROPERTIES, propertySubGroup=GENERAL, propertyDataType=String, propertyType=USER]], TEXT_PROPERTIES.AAAA=[Property [propertyName=charset, propertyRenderer=ELT_CHARACTER_SET_WIDGET, propertyGroup=TEXT_PROPERTIES, propertySubGroup=AAAA, propertyDataType=String, propertyType=USER], Property [propertyName=phase, propertyRenderer=ELT_PHASE_WIDGET, propertyGroup=TEXT_PROPERTIES, propertySubGroup=AAAA, propertyDataType=String, propertyType=USER]]}, RADIO_PROPERTIES={RADIO_PROPERTIES.safe=[Property [propertyName=safe, propertyRenderer=ELT_SAFE_PROPERTY_WIDGET, propertyGroup=RADIO_PROPERTIES, propertySubGroup=safe, propertyDataType=boolean, propertyType=USER]], RADIO_PROPERTIES.header=[Property [propertyName=has_header, propertyRenderer=ELT_HAS_HEADER_WIDGET, propertyGroup=RADIO_PROPERTIES, propertySubGroup=header, propertyDataType=boolean, propertyType=USER]]}, Schema={Schema.GENERAL=[Property [propertyName=Schema, propertyRenderer=ELT_SCHEMA_WIDGET, propertyGroup=Schema, propertySubGroup=GENERAL, propertyDataType=boolean, propertyType=USER]]}, RUNTIME_PROP={RUNTIME_PROP.GENERAL=[Property [propertyName=RuntimeProps, propertyRenderer=ELT_RUNTIME_PROPERTIES_WIDGET, propertyGroup=RUNTIME_PROP, propertySubGroup=GENERAL, propertyDataType=boolean, propertyType=USER]]}}";
		//When
		PropertyTreeBuilder propertyTreeBuilder = new PropertyTreeBuilder(inputComponentProperties);
		
		//Then
		assertEquals(expectedTree,propertyTreeBuilder.getPropertyTree().toString());
	}
}
