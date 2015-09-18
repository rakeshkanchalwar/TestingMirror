package com.bitwise.app.eltproperties.property;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.bitwise.app.eltproperties.testdata.PropertyStore;

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
		String expectedTree = "PropertyTreeBuilder [propertyTree={TextProperties={TextProperties.GENERAL=[Property [propertyName=path, propertyRenderer=TEXT, propertyGroup=TextProperties, propertySubGroup=GENERAL, propertyDataType=String, propertyType=USER, propertyListeners=[]], Property [propertyName=delimiter, propertyRenderer=TEXT, propertyGroup=TextProperties, propertySubGroup=GENERAL, propertyDataType=String, propertyType=USER, propertyListeners=[]]], TextProperties.Opetional=[Property [propertyName=charset, propertyRenderer=TEXT, propertyGroup=TextProperties, propertySubGroup=Opetional, propertyDataType=String, propertyType=USER, propertyListeners=[]], Property [propertyName=phase, propertyRenderer=TEXT, propertyGroup=TextProperties, propertySubGroup=Opetional, propertyDataType=String, propertyType=USER, propertyListeners=[]]]}, RadioProperties={RadioProperties.safe=[Property [propertyName=safe, propertyRenderer=RADIO, propertyGroup=RadioProperties, propertySubGroup=safe, propertyDataType=boolean, propertyType=USER, propertyListeners=[]]], RadioProperties.header=[Property [propertyName=has_header, propertyRenderer=RADIO, propertyGroup=RadioProperties, propertySubGroup=header, propertyDataType=boolean, propertyType=USER, propertyListeners=[]]]}}]";
		
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
		String expectedTree="{TextProperties={TextProperties.GENERAL=[Property [propertyName=path, propertyRenderer=TEXT, propertyGroup=TextProperties, propertySubGroup=GENERAL, propertyDataType=String, propertyType=USER, propertyListeners=[]], Property [propertyName=delimiter, propertyRenderer=TEXT, propertyGroup=TextProperties, propertySubGroup=GENERAL, propertyDataType=String, propertyType=USER, propertyListeners=[]]], TextProperties.Opetional=[Property [propertyName=charset, propertyRenderer=TEXT, propertyGroup=TextProperties, propertySubGroup=Opetional, propertyDataType=String, propertyType=USER, propertyListeners=[]], Property [propertyName=phase, propertyRenderer=TEXT, propertyGroup=TextProperties, propertySubGroup=Opetional, propertyDataType=String, propertyType=USER, propertyListeners=[]]]}, RadioProperties={RadioProperties.safe=[Property [propertyName=safe, propertyRenderer=RADIO, propertyGroup=RadioProperties, propertySubGroup=safe, propertyDataType=boolean, propertyType=USER, propertyListeners=[]]], RadioProperties.header=[Property [propertyName=has_header, propertyRenderer=RADIO, propertyGroup=RadioProperties, propertySubGroup=header, propertyDataType=boolean, propertyType=USER, propertyListeners=[]]]}}";
		//When
		PropertyTreeBuilder propertyTreeBuilder = new PropertyTreeBuilder(inputComponentProperties);
		
		//Then
		assertEquals(expectedTree,propertyTreeBuilder.getPropertyTree().toString());
	}
}
