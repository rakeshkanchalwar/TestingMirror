package com.bitwise.app.propertywindow.widgets.filterproperty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.widgets.utility.WidgetUtility;

// TODO: Auto-generated Javadoc
/**
 * The Class ELTFilterPropertyWizard.
 * 
 * @author Bitwise
 */
public class ELTFilterPropertyWizard {

	private static final Logger logger = LogFactory.INSTANCE.getLogger(ELTFilterPropertyWizard.class);
	
	private Table table;

	private Shell shell;
	private final List<ELTFilterProperties> propertyLst;
	public static final String FilterInputFieldName = "Component Name"; //$NON-NLS-1$
	private Set<String> filterSet;
	private String componentName;
	private Label lblHeader;
	private final String PROPERTY_EXISTS_ERROR = Messages.RuntimePropertAlreadyExists;
	public static final String[] PROPS = { FilterInputFieldName };
	private final String PROPERTY_NAME_BLANK_ERROR = Messages.EmptyNameNotification;
	private Label lblPropertyError;
	private boolean isOkPressed;
	private TableViewer tableViewer;
	private ControlDecoration decorator;
	public ControlDecoration scaleDecorator;
	private Button addButton, deleteAll, okButton, deleteButton, cacelButton, button, upButton, downButton;
	private boolean isAnyUpdatePerformed;

	
		


	/**
	 * Instantiates a new ELT filter property wizard.
	 */
	public ELTFilterPropertyWizard() {
		propertyLst = new ArrayList<ELTFilterProperties>();
		filterSet = new HashSet<String>();
	}

	// Add New Property After Validating old properties
	private void addNewProperty(TableViewer tv) {

		isAnyUpdatePerformed = true;
		ELTFilterProperties filter = new ELTFilterProperties();
		if (propertyLst.size() != 0) {
			if (!validate())
				return;
			filter.setPropertyname(""); //$NON-NLS-1$
			propertyLst.add(filter);
			tv.refresh();

		} else {
			filter.setPropertyname("");//$NON-NLS-1$
			propertyLst.add(filter);
			tv.refresh();
		}
	}

