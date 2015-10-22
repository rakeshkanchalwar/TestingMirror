package com.bitwise.app.perspective.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Test;

/**
 * 
 * @author Bitwise
 * Aug 28, 2015
 *
 */

public class ELTPerspectivesTest {
			
	@Test
	public void itShouldHaveELTPerspectiveID(){
		//Given
		String expectedPerspective = "com.bitwise.app.perspective.ETLPerspective";
		
		//When
		String actualPerspectiveID = ELTPerspectives.ETLPerspective.toString();
				
		//Then
		assertEquals(expectedPerspective, actualPerspectiveID);
	}
	
	@Test
	public void itShouldHaveOnePerspectiveID(){
		//Given
		ArrayList<String> expectedPerspectiveList = new ArrayList<String>(){
			private static final long serialVersionUID = 1L;
			{
				add("com.bitwise.app.perspective.ETLPerspective");
			}};		
		
		//When
		ELTPerspectives[] actualPerspectiveList = ELTPerspectives.values();
				
		//Then
		//Check in case of more than required ids
		assertFalse(expectedPerspectiveList.size() != actualPerspectiveList.length);
	}
	
	@Test
	public void itShouldReturnTrueIfGivenPerspectiveIDIsExist(){
		//Given
		String eltPerspectiveID="com.bitwise.app.perspective.ETLPerspective";
		
		//When-Then
		assertTrue(ELTPerspectives.contains(eltPerspectiveID));
	}
	
	@Test
	public void itShouldReturnFalseIfGivenPerspectiveIDDoesNotExist(){
		//Given
		String eltPerspectiveID="com.bitwise.app.perspective.TestPerspective";
		
		//When-Then
		assertFalse(ELTPerspectives.contains(eltPerspectiveID));
	}
}
