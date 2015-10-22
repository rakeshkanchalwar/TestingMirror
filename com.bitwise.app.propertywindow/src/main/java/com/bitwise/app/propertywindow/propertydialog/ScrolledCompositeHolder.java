package com.bitwise.app.propertywindow.propertydialog;

import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;

// TODO: Auto-generated Javadoc
/**
 * 
 * @author Bitwise
 * Sep 08, 2015
 * 
 */

public class ScrolledCompositeHolder {
	private Composite composite;
	private ScrolledComposite scrolledComposite;
		
	/**
	 * Instantiates a new scrolled composite holder.
	 * 
	 * @param scrolledComposite
	 *            the scrolled composite
	 * @param composite
	 *            the composite
	 */
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
