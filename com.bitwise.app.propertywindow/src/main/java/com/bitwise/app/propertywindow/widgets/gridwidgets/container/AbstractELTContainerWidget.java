package com.bitwise.app.propertywindow.widgets.gridwidgets.container;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;

// TODO: Auto-generated Javadoc
/**
 * 
 * @author Bitwise
 * Sep 18, 2015
 * 
 */

public abstract class AbstractELTContainerWidget {
	protected Composite inputContainer;
	protected Composite outputContainer;
	
	/**
	 * Instantiates a new abstract elt container widget.
	 * 
	 * @param container
	 *            the container
	 */
	public AbstractELTContainerWidget(Composite container){
		this.inputContainer = container;
	};
	
	/**
	 * Creates the container widget.
	 */
	public abstract void createContainerWidget();
	
	/**
	 * Number of basic widgets.
	 * 
	 * @param subWidgetCount
	 *            the sub widget count
	 * @return the abstract elt container widget
	 */
	public abstract AbstractELTContainerWidget numberOfBasicWidgets(int subWidgetCount);
	
	/**
	 * Attach widget.
	 * 
	 * @param eltWidget
	 *            the elt widget
	 */
	public abstract void attachWidget(AbstractELTWidget eltWidget);
	
	public Composite getContainerControl(){
		return outputContainer;
	}
}
