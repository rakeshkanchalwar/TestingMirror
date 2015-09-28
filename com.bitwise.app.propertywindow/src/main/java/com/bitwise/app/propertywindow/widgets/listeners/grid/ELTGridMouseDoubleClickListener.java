package com.bitwise.app.propertywindow.widgets.listeners.grid;

import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.listeners.ELTMouseDoubleClickListener;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper;

public class ELTGridMouseDoubleClickListener extends ELTMouseDoubleClickListener{

	@Override
	public void mouseDoubleClickAction(PropertyDialogButtonBar propertyDialogButtonBar,ListenerHelper helpers, Widget... widgets){
		// TODO Auto-generated method stub
		ELTGridDetails eltGridDetails = (ELTGridDetails)helpers.getObject();
		eltGridDetails.getGridWidgetCommonBuilder().createDefaultSchema(eltGridDetails.getGrids(), eltGridDetails.getTableViewer(), eltGridDetails.getLabel());

	}

}
