package com.bitwise.app.propertywindow.widgets.listeners;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;

public class ELTModifyListener implements IELTListener{

	ControlDecoration txtDecorator;
	
	@Override
	public int getListenerType() {
		return SWT.Modify;
	}

	@Override
	public Listener getListener(
			PropertyDialogButtonBar propertyDialogButtonBar,
			ListenerHelper helpers, Widget... widgets) {
		final Widget[] widgetList = widgets;
		if (helpers != null) {
			txtDecorator = (ControlDecoration) helpers.getObject();
		}
		Listener listener=new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				String string=((Text)widgetList[0]).getText().trim();
				if(event.type==SWT.Modify){
					if(string.isEmpty()){
						txtDecorator.setDescriptionText(Messages.EMPTYFIELDMESSAGE);
						txtDecorator.show();
						((Text) widgetList[0]).setBackground(new Color(Display.getDefault(), 255, 255, 204));
					}else{
						//txtDecorator.setDescriptionText(Messages.EMPTYFIELDMESSAGE);
						txtDecorator.hide();
					((Text) widgetList[0]).setBackground(new Color(Display.getDefault(), 255, 255, 255));
					}
					
				}else{
					//txtDecorator.setDescriptionText(Messages.EMPTYFIELDMESSAGE);
					txtDecorator.hide();
				((Text) widgetList[0]).setBackground(new Color(Display.getDefault(), 255, 255, 255));
				
				}
			}		
		};
		
		return listener;
	}

}
