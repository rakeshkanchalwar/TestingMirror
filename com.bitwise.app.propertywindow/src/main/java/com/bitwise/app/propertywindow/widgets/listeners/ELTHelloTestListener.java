package com.bitwise.app.propertywindow.widgets.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 18, 2015
 * 
 */

public class ELTHelloTestListener implements IELTListener{

	@Override
	public int getListenerType() {
		return SWT.Selection;
	}

	@Override
	public Listener getListener(PropertyDialogButtonBar propertyDialogButtonBar,Widget... widgets) {
		
		int index = 0;
		
		final Widget[] widgetArray = widgets.clone();
		
		
		Listener listener = new Listener() {
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if(event.type == SWT.Selection){
					System.out.println("Hello " + ((Text)widgetArray[0]).getText());
					
				}

			}
		};


		return listener;
	}

}
