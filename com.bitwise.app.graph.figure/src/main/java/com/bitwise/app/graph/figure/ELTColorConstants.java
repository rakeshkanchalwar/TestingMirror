package com.bitwise.app.graph.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.Color;

public interface ELTColorConstants {

	public final static Color lightRed = new Color(null,235, 176, 182);
	public final static Color darkRed = new Color(null,191, 52, 114);
	public final static Color darkGrey = new Color(null,115, 119, 120);
	public final static Color lightGrey = new Color(null,220, 221, 227);
	public final static Color lightBlue = new Color(null, 22, 169, 199);
	public final static Color darkBlue =  new Color(null, 17, 128, 151);
	public final static Color componentSelectBlue =  new Color(null, 83,126,137);
	public final static Color black = ColorConstants.black;

	
	public final static Color bgComponent = lightGrey;
	public final static Color bgComponentSelected = componentSelectBlue;
	public final static Color componentBorder = darkGrey;
	public final static Color componentSelectedBorder = darkBlue;
	
	
}
