package com.bitwise.app.propertywindow.widgets.listeners.grid;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.listeners.ELTSelectionTaskListener;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper;
import com.bitwise.app.propertywindow.widgets.utility.WidgetUtility;

public class ELTGridDeleteAllSelectionListener extends ELTSelectionTaskListener{

	@Override
	public void selectionListenerAction(
			PropertyDialogButtonBar propertyDialogButtonBar,
			ListenerHelper helpers, Widget... widgets) {
			Table table = (Table) widgets[0];
		boolean userAns = WidgetUtility.eltConfirmMessage("Do you really want to delete all properties?");
		if (userAns) {
			ELTGridDetails eltGridDetails = (ELTGridDetails)helpers.getObject();
			eltGridDetails.getGrids().removeAll(eltGridDetails.getGrids());
			eltGridDetails.getTableViewer().refresh();
		}
		
	
	}

	
}
