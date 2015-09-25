package com.bitwise.app.propertywindow.widgets.listeners;


import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;

public class ELTSelectionListener implements IELTListener{

	@Override
	public int getListenerType() {
		
		return SWT.Selection;
	}

	@Override
	public Listener getListener(PropertyDialogButtonBar propertyDialogButtonBar,ListenerHelper helpers, Widget... widgets) {
		final Widget[] widgetList = widgets;
		/*for(Widget widget: widgets){
			widgetList.add(widget);
		}*/
				
		Listener listener=new Listener() {
			@Override
			public void handleEvent(Event event) {
				if(((Combo)widgetList[0]).getText().equals("Parameter")){
					((Text)widgetList[1]).setVisible(true);
				}else{
					((Text)widgetList[1]).setVisible(false);
				}
			}
		};
		return listener;
	}

	
}
