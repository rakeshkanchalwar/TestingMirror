package com.bitwise.app.propertywindow.widgets.gridwidgets.container;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 18, 2015
 * 
 */

public abstract class AbstractELTContainerWidget {
	protected Composite inputContainer;
	protected Composite outputContainer;
	
	public AbstractELTContainerWidget(Composite container){
		this.inputContainer = container;
	};
	
	public abstract void createContainerWidget();
	
	public abstract AbstractELTContainerWidget numberOfBasicWidgets(int subWidgetCount);
	
	public abstract void attachWidget(AbstractELTWidget eltWidget);
	
	public Composite getContainerControl(){
		return outputContainer;
	}
}
