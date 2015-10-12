package com.bitwise.app.propertywindow.fixedwidthschema;

import com.bitwise.app.propertywindow.property.ComponentConfigrationProperty;
import com.bitwise.app.propertywindow.property.ComponentMiscellaneousProperties;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.customwidgets.schema.ELTSchemaGridWidget;
import com.bitwise.app.propertywindow.widgets.listeners.grid.ELTCellEditorIsNumericValidator;
import com.bitwise.app.propertywindow.widgets.listeners.grid.schema.ELTCellEditorFieldValidator;

public class ELTFixedWidget extends ELTSchemaGridWidget{

	public ELTFixedWidget(ComponentConfigrationProperty componentConfigrationProperty,
			ComponentMiscellaneousProperties componentMiscellaneousProperties, PropertyDialogButtonBar propertyDialogButtonBar) {
		super(componentConfigrationProperty, componentMiscellaneousProperties, propertyDialogButtonBar);
	}

	@Override
	protected String[] getPropertiesToShow() {
		return new String[]{ FIELDNAME, DATATYPE, DATEFORMAT, SCALE, LENGTH };
	}
	
	@Override
	protected FixedWidthGridWidgetBuilder getGridWidgetBuilder() {
		return FixedWidthGridWidgetBuilder.INSTANCE;
	}
	
	protected FixedWidthGridContentProvider getContentProvider() {
		return new FixedWidthGridContentProvider();
	}
	
	protected FixedWidthGridLabelProvider getLableProvider() {
		return new FixedWidthGridLabelProvider();
	}
	
	protected FixedWidthGridCellModifier getCellModifier() {
		return new FixedWidthGridCellModifier(tableViewer);
	}

	@Override
	protected void addValidators() {
		editors[0].setValidator(new ELTCellEditorFieldValidator(table, schemaGridRowList, fieldNameDecorator,propertyDialogButtonBar));
		editors[3].setValidator(new ELTCellEditorIsNumericValidator(scaleDecorator,propertyDialogButtonBar)); 
		editors[4].setValidator(new ELTCellEditorIsNumericValidator(scaleDecorator,propertyDialogButtonBar)); 
	}
	
}