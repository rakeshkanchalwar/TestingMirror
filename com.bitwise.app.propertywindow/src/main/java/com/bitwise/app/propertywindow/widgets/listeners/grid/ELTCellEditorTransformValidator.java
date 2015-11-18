package com.bitwise.app.propertywindow.widgets.listeners.grid;

import java.util.List;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.widgets.Table;

import com.bitwise.app.common.datastructure.property.OperationField;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;

// TODO: Auto-generated Javadoc
/**
 * The Class ELTCellEditorFieldValidator.
 * 
 * @author Bitwise
 */
public class ELTCellEditorTransformValidator implements ICellEditorValidator {

	private Table table;
	private List grids;
	private ControlDecoration fieldNameDecorator;
	private PropertyDialogButtonBar propertyDialogButtonBar;
	
	/**
	 * Instantiates a new ELT cell editor field validator.
	 * 
	 * @param table
	 *            the table
	 * @param schemaGrids
	 *            the schema grids
	 * @param fieldNameDecorator
	 *            the field name decorator
	 * @param propertyDialogButtonBar
	 *            the property dialog button bar
	 */
	public ELTCellEditorTransformValidator(Table table, List grids,
			ControlDecoration fieldNameDecorator,PropertyDialogButtonBar propertyDialogButtonBar) {
		super();
		this.table = table;
		this.grids = grids;
		this.fieldNameDecorator = fieldNameDecorator;
		this.propertyDialogButtonBar=propertyDialogButtonBar;
	}

	@Override
	public String isValid(Object value) {
		String selectedGrid = table.getItem(table.getSelectionIndex()).getText();
		for (int i = 0; i < grids.size(); i++) {
			OperationField operationField = (OperationField)grids.get(i);
				String stringValue = (String) value;
				if ((operationField.getName().equalsIgnoreCase(stringValue) &&
						!selectedGrid.equalsIgnoreCase(stringValue))) {
				fieldNameDecorator.show();
				/*propertyDialogButtonBar.enableOKButton(false);
				propertyDialogButtonBar.enableApplyButton(false);*/
				return "Error";
			} else{ 
				fieldNameDecorator.hide();
				/*propertyDialogButtonBar.enableOKButton(true);
				propertyDialogButtonBar.enableApplyButton(true);*/
			}
		}
		return null;
	}

}
