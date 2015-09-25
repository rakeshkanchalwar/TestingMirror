package com.bitwise.app.propertywindow.widgets.listeners;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.utility.WidgetUtility;

public class ELTVerifyTextListener implements IELTListener{

	@Override
	public int getListenerType() {
		// TODO Auto-generated method stub
		return SWT.Verify;
	}

	@Override
	public Listener getListener(PropertyDialogButtonBar propertyDialogButtonBar, ListenerHelper helpers,  Widget... widgets) {
			final Widget[] widgetList = widgets;
			final ControlDecoration txtDecorator=WidgetUtility.addDecorator((Text)widgetList[0], Messages.CHARACTERSET);
				Listener listener=new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					String string=event.text;
					Matcher matchs=Pattern.compile("[\\w]*").matcher(string);
					if(!matchs.matches()){
						txtDecorator.show();
						event.doit=false;
				}else
					txtDecorator.hide();
					
				}
			};
		return listener;
	}

}
