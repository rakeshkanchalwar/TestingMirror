package com.bitwise.app.eltproperties.window;

import java.util.ArrayList;

import org.junit.Test;

import com.bitwise.app.eltproperties.property.Property;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 04, 2015
 * 
 */

public class ELTPropertyWindowTest {

	@Test
	public void itShouldCreatePropertyWindow(){		
		ArrayList<Property> inputComponentProperties = new ArrayList<>();
		ELTPropertyWindow eltPropertyWindow=new ELTPropertyWindow(inputComponentProperties);
		eltPropertyWindow.open();
	}
}
