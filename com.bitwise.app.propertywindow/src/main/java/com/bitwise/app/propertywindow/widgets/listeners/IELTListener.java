package com.bitwise.app.propertywindow.widgets.listeners;

import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 18, 2015
 * 
 */

public interface IELTListener {
	public int getListenerType();
	public Listener getListener(PropertyDialogButtonBar propertyDialogButtonBar,Widget... widgets);
}
