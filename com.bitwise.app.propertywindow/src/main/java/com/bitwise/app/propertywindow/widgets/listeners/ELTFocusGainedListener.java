package com.bitwise.app.propertywindow.widgets.listeners;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper.HelperType;

/**
 * The listener interface for receiving ELTFocusGained events. The class that is interested in processing a
 * ELTFocusGained event implements this interface, and the object created with that class is registered with a component
 * using the component's <code>addELTFocusGainedListener<code> method. When
 * the ELTFocusGained event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see ELTFocusGainedEvent
 */
public class ELTFocusGainedListener implements IELTListener {

	ControlDecoration txtDecorator;

	@Override
	public int getListenerType() {
		return SWT.FocusIn;
	}

	@Override
	public Listener getListener(
			PropertyDialogButtonBar propertyDialogButtonBar,
			ListenerHelper helper, Widget... widgets) {
		final Widget[] widgetList = widgets;
		if (helper != null) {
			txtDecorator = (ControlDecoration) helper.get(HelperType.CONTROL_DECORATION);
		}

		Listener listener = new Listener() {

			@Override
			public void handleEvent(Event event) {
				String charSet = ((Text) widgetList[0]).getText().trim();
				if (event.type == SWT.FocusIn) {
					((Text) widgetList[0]).setText(charSet.replace("@{", "").replace("}", ""));
				}
			}
		};

		return listener;
	}

}
