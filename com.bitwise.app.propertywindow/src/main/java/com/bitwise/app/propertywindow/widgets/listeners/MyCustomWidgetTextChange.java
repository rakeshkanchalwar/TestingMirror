package com.bitwise.app.propertywindow.widgets.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.utils.WordUtils;

public class MyCustomWidgetTextChange implements IELTListener{

	@Override
	public int getListenerType() {
		// TODO Auto-generated method stub
		return SWT.CHANGED;
	}

	@Override
	public Listener getListener(final PropertyDialogButtonBar propertyDialogButtonBar,final ListenerHelper helpers, Widget... widgets) {
		// TODO Auto-generated method stub
		final Widget[]  wigetList = widgets;
		
		
		
		Listener listener = new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if(event.type == SWT.CHANGED){
					System.out.println(((Text)wigetList[0]).getText());
					if(((Text)wigetList[0]).getText().equals("")){
						//customWidget.getApplyButton().setEnabled(false);
						System.out.println("Deasebaling apply");
						propertyDialogButtonBar.enableApplyButton(false);
					}else{
						//customWidget.getApplyButton().setEnabled(true);
						propertyDialogButtonBar.enableApplyButton(true);
						WordUtils wordUtils = (WordUtils)helpers.getObject();
						System.out.println(wordUtils.capitalize("hytyu ytuty tyu ytuty", null));
					}
				}
				
			}
		};
		return listener;
	}

}
