package com.bitwise.app.propertywindow.widgets.listeners;


import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper.HelperType;

/**
 * 
 */
public class FocusInListener implements IELTListener {

	ControlDecoration txtDecorator;

	@Override
	public int getListenerType() {
		return SWT.FocusIn;
	}

	@Override
	public Listener getListener(PropertyDialogButtonBar propertyDialogButtonBar, ListenerHelper helper, Widget... widgets) {
		final Widget[] widgetList = widgets;
		if (helper != null){
			txtDecorator = (ControlDecoration) helper.get(HelperType.CONTROL_DECORATION);
		}
		
		Listener listener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				String charSet = ((Text) widgetList[0]).getText().trim();
				if(SWT.FocusIn == event.type) {
					if (StringUtils.isBlank(charSet)) {
						txtDecorator.show();
						((Text) widgetList[0]).setBackground(new Color(Display.getDefault(), 255, 255, 255));
						((Text) widgetList[0]).setToolTipText(txtDecorator.getDescriptionText());
					} else {
						txtDecorator.hide();
					}
				}
			}
		};
		return listener;
	}
}
