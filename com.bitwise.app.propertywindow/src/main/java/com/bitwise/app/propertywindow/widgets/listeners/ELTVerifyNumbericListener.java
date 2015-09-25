package com.bitwise.app.propertywindow.widgets.listeners;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;

public class ELTVerifyNumbericListener implements IELTListener{

	private ControlDecoration txtDecorator;

	@Override
	public int getListenerType() {
		// TODO Auto-generated method stub
		return SWT.Verify;
	}

	@Override
	public Listener getListener(PropertyDialogButtonBar propertyDialogButtonBar, ListenerHelper helper,  Widget... widgets) {
			final Widget[] widgetList = widgets;
			if (helper != null) {
				txtDecorator = (ControlDecoration) helper.getObject();
				
			}
				Listener listener=new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					String string=event.text;
					Matcher matchs=Pattern.compile("[\\d]*").matcher(string);
					if(!matchs.matches()){
						txtDecorator.setDescriptionText(Messages.FIELDPHASE);
						txtDecorator.show();
						event.doit=false;
				}else
					txtDecorator.hide();
					
				}
			};
		return listener;
	}
}
