package com.bitwise.app.propertywindow.widgets.listeners.extended;

import org.eclipse.jface.viewers.ICellEditorListener;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;

// TODO: Auto-generated Javadoc
/**
 * 
 * @author Bitwise
 * Oct 12, 2015
 * 
 */

public class GridCellEditorListener implements ICellEditorListener{

	private PropertyDialogButtonBar propertyDialogButtonBar;
	
	/**
	 * Instantiates a new grid cell editor listener.
	 * 
	 * @param propertyDialogButtonBar
	 *            the property dialog button bar
	 */
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
