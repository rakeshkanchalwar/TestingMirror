package com.bitwise.app.propertywindow.widgets.listeners;


import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.utility.WidgetUtility;

public class ELTEmptyTextModifyListener implements IELTListener{

	@Override
	public int getListenerType() {
		
		return SWT.Modify;
	}
	@Override
	public Listener getListener(PropertyDialogButtonBar propertyDialogButtonBar,ListenerHelper helpers, Widget... widgets) {
		final Widget[] widgetList = widgets;
				
		Listener listener=new Listener() {
			@Override
			public void handleEvent(Event event) {
				ControlDecoration	fieldNameDecorator = WidgetUtility.addDecorator((Text)widgetList[0],Messages.OperationClassBlank);
				if (((Text)widgetList[0]).getText().trim().isEmpty()) {
					fieldNameDecorator.show(); 
					((Button)widgetList[1]).setEnabled(false);
					((Text)widgetList[0]).setBackground(new Color(Display.getDefault(), 255,
							255, 204));
				} else {
					fieldNameDecorator.hide(); 
					((Button)widgetList[1]).setEnabled(true); 
				}
				}
		};
		return listener;
	}

	
}
