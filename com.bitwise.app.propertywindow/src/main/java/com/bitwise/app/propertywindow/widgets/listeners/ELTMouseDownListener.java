package com.bitwise.app.propertywindow.widgets.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving ELTMouseDown events. The class that is interested in processing a ELTMouseDown
 * event implements this interface, and the object created with that class is registered with a component using the
 * component's <code>addELTMouseDownListener<code> method. When
 * the ELTMouseDown event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see ELTMouseDownEvent
 */
public abstract class ELTMouseDownListener implements IELTListener{

	@Override
	public int getListenerType() {
		// TODO Auto-generated method stub
		return SWT.MouseDown;
	}

	@Override
	public Listener getListener(
			final PropertyDialogButtonBar propertyDialogButtonBar,
			final ListenerHelper helpers, final Widget... widgets) {
		Listener listener=new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				mouseDownAction(propertyDialogButtonBar,helpers, widgets);
			}
			};
	return listener;
	}
	
	/**
	 * Mouse down action.
	 * 
	 * @param propertyDialogButtonBar
	 *            the property dialog button bar
	 * @param helpers
	 *            the helpers
	 * @param widgets
	 *            the widgets
	 */
	public abstract void mouseDownAction(
			PropertyDialogButtonBar propertyDialogButtonBar,
			final ListenerHelper helpers, Widget... widgets);	
}
