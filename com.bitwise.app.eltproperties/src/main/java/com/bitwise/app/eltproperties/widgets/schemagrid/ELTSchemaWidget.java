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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.bitwise.app.eltproperties.widgets.IELTWidget;

/**
 * @author rahulma
 * This class demonstrates CellEditors. It allows you to create and edit
 * SchemaGrid objects
 */
public class ELTSchemaWidget implements IELTWidget {

	private List<SchemaGrid> schemaGrids;
	private Table table;
	private Shell shell;
	private Object properties;
	private String propertyName;

	public ELTSchemaWidget() {
		schemaGrids = new ArrayList<SchemaGrid>();
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
	
	@Override
	public void attachToPropertySubGroup(Group subGroup) {

		shell = subGroup.getShell();
		Composite composite = new Composite(subGroup, SWT.NONE);
		composite.setLayout(new FormLayout());

		// Add the TableViewer
		final TableViewer tableViewer = new TableViewer(composite, SWT.FULL_SELECTION);
		tableViewer.setContentProvider(new SchemaGridContentProvider());
		tableViewer.setLabelProvider(new SchemaGridLabelProvider());
		tableViewer.setInput(schemaGrids);

		// Set up the table
		table = tableViewer.getTable();
		FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(0, 290);
		fd_table.right = new FormAttachment(0, 429);
		fd_table.top = new FormAttachment(0, 10);
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

		/*
		 * Table mouse click event.
		 * Add new column in schema grid with default values.
		 * 
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {

				SchemaGrid schemaGrid = new SchemaGrid();
				schemaGrid.setFieldName("Id" + schemaGrids.size());
				schemaGrid.setLimit("\"|\"");
				schemaGrid.setDataType(Integer.valueOf("0"));
				schemaGrids.add(schemaGrid);
				tableViewer.refresh(); 
			}
		});
		
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
		final ControlDecoration txtDecorator=	addDecorator(fieldNametext)	;
				/*
				 * Field name validation, It should not get repeated.  
				 */
				ICellEditorValidator iCellEditorValidator = new ICellEditorValidator() {

					@Override
					public String isValid(Object value) {
						String selectedGrid = table.getItem(table.getSelectionIndex()).getText();
						for (SchemaGrid schemaGrid : schemaGrids) {
							if (schemaGrid.getFieldName().equalsIgnoreCase(
									(String) value) && !selectedGrid.equalsIgnoreCase((String) value)) {
					            txtDecorator.show();
								return "Error";
							} else{
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
		
		
		
		Button btnRemove = new Button(c1, SWT.CENTER);
		btnRemove.setBounds(0, 0, 88, 25);
		shell = composite.getShell();
		
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
		btnRemove.setText("Remove");
		
		Button btnRemoveall = new Button(c1, SWT.NONE);
		btnRemoveall.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				schemaGrids.removeAll(schemaGrids);
				tableViewer.refresh();
			}
		}); 
		btnRemoveall.setBounds(104, 0, 75, 25);
		btnRemoveall.setText("RemoveAll");	
		
	}

	
	public ControlDecoration addDecorator(TextCellEditor fieldNametext){
		final ControlDecoration txtDecorator = new ControlDecoration(fieldNametext.getControl(), SWT.TOP|SWT.LEFT);
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
		LinkedHashMap<String, Object> property=new LinkedHashMap<>();
		property.put(propertyName, schemaGrids);
		return property;
	}  

	@Override
	public void setProperties(String propertyName, Object properties) {
		this.properties =  properties;
		this.propertyName = propertyName;
		if(properties != null)
			schemaGrids.addAll((List<SchemaGrid>)properties);
		
	}
	
}
