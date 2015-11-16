package com.bitwise.app.propertywindow.widgets.customwidgets.schema;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.ImageIcon;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import com.bitwise.app.common.util.XMLConfigUtil;
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
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTSubgroupComposite;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper.HelperType;
import com.bitwise.app.propertywindow.widgets.listeners.grid.ELTGridDetails;
import com.bitwise.app.propertywindow.widgets.listeners.grid.GridChangeListener;
import com.bitwise.app.propertywindow.widgets.utility.GridWidgetCommonBuilder;
import com.bitwise.app.propertywindow.widgets.utility.WidgetUtility;

// TODO: Auto-generated Javadoc
/**
 * The Class ELTSchemaGridWidget.
 * 
 * @author Bitwise
 */
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
	private Button button;
	private LinkedHashMap<String, Object> property = new LinkedHashMap<>();
	private Shell shell;

	/**
	 * Instantiates a new ELT schema grid widget.
	 * 
	 * @param componentConfigrationProperty
	 *            the component configration property
	 * @param componentMiscellaneousProperties
	 *            the component miscellaneous properties
	 * @param propertyDialogButtonBar
	 *            the property dialog button bar
	 */
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

	/**
	 * Adds the validators.
	 */
	protected abstract void addValidators();

	/**
	 * Sets the decorator.
	 */
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
		
		
		

		AbstractELTWidget eltTableViewer = new ELTTableViewer(getContentProvider(), getLableProvider());
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

		ELTSubgroupComposite buttonSubGroup = new ELTSubgroupComposite(
				container.getContainerControl());
		buttonSubGroup.createContainerWidget();

		try {
			AbstractELTWidget addButton = getButton("");
			AbstractELTWidget deleteButton = getButton("");
			AbstractELTWidget upButton = getButton("");
			AbstractELTWidget downButton = getButton("");
			
			buttonSubGroup.attachWidget(addButton);
			buttonSubGroup.attachWidget(deleteButton);
			buttonSubGroup.attachWidget(upButton);
			buttonSubGroup.attachWidget(downButton);
			
			String addIconPath = XMLConfigUtil.INSTANCE.CONFIG_FILES_PATH + "/icons/add.png";
			((Button) addButton.getSWTWidgetControl()).setImage(new Image(null, addIconPath));
			
			String deleteIconPath = XMLConfigUtil.INSTANCE.CONFIG_FILES_PATH + "/icons/delete.png";
			((Button) deleteButton.getSWTWidgetControl()).setImage(new Image(null, deleteIconPath));
			
			String upIconPath = XMLConfigUtil.INSTANCE.CONFIG_FILES_PATH + "/icons/up.png";
			((Button) upButton.getSWTWidgetControl()).setImage(new Image(null, upIconPath));
			((Button) upButton.getSWTWidgetControl()).addSelectionListener(new SelectionAdapter() {
				int index = 0, index2 = 0;
				@Override
				public void widgetSelected(SelectionEvent e) {
					index = table.getSelectionIndex();
					
					if(index > 0){
						index2 = index-1;
						String text = tableViewer.getTable().getItem(index).getText(0);
						String value = tableViewer.getTable().getItem(index2).getText(0);
						SchemaGrid grid=new SchemaGrid();
						grid.setFieldName(text);
									
						SchemaGrid grid1=new SchemaGrid();
						grid1.setFieldName(value);
						int temp1=schemaGridRowList.indexOf(grid);
						int temp2=schemaGridRowList.indexOf(grid1);  
						grid1=(SchemaGrid) schemaGridRowList.get(temp1);
						grid=(SchemaGrid) schemaGridRowList.get(temp2);
						schemaGridRowList.set(index2,grid1);
						schemaGridRowList.set(index, grid);  
						tableViewer.refresh(); 
						table.setSelection(index-1);
					}
					
				}
			});
			
			
			String downIconPath = XMLConfigUtil.INSTANCE.CONFIG_FILES_PATH + "/icons/down.png";
			((Button) downButton.getSWTWidgetControl()).setImage(new Image(null, downIconPath));
			((Button) downButton.getSWTWidgetControl()).addSelectionListener(new SelectionAdapter() {
				int index = 0, index2 = 0;
				@Override
				public void widgetSelected(SelectionEvent e) {
					index = table.getSelectionIndex();
					
					
					if(index < schemaGridRowList.size()-1){
						String text = tableViewer.getTable().getItem(index).getText(0);
						index2 = index+1;
						String value = tableViewer.getTable().getItem(index2).getText(0);
						SchemaGrid grid=new SchemaGrid();
						grid.setFieldName(text);
									
						SchemaGrid grid1=new SchemaGrid();
						grid1.setFieldName(value);
						int temp1=schemaGridRowList.indexOf(grid);
						int temp2=schemaGridRowList.indexOf(grid1);  
						grid1=(SchemaGrid) schemaGridRowList.get(temp1);
						grid=(SchemaGrid) schemaGridRowList.get(temp2);
						schemaGridRowList.set(index2,grid1 );
						schemaGridRowList.set(index, grid);  
						tableViewer.refresh(); 
						table.setSelection(index+1);
					}
				}
			});
			
			addButton.attachListener(ListenerFactory.Listners.GRID_ADD_SELECTION.getListener(),
					propertyDialogButtonBar, helper, table);
			deleteButton.attachListener(ListenerFactory.Listners.GRID_DELETE_SELECTION.getListener(),
					propertyDialogButtonBar, helper, table);
			
		} catch (Exception e) {
			// TODO add logger
			throw new RuntimeException("Failed to attach listener to buttons",
					e);
		}

		GridChangeListener gridChangeListener = new GridChangeListener(cellEditors, propertyDialogButtonBar);
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
				.buttonWidth(20);
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
	
	private void subgroupComposite(Composite com){
		Composite comp = new Composite(com, SWT.NONE);
		com.setBounds(0, 38, 520, 30);
	}
}
