package com.bitwise.app.propertywindow.widgets.listeners.grid;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.listeners.ELTMouseDoubleClickListener;
import com.bitwise.app.propertywindow.widgets.listeners.ELTMouseDownListener;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper;
import com.bitwise.app.propertywindow.widgets.utility.WidgetUtility;

public class ELTGridMouseDownListener extends ELTMouseDownListener{
	public ControlDecoration fieldNameDecorator;
	@Override
	public void mouseDownAction(PropertyDialogButtonBar propertyDialogButtonBar,ListenerHelper helpers, Widget... widgets){
		fieldNameDecorator =	WidgetUtility.addDecorator((Control)widgets[0],Messages.FIELDNAMEERROR)	;
		fieldNameDecorator.show();
	}

}
