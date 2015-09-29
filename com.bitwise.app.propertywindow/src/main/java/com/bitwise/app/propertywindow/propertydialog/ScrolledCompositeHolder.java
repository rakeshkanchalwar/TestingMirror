package com.bitwise.app.propertywindow.propertydialog;

import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 08, 2015
 * 
 */

public class ScrolledCompositeHolder {
	private Composite composite;
	private ScrolledComposite scrolledComposite;
	
	private ScrolledCompositeHolder(){
		
	}
	
	public ScrolledCompositeHolder(ScrolledComposite scrolledComposite , Composite composite) {
		super();
		this.composite = composite;
		this.scrolledComposite = scrolledComposite;
	}

	public Composite getComposite() {
		return composite;
	}

	public ScrolledComposite getScrolledComposite() {
		return scrolledComposite;
	}
	
}
