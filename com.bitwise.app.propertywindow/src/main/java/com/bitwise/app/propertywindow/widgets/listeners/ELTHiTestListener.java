package com.bitwise.app.propertywindow.widgets.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 18, 2015
 * 
 */

public class ELTHiTestListener implements IELTListener{

	@Override
	public int getListenerType() {
		// TODO Auto-generated method stub
		return SWT.MouseDoubleClick;
	}

	@Override
	public Listener getListener() {
		Listener listener = new Listener() {
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if(event.type == SWT.MouseDoubleClick){
					System.out.println("Hi");
				}

			}
		};


		return listener;
	}

}
