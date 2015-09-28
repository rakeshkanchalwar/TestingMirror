package com.bitwise.app.propertywindow.widgets.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;

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

public abstract void mouseDoubleClickAction(
		PropertyDialogButtonBar propertyDialogButtonBar,
		final ListenerHelper helpers, Widget... widgets);	
}
