package com.bitwise.app.propertywindow.widgets.listeners.extended;

import org.eclipse.jface.viewers.ICellEditorListener;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Oct 12, 2015
 * 
 */

public class GridCellEditorListener implements ICellEditorListener{

	private PropertyDialogButtonBar propertyDialogButtonBar;
	
	public GridCellEditorListener(PropertyDialogButtonBar propertyDialogButtonBar){
		this.propertyDialogButtonBar = propertyDialogButtonBar;
	}
	
	@Override
	public void applyEditorValue() {
		// TODO Auto-generated method stub
	}

	@Override
	public void cancelEditor() {
		// TODO Auto-generated method stub
	}

	@Override
	public void editorValueChanged(boolean oldValidState, boolean newValidState) {
		propertyDialogButtonBar.enableApplyButton(true);	
		
	}

}
