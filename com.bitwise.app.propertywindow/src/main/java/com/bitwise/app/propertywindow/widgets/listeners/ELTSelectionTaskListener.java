package com.bitwise.app.propertywindow.widgets.listeners;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving ELTSelectionTask events. The class that is interested in processing a
 * ELTSelectionTask event implements this interface, and the object created with that class is registered with a
 * component using the component's <code>addELTSelectionTaskListener<code> method. When
 * the ELTSelectionTask event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see ELTSelectionTaskEvent
 */
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
	
	/**
	 * Selection listener action.
	 * 
	 * @param propertyDialogButtonBar
	 *            the property dialog button bar
	 * @param helpers
	 *            the helpers
	 * @param widgets
	 *            the widgets
	 */
	public abstract void selectionListenerAction(PropertyDialogButtonBar propertyDialogButtonBar,
			final ListenerHelper helpers, Widget... widgets);
	
}
