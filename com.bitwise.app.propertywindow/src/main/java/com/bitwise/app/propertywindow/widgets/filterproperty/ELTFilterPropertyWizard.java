package com.bitwise.app.propertywindow.widgets.filterproperty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
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
import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.widgets.utility.WidgetUtility;

public class ELTFilterPropertyWizard {

	private static final Logger logger = LogFactory.INSTANCE.getLogger(ELTFilterPropertyWizard.class);
	
	private Table table;

	private Shell shell;
	private List<ELTFilterProperties> propertyLst;
	public static final String FilterInputFieldName = "Component Name"; //$NON-NLS-1$
	private Set<String> filterSet;
	private String componentName;
	private Label lblHeader;
	private String PROPERTY_EXISTS_ERROR = Messages.RuntimePropertAlreadyExists;
	public static final String[] PROPS = { FilterInputFieldName };
	private String PROPERTY_NAME_BLANK_ERROR = Messages.EmptyNameNotification;
	private Label lblPropertyError;
	private boolean isOkPressed;
	private TableViewer tableViewer;
	private ControlDecoration decorator;
	public ControlDecoration scaleDecorator;
	private Button addButton, deleteAll, applyButton, okButton, deleteButton, cacelButton;
	private boolean isAnyUpdatePerformed;
	private Shell parentShell;


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

		tableViewer = new TableViewer(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				addNewProperty(tableViewer);
			}

			@Override
			public void mouseDown(MouseEvent e) {
				// enableButtons();
				lblPropertyError.setVisible(false);
			}
		});
		table.setBounds(10, 50, 465, 365);
		tableViewer.setContentProvider(new ELTFilterContentProvider());
		tableViewer.setLabelProvider(new ELTFilterLabelProvider());
		tableViewer.setInput(propertyLst);

		TableColumn tc1 = new TableColumn(table, SWT.CENTER);
		tc1.setText("Field Name");
		tc1.setWidth(460);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

	/**
	 * @return
	 * @wbp.parser.entryPoint
	 */
	public Set<String> launchRuntimeWindow() {

		shell = new Shell(parentShell, SWT.WRAP | SWT.APPLICATION_MODAL);
		isOkPressed = false;
		isAnyUpdatePerformed = false;
		shell.setSize(506, 540);
		shell.setLayout(null);
		shell.setText("Filter Property");
		lblHeader = new Label(shell, SWT.NONE);
		lblHeader.setBounds(10, 14, 450, 15);
		if (getComponentName() != null)
			lblHeader.setText(getComponentName() + "Filter Operation Field"); //$NON-NLS-1$
			else
			lblHeader.setText("Filter Component Property");
		new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL).setBounds(0, 35, 523, 2);
		// Below Event will be fired when user closes the Runtime window
		shell.addListener(SWT.Close, new Listener() {
			public void handleEvent(Event event) {

				if (table.getItemCount() != 0 && !isOkPressed && isAnyUpdatePerformed) {
					int style = SWT.APPLICATION_MODAL | SWT.YES | SWT.NO;
					MessageBox messageBox = new MessageBox(shell, style);
					messageBox.setText("Information"); //$NON-NLS-1$
					messageBox.setMessage(Messages.MessageBeforeClosingWindow);
					event.doit = messageBox.open() == SWT.YES;
				}
			}
		});

		createTable();

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 418, 513, 83);
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

		decorator = WidgetUtility.addDecorator(propertyNameEditor.getControl(), Messages.CHARACTERSET);
		loadProperties(tableViewer);

		Monitor primary = shell.getDisplay().getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();

		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;

		shell.setLocation(x, y);

		shell.open();

		while (!shell.isDisposed()) {
			if (!shell.getDisplay().readAndDispatch())
				shell.getDisplay().sleep();
		}

		return filterSet;
	}

	// Creates The buttons For the widget
	private void createButtons(Composite composite) {
		new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL).setBounds(0, 41, 513, 2);
		addButton = new Button(composite, SWT.NONE);
		addButton.setText("Add"); //$NON-NLS-1$
		addButton.setBounds(10, 10, 75, 25);
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addNewProperty(tableViewer);
			}
		});

		deleteButton = new Button(composite, SWT.NONE);
		deleteButton.setText("Delete"); //$NON-NLS-1$
		deleteButton.setBounds(91, 10, 75, 25);
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				int temp = table.getSelectionIndex();
				if (temp == -1)
					MessageDialog.openError(shell, "Error", //$NON-NLS-1$
							Messages.SelectRowToDelete);
				else {
					table.remove(temp);
					propertyLst.remove(temp);
					isAnyUpdatePerformed = true;
				}
			}
		});
		deleteButton.setImage(null);

		deleteAll = new Button(composite, SWT.NONE);
		deleteAll.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (table.getItemCount() != 0) {
					boolean userAns = MessageDialog.openConfirm(shell, "Remove all", //$NON-NLS-1$
							Messages.ConfirmToDeleteAllProperties);
					if (userAns) {
						table.removeAll();
						propertyLst.removeAll(propertyLst);
						lblPropertyError.setVisible(false);
						disableButtons();
					}
				}
			}
		});
		deleteAll.setBounds(172, 10, 75, 25);
		deleteAll.setText("Delete All"); //$NON-NLS-1$

		applyButton = new Button(composite, SWT.NONE);
		applyButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				if (validate()) {
					if (isAnyUpdatePerformed) {
						filterSet.clear();
						for (ELTFilterProperties temp : propertyLst) {
							filterSet.add(temp.getPropertyname());
						}
						MessageBox messageBox = new MessageBox(shell, SWT.NONE);
						messageBox.setText("Information"); //$NON-NLS-1$
						messageBox.setMessage(Messages.PropertyAppliedNotification);
						messageBox.open();
						isAnyUpdatePerformed = false;
					}
				}
			}
		});
		applyButton.setBounds(253, 10, 75, 25);
		applyButton.setText("Apply");

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

	protected boolean validate() {

		int propertyCounter = 0;

		for (ELTFilterProperties temp : propertyLst) {
			if (!temp.getPropertyname().trim().isEmpty()) {
				//if(temp.getPropertyname().trim().equalsIgnoreCase(anotherString))
			} else {
				table.setSelection(propertyCounter);
				lblPropertyError.setVisible(true);
				lblPropertyError.setText(Messages.EmptyNameNotification);
				disableButtons();
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
					disableButtons();
					return "ERROR"; //$NON-NLS-1$
				}else if(!valueToValidate.matches("[\\w+]*"))
				{
					decorator.show();
					disableButtons();
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
						disableButtons();
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

	void disableButtons() {
		okButton.setEnabled(false);
		applyButton.setEnabled(false);
		addButton.setEnabled(false);

	}

	void enableButtons() {
		okButton.setEnabled(true);
		applyButton.setEnabled(true);
		addButton.setEnabled(true);
	}

}
