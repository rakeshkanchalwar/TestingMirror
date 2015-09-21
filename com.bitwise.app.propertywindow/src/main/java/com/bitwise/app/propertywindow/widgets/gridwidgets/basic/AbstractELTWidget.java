package com.bitwise.app.propertywindow.widgets.gridwidgets.basic;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.widgets.listeners.IELTListener;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 18, 2015
 * 
 */

public abstract class AbstractELTWidget {
	protected Widget widget;

	public abstract void attachWidget(Composite container);
	
	public void attachListener(IELTListener ELTListener,Widget... widgets) throws Exception{
		if(widget != null)
			widget.addListener(ELTListener.getListenerType(), ELTListener.getListener(widgets));
		else
			throw new Exception("IELTWidget.widget object has set in sub class ");
	}
	
	public Widget getWidgetControl(){
		return widget;
	}
}
