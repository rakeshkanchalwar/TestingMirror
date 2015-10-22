package com.bitwise.app.propertywindow.widgets.listeners.grid;

import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.listeners.ELTMouseDoubleClickListener;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper;

/**
 * The listener interface for receiving ELTGridMouseDoubleClick events. The class that is interested in processing a
 * ELTGridMouseDoubleClick event implements this interface, and the object created with that class is registered with a
 * component using the component's <code>addELTGridMouseDoubleClickListener<code> method. When
 * the ELTGridMouseDoubleClick event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see ELTGridMouseDoubleClickEvent
 */
public class ELTGridMouseDoubleClickListener extends ELTMouseDoubleClickListener{

	@Override
	public void mouseDoubleClickAction(PropertyDialogButtonBar propertyDialogButtonBar,ListenerHelper helpers, Widget... widgets){
		// TODO Auto-generated method stub
		ELTGridDetails eltGridDetails = (ELTGridDetails)helpers.getObject();
		eltGridDetails.getGridWidgetCommonBuilder().createDefaultSchema(eltGridDetails.getGrids(), eltGridDetails.getTableViewer(), eltGridDetails.getLabel());

	}

}