	public void setRuntimePropertySet(HashSet<String> runtimePropertySet) {
		this.filterSet = runtimePropertySet;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	// Loads Already Saved Properties..
	private void loadProperties(TableViewer tv) {

		if (filterSet != null && !filterSet.isEmpty()) {
			for (String key : filterSet) {
				ELTFilterProperties filter = new ELTFilterProperties();
				if (validateBeforeLoad(key)) {
					filter.setPropertyname(key);
					propertyLst.add(filter);
				}
			}
			tv.refresh();
		} else{
			
		logger.debug("LodProperties :: Empty Map");
		}
	}

	private boolean validateBeforeLoad(String key) {
		if (key.trim().isEmpty()) {
			return false;
		}
		return true;

	}

	// Method for creating Table
	private void createTable() {
		
		tableViewer = new TableViewer(shell, SWT.BORDER|SWT.MULTI);
		table = tableViewer.getTable();
		table.setBackground(new Color(Display.getCurrent(),204,204,204));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				addNewProperty(tableViewer);
			}

			@Override
			public void mouseDown(MouseEvent e) {
				lblPropertyError.setVisible(false);
			}
		});
		tableViewer.getTable().addTraverseListener(new TraverseListener() {
			
			@Override
			public void keyTraversed(TraverseEvent e) {
				if(e.keyCode==SWT.ARROW_UP){
					e.doit=false;
				}
				
			}
		});
		table.setBounds(10, 70, 465, 400);
		tableViewer.setContentProvider(new ELTFilterContentProvider());
		tableViewer.setLabelProvider(new ELTFilterLabelProvider());
		tableViewer.setInput(propertyLst);

		TableColumn tc1 = new TableColumn(table, SWT.CENTER);
		tc1.setText("Field Name");
		tc1.setWidth(460);
		table.setHeaderVisible(true);
		
		 /*final TableCursor cursor = new TableCursor(table, SWT.NONE);
		 ControlEditor editor = new ControlEditor(cursor);
		 editor.grabHorizontal = true;
		 editor.grabVertical = true;
		 
		 cursor.addSelectionListener(new SelectionAdapter() {
			 @Override
			 public void widgetSelected(SelectionEvent e){
				 table.setSelection(new TableItem[] { cursor.getRow() });
			 }
			 
			 public void widgetDefaultSelected(SelectionEvent event) {
				 	Text text = new Text(table, SWT.None);
				 	text.setFocus();
				 	text.setText(cursor.getRow().getText(cursor.getColumn()));
				 	text.setFocus();
			 }
		});*/
		
		/*cursor.addSelectionListener(new SelectionAdapter() {
			@Override
			 public void widgetSelected(SelectionEvent e) {
			        table.setSelection(new TableItem[] { cursor.getRow() });*/
			
		/*public void widgetDefaultSelected(SelectionEvent e){
			  final Text text = new Text(cursor, SWT.NONE);
		        TableItem row = cursor.getRow();
		        int column = cursor.getColumn();
		        text.setText(row.getText(column));
		        text.addKeyListener(new KeyAdapter() {
		          public void keyPressed(KeyEvent e) {
		            // close the text editor and copy the data over
		            // when the user hits "ENTER"
		            if (e.character == SWT.CR) {
		              TableItem row = cursor.getRow();
		              int column = cursor.getColumn();
		              row.setText(column, text.getText());
		              text.dispose();
		            }
		            // close the text editor when the user hits "ESC"
		            if (e.character == SWT.ESC) {
		              text.dispose();
		            }
		          }
		        });
		        editor.setEditor(text);
		        text.setFocus();
		}   */
		//});
		/*TableItem item=new TableItem(table, SWT.NONE);
		Color black=shell.getDisplay().getSystemColor(S)
			item.setBackground(color);*/
		
		/*TableColumn column = new TableColumn(tableViewer.getTable(), SWT.NONE);
	        column.setText(" ");
	        column.setWidth(20);
	       
	        TableViewerColumn actionsNameCol = new TableViewerColumn(tableViewer, column);
	        actionsNameCol.setLabelProvider(new ColumnLabelProvider(){
	            //make sure you dispose these buttons when viewer input changes
	            Map<Object, Button> buttons = new HashMap<Object, Button>();

	            @Override
	            public void update(ViewerCell cell) {
	                TableItem item = (TableItem) cell.getItem();
	               
	                if(buttons.containsKey(cell.getElement()) && !buttons.get(cell.getElement()).isDisposed() )
	                {
	                    button = buttons.get(cell.getElement());
	                }
	                else
	                {
	                     button = new Button((Composite) cell.getViewerRow(1).getControl(),SWT.NONE);
	                    button.setText("+");
	                    button.pack();
	                   buttons.put(cell.getElement(), button);
	                   
	                   TableEditor editor = new TableEditor(item.getParent());
		                editor.grabHorizontal  = true;
		                editor.grabVertical = true;
		                editor.setEditor(button , item, cell.getColumnIndex());
		                editor.layout();
		              
	                }
	                
	            }
	           });*/
	}

	/**
	 * @return
	 * @wbp.parser.entryPoint
	 */
	public Set<String> launchRuntimeWindow(Shell parentShell) {

		shell = new  Shell(parentShell, SWT.WRAP | SWT.APPLICATION_MODAL);
		isOkPressed = false;
		isAnyUpdatePerformed = false;
		shell.setSize(506, 540);
		shell.setLayout(null);
		shell.setText("Properties");
		lblHeader = new Label(shell, SWT.NONE);
		lblHeader.setBounds(10, 14, 450, 15);
		if (getComponentName() != null){
			lblHeader.setText(getComponentName() + "Properties"); //$NON-NLS-1$
		}/*else
			lblHeader.setText("Filter Operation Field");*/
		//new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL).setBounds(0, 35, 523, 2);
		
		Composite com = new Composite(shell, SWT.NONE);
		com.setBounds(0, 40, 520, 30);
		createIcons(com);
		
		
		// Below Event will be fired when user closes the Runtime window
		shell.addListener(SWT.CLOSE, new Listener() {
			@Override
			public void handleEvent(Event event) {
			
				if (table.getItemCount() != 0 && !isOkPressed && isAnyUpdatePerformed) {
					int style = SWT.APPLICATION_MODAL | SWT.YES | SWT.NO;
					MessageBox messageBox = new MessageBox(shell, style);
					messageBox.setText("Information"); //$NON-NLS-1$
					messageBox.setMessage(Messages.MessageBeforeClosingWindow);
				}
				
			}
		});

		createTable();

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 438, 513, 83);
		//composite.setBounds(0, 30, 250, 30);
		createButtons(composite);

		lblPropertyError = new Label(composite, SWT.NONE);
		lblPropertyError.setForeground(new Color(Display.getDefault(), 255, 0, 0));
		lblPropertyError.setBounds(28, 57, 258, 15);
		lblPropertyError.setVisible(false);

		 CellEditor propertyNameEditor = new TextCellEditor(table);
		

		CellEditor[] editors = new CellEditor[] { propertyNameEditor };
		propertyNameEditor.setValidator(createNameEditorValidator(PROPERTY_NAME_BLANK_ERROR));
		
		
		tableViewer.setColumnProperties(PROPS);
		tableViewer.setCellModifier(new ELTCellModifier(tableViewer));
		tableViewer.setCellEditors(editors);
		

		decorator = WidgetUtility.addDecorator(propertyNameEditor.getControl(), Messages.CHARACTERSET);
		loadProperties(tableViewer);

		Monitor primary = shell.getDisplay().getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();

		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;

		shell.setLocation(x, y);
		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!shell.getDisplay().readAndDispatch())
				shell.getDisplay().sleep();
		}

		return filterSet;
	}
	
	private void createIcons(Composite composite){
		
		new Label(composite, SWT.SEPARATOR|SWT.HORIZONTAL).setBounds(0, 41, 513, 60);
		addButton = new Button(composite, SWT.PUSH);
		//addButton.setText("+");
		String addIconPath = XMLConfigUtil.INSTANCE.CONFIG_FILES_PATH + "/icons/add.png";
		addButton.setImage(new Image(null, addIconPath));
		addButton.setBounds(384, 10, 20, 20);
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addNewProperty(tableViewer);
			}
		});
		
		
		deleteButton = new Button(composite, SWT.PUSH);
		//deleteButton.setText("X");
		String deleteIonPath = XMLConfigUtil.INSTANCE.CONFIG_FILES_PATH + "/icons/delete.png";
		deleteButton.setImage(new Image(null, deleteIonPath));
		deleteButton.setBounds(406, 10, 25, 20);
		deleteButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {				
				/*if (temp == -1){
					MessageDialog.openError(shell, "Error", //$NON-NLS-1$
							Messages.SelectRowToDelete);
						 
				}else {*/
				/*for(int i=0;i<temp.length;i++){
					table.remove(temp[i]);
					propertyLst.remove(temp[i]);}*/
				IStructuredSelection selection = (IStructuredSelection)tableViewer.getSelection();
				for (Iterator<?> iterator = selection.iterator(); iterator.hasNext(); )
				 {
				   Object selectedObject = iterator.next();
				   tableViewer.remove(selectedObject);
				   propertyLst.remove(selectedObject);
				 }
					isAnyUpdatePerformed = true;
					 
				//}
			}
		});
		
		upButton = new Button(composite, SWT.PUSH);
		//upButton.setText("^");
		String upIonPath = XMLConfigUtil.INSTANCE.CONFIG_FILES_PATH + "/icons/up.png";
		upButton.setImage(new Image(null, upIonPath));
		upButton.setBounds(433, 10, 20, 20);
		
		upButton.addSelectionListener(new SelectionAdapter() {
			int index1=0,index2=0;

			List<String> list=new LinkedList<String>();
			@Override
			public void widgetSelected(SelectionEvent e) {
	
				index1=table.getSelectionIndex();
				index2=index1-1;
				String data=tableViewer.getTable().getItem(index1).getText();
				//list.add(index1,data);
				System.out.println(index1);
				if(index2 == -1){
					table.setSelection(index1);
					tableViewer.getTable().getItem(index1).setText(data);
				}else{
				
				String data2=tableViewer.getTable().getItem(index2).getText();
				ELTFilterProperties filter = new ELTFilterProperties();
				filter.setPropertyname(data2);
				propertyLst.set(index1, filter);
				filter.setPropertyname(data);
				propertyLst.set(index2, filter);
			/*	tableViewer.getTable().getItem(index1).setText(data2);
				tableViewer.getTable().getItem(index2).setText(data);*/
				table.setSelection(index1-1);
				//table.redraw();
				}
					/*System.out.println(index1+" "+index2);
				System.out.println("Index::"+index1+"::");*/
				
			}
		});
		
		downButton = new Button(composite, SWT.PUSH);
		//downButton.setText("->");
		String downIonPath = XMLConfigUtil.INSTANCE.CONFIG_FILES_PATH + "/icons/down.png";
		downButton.setImage(new Image(null, downIonPath));
		downButton.setBounds(450, 10, 25, 20);
		downButton.addSelectionListener(new SelectionAdapter() {
			int index1=0,index2=0;
			String temp=null;
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				index1=table.getSelectionIndex();
				index1=table.getSelectionIndex();
				
				index2=index1+1;
				
				String data=tableViewer.getTable().getItem(index1).getText();
				String data2=tableViewer.getTable().getItem(index2).getText();
				
				
				tableViewer.getTable().getItem(index1).setText(data2);
				tableViewer.getTable().getItem(index2).setText(data);
				
				table.setSelection(index1+1);
			}
		});
	}

	// Creates The buttons For the widget
	private void createButtons(Composite composite) {
		new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL).setBounds(0, 41, 513, 2);

		okButton = new Button(composite, SWT.NONE);
		okButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (validate()) {
					filterSet.clear();
					isOkPressed = true;
					for (ELTFilterProperties temp : propertyLst) {
						filterSet.add(temp.getPropertyname());
					}

					shell.close();
				} else{
					return;
					}
			}
		});
		okButton.setBounds(321, 52, 75, 25);
		okButton.setText("OK"); //$NON-NLS-1$
		cacelButton = new Button(composite, SWT.NONE);
		cacelButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				shell.close();
			}
		});
		cacelButton.setBounds(400, 52, 75, 25);

		cacelButton.setText("Cancel"); //$NON-NLS-1$

	}

	/**
	 * Validate.
	 * 
	 * @return true, if successful
	 */
	protected boolean validate() {

		int propertyCounter = 0;

		for (ELTFilterProperties temp : propertyLst) {
			if (!temp.getPropertyname().trim().isEmpty()) {
				//if(temp.getPropertyname().trim().equalsIgnoreCase(anotherString))
			} else {
				table.setSelection(propertyCounter);
				lblPropertyError.setVisible(true);
				lblPropertyError.setText(Messages.EmptyNameNotification);
				//disableButtons();
				return false;
			}
			propertyCounter++;
					
			
		}
		return true;
	}

	// Creates CellNAme Validator for table's cells
	private ICellEditorValidator createNameEditorValidator(final String ErrorMessage) {
		ICellEditorValidator propertyValidator = new ICellEditorValidator() {
			@Override
			public String isValid(Object value) {
				isAnyUpdatePerformed = true;
				String currentSelectedFld = table.getItem(table.getSelectionIndex()).getText();
				String valueToValidate = String.valueOf(value).trim();
				if (valueToValidate.isEmpty()) {
					lblPropertyError.setText(ErrorMessage);
					lblPropertyError.setVisible(true);
					//disableButtons();
					return "ERROR"; //$NON-NLS-1$
				}else if(!valueToValidate.matches("[\\w+]*"))
				{
					decorator.show();
					//disableButtons();
					 return "Invalid";
				} else {
					decorator.hide();
					enableButtons();
				}

				for (ELTFilterProperties temp : propertyLst) {
					if (!currentSelectedFld.equalsIgnoreCase(valueToValidate)
							&& temp.getPropertyname().trim().equalsIgnoreCase(valueToValidate)) {
						lblPropertyError.setText(PROPERTY_EXISTS_ERROR);
						lblPropertyError.setVisible(true);
						//disableButtons();
						return "ERROR"; //$NON-NLS-1$
					} else
						enableButtons();
					lblPropertyError.setVisible(false);
				}

				return null;

			}
		};
		return propertyValidator;
	}

	/**
	 * Disable buttons.
	 */
	void disableButtons() {
		okButton.setEnabled(false);

	}

	/**
	 * Enable buttons.
	 */
	void enableButtons() {
		okButton.setEnabled(true);
		addButton.setEnabled(true);
	}
	
	public static void main(String[] args) {
		ELTFilterPropertyWizard obj = new ELTFilterPropertyWizard();
			obj.launchRuntimeWindow(new Shell());
	}
}
