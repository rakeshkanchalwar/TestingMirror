package com.bitwise.app.propertywindow.property;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.bitwise.app.propertywindow.property.Property;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 04, 2015
 * 
 */

public class PropertyTest {

	@Test
	public void itShouldAllowToAddOptionalPropertyGroupName(){
		//Given
		String expectedProperty = "Property [propertyName=delimiter, propertyRenderer=TEXT, propertyGroup=TextProperties, propertySubGroup=GENERAL, propertyDataType=String, propertyType=USER, propertyListeners=[]]";
		
		//When
		Property property=new Property.Builder("String","delimiter","TEXT").group("TextProperties").build();
		
		//Then
		assertEquals(expectedProperty,property.toString());	
	}
	
	@Test
	public void itShouldAllowToAddOptionalPropertySubGroupName(){
		//Given
		String expectedProperty = "Property [propertyName=delimiter, propertyRenderer=TEXT, propertyGroup=TextProperties, propertySubGroup=TextSubgroup, propertyDataType=String, propertyType=USER, propertyListeners=[]]";
		
		//When
		Property property=new Property.Builder("String","delimiter","TEXT").group("TextProperties").subGroup("TextSubgroup").build();
		
		//Then
		assertEquals(expectedProperty,property.toString());	
	}
	
	@Test
	public void itShouldAllowToAddOptionalPropertyType(){
		//Given
		String expectedProperty = "Property [propertyName=delimiter, propertyRenderer=TEXT, propertyGroup=TextProperties, propertySubGroup=TextSubgroup, propertyDataType=String, propertyType=System, propertyListeners=[]]";
		
		//When
		Property property=new Property.Builder("String","delimiter","TEXT")
		.group("TextProperties")
		.subGroup("TextSubgroup")
		.type("System").build();
		//Then
		assertEquals(expectedProperty,property.toString());
	}
		
	
	@Test
	public void itShouldGiveAlmostUniqHashCode(){
		//Given
		int expectedHashCode = 790634273; 
				
		//When
		Property property=new Property.Builder("String","delimiter","TEXT")
		.group("TextProperties")
		.subGroup("TextSubgroup")
		.type("System").build();
		
		//Then
		assertEquals(expectedHashCode,property.hashCode());
	}
	
	@Test
	public void itShouldHaveEqualMethod(){
		//Given
		Property expectedProperty=new Property.Builder("String","delimiter","TEXT")
		.group("TextProperties")
		.subGroup("TextSubgroup")
		.type("System").build();
		
		
		//When
		Property actualProperty=new Property.Builder("String","delimiter","TEXT")
		.group("TextProperties")
		.subGroup("TextSubgroup")
		.type("System").build();
		
		assertTrue(actualProperty.equals(expectedProperty));
	}
	
	@Test
	public void itShouldGiveSubgroupIDAsConcatinationOfGroupAndSubGroupName(){
		//Given
		String expectedSubgroupName = "TextProperties.TestSubGroup";
		
		//When
		Property property=new Property.Builder("String","delimiter","TEXT")
		.group("TextProperties")
		.subGroup("TestSubGroup").build();
		
		//Then
		//assertEquals(expectedSubgroupName,property.getPropertySubGroup());
		assertEquals(expectedSubgroupName,property.getPropertySubGroupID());
	}
	
	@Test
	public void itShouldGiveSubgroupName(){
		//Given
		String expectedSubgroupName = "TestSubGroup";
		
		//When
		Property property=new Property.Builder("String","delimiter","TEXT")
		.group("TextProperties")
		.subGroup("TestSubGroup").build();
		
		//Then
		//assertEquals(expectedSubgroupName,property.getPropertySubGroup());
		assertEquals(expectedSubgroupName,property.getPropertySubGroup());
	}
}
