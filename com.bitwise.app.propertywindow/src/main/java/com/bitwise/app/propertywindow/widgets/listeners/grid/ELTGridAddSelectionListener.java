package com.bitwise.app.propertywindow.widgets.listeners.grid;

import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.listeners.ELTSelectionTaskListener;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper;

public class ELTGridAddSelectionListener extends ELTSelectionTaskListener{

	@Override
	public void selectionListenerAction(
			PropertyDialogButtonBar propertyDialogButtonBar,
			ListenerHelper helpers, Widget... widgets) {
		ELTGridDetails eltGridDetails = (ELTGridDetails)helpers.getObject();
		eltGridDetails.getGridWidgetCommonBuilder().createDefaultSchema(eltGridDetails.getGrids(), eltGridDetails.getTableViewer(), eltGridDetails.getLabel());
		
	}

	
}
