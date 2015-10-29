package com.bitwise.app.propertywindow.widgets.filterproperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
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
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
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
	private Button addButton, deleteAll, applyButton, okButton, deleteButton, cacelButton, button, upButton, downButton;
	private boolean isAnyUpdatePerformed;
	private Image image;
	
		Display disple= Display.getDefault();



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
		
		tableViewer = new TableViewer(shell, SWT.BORDER | SWT.MULTI);
		table = tableViewer.getTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				addNewProperty(tableViewer);
				applyButton.setEnabled(true);
			}

			@Override
			public void mouseDown(MouseEvent e) {
				lblPropertyError.setVisible(false);
			}
		});
		/*table.addListener(SWT.CTRL, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				int temp=tableViewer.getTable().getSelectionIndex();
				if(event.type == SWT.CTRL){
					//event.doit=false;
					tableViewer.getTable().setSelection(temp);
				}
				
			}
		});*/
		tableViewer.getTable().addTraverseListener(new TraverseListener() {
			
			@Override
			public void keyTraversed(TraverseEvent e) {
				if(e.keyCode==SWT.TAB){
					e.doit=true;
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
		//table.setLinesVisible(true);
		
		/*TableItem item=new TableItem(table, SWT.NONE);
		Color black=shell.getDisplay().getSystemColor(S)
			item.setBackground(color);*/
		
		/*TableColumn column = new TableColumn(tableViewer.getTable(), SWT.NONE);
	        column.setText(" ");
	        column.setWidth(20);*/
	       
	       /* TableViewerColumn actionsNameCol = new TableViewerColumn(tableViewer, column);
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
	                     button = new Button((Composite) cell.getViewerRow().getControl(),SWT.NONE);
	                    button.setText("X");
	                    button.pack();
	                   buttons.put(cell.getElement(), button);
	                   
	                   TableEditor editor = new TableEditor(item.getParent());
		                editor.grabHorizontal  = true;
		                editor.grabVertical = true;
		                editor.setEditor(button , item, cell.getColumnIndex());
		                editor.layout();
		                
		               button.addSelectionListener(new SelectionAdapter() {
		            	   public void widgetSelected(SelectionEvent e) { 
		            		   addNewProperty(tableViewer);
		            		   TableItem item = new TableItem(table, SWT.NONE);
		                         item.setText(text.getText());
		            	   }
					}); 
	                   
	                    button.addSelectionListener(new SelectionAdapter() {
		                	@Override
		        			public void widgetSelected(SelectionEvent e) {
		              
		                		 //System.out.println(""+table.getSelectionIndex()+"");
		                		ISelection selection = tableViewer.getSelection(); 
		                		
		                
		                		
		                		int temp = table.getSelectionIndex();
		        				if (temp == -1||selection instanceof IStructuredSelection){
		        				
		        					Iterator iterator = ((IStructuredSelection)selection).iterator(); 
		        					while(iterator.hasNext()) { 
		        					Object obj = iterator.next();
		        					tableViewer.remove(obj);
		        					propertyLst.remove(obj);
		        					button.dispose();
		        					
		        					}
		        					MessageDialog.openError(shell, "Error", //$NON-NLS-1$
		        							Messages.SelectRowToDelete);
		        				}else {
		        					table.remove(temp);
		        					propertyLst.remove(temp);
		        					button.dispose();
		        					isAnyUpdatePerformed = true;
		        				}
		        				
		        			}
						});
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
		new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL).setBounds(0, 35, 523, 2);
		
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

		final CellEditor propertyNameEditor = new TextCellEditor(table);

		CellEditor[] editors = new CellEditor[] { propertyNameEditor };
		propertyNameEditor.setValidator(createNameEditorValidator(PROPERTY_NAME_BLANK_ERROR));

		tableViewer.setColumnProperties(PROPS);
		tableViewer.setCellModifier(new ELTCellModifier(tableViewer));
		tableViewer.setCellEditors(editors);
		//applyButton.setEnabled(false);

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
		addButton.setImage(new Image(disple, "resources/icons/add_arrow.png"));
		addButton.setBounds(384, 10, 20, 20);
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addNewProperty(tableViewer);
			}
		});
		
		deleteButton = new Button(composite, SWT.PUSH|SWT.BORDER);
		//deleteButton.setText("X");
		deleteButton.setImage(new Image(disple, "resources/icons/delete.png"));
		deleteButton.setBounds(406, 10, 25, 20);
		deleteButton.addSelectionListener(new SelectionAdapter() {
			int[] temp;
			@Override
			public void widgetSelected(SelectionEvent e) {

				temp = tableViewer.getTable().getSelectionIndices();
				System.out.println(temp);
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
		upButton.setImage(new Image(disple, "resources/icons/green-up.png"));
		upButton.setBounds(433, 10, 20, 20);
		
		upButton.addSelectionListener(new SelectionAdapter() {
			int temp=0;
			@Override
			public void widgetSelected(SelectionEvent e) {
				temp=table.getSelectionIndex();
				table.setSelection(temp-1);
			}
		});
		
		downButton = new Button(composite, SWT.PUSH|SWT.BORDER);
		//downButton.setText("->");
		downButton.setImage(new Image(disple, "resources/icons/down_arrow.png"));
		downButton.setBounds(450, 10, 25, 20);
		downButton.addSelectionListener(new SelectionAdapter() {
			int temp=0;
			@Override
			public void widgetSelected(SelectionEvent e) {
				temp=table.getSelectionIndex();
				table.setSelection(temp+1);
			}
		});
	}

	// Creates The buttons For the widget
	private void createButtons(Composite composite) {
		new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL).setBounds(0, 41, 513, 2);
		/*addButton = new Button(composite, SWT.NONE);
		addButton.setText("Add"); //$NON-NLS-1$
		addButton.setBounds(10, 10, 75, 25);
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addNewProperty(tableViewer);
				applyButton.setEnabled(true);
			}
		});*/

		/*deleteButton = new Button(composite, SWT.NONE);
		deleteButton.setText("Delete"); //$NON-NLS-1$
		deleteButton.setBounds(91, 10, 75, 25);
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				int temp = table.getSelectionIndex();
				if (temp == -1){
					MessageDialog.openError(shell, "Error", //$NON-NLS-1$
							Messages.SelectRowToDelete);
						button.dispose();
				}else {
					table.remove(temp);
					propertyLst.remove(temp);
					button.dispose();
					isAnyUpdatePerformed = true;
					applyButton.setEnabled(true);
				}
			}
		});
		deleteButton.setImage(null);*/

		/*deleteAll = new Button(composite, SWT.NONE);
		deleteAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (table.getItemCount() != 0) {
					boolean userAns = MessageDialog.openConfirm(shell, "Remove all", //$NON-NLS-1$
							Messages.ConfirmToDeleteAllProperties);
					if (userAns) {
						table.removeAll();
						propertyLst.removeAll(propertyLst);
						lblPropertyError.setVisible(false);
						okButton.setEnabled(true);
						addButton.setEnabled(true);
						applyButton.setEnabled(true);
					}
				}
			}
		});
		deleteAll.setBounds(172, 10, 75, 25);
		deleteAll.setText("Delete All"); *///$NON-NLS-1$

		/*applyButton = new Button(composite, SWT.NONE);
		applyButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				if (validate()) {
					if (isAnyUpdatePerformed) {
						filterSet.clear();
						for (ELTFilterProperties temp : propertyLst) {
							filterSet.add(temp.getPropertyname());
						}
						//MessageBox messageBox = new MessageBox(shell, SWT.NONE);
						//messageBox.setText("Information"); //$NON-NLS-1$
						//messageBox.setMessage(Messages.PropertyAppliedNotification);
						//messageBox.open();
						applyButton.setEnabled(false);
						isAnyUpdatePerformed = false;
					}
				}
			}
		});
		applyButton.setBounds(253, 10, 75, 25);
		applyButton.setText("Apply");*/

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
	/*void disableButtons() {
		okButton.setEnabled(false);
		//applyButton.setEnabled(false);
		addButton.setEnabled(false);

	}*/

	/**
	 * Enable buttons.
	 */
	void enableButtons() {
		okButton.setEnabled(true);
		//applyButton.setEnabled(true);
		addButton.setEnabled(true);
	}
	
	public static void main(String[] args) {
		ELTFilterPropertyWizard obj = new ELTFilterPropertyWizard();
			obj.launchRuntimeWindow(new Shell());
	}
}
