package com.bitwise.app.propertywindow.widgets.customwidgets.schema;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.widgets.utility.GridWidgetCommonBuilder;
import com.bitwise.app.propertywindow.widgets.utility.WidgetUtility;

public class SchemaUtility implements GridWidgetCommonBuilder {


	public static String[] dataTypeList;
	public static String[]dataTypeKey;
	public static String[]dataTypeValue;

	
	public static CellEditor[] createCellEditorList(Table table,int size){
		
		CellEditor[] cellEditors = new CellEditor[size];
		cellEditors[0]=new TextCellEditor(table);
		cellEditors[1]=new ComboBoxCellEditor(table, getDataTypeKey(),
				SWT.READ_ONLY);
		cellEditors[2]=new TextCellEditor(table);
		cellEditors[3]=new TextCellEditor(table);
		return cellEditors;
	}


	/*
	 * Table mouse click event.
	 * Add new column in schema grid with default values.
	 * 
	 */
	@Override
	public void createDefaultSchema(List grids,
			TableViewer tableViewer, Label errorLabel,Widget... widgets) {
		SchemaGrid schemaGrid = new SchemaGrid();
		schemaGrid.setFieldName("");
		schemaGrid.setDateFormat("");
		schemaGrid.setScale("");
		schemaGrid.setDataType(Integer.valueOf("0"));
		
		if(!grids.contains(schemaGrid))
		{
			grids.add(schemaGrid);  
		tableViewer.refresh();
		}
		}


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
		if(dataTypeValue!=null)
		return dataTypeValue;
		else{
			setDataTypeKeyValue();
			return dataTypeValue;
		}
	}
	
	public static String[] getDataTypeKey() {
		if(dataTypeKey!=null)
			return dataTypeKey;
			else{
				setDataTypeKeyValue();
				return dataTypeKey;
			}
	}




	
	


}
