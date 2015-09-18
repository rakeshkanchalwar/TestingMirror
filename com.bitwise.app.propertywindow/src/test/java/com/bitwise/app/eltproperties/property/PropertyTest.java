package com.bitwise.app.eltproperties.property;

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
		Property property=new Property("String","delimiter","TEXT");
		property.group("TextProperties");
		
		//Then
		assertEquals(expectedProperty,property.toString());	
	}
	
	@Test
	public void itShouldAllowToAddOptionalPropertySubGroupName(){
		//Given
		String expectedProperty = "Property [propertyName=delimiter, propertyRenderer=TEXT, propertyGroup=TextProperties, propertySubGroup=TextSubgroup, propertyDataType=String, propertyType=USER, propertyListeners=[]]";
		
		//When
		Property property=new Property("String","delimiter","TEXT");
		property.group("TextProperties");
		property.subGroup("TextSubgroup");
		
		//Then
		assertEquals(expectedProperty,property.toString());	
	}
	
	@Test
	public void itShouldAllowToAddOptionalPropertyType(){
		//Given
		String expectedProperty = "Property [propertyName=delimiter, propertyRenderer=TEXT, propertyGroup=TextProperties, propertySubGroup=TextSubgroup, propertyDataType=String, propertyType=System, propertyListeners=[]]";
		
		//When
		Property property=new Property("String","delimiter","TEXT");
		property.group("TextProperties");
		property.subGroup("TextSubgroup");
		property.type("System");
		//Then
		assertEquals(expectedProperty,property.toString());
	}
	
	@Test
	public void itShouldAllowToAddOptionalPropertyListener(){
		//Given
		String expectedProperty = "Property [propertyName=delimiter, propertyRenderer=TEXT, propertyGroup=TextProperties, propertySubGroup=GENERAL, propertyDataType=String, propertyType=USER, propertyListeners=[validator, keyPressedAction]]";
		
		//When
		Property property=new Property("String","delimiter","TEXT");
		property.group("TextProperties");
		property.listener("validator");
		property.listener("keyPressedAction");
		
		//Then
		assertEquals(expectedProperty,property.toString());
	}
	
	@Test
	public void itShouldGiveAlmostUniqHashCode(){
		//Given
		int expectedHashCode = 790634273; 
				
		//When
		Property property=new Property("String","delimiter","TEXT");
		property.group("TextProperties");
		property.subGroup("TextSubgroup");
		property.type("System");
		property.listener("validator");
		property.listener("keyPressedAction");
		
		//Then
		assertEquals(expectedHashCode,property.hashCode());
	}
	
	@Test
	public void itShouldHaveEqualMethod(){
		//Given
		Property expectedProperty=new Property("String","delimiter","TEXT");
		expectedProperty.group("TextProperties");
		expectedProperty.subGroup("TextSubgroup");
		expectedProperty.type("System");
		expectedProperty.listener("validator");
		expectedProperty.listener("keyPressedAction");
		
		
		//When
		Property actualProperty=new Property("String","delimiter","TEXT");
		actualProperty.group("TextProperties");
		actualProperty.subGroup("TextSubgroup");
		actualProperty.type("System");
		actualProperty.listener("validator");
		actualProperty.listener("keyPressedAction");
		
		
		assertTrue(actualProperty.equals(expectedProperty));
	}
	
	@Test
	public void itShouldGiveSubgroupNameAsConcatinationOfGroupAndSubGroupName(){
		//Given
		String expectedSubgroupName = "TextProperties.TestSubGroup";
		
		//When
		Property property=new Property("String","delimiter","TEXT");
		property.group("TextProperties");
		property.subGroup("TestSubGroup");
		
		//Then
		assertEquals(expectedSubgroupName,property.getPropertySubGroup());
	}
}
