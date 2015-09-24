package com.bitwise.app.propertywindow.widgets.gridwidgets.basic;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.listeners.IELTListener;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 18, 2015
 * 
 */

public abstract class AbstractELTWidget {
	protected Widget widget;
	protected Object jfaceWidgets;

	public abstract void attachWidget(Composite container);
	
	public void attachListener(IELTListener ELTListener,PropertyDialogButtonBar propertyDialogButtonBar,ListenerHelper helpers, Widget... widgets) throws Exception{
		if(widget != null)
			widget.addListener(ELTListener.getListenerType(), ELTListener.getListener(propertyDialogButtonBar, helpers, widgets));
		else
			throw new Exception("IELTWidget.widget object has set in sub class ");
	}
	
	public Widget getSWTWidgetControl(){
		return widget;
	}
	
	public Object getJfaceWidgetControl(){
		return jfaceWidgets;
	}
}
