package com.bitwise.app.eltproperties.exceptions;


/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 04, 2015
 * 
 */

public class EmptyComponentPropertiesException extends Exception{

	private static final long serialVersionUID = 1229993313725505841L;

	public EmptyComponentPropertiesException(){
        super("Found empty property list");
    }	
}
