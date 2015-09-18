package com.bitwise.app.propertywindow.widgets.schemagrid;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;

public class SchemaUtility {
	
	public static CellEditor[] createCellEditorList(Table table,int size){
		
		CellEditor[] cellEditors = new CellEditor[size];
		cellEditors[0]=new TextCellEditor(table);
		cellEditors[1]=new ComboBoxCellEditor(table, ELTSchemaWidget.getDataType(),
				SWT.READ_ONLY);
		cellEditors[2]=new TextCellEditor(table);
		cellEditors[3]=new TextCellEditor(table);
		return cellEditors;
	}

}
