package com.bitwise.app.propertywindow.property;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * 
 * @author Bitwise
 * Sep 04, 2015
 * 
 */

public interface IPropertyTreeBuilder {
	public LinkedHashMap<String, LinkedHashMap<String, ArrayList<Property>>> getPropertyTree();
}
