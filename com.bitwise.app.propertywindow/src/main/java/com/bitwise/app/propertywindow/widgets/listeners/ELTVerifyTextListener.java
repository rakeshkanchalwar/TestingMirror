package com.bitwise.app.propertywindow.widgets.listeners;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;

public class ELTVerifyTextListener implements IELTListener{

	@Override
	public int getListenerType() {
		// TODO Auto-generated method stub
		return SWT.Verify;
	}

	@Override
	public Listener getListener(PropertyDialogButtonBar propertyDialogButtonBar, Widget... widgets) {
			final Widget[] widgetList = widgets;
				Listener listener=new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					System.out.println(((Text)widgetList[0]).getText());
					//String text=((Text)widgetList[0]).getText();
					String string=event.text;
					Matcher matchs=Pattern.compile("[\\w]*").matcher(string);
					if(!matchs.matches()){
						event.doit=false;
			
					}
				}
			};
		return listener;
	}

}


