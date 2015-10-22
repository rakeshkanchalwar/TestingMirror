package com.bitwise.app.propertywindow.widgets.listeners.grid;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.listeners.extended.GridCellEditorListener;

// TODO: Auto-generated Javadoc
/**
 * 
 * @author Bitwise
 * Oct 12, 2015
 * 
 */

public class GridChangeListener {
	private ArrayList<CellEditor> cellEditors;
	private PropertyDialogButtonBar propertyDialogButtonBar;
	private GridChangeListener(){
		
	}
	
	/**
	 * Instantiates a new grid change listener.
	 * 
	 * @param cellEditors
	 *            the cell editors
	 * @param propertyDialogButtonBar
	 *            the property dialog button bar
	 */
	public GridChangeListener(CellEditor[] cellEditors,PropertyDialogButtonBar propertyDialogButtonBar){
		this.cellEditors = new ArrayList<>();
		this.cellEditors.addAll(Arrays.asList(cellEditors));
		this.propertyDialogButtonBar = propertyDialogButtonBar;
	}
	
	/**
	 * Attach cell change listener.
	 */
	public void attachCellChangeListener(){
		for(CellEditor cellEditor : cellEditors){
			if(cellEditor instanceof TextCellEditor)
				cellEditor.addListener(new GridCellEditorListener(propertyDialogButtonBar));
			else if(cellEditor instanceof ComboBoxCellEditor){
				attachComboChangeListener(cellEditor);
			}
		}
	}
	private void attachComboChangeListener(CellEditor cellEditor) {
		((CCombo)cellEditor.getControl()).addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				propertyDialogButtonBar.enableApplyButton(true);
				super.widgetSelected(e);
			}
			
		});
	}
}
