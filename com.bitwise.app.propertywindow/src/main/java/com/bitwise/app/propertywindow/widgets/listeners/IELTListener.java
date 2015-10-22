package com.bitwise.app.propertywindow.widgets.listeners;

import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;

// TODO: Auto-generated Javadoc
/**
 * 
 * @author Bitwise
 * Sep 18, 2015
 * 
 */

public interface IELTListener {
	public int getListenerType();
	
	/**
	 * Gets the listener.
	 * 
	 * @param propertyDialogButtonBar
	 *            the property dialog button bar
	 * @param helpers
	 *            the helpers
	 * @param widgets
	 *            the widgets
	 * @return the listener
	 */
	public Listener getListener(PropertyDialogButtonBar propertyDialogButtonBar,ListenerHelper helpers,Widget... widgets);
}
