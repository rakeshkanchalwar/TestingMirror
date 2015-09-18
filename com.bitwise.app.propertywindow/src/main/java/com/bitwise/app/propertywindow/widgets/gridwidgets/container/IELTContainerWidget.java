package com.bitwise.app.propertywindow.widgets.gridwidgets.container;

import org.eclipse.swt.widgets.Composite;

import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.IELTWidget;

public abstract class IELTContainerWidget {
	
	protected Composite container;
	
	public IELTContainerWidget(Composite container){
		this.container = container;
	};
	
	public abstract void createContainerWidget();
	
	public abstract void attachWidget(IELTWidget eltWidget);
}
