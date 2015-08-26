package com.bitwise.app.perspective.config;

import java.util.ArrayList;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Aug 25, 2015
 *
 */

public class ELTToolPerspetiveList {
	
	public static ArrayList<String> requiredPerspetives;
	
	static{
		requiredPerspetives = new ArrayList<>();
		requiredPerspetives.add("org.eclipse.jdt.ui.JavaPerspective");
		requiredPerspetives.add("org.eclipse.wst.xml.ui.perspective");
		requiredPerspetives.add("com.bitwise.app.perspective.ETLPerspective");
	}
}
