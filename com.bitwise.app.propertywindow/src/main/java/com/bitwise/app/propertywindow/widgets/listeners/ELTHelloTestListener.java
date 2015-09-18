package com.bitwise.app.propertywindow.widgets.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class ELTHelloTestListener implements IELTListener{

	@Override
	public int getListenerType() {
		return SWT.Selection;
	}

	@Override
	public Listener getListener() {
		
		Listener listener = new Listener() {
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if(event.type == SWT.Selection){
					System.out.println("Say Hello");
				}

			}
		};


		return listener;
	}

}
