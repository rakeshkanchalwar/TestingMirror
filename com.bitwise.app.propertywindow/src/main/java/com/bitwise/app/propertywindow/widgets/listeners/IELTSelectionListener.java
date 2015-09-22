package com.bitwise.app.propertywindow.widgets.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;

public class IELTSelectionListener implements IELTListener{

	@Override
	public int getListenerType() {
		return SWT.MouseEnter;
	}

	@Override
	public Listener getListener(PropertyDialogButtonBar propertyDialogButtonBar,Widget... widgets) {
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
