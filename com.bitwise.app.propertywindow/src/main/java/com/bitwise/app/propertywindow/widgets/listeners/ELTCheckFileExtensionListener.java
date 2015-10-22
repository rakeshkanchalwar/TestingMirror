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

/**
 * The listener interface for receiving ELTCheckFileExtension events. The class that is interested in processing a
 * ELTCheckFileExtension event implements this interface, and the object created with that class is registered with a
 * component using the component's <code>addELTCheckFileExtensionListener<code> method. When
 * the ELTCheckFileExtension event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see ELTCheckFileExtensionEvent
 */
public class ELTCheckFileExtensionListener implements IELTListener{

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
				if(!((Button)widgetList[1]).getSelection()){
				ControlDecoration	fieldNameMustJava = WidgetUtility.addDecorator((Text)widgetList[0],Messages.INVALID_FILE);
				if(!WidgetUtility.isFileExtention((((Text)widgetList[0]).getText()).trim(), ".java") && !(((Text)widgetList[0]).getText().trim().isEmpty())){
					fieldNameMustJava.show();
				((Text)widgetList[0]).setBackground(new Color(Display.getDefault(), 255,
						255, 204));
				}
					else  
					{   
						((Text)widgetList[0]).setBackground(new Color(Display.getDefault(), 255,
							255, 255));
					fieldNameMustJava.hide(); 
					}
				}
			} 
		};
		return listener;
	}

	
}
