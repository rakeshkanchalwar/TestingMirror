package com.bitwise.app.eltproperties.widgets.schemagrid;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import com.bitwise.app.eltproperties.Messages;
import com.bitwise.app.eltproperties.widgets.AbstractELTWidget;
import com.bitwise.app.eltproperties.widgets.utility.WidgetUtility;

/**
 * @author rahulma
 * This class demonstrates CellEditors. It allows you to create and edit
 * SchemaGrid objects
 */ 
public class ELTSchemaWidget extends AbstractELTWidget {

	private List<SchemaGrid> schemaGrids = new ArrayList<SchemaGrid>();
	private LinkedHashMap<String, Object> property=new LinkedHashMap<>();
	private Table table;
	private Shell shell;
	private Object properties;
	private String propertyName;
	public TableViewer tableViewer;
	public ControlDecoration fieldNameDecorator;
	public ControlDecoration scaleDecorator;
	private Label errorLable;
	int counter=0;
	public ELTSchemaWidget() { 
	}
 
	// Table column names/properties
	public static final String FIELDNAME = Messages.FIELDNAME;
	public static final String DATEFORMAT = Messages.DATEFORMAT;
	public static final String DATATYPE = Messages.DATATYPE;
	public static final String SCALE = Messages.SCALE;
	public static final String[] PROPS = { FIELDNAME, DATATYPE, DATEFORMAT,SCALE };
	public static String[] dataTypeList;

	//get the datatype list from property file.
	public static String[] getDataType() {
		if (dataTypeList != null)
			return dataTypeList;
		else {
			String schemaList = Messages.DATATYPELIST;
			dataTypeList = schemaList.split(",");
			return dataTypeList;
		}
	}
	
	/**
	 * This method has main logic for schema grid.
	 * @param Group 
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void attachToPropertySubGroup(Group subGroup) {
	
		shell = subGroup.getShell(); 
		Composite composite = new Composite(subGroup, SWT.NONE);
		composite.setLayout(new FormLayout());
		
		// Create Error Lable
		errorLable = WidgetUtility.createLable(new Label(composite, SWT.NONE));
		// Add the TableViewer
		tableViewer = WidgetUtility.createTableViewer(new TableViewer(composite, SWT.FULL_SELECTION), new SchemaGridContentProvider(), new SchemaGridLabelProvider());
		tableViewer.setInput(schemaGrids);

		// Set up the table
		table = tableViewer.getTable();
		
		FormData fd_table = WidgetUtility.createFormData();
		fd_table.top=new FormAttachment(errorLable, 6);
		table.setLayoutData(fd_table); 
		
		//Create Table column 
		WidgetUtility.createTableColumns(table, PROPS);


		for (int i = 0, n = table.getColumnCount(); i < n; i++) {
			table.getColumn(i).pack();
		}
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		CellEditor[] editors = SchemaUtility.createCellEditorList(table,4);

		
		// Set the editors, cell modifier, and column properties
		tableViewer.setColumnProperties(PROPS);
		tableViewer.setCellModifier(new SchemaGridCellModifier(tableViewer));
		tableViewer.setCellEditors(editors);

		//Adding the decorator to show error message when field name same.
		fieldNameDecorator =	WidgetUtility.addDecorator(editors[0].getControl(),Messages.FIELDNAMEERROR)	;
		scaleDecorator =	WidgetUtility.addDecorator(editors[3].getControl(),Messages.SCALEERROR)	;
	  
		 
		/*
		 * Table mouse click event.
		 * Add new column in schema grid with default values.
		 * 
		 */
		table.addMouseListener(new MouseAdapter() { 
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				createDefaultSchema(); 
			}
			@Override
			public void mouseDown(MouseEvent e) {

				fieldNameDecorator.hide();
			}
		});
				/*
				 * Field name validation, It should not get repeated.  
				 */
				ICellEditorValidator iCellEditorValidator = new ICellEditorValidator() {

					@Override
					public String isValid(Object value) {
						String selectedGrid = table.getItem(table.getSelectionIndex()).getText();
						for (SchemaGrid schemaGrid : schemaGrids) {
							if ((schemaGrid.getFieldName().equalsIgnoreCase(
									(String) value) && !selectedGrid.equalsIgnoreCase((String) value) )|| ((String) value).isEmpty() ) {
								fieldNameDecorator.show();
								return "Error";
							} else{
								errorLable.setVisible(false); 
								fieldNameDecorator.hide();
							}
						}
						return null;
					} 
				};
				
				/*
				 * Field name validation, To check numeric data.
				 */
				ICellEditorValidator scaleFieldValidator = new ICellEditorValidator() {
   
					@Override 
					public String isValid(Object value) {    
						String selectedGrid=(String) value;
							if(!selectedGrid.matches("\\d+")){     
								scaleDecorator.show();   
							return "Error";   
						}else{       
							scaleDecorator.hide(); 
							    
						}
						return null; 
					} 
				};
				
		// Apply validator to text field.
		editors[0].setValidator(iCellEditorValidator);
		editors[3].setValidator(scaleFieldValidator);
		Composite c1=new Composite(composite, SWT.NONE);
		FormData fd_c1 = new FormData();
		fd_c1.right = new FormAttachment(0, 290); 
		fd_c1.top = new FormAttachment(0, 295);
		fd_c1.left = new FormAttachment(0, 5);
		c1.setLayoutData(fd_c1);
		c1.setLayout(null);
		
		
		Button addButton = new Button(c1, SWT.CENTER);
		addButton.setBounds(0, 0, 88, 25);
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				createDefaultSchema(); 		
				} 
		});
		addButton.setText("Add");
		Button btnRemove = new Button(c1, SWT.CENTER);
		btnRemove.setBounds(90, 0, 88, 25);
		btnRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int temp = table.getSelectionIndex();
				if (temp == -1) {
					MessageDialog.openError(shell, "Error",
							"Please Select row to delete");
				} else {
					table.remove(temp);
					schemaGrids.remove(temp);
				}
			} 
		}); 
		btnRemove.setText("Delete");
		
		Button btnRemoveall = new Button(c1, SWT.NONE);
		btnRemoveall.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				schemaGrids.removeAll(schemaGrids);
				tableViewer.refresh();
			}
		}); 
		btnRemoveall.setBounds(180, 0, 88, 25);
		btnRemoveall.setText("Delete All");	
			
	}
 
  
	@Override
	public LinkedHashMap<String,Object> getProperties() {
		property.put(propertyName, schemaGrids); 
		return property; 
	}  
 
	@Override
	public void setProperties(String propertyName, Object properties) {
		this.properties =  properties;
		this.propertyName = propertyName;
		if(this.properties!=null)   
		{
		schemaGrids =(List<SchemaGrid>) this.properties;
		tableViewer.setInput(schemaGrids);
		tableViewer.refresh();
		} 
	}

	@Override 
	public void setComponentName(String componentName) {
		// TODO Auto-generated method stub 
		
	} 
	 
	public void createDefaultSchema(){
		SchemaGrid schemaGrid = new SchemaGrid();
		schemaGrid.setFieldName("");
		schemaGrid.setDateFormat("");
		schemaGrid.setScale("");
		schemaGrid.setDataType(Integer.valueOf("0"));
		
		if(!schemaGrids.contains(schemaGrid))
		{
		schemaGrids.add(schemaGrid);  
		tableViewer.refresh();
		}
		else
		{
			errorLable.setVisible(true);
		}
	}
}
