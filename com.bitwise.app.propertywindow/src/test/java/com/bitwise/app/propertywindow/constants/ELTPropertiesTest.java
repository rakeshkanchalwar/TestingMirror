package com.bitwise.app.propertywindow.constants;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Oct 05, 2015
 * 
 */

public class ELTPropertiesTest {
	
	@Test
	public void itShouldProvideNameProperty(){
		assertEquals("name",ELTProperties.NAME_PROPERTY.getPropertyName());		
	}
}
