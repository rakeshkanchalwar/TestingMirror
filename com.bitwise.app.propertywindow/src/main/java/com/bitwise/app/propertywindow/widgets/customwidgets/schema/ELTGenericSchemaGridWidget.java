package com.bitwise.app.propertywindow.widgets.customwidgets.schema;

import com.bitwise.app.propertywindow.property.ComponentConfigrationProperty;
import com.bitwise.app.propertywindow.property.ComponentMiscellaneousProperties;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.listeners.grid.ELTCellEditorIsNumericValidator;
import com.bitwise.app.propertywindow.widgets.listeners.grid.schema.ELTCellEditorFieldValidator;

public class ELTGenericSchemaGridWidget extends ELTSchemaGridWidget {

	public ELTGenericSchemaGridWidget(ComponentConfigrationProperty componentConfigrationProperty,
			ComponentMiscellaneousProperties componentMiscellaneousProperties, PropertyDialogButtonBar propertyDialogButtonBar) {
		super(componentConfigrationProperty, componentMiscellaneousProperties,propertyDialogButtonBar);
	}
	
	@Override
	protected String[] getPropertiesToShow() {
		return new String[]{ FIELDNAME, DATATYPE, DATEFORMAT, SCALE };
	}

	@Override
	protected GeneralGridWidgetBuilder getGridWidgetBuilder() {
		return GeneralGridWidgetBuilder.INSTANCE;
	}
	
	protected SchemaGridContentProvider getContentProvider() {
		return new SchemaGridContentProvider();
	}
	
	protected SchemaGridLabelProvider getLableProvider() {
		return new SchemaGridLabelProvider();
	}
	
	protected SchemaGridCellModifier getCellModifier() {
		return new SchemaGridCellModifier(tableViewer);
	}

	@Override
	protected void addValidators() {
		editors[0].setValidator(new ELTCellEditorFieldValidator(table, schemaGridRowList, fieldNameDecorator,propertyDialogButtonBar));
		editors[3].setValidator(new ELTCellEditorIsNumericValidator(scaleDecorator,propertyDialogButtonBar)); 
	}

}
