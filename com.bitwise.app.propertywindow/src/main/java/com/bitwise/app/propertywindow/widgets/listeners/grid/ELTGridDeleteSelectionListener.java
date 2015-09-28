package com.bitwise.app.propertywindow.widgets.listeners.grid;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.listeners.ELTSelectionTaskListener;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper;
import com.bitwise.app.propertywindow.widgets.utility.WidgetUtility;

public class ELTGridDeleteSelectionListener extends ELTSelectionTaskListener{

	@Override
	public void selectionListenerAction(
			PropertyDialogButtonBar propertyDialogButtonBar,
			ListenerHelper helpers, Widget... widgets) {

		ELTGridDetails eltGridDetails = (ELTGridDetails)helpers.getObject();
		Table table =eltGridDetails.getTableViewer().getTable();
		int temp = table.getSelectionIndex();
		if (temp == -1) {
			WidgetUtility.errorMessage("Please Select row to delete");
		} else {
			table.remove(temp);
			eltGridDetails.getGrids().remove(temp);
		}		
	}

	
}
