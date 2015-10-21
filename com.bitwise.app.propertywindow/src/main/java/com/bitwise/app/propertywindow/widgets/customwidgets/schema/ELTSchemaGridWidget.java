package com.bitwise.app.propertywindow.widgets.customwidgets.schema;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.property.ComponentConfigrationProperty;
import com.bitwise.app.propertywindow.property.ComponentMiscellaneousProperties;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.customwidgets.AbstractWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultButton;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTTable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTTableViewer;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper.HelperType;
import com.bitwise.app.propertywindow.widgets.listeners.grid.ELTGridDetails;
import com.bitwise.app.propertywindow.widgets.listeners.grid.GridChangeListener;
import com.bitwise.app.propertywindow.widgets.utility.GridWidgetCommonBuilder;
import com.bitwise.app.propertywindow.widgets.utility.WidgetUtility;

public abstract class ELTSchemaGridWidget extends AbstractWidget {
	// Table column names/properties
	public static final String FIELDNAME = Messages.FIELDNAME;
	public static final String DATEFORMAT = Messages.DATEFORMAT;
	public static final String DATATYPE = Messages.DATATYPE;
	public static final String SCALE = Messages.SCALE;
	public static final String LENGTH = Messages.LENGTH;

	protected ControlDecoration fieldNameDecorator;
	protected ControlDecoration scaleDecorator;
	protected ControlDecoration lengthDecorator;
	protected TableViewer tableViewer;
	protected List schemaGridRowList = new ArrayList();
	protected CellEditor[] editors;
	protected Table table;

	protected GridWidgetCommonBuilder gridWidgetBuilder = getGridWidgetBuilder();
	protected final String[] PROPS = getPropertiesToShow();

	private Object properties;
	private String propertyName;
	private ListenerHelper helper;
	private LinkedHashMap<String, Object> property = new LinkedHashMap<>();

	public ELTSchemaGridWidget(
			ComponentConfigrationProperty componentConfigrationProperty,
			ComponentMiscellaneousProperties componentMiscellaneousProperties,
			PropertyDialogButtonBar propertyDialogButtonBar) {
		super(componentConfigrationProperty, componentMiscellaneousProperties,
				propertyDialogButtonBar);

		this.propertyName = componentConfigrationProperty.getPropertyName();
		this.properties = componentConfigrationProperty.getPropertyValue();
	}

	protected abstract String[] getPropertiesToShow();

	protected abstract GridWidgetCommonBuilder getGridWidgetBuilder();

	protected abstract IStructuredContentProvider getContentProvider();

	protected abstract ITableLabelProvider getLableProvider();

	protected abstract ICellModifier getCellModifier();

	protected abstract void addValidators();

	protected abstract void setDecorator();

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		List<GridRow> tempGrid = new ArrayList<>();

		for (GridRow gridRow : (List<GridRow>) schemaGridRowList) {
			tempGrid.add(gridRow.copy());
		}

		if (!schemaGridRowList.equals(this.properties)) {
			propertyDialogButtonBar.enableApplyButton(true);
		}

