package com.bitwise.app.propertywindow.widgets.utility;

import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import com.bitwise.app.propertywindow.messages.Messages;

public abstract class GridWidgetCommonBuilder {
	public abstract void createDefaultSchema(List grids,TableViewer tableViewer,Label errorLabel);
	public abstract CellEditor[] createCellEditorList(Table table,int size);
	
	protected CellEditor[] createCellEditor(int size){
		CellEditor[] cellEditor = new CellEditor[size];
		return cellEditor;
	}
	
	protected void addTextEditor(Table table, CellEditor[] cellEditor, int position){
		cellEditor[position]=new TextCellEditor(table);
	}
	
	protected void addComboBox(Table table, CellEditor[] cellEditor, String[] data, int position){
		cellEditor[position] = new ComboBoxCellEditor(table, data,SWT.READ_ONLY);		
	}
	
	public static String[] dataTypeList;
	public static String[] dataTypeKey;
	public static String[] dataTypeValue;

	public static void setDataTypeKeyValue() {
		if (dataTypeList != null)
		{
			dataTypeKey= new String[dataTypeList.length];
			dataTypeValue=new String[dataTypeList.length];
			for (int i=0;i<dataTypeList.length;i++) {
				String[] data = dataTypeList[i].split("#");
				dataTypeKey[i]=data[0];
				dataTypeValue[i]=data[1];
			}
		}
		else {
			String schemaList = Messages.DATATYPELIST;
			dataTypeList = schemaList.split(",");
			dataTypeKey= new String[dataTypeList.length];
			dataTypeValue=new String[dataTypeList.length];
			for (int i=0;i<dataTypeList.length;i++) {
				String[] data = dataTypeList[i].split("#");
				dataTypeKey[i]=data[0];
				dataTypeValue[i]=data[1];
			}
		}
			
	}


	public static String[] getDataTypeValue() {
		if(dataTypeValue!=null){
			return dataTypeValue;
		}
		else{
			setDataTypeKeyValue();
			return dataTypeValue;
		}
	}
	
	public static String[] getDataTypeKey() {
		if(dataTypeKey!=null){
			return dataTypeKey;
		}
		else{
			setDataTypeKeyValue();
			return dataTypeKey;
		}
	}
}
