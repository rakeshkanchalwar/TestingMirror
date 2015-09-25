package com.bitwise.app.propertywindow.widgets.listeners;


import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;

public class ELTEnableButtonListener implements IELTListener{

	@Override
	public int getListenerType() {
		 
		return SWT.Selection;
	}
	@Override
	public Listener getListener(PropertyDialogButtonBar propertyDialogButtonBar,ListenerHelper helpers, Widget... widgets) {
		final Widget[] widgetList = widgets;
				 
		Listener listener=new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (((Button)widgetList[0]).getSelection()) {
					((Button)widgetList[1]).setEnabled(false);
					((Button)widgetList[2]).setEnabled(false);
				} else {
					((Button)widgetList[1]).setEnabled(true);
					((Button)widgetList[2]).setEnabled(true);
				}
				}
		};
		return listener;
	}

	
}
