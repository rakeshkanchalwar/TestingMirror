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
 * The listener interface for receiving ELTNormalFocusOut events. The class that is interested in processing a
 * ELTNormalFocusOut event implements this interface, and the object created with that class is registered with a
 * component using the component's <code>addELTNormalFocusOutListener<code> method. When
 * the ELTNormalFocusOut event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see ELTNormalFocusOutEvent
 */
public class ELTNormalFocusOutListener implements IELTListener {

	ControlDecoration txtDecorator;

	@Override
	public int getListenerType() {
		return SWT.FocusOut;
	}

	@Override
	public Listener getListener(PropertyDialogButtonBar propertyDialogButtonBar, ListenerHelper helper,
			Widget... widgets) {
		final Widget[] widgetList = widgets;
		if (helper != null) {
			txtDecorator = (ControlDecoration) helper.get(HelperType.CONTROL_DECORATION);
			txtDecorator.hide();
		}

		Listener listener = new Listener() {

			@Override
			public void handleEvent(Event event) {
				String charSet = ((Text) widgetList[0]).getText().trim();
				if (SWT.FocusOut == event.type) {
					if (StringUtils.isBlank(charSet)){
						txtDecorator.show();
						((Text) widgetList[0]).setBackground(new Color(Display.getDefault(), 255, 255, 204));
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
