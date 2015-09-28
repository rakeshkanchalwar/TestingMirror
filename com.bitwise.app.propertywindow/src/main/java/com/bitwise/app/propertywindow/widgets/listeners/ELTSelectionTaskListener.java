package com.bitwise.app.propertywindow.widgets.listeners;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;

public abstract class ELTSelectionTaskListener implements IELTListener {
	@Override
	public int getListenerType() {

		return SWT.Selection;
	}

	@Override
	public Listener getListener(
			final PropertyDialogButtonBar propertyDialogButtonBar,
			final ListenerHelper helper, final Widget... widgets) {

		Listener listener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				selectionListenerAction(propertyDialogButtonBar,helper, widgets);
			}
		};
		return listener;
	}
	public abstract void selectionListenerAction(PropertyDialogButtonBar propertyDialogButtonBar,
			final ListenerHelper helpers, Widget... widgets);
	
}
