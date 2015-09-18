package com.bitwise.app.propertywindow.widgets.gridwidgets.container;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.IELTWidget;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 18, 2015
 * 
 */

public abstract class IELTContainerWidget {
	
	protected Composite inputContainer;
	protected Composite outputContainer;
	
	public IELTContainerWidget(Composite container){
		this.inputContainer = container;
	};
	
	public abstract void createContainerWidget();
	
	public abstract void attachWidget(IELTWidget eltWidget);
	
	public Composite getContainerControl(){
		return outputContainer;
	}
}
