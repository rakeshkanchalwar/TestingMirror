package com.bitwise.app.eltproperties.property;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 04, 2015
 * 
 */

public interface IPropertyTreeBuilder {
	public LinkedHashMap<String, LinkedHashMap<String, ArrayList<Property>>> getPropertyTree();
}
