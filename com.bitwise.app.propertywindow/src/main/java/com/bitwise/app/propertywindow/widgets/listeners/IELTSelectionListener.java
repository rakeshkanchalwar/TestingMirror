package com.bitwise.app.propertywindow.widgets.listeners;

import org.eclipse.core.databinding.SetBinding;
import org.eclipse.osgi.framework.eventmgr.EventListeners;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;

public class IELTSelectionListener implements IELTListener{

	@Override
	public int getListenerType() {
		return SWT.MouseEnter;
	}

	@Override
	public Listener getListener() {
		/*SelectionAdapter selection=new SelectionAdapter() {
			@Override 
		    public void widgetSelected(SelectionEvent e) {
			
			}
		};*/
		Listener list=new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				if(event.text.isEmpty()){
					
				}
			}
		};
		return null;
	}

}