		property.put(propertyName, tempGrid);
		return property;
	}

	// Operational class label.
	AbstractELTWidget fieldError = new ELTDefaultLable(Messages.FIELDNAMEERROR)
			.lableWidth(250);

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {
		ListenerFactory listenerFactory = new ListenerFactory();

		ELTDefaultSubgroupComposite gridSubGroup = new ELTDefaultSubgroupComposite(
				container.getContainerControl());
		gridSubGroup.createContainerWidget();

		AbstractELTWidget eltTableViewer = new ELTTableViewer(
				getContentProvider(), getLableProvider());
		gridSubGroup.attachWidget(eltTableViewer);

		// eltTableViewer.getSWTWidgetControl().
		tableViewer = (TableViewer) eltTableViewer.getJfaceWidgetControl();
		tableViewer.setInput(schemaGridRowList);
		// Set the editors, cell modifier, and column properties
		tableViewer.setColumnProperties(PROPS);
		tableViewer.setCellModifier(getCellModifier());
		ELTTable eltTable = new ELTTable(tableViewer);
		gridSubGroup.attachWidget(eltTable);
		table = (Table) eltTable.getSWTWidgetControl();
		// Create Table column
		WidgetUtility.createTableColumns(table, PROPS);
		// Set up the table
		for (int columnIndex = 0, n = table.getColumnCount(); columnIndex < n; columnIndex++) {
			table.getColumn(columnIndex).pack();
			table.getColumn(columnIndex).setWidth(80);
		}
		editors = gridWidgetBuilder.createCellEditorList(table, PROPS.length);
		tableViewer.setCellEditors(editors);

		// Adding the decorator to show error message when field name same.
		setDecorator();

		addValidators();

		helper = getListenerHelper();
		try {
			eltTable.attachListener(ListenerFactory.Listners.GRID_MOUSE_DOUBLE_CLICK.getListener(),
					propertyDialogButtonBar, helper, table);
			eltTable.attachListener(ListenerFactory.Listners.GRID_MOUSE_DOWN.getListener(),
					propertyDialogButtonBar, helper, editors[0].getControl());
		} catch (Exception e) {
			// TODO add logger
			throw new RuntimeException("Failed to attach listeners to table");
		}

		addButtonsAndRegisterListners(container, listenerFactory, editors);

		populateWidget();
	}

	private void addButtonsAndRegisterListners(
			AbstractELTContainerWidget container,
			ListenerFactory listenerFactory, CellEditor[] cellEditors) {

		ELTDefaultSubgroupComposite buttonSubGroup = new ELTDefaultSubgroupComposite(
				container.getContainerControl());
		buttonSubGroup.createContainerWidget();

		try {
			AbstractELTWidget addButton = getButton("Add");
			AbstractELTWidget deleteButton = getButton("Delete");
			AbstractELTWidget deleteAllButton = getButton("Delete All");
			buttonSubGroup.attachWidget(addButton);
			buttonSubGroup.attachWidget(deleteButton);
			buttonSubGroup.attachWidget(deleteAllButton);

			addButton.attachListener(ListenerFactory.Listners.GRID_ADD_SELECTION.getListener(),
					propertyDialogButtonBar, helper, table);
			deleteButton.attachListener(ListenerFactory.Listners.GRID_DELETE_SELECTION.getListener(),
					propertyDialogButtonBar, helper, table);
			deleteAllButton.attachListener(ListenerFactory.Listners.GRID_DELETE_ALL.getListener(),
					propertyDialogButtonBar, helper, table);
		} catch (Exception e) {
			// TODO add logger
			throw new RuntimeException("Failed to attach listener to buttons",
					e);
		}

		GridChangeListener gridChangeListener = new GridChangeListener(
				cellEditors, propertyDialogButtonBar);
		gridChangeListener.attachCellChangeListener();

	}

	private void populateWidget() {
		if (this.properties != null) {
			List<GridRow> tempGrid = new ArrayList<>();
			tempGrid = (List<GridRow>) this.properties;

			for (GridRow gridRow : tempGrid) {
				schemaGridRowList.add(gridRow.copy());
			}

			property.put(propertyName, schemaGridRowList);
			tableViewer.setInput(schemaGridRowList);
			tableViewer.refresh();
		}
	}

	private AbstractELTWidget getButton(String displayName) {
		// Create browse button.
		AbstractELTWidget button = new ELTDefaultButton(displayName)
				.buttonWidth(60);
		return button;
	}

	private ListenerHelper getListenerHelper() {
		if (helper == null) {
			helper = new ListenerHelper();
			ELTGridDetails value = new ELTGridDetails(schemaGridRowList, tableViewer, 
					(Label) fieldError.getSWTWidgetControl(), gridWidgetBuilder);
			helper.put(HelperType.SCHEMA_GRID, value);
			//TODO : temporary change it
			validationStatus.setIsValid(true);
			helper.put(HelperType.VALIDATION_STATUS, validationStatus);
		}
		return helper;
	}
}
