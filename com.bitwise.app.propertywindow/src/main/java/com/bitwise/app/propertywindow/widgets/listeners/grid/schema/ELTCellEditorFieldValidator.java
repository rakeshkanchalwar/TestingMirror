package com.bitwise.app.propertywindow.widgets.listeners.grid.schema;

import java.util.List;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import com.bitwise.app.propertywindow.widgets.customwidgets.schema.SchemaGrid;

public class ELTCellEditorFieldValidator implements ICellEditorValidator {

	private Table table;
	private List schemaGrids;
	private ControlDecoration fieldNameDecorator;
	
	public ELTCellEditorFieldValidator(Table table, List schemaGrids,
			ControlDecoration fieldNameDecorator) {
		super();
		this.table = table;
		this.schemaGrids = schemaGrids;
		this.fieldNameDecorator = fieldNameDecorator;
	}

	@Override
	public String isValid(Object value) {
		String selectedGrid = table.getItem(table.getSelectionIndex()).getText();
		for (int i = 0; i < schemaGrids.size(); i++) {
				if ((((SchemaGrid)schemaGrids.get(i)).getFieldName().equalsIgnoreCase(
					(String) value) && !selectedGrid.equalsIgnoreCase((String) value) )|| ((String) value).isEmpty() ) {
				fieldNameDecorator.show();
				return "Error";
			} else{
				fieldNameDecorator.hide();
			}
		}
		return null;
	}

}
