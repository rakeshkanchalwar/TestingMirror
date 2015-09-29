package com.bitwise.app.propertywindow.widgets.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.customwidgets.runtimeproperty.ELTRuntimePropertiesWidget;

public class ELTRuntimeButtonClickListener implements IELTListener {

	@Override
	public int getListenerType() {
		return SWT.Selection;
	}

	@Override
	public Listener getListener(
			PropertyDialogButtonBar propertyDialogButtonBar,
			final ListenerHelper helpers, Widget... widgets) {
		Listener listener = new Listener() {
			public void handleEvent(Event event) {
				if (event.type == SWT.Selection) {
					((ELTRuntimePropertiesWidget)helpers.object).newWindowLauncher();
				}

			}
		};

		return listener;
	}
}
