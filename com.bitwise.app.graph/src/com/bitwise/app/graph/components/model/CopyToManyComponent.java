package com.bitwise.app.graph.components.model;

import org.eclipse.swt.graphics.Image;

public class CopyToManyComponent extends Component{
	/** A 16x16 pictogram of an elliptical shape. */
	//private static final Image COPYTOMANY_ICON = createImage("icons/CopyToMany_Palette.png");
	private static final Image COPYTOMANY_ICON = createImage("icons/broadcast2.png");

	private static final long serialVersionUID = 1;

	public Image getIcon() {
		return COPYTOMANY_ICON;
	}

	public String toString() {
		return "CopyToMany " + hashCode();
	}
}
