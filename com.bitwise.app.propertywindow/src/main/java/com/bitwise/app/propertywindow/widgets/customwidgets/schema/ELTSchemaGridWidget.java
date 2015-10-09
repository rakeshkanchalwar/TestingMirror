package com.bitwise.app.propertywindow.widgets.customwidgets.schema;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.xml.validation.Schema;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
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
import com.bitwise.app.propertywindow.widgets.listeners.grid.ELTCellEditorIsNumericValidator;
import com.bitwise.app.propertywindow.widgets.listeners.grid.ELTGridDetails;
import com.bitwise.app.propertywindow.widgets.listeners.grid.schema.ELTCellEditorFieldValidator;
import com.bitwise.app.propertywindow.widgets.utility.WidgetUtility;

public class ELTSchemaGridWidget extends AbstractWidget {

	public ELTSchemaGridWidget(
			ComponentConfigrationProperty componentConfigrationProperty,
			ComponentMiscellaneousProperties componentMiscellaneousProperties,
			PropertyDialogButtonBar propertyDialogButtonBar) {
		super(componentConfigrationProperty, componentMiscellaneousProperties,
				propertyDialogButtonBar);

		this.propertyName = componentConfigrationProperty.getPropertyName();
		this.properties =  componentConfigrationProperty.getPropertyValue(); 
		
	}

	private Table table;
	private List schemaGrids = new ArrayList();
	public ControlDecoration fieldNameDecorator;
	public ControlDecoration scaleDecorator;
	private Object properties;
	private String propertyName;
	public TableViewer tableViewer;
	private ListenerHelper helper; 
	private ELTTable eltTable;
	private AbstractELTWidget addButton;
	private AbstractELTWidget deleteButton;
	private AbstractELTWidget deleteAllButton;
	private ListenerFactory listenerFactory;
	private CellEditor[] editors;
	private LinkedHashMap<String, Object> property=new LinkedHashMap<>();

	// Table column names/properties
	public static final String FIELDNAME = Messages.FIELDNAME;
	public static final String DATEFORMAT = Messages.DATEFORMAT;
	public static final String DATATYPE = Messages.DATATYPE;
	public static final String SCALE = Messages.SCALE;
	public static final String[] PROPS = { FIELDNAME, DATATYPE, DATEFORMAT,
			SCALE };
	// Operational class label.
	AbstractELTWidget fieldError = new ELTDefaultLable(Messages.FIELDNAMEERROR).lableWidth(250);

	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {

		listenerFactory = new ListenerFactory();
	
		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite = new ELTDefaultSubgroupComposite(container.getContainerControl());
		eltSuDefaultSubgroupComposite.createContainerWidget();
		
		AbstractELTWidget eltTableViewer = new ELTTableViewer(new SchemaGridContentProvider(), new SchemaGridLabelProvider());
		eltSuDefaultSubgroupComposite.attachWidget(eltTableViewer);
		
		tableViewer = (TableViewer) eltTableViewer.getJfaceWidgetControl();
		tableViewer.setInput(schemaGrids);
		// Set up the table
		eltTable = new ELTTable(tableViewer);
		eltSuDefaultSubgroupComposite.attachWidget(eltTable); 
		table = (Table)eltTable.getSWTWidgetControl();
		//Create Table column 
		WidgetUtility.createTableColumns(table, PROPS);
		for (int i = 0, n = table.getColumnCount(); i < n; i++) {
			table.getColumn(i).pack(); 
			table.getColumn(i).setWidth(80);
			
		}
		
		table.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println(schemaGrids.toString());
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		editors = SchemaUtility.createCellEditorList(table,4);

		// Set the editors, cell modifier, and column properties
		tableViewer.setColumnProperties(PROPS);
		tableViewer.setCellModifier(new SchemaGridCellModifier(tableViewer));
		tableViewer.setCellEditors(editors); 
		//Adding the decorator to show error message when field name same.
		fieldNameDecorator =	WidgetUtility.addDecorator(editors[0].getControl(),Messages.FIELDNAMEERROR)	;
		scaleDecorator =	WidgetUtility.addDecorator(editors[3].getControl(),Messages.SCALEERROR)	;
		
		editors[0].setValidator(new ELTCellEditorFieldValidator(table, schemaGrids, fieldNameDecorator,propertyDialogButtonBar));
		editors[3].setValidator(new ELTCellEditorIsNumericValidator(scaleDecorator,propertyDialogButtonBar)); 

		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite2 = new ELTDefaultSubgroupComposite(container.getContainerControl());
		eltSuDefaultSubgroupComposite2.createContainerWidget();
		// Create browse button.
		addButton = new ELTDefaultButton("Add").buttonWidth(60);
		eltSuDefaultSubgroupComposite2.attachWidget(addButton);

		deleteButton= new ELTDefaultButton("Delete").buttonWidth(60);
		eltSuDefaultSubgroupComposite2.attachWidget(deleteButton); 

		deleteAllButton= new ELTDefaultButton("Delete All").buttonWidth(60);
		eltSuDefaultSubgroupComposite2.attachWidget(deleteAllButton); 

		helper= new ListenerHelper("schemaGrid", new ELTGridDetails(schemaGrids,tableViewer,(Label)fieldError.getSWTWidgetControl(),new SchemaUtility()));
		try {
			eltTable.attachListener(listenerFactory.getListener("ELTGridMouseDoubleClickListener"),propertyDialogButtonBar, helper,table);
			eltTable.attachListener(listenerFactory.getListener("ELTGridMouseDownListener"),propertyDialogButtonBar, helper,editors[0].getControl());
			addButton.attachListener(listenerFactory.getListener("ELTGridAddSelectionListener"),propertyDialogButtonBar, helper,table);
			deleteButton.attachListener(listenerFactory.getListener("ELTGridDeleteSelectionListener"),propertyDialogButtonBar, helper,table);
			deleteAllButton.attachListener(listenerFactory.getListener("ELTGridDeleteAllSelectionListener"),propertyDialogButtonBar, helper,table); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
 
	@Override
	public LinkedHashMap<String,Object> getProperties() {
		List<SchemaGrid> tempGrid = new ArrayList<>();
		
		for(SchemaGrid schemaGrid : (List<SchemaGrid>)schemaGrids){
			tempGrid.add(schemaGrid.copy());
		}
		
		if(!schemaGrids.equals(this.properties)){
			propertyDialogButtonBar.enableApplyButton(true);
		}
		
		property.put(propertyName, tempGrid); 
		return property; 
	}  
 
	
	public void populateWidget() {
		
		if(this.properties!=null)   
		{
			List<SchemaGrid> tempGrid = new ArrayList<>();
			tempGrid =(List<SchemaGrid>) this.properties;
		
			for(SchemaGrid schemaGrid : tempGrid){
				schemaGrids.add(schemaGrid.copy());
			}
			
			property.put(propertyName, schemaGrids); 
 
		tableViewer.setInput(schemaGrids);
		helper=new ListenerHelper("schemaGrid", new ELTGridDetails(schemaGrids,tableViewer,(Label)fieldError.getSWTWidgetControl(),new SchemaUtility()));
		try {
		eltTable.attachListener(listenerFactory.getListener("ELTGridMouseDoubleClickListener"),propertyDialogButtonBar, helper,table);
		eltTable.attachListener(listenerFactory.getListener("ELTGridMouseDownListener"),propertyDialogButtonBar, helper,editors[0].getControl());
		addButton.attachListener(listenerFactory.getListener("ELTGridAddSelectionListener"),propertyDialogButtonBar, helper,table);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tableViewer.refresh();
		}  
	}
}
