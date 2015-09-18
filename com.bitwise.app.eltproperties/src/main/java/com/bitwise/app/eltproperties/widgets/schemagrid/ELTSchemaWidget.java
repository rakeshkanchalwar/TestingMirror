package com.bitwise.app.eltproperties.widgets.schemagrid;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.bitwise.app.eltproperties.Messages;
import com.bitwise.app.eltproperties.widgets.AbstractELTWidget;

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
	public ControlDecoration txtDecorator;
	private Label errorLable;
	int counter=0;
	public ELTSchemaWidget() { 
	}
 
	// Table column names/properties
	public static final String FIELDNAME = Messages.FIELDNAME;
	public static final String LIMIT = Messages.LIMIT;
	public static final String DATATYPE = Messages.DATATYPE;
	public static final String[] PROPS = { FIELDNAME, DATATYPE, LIMIT };
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
		
		errorLable = new Label(composite, SWT.NONE);
		errorLable.setForeground(new Color(Display.getDefault(), 255, 0,
				0));
		FormData fd_errorLable = new FormData();
		fd_errorLable.right = new FormAttachment(100, -36);
		errorLable.setLayoutData(fd_errorLable);
		errorLable.setText(Messages.FIELDNAMEERROR);
		errorLable.setVisible(false);
  
		// Add the TableViewer
		tableViewer = new TableViewer(composite, SWT.FULL_SELECTION);
		tableViewer.setContentProvider(new SchemaGridContentProvider());
		tableViewer.setLabelProvider(new SchemaGridLabelProvider());
		tableViewer.setInput(schemaGrids);

		// Set up the table
		table = tableViewer.getTable();
		FormData fd_table = new FormData();
		fd_table.top = new FormAttachment(errorLable, 6);
		fd_table.bottom = new FormAttachment(0, 290);
		fd_table.right = new FormAttachment(0, 429);
		fd_table.left = new FormAttachment(0, 5);
		table.setLayoutData(fd_table);  

		new TableColumn(table, SWT.CENTER).setText(FIELDNAME);
		new TableColumn(table, SWT.CENTER).setText(DATATYPE);
		new TableColumn(table, SWT.CENTER).setText(LIMIT);

		for (int i = 0, n = table.getColumnCount(); i < n; i++) {
			table.getColumn(i).pack();
		}
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		CellEditor[] editors = new CellEditor[3];
		TextCellEditor fieldNametext = new TextCellEditor(table);
		editors[0] = fieldNametext;
		editors[1] = new ComboBoxCellEditor(table, ELTSchemaWidget.getDataType(),
				SWT.READ_ONLY);
		editors[2] = new TextCellEditor(table);
		
		// Set the editors, cell modifier, and column properties
		tableViewer.setColumnProperties(PROPS);
		tableViewer.setCellModifier(new SchemaGridCellModifier(tableViewer));
		tableViewer.setCellEditors(editors);

		//Adding the decorator to show error message when field name same.
		txtDecorator =	addDecorator(fieldNametext)	;
		
		
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

				txtDecorator.hide();
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
					            txtDecorator.show();
								return "Error";
							} else{
								errorLable.setVisible(false); 
								txtDecorator.hide();
							}
						}
						return null;
					} 
				};
		// Apply validator to text field.
		fieldNametext.setValidator(iCellEditorValidator);
		Composite c1=new Composite(composite, SWT.NONE);
		FormData fd_c1 = new FormData();
		fd_c1.right = new FormAttachment(0, 428);
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

	
	public ControlDecoration addDecorator(TextCellEditor fieldNametext){
		txtDecorator = new ControlDecoration(fieldNametext.getControl(), SWT.TOP|SWT.LEFT);
		FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry .DEC_ERROR);
		Image img = fieldDecoration.getImage();
		txtDecorator.setImage(img);
		txtDecorator.setDescriptionText(Messages.FIELDNAMEERROR);
		// hiding it initially
		txtDecorator.hide();
		return txtDecorator; 
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
		schemaGrid.setLimit(""); 
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
