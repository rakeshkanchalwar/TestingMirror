package com.bitwise.app.propertywindow.fixedwidthschema;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TableViewer;

import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.widgets.customwidgets.schema.ELTSchemaGridWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.schema.SchemaUtility;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.utility.WidgetUtility;

public class ELTFixedWidget extends ELTSchemaGridWidget{
	public static final String Length=Messages.FIELDPHASE;
	public TableViewer tableViewer;
	public static final String[] PROPS = { FIELDNAME, DATATYPE, DATEFORMAT, SCALE, Length };
	
	public ELTFixedWidget(){
		super();
	}

	/*@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container){
		WidgetUtility.createTableColumns(table, PROPS);
		for (int i = 0, n = table.getColumnCount(); i < n; i++) {
			table.getColumn(i).pack(); 
			table.getColumn(i).setWidth(80);
	}
		CellEditor[] editors = SchemaUtility.createCellEditorList(table, 5);
		tableViewer.setColumnProperties(PROPS);
		*/
//}
}