package com.bitwise.app.propertywindow.widgets.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving ELTMouseDoubleClick events. The class that is interested in processing a
 * ELTMouseDoubleClick event implements this interface, and the object created with that class is registered with a
 * component using the component's <code>addELTMouseDoubleClickListener<code> method. When
 * the ELTMouseDoubleClick event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see ELTMouseDoubleClickEvent
 */
public abstract class ELTMouseDoubleClickListener implements IELTListener{

	@Override
	public int getListenerType() {
		// TODO Auto-generated method stub
		return SWT.MouseDoubleClick;
	}

	@Override
	public Listener getListener(
			final PropertyDialogButtonBar propertyDialogButtonBar,
			final ListenerHelper helpers, final Widget... widgets) {
		Listener listener=new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				mouseDoubleClickAction(propertyDialogButtonBar,helpers, widgets);
			}
			};
	return listener;
	}

/**
 * Mouse double click action.
 * 
 * @param propertyDialogButtonBar
 *            the property dialog button bar
 * @param helpers
 *            the helpers
 * @param widgets
 *            the widgets
 */
public abstract void mouseDoubleClickAction(
		PropertyDialogButtonBar propertyDialogButtonBar,
		final ListenerHelper helpers, Widget... widgets);	
}
