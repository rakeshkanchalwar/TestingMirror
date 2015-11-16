package com.bitwise.app.propertywindow.widgets.customwidgets.operational;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.bitwise.app.common.datastructure.property.NameValueProperty;
import com.bitwise.app.common.datastructure.property.OperationField;
import com.bitwise.app.common.datastructure.property.PropertyField;
import com.bitwise.app.common.datastructure.property.TransformOperation;
import com.bitwise.app.common.datastructure.property.TransformPropertyGrid;
import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.messagebox.ConfirmCancelMessageBox;
import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.customwidgets.AbstractWidget.ValidationStatus;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultButton;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultCheckBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultTextBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTTable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper.HelperType;
import com.bitwise.app.propertywindow.widgets.listeners.grid.ELTGridDetails;
import com.bitwise.app.propertywindow.widgets.listeners.grid.transform.ELTTransforAddPropValueListener;
import com.bitwise.app.propertywindow.widgets.listeners.grid.transform.ELTTransforAddSelectionListener;
import com.bitwise.app.propertywindow.widgets.utility.FilterOperationClassUtility;
import com.bitwise.app.propertywindow.widgets.utility.GridWidgetCommonBuilder;
import com.bitwise.app.propertywindow.widgets.utility.SWTResourceManager;

// TODO: Auto-generated Javadoc
/**
 * The Class ELTOperationClassDialog.
 * 
 * @author Bitwise
 */
public class TransformDialog extends Dialog {

	
	public static final String PROPERTY_NAME = "Property Name";
	public static final String PROPERTY_VALUE = "Property Values";
	public static final String OPERATIONAL_INPUT_FIELD = "Operation Input Fields";
	public static final String OPERATIONAL_OUTPUT_FIELD = "Operation Output Fields";
	private static final String ADD_ICON = XMLConfigUtil.CONFIG_FILES_PATH + "/icons/" + "add.png";
	private Composite container;
	private static final String[] NAME_VALUE_COLUMN = {PROPERTY_NAME, PROPERTY_VALUE};
	
	private ELTTransforAddSelectionListener eltTransforAddSelectionListener = new ELTTransforAddSelectionListener();
	private ELTTransforAddPropValueListener eltTransforAddPropValueListener = new ELTTransforAddPropValueListener();
  
    private final List<OperationField> opOutputOuterFields = new ArrayList<OperationField>();
    private final List<NameValueProperty> opOuterClassProperty = new ArrayList<NameValueProperty>();    
    private List<TransformOperation> transformOperationList = new ArrayList<>();
	
    private TableViewer innerOpInputTabViewer;
	private TableViewer innerOpOutputTabViewer;
	private TableViewer innerKeyValueTabViewer;
	private TableViewer outerKeyValueTabViewer;
	private TableViewer	outerOpTabViewer;
	
	private Text fileName;
	private Button applyButton;
	private ExpandBar expandBar = null;
	private PropertyDialogButtonBar propertyDialogButtonBar;
	private Button btnCheckButton;
	private ValidationStatus validationStatus;
	
	
	private TransformPropertyGrid transformPropertyGrid;
	private 


	// Operational class label.
	AbstractELTWidget fieldError = new ELTDefaultLable(Messages.FIELDNAMEERROR).lableWidth(250);
	/**
	 * Create the dialog.
	 * @param parentShell
	 * @param operationClassProperty 
	 */
	public TransformDialog(Shell parentShell,PropertyDialogButtonBar propertyDialogButtonBar,TransformPropertyGrid transformPropertyGrid) {
		super(parentShell);
		setShellStyle(SWT.CLOSE | SWT.RESIZE | SWT.TITLE |  SWT.WRAP | SWT.APPLICATION_MODAL);
		this.propertyDialogButtonBar=propertyDialogButtonBar;
		this.transformPropertyGrid = transformPropertyGrid;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		container = (Composite) super.createDialogArea(parent);
		btnCheckButton =new Button(container, SWT.CHECK);
		fileName=new Text(container, SWT.BORDER);
		//ColumnLayout cl_container = new FormData();
		//cl_container.maxNumColumns = 1;
		container.setLayout(new FormLayout());
		container.getShell().setText("Transform");
	
		Composite middleContainerComposite = new Composite(container, SWT.NONE);
		FormData fd_middleContainerComposite = new FormData();
		fd_middleContainerComposite.top = new FormAttachment(0, 10);
		fd_middleContainerComposite.bottom = new FormAttachment(100, -10);
		middleContainerComposite.setLayoutData(fd_middleContainerComposite);
			
		Composite leftContainerComposite = new Composite(container, SWT.NONE);
		fd_middleContainerComposite.left = new FormAttachment(leftContainerComposite, 6);
		FormData fd_leftContainerComposite_1 = new FormData();
		fd_leftContainerComposite_1.right = new FormAttachment(0, 112);
		fd_leftContainerComposite_1.top = new FormAttachment(0, 40);
		fd_leftContainerComposite_1.bottom = new FormAttachment(100, -10);
		fd_leftContainerComposite_1.left = new FormAttachment(0, 20);
		leftContainerComposite.setLayoutData(fd_leftContainerComposite_1);
		
	    
		Composite composite = new Composite(container, SWT.NONE);
		FormData fd_composite = new FormData();
		fd_composite.right = new FormAttachment(middleContainerComposite, -6);
		composite.setLayoutData(fd_composite);
		
		Button leftTreeAddButton = new Button(composite, SWT.NONE);
		leftTreeAddButton.setBounds(14, 10, 36, 25);
		leftTreeAddButton.setImage(SWTResourceManager.getImage(ADD_ICON));
		
	    final Tree tree = new Tree(leftContainerComposite, SWT.CHECK | SWT.BORDER);
		tree.setBounds(0, 10, 92, 428);
		
		leftTreeAddButton.addSelectionListener(new SelectionAdapter() {
			@Override 
			public void widgetSelected(SelectionEvent e) {
				TreeItem item = new TreeItem(tree, SWT.NONE);
			      item.setText("item ");
			}
		});
		
	      final TreeEditor editor = new TreeEditor(tree);
	        //The editor must have the same size as the cell and must
	        //not be any smaller than 50 pixels.
	        editor.horizontalAlignment = SWT.LEFT;
	        editor.grabHorizontal = true;
	        editor.minimumWidth = 50;
	        
	        tree.addSelectionListener(new SelectionAdapter() {
	                public void widgetSelected(SelectionEvent e) {
	                        // Clean up any previous editor control
	                        Control oldEditor = editor.getEditor();
	                        if (oldEditor != null) oldEditor.dispose();
	        
	                        // Identify the selected row
	                        TreeItem item = (TreeItem)e.item;
	                        if (item == null) return;
	        
	                        // The control that will be the editor must be a child of the Tree
	                        Text newEditor = new Text(tree, SWT.NONE);
	                        newEditor.setText(item.getText());
	                        newEditor.addModifyListener(new ModifyListener() {
	                                public void modifyText(ModifyEvent e) {
	                                        Text text = (Text)editor.getEditor();
	                                        editor.getItem().setText(text.getText());
	                                }
	                        });
	                        newEditor.selectAll();
	                        newEditor.setFocus();
	                        editor.setEditor(newEditor, item);
	                }
	        }); 
	 
	    applyDrag(tree);
	    
		Composite rightContainerComposite = new Composite(container, SWT.NONE);
		fd_middleContainerComposite.right = new FormAttachment(rightContainerComposite, -6);
		FormData fd_rightContainerComposite = new FormData();
		fd_rightContainerComposite.right = new FormAttachment(100, -10);
		fd_rightContainerComposite.left = new FormAttachment(0, 914);
		fd_rightContainerComposite.top = new FormAttachment(0, 10);
		fd_rightContainerComposite.bottom = new FormAttachment(100, -10);
		rightContainerComposite.setLayoutData(fd_rightContainerComposite);
		
		outerOpTabViewer = createTableViewer(rightContainerComposite, new String[]{OPERATIONAL_OUTPUT_FIELD},new TransformGridContentProvider(),new OperationLabelProvider());
		outerOpTabViewer.setCellModifier(new OperationGridCellModifier(outerOpTabViewer));
		outerOpTabViewer.setInput(opOutputOuterFields); 
		applyDrop(outerOpTabViewer, opOutputOuterFields, true);

		tree.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event event) {
		       if(((TreeItem)event.item).getChecked()){
		    	   OperationField opField = new OperationField();
		    	   opField.setName(((TreeItem)event.item).getText()); 
		    	   opOutputOuterFields.add(opField);
		    	   outerOpTabViewer.refresh(); 
		      }
		      } 
		    });

		Composite topAddButtonComposite = new Composite(middleContainerComposite, SWT.NONE);
		topAddButtonComposite.setBounds(10, 0, 777, 35);
		
		Button btnAddOperation = new Button(topAddButtonComposite, SWT.NONE);
		
		btnAddOperation.setImage(SWTResourceManager.getImage(ADD_ICON));
		btnAddOperation.setBounds(731, 10, 36, 25);
			
		final ScrolledComposite expandBarScrolledComposite = new ScrolledComposite(middleContainerComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		expandBarScrolledComposite.setVisible(true);
		expandBarScrolledComposite.setBounds(10, 41, 777, 220);
		expandBarScrolledComposite.setExpandHorizontal(true);
		expandBarScrolledComposite.setExpandVertical(true);
		
		expandBar = new ExpandBar(expandBarScrolledComposite, SWT.NONE);
		expandBar.setVisible(true);
		addExpandItem(container, expandBarScrolledComposite,new TransformOperation());
		
		Composite nameValueComposite = new Composite(middleContainerComposite, SWT.NONE);
		nameValueComposite.setBounds(10, 267, 770, 195);

		
		
		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite2 = new ELTDefaultSubgroupComposite(nameValueComposite);
		eltSuDefaultSubgroupComposite2.createContainerWidget();
		AbstractELTWidget addButton = getButton("");
		AbstractELTWidget deleteButton = getButton(""); 
		eltSuDefaultSubgroupComposite2.attachWidget(addButton);
		eltSuDefaultSubgroupComposite2.attachWidget(deleteButton);
		Button addOutKeyValueButton=(Button) addButton.getSWTWidgetControl();
		addOutKeyValueButton.setParent(nameValueComposite);
		addOutKeyValueButton.setBounds(683, 10, 39, 25);
		addOutKeyValueButton.setImage(SWTResourceManager.getImage(ADD_ICON));
		
		Button deleteOutKeyValueButton = (Button) deleteButton.getSWTWidgetControl();
		deleteOutKeyValueButton.setParent(nameValueComposite);
		deleteOutKeyValueButton.setBounds(728, 10, 39, 25);
		deleteOutKeyValueButton.setImage(SWTResourceManager.getImage(ADD_ICON));	

		outerKeyValueTabViewer = createTableViewer(nameValueComposite, NAME_VALUE_COLUMN, new TransformGridContentProvider(),new PropertyLabelProvider());
		outerKeyValueTabViewer.setCellModifier(new PropertyGridCellModifier(outerKeyValueTabViewer));
		outerKeyValueTabViewer.setInput(opOuterClassProperty); 
		applyDrop(outerKeyValueTabViewer, opOuterClassProperty, false);

		ELTDefaultSubgroupComposite defaultnameValueComposite = new ELTDefaultSubgroupComposite(nameValueComposite);
		defaultnameValueComposite.createContainerWidget();

		ELTTable eltPropOuterTable = new ELTTable(outerKeyValueTabViewer);
		defaultnameValueComposite.attachWidget(eltPropOuterTable);
	
		
		
		
		
		
		btnAddOperation.addSelectionListener(new SelectionAdapter() {
			@Override 
			public void widgetSelected(SelectionEvent e) {
					addExpandItem(container, expandBarScrolledComposite,new TransformOperation());
			}
		}); 
		
		ListenerHelper helperPropertyValue=getListenerHelper(opOuterClassProperty, outerKeyValueTabViewer, fieldError,eltTransforAddPropValueListener);

		/*
		 * Listener attached for property name value Outer main grid
		 */
			try {
				addButton.attachListener(ListenerFactory.Listners.GRID_ADD_SELECTION.getListener(),propertyDialogButtonBar, helperPropertyValue, outerKeyValueTabViewer.getTable());
				deleteButton.attachListener(ListenerFactory.Listners.GRID_DELETE_SELECTION.getListener(),propertyDialogButtonBar, helperPropertyValue, outerKeyValueTabViewer.getTable());
				eltPropOuterTable.attachListener(ListenerFactory.Listners.GRID_MOUSE_DOUBLE_CLICK.getListener(),	propertyDialogButtonBar, helperPropertyValue, outerKeyValueTabViewer.getTable());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		//eltTable.attachListener(ListenerFactory.Listners.GRID_MOUSE_DOWN.getListener(),propertyDialogButtonBar, helperOpOut, editors[0].getControl());

			populateWidget();
		
		return container;
	}

	private void addExpandItem(Composite shell, ScrolledComposite expandBarScrolledComposite,TransformOperation transformOperation ) {
		Composite middleContainerComposite = new Composite(shell, SWT.NONE);
		FormData fd_middleContainerComposite = new FormData();
		fd_middleContainerComposite.top = new FormAttachment(0, 10);
		fd_middleContainerComposite.bottom = new FormAttachment(100, -10);
		middleContainerComposite.setLayoutData(fd_middleContainerComposite);
		
		Composite leftContainerComposite = new Composite(shell, SWT.NONE);
		fd_middleContainerComposite.left = new FormAttachment(leftContainerComposite, 6);
		FormData fd_leftContainerComposite = new FormData();
		fd_leftContainerComposite.top = new FormAttachment(0, 10);
		fd_leftContainerComposite.bottom = new FormAttachment(100, -10);
		fd_leftContainerComposite.right = new FormAttachment(0, 112);
		fd_leftContainerComposite.left = new FormAttachment(0, 20);
		leftContainerComposite.setLayoutData(fd_leftContainerComposite);

		Composite rightContainerComposite = new Composite(shell, SWT.NONE);
		fd_middleContainerComposite.right = new FormAttachment(rightContainerComposite, -6);
		FormData fd_rightContainerComposite = new FormData();
		fd_rightContainerComposite.top = new FormAttachment(0, 10);
		fd_rightContainerComposite.bottom = new FormAttachment(100, -10);
		fd_rightContainerComposite.right = new FormAttachment(100, -10);
		fd_rightContainerComposite.left = new FormAttachment(0, 914);
		rightContainerComposite.setLayoutData(fd_rightContainerComposite);
		
		ExpandItem expandItem = new ExpandItem(expandBar, SWT.V_SCROLL);
		expandItem.setExpanded(true);
		expandItem.setText("Operation"); 
		expandItem.setHeight(200);
		
		Composite expandItemContainerComposite = new Composite(expandBar, SWT.NONE);
		expandItemContainerComposite.setVisible(true);
		expandItem.setControl(expandItemContainerComposite);
		expandItemContainerComposite.setLayout(new RowLayout(SWT.VERTICAL));
		
		Composite OpInputFieldComposite = new Composite(expandItemContainerComposite,SWT.NONE);
		OpInputFieldComposite.setLayoutData(new RowData(170, 142));
		OpInputFieldComposite.setSize(new Point(200, 0));
		OpInputFieldComposite.setVisible(true);

		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite2 = new ELTDefaultSubgroupComposite(OpInputFieldComposite);
		eltSuDefaultSubgroupComposite2.createContainerWidget();
		AbstractELTWidget addButton = getButton("");
		AbstractELTWidget deleteButton = getButton(""); 
		eltSuDefaultSubgroupComposite2.attachWidget(addButton);
		eltSuDefaultSubgroupComposite2.attachWidget(deleteButton);
		Button btnNewButton_4=(Button) addButton.getSWTWidgetControl();
		btnNewButton_4.setParent(OpInputFieldComposite);
		btnNewButton_4.setBounds(117, 10, 35, 25);
		btnNewButton_4.setImage(SWTResourceManager.getImage(ADD_ICON));
		
		Button btnNewButton_5 = (Button) deleteButton.getSWTWidgetControl();
		btnNewButton_5.setParent(OpInputFieldComposite);
		btnNewButton_5.setBounds(88, 10, 28, 25);
		btnNewButton_5.setImage(SWTResourceManager.getImage(ADD_ICON));		 
		
		innerOpInputTabViewer = createTableViewer(OpInputFieldComposite, new String[]{OPERATIONAL_INPUT_FIELD},new TransformGridContentProvider(),new OperationLabelProvider());
		innerOpInputTabViewer.setCellModifier(new OperationGridCellModifier(innerOpInputTabViewer));
		innerOpInputTabViewer.setInput(transformOperation.getInputFields());
		//applyDrop(tableViewer.getTable(), tableViewer,new OperationField(true,false));
		applyDrop(innerOpInputTabViewer, transformOperation.getInputFields(), true);
		ELTTable eltOpInTable = new ELTTable(innerOpInputTabViewer);
		
		eltSuDefaultSubgroupComposite2.attachWidget(eltOpInTable);
		
		
		
		Composite composite_4 = new Composite(expandItemContainerComposite, SWT.NONE);
		composite_4.setLayoutData(new RowData(336, SWT.DEFAULT));
		composite_4.setVisible(true);
		composite_4.setLayout(new RowLayout(SWT.VERTICAL));
		
		Composite fileSelectionComposite = new Composite(composite_4, SWT.NONE);
		fileSelectionComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
		fileSelectionComposite.setLayoutData(new RowData(331, 63));
		
		AbstractELTWidget fileNameText = new ELTDefaultTextBox().grabExcessHorizontalSpace(true).textBoxWidth(150);
		AbstractELTWidget isParameterCheckbox = new ELTDefaultCheckBox("Is Parameter").checkBoxLableWidth(100);
		
		FilterOperationClassUtility.createOperationalClass(fileSelectionComposite, propertyDialogButtonBar, fileNameText, isParameterCheckbox, validationStatus);
		fileName=(Text)fileNameText.getSWTWidgetControl();
		btnCheckButton=(Button) isParameterCheckbox.getSWTWidgetControl();
		
		Composite nameValueInnerComposite = new Composite(composite_4, SWT.NONE);
		innerKeyValueTabViewer = createTableViewer(nameValueInnerComposite, NAME_VALUE_COLUMN, new TransformGridContentProvider(),new PropertyLabelProvider());
		innerKeyValueTabViewer.setCellModifier(new PropertyGridCellModifier(innerKeyValueTabViewer));
		innerKeyValueTabViewer.setInput(transformOperation.getNameValueProps());  
		
		ELTDefaultSubgroupComposite defaultnameValueInnerComposite = new ELTDefaultSubgroupComposite(nameValueInnerComposite);
		defaultnameValueInnerComposite.createContainerWidget(); 
		
		ELTTable eltPropValueTable = new ELTTable(innerKeyValueTabViewer);
		defaultnameValueInnerComposite.attachWidget(eltPropValueTable);
		
		AbstractELTWidget addInnerPropValueButton = getButton("");
		AbstractELTWidget deleteInnerPropValueButton = getButton("");
		
		defaultnameValueInnerComposite.attachWidget(addInnerPropValueButton);
		defaultnameValueInnerComposite.attachWidget(deleteInnerPropValueButton);
		Button btnInnerPropValueAddButton=(Button) addInnerPropValueButton.getSWTWidgetControl();
		btnInnerPropValueAddButton.setParent(nameValueInnerComposite);
		btnInnerPropValueAddButton.setBounds(289, 0, 39, 25);
		btnInnerPropValueAddButton.setImage(SWTResourceManager.getImage(ADD_ICON));
		
		Button btnInnerPropValueDeleteButton=(Button) deleteInnerPropValueButton.getSWTWidgetControl();
		btnInnerPropValueDeleteButton.setParent(nameValueInnerComposite);
		btnInnerPropValueDeleteButton.setBounds(244, 0, 39, 25);
		btnInnerPropValueDeleteButton.setImage(SWTResourceManager.getImage(ADD_ICON));


		Composite opOutputFieldComposite = new Composite(expandItemContainerComposite, SWT.NONE);
		opOutputFieldComposite.setLayoutData(new RowData(211, 141));
		opOutputFieldComposite.setVisible(true);

		ELTDefaultSubgroupComposite buttonOprationOutputComposite = new ELTDefaultSubgroupComposite(opOutputFieldComposite);
		buttonOprationOutputComposite.createContainerWidget();
		AbstractELTWidget addOpOutButton = getButton("");
		AbstractELTWidget deleteOpOutButton = getButton("");
		
		buttonOprationOutputComposite.attachWidget(addOpOutButton);
		buttonOprationOutputComposite.attachWidget(deleteOpOutButton);
		Button btnOpOutAddButton=(Button) addOpOutButton.getSWTWidgetControl();
		btnOpOutAddButton.setParent(opOutputFieldComposite);
		btnOpOutAddButton.setBounds(135, 10, 28, 25);
		btnOpOutAddButton.setImage(SWTResourceManager.getImage(ADD_ICON));
		
		Button btnOpOutDeleteButton=(Button) deleteOpOutButton.getSWTWidgetControl();
		btnOpOutDeleteButton.setParent(opOutputFieldComposite);
		btnOpOutDeleteButton.setBounds(101, 10, 28, 25);
		btnOpOutDeleteButton.setImage(SWTResourceManager.getImage(ADD_ICON));
		

		
		
		innerOpOutputTabViewer = createTableViewer(opOutputFieldComposite, new String[]{"Operation Output Fields"},new TransformGridContentProvider(),new OperationLabelProvider());
		innerOpOutputTabViewer.setCellModifier(new OperationGridCellModifier(innerOpOutputTabViewer));
		innerOpOutputTabViewer.setInput(transformOperation.getOutputFields());
		ELTTable eltOpOutTable = new ELTTable(innerOpOutputTabViewer);
		
		buttonOprationOutputComposite.attachWidget(eltOpOutTable);

		
		applyDragFromTableViewer(innerOpOutputTabViewer.getTable());
		//applyDropToTree(tree_1, tableViewerOutput,new OperationField(false,false)); 		
		expandBarScrolledComposite.setContent(expandBar);
		expandBarScrolledComposite.setMinSize(expandBar.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		ListenerHelper helperOpIn=getListenerHelper(transformOperation.getInputFields(), innerOpInputTabViewer, fieldError,eltTransforAddSelectionListener);  
		ListenerHelper helperOpOut=getListenerHelper(transformOperation.getOutputFields(), innerOpOutputTabViewer, fieldError,eltTransforAddSelectionListener);
		ListenerHelper helperPropertyValue=getListenerHelper(transformOperation.getNameValueProps(), innerKeyValueTabViewer, fieldError,eltTransforAddPropValueListener);
		try {
			
			/*
			 * Listener attached for operational input and operation output grid
			 */
			addButton.attachListener(ListenerFactory.Listners.GRID_ADD_SELECTION.getListener(),propertyDialogButtonBar, helperOpIn, innerOpInputTabViewer.getTable());
			deleteButton.attachListener(ListenerFactory.Listners.GRID_DELETE_SELECTION.getListener(),propertyDialogButtonBar, helperOpIn, innerOpInputTabViewer.getTable());
			addOpOutButton.attachListener(ListenerFactory.Listners.GRID_ADD_SELECTION.getListener(),propertyDialogButtonBar, helperOpOut, innerOpOutputTabViewer.getTable());
			deleteOpOutButton.attachListener(ListenerFactory.Listners.GRID_DELETE_SELECTION.getListener(),propertyDialogButtonBar, helperOpOut, innerOpOutputTabViewer.getTable());  
			eltOpInTable.attachListener(ListenerFactory.Listners.GRID_MOUSE_DOUBLE_CLICK.getListener(),	propertyDialogButtonBar, helperOpIn, innerOpInputTabViewer.getTable());
			eltOpOutTable.attachListener(ListenerFactory.Listners.GRID_MOUSE_DOUBLE_CLICK.getListener(),	propertyDialogButtonBar, helperOpOut, innerOpOutputTabViewer.getTable());
			//eltTable.attachListener(ListenerFactory.Listners.GRID_MOUSE_DOWN.getListener(),propertyDialogButtonBar, helperOpOut, editors[0].getControl());

			/*
			 * Listener attached for property name value inner grid
			 */
			addInnerPropValueButton.attachListener(ListenerFactory.Listners.GRID_ADD_SELECTION.getListener(),propertyDialogButtonBar, helperPropertyValue, innerKeyValueTabViewer.getTable());
			deleteInnerPropValueButton.attachListener(ListenerFactory.Listners.GRID_DELETE_SELECTION.getListener(),propertyDialogButtonBar, helperPropertyValue, innerKeyValueTabViewer.getTable());
			eltPropValueTable.attachListener(ListenerFactory.Listners.GRID_MOUSE_DOUBLE_CLICK.getListener(),	propertyDialogButtonBar, helperPropertyValue, innerKeyValueTabViewer.getTable());
			//eltTable.attachListener(ListenerFactory.Listners.GRID_MOUSE_DOWN.getListener(),propertyDialogButtonBar, helperOpOut, editors[0].getControl());
			transformOperationList.add(transformOperation);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
 
	public static TableViewer createTableViewer(Composite composite,String[] prop,IStructuredContentProvider iStructuredContentProvider,ITableLabelProvider iTableLabelProvider){
			TableViewer tableViewer= new TableViewer(composite,SWT.BORDER|SWT.FULL_SELECTION);
		    tableViewer.setContentProvider(iStructuredContentProvider);
		    tableViewer.setLabelProvider( iTableLabelProvider);
			tableViewer.setColumnProperties(prop); 
		
			Table table = tableViewer.getTable();
			table.setVisible(true);
			table.setLinesVisible(true);
			table.setHeaderVisible(true);
			table.setBounds(10, 10, 757, 151);
			
			createTableColumns(table,prop);
			for (int columnIndex = 0, n = table.getColumnCount(); columnIndex < n; columnIndex++) {
				table.getColumn(columnIndex).pack();
			}

			CellEditor[] editors = null; 
			editors =createCellEditorList(table,prop.length); 
			tableViewer.setCellEditors(editors);
			return tableViewer;
	}
	
	 public static void createTableColumns(Table table,String[] fields){
			for (String field : fields) {
				TableColumn tableColumn= new TableColumn(table, SWT.CENTER);
				tableColumn.setText(field);
				tableColumn.pack();
			}
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			
		}
		public static CellEditor[] createCellEditorList(Table table,int size){
			CellEditor[] cellEditor = new CellEditor[size];
			for(int i=0;i<size;i++)
			addTextEditor(table,cellEditor, i);

			return cellEditor;
		}
		protected static void addTextEditor(Table table, CellEditor[] cellEditor, int position){
			cellEditor[position]=new TextCellEditor(table, SWT.COLOR_GREEN);
		}


		public void applyDrag(Control sourceControl){
		    Transfer[] types = new Transfer[] { TextTransfer.getInstance() };
		    int operations = DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_LINK;
		    final Tree tree =(Tree)sourceControl;
		     DragSource source = new DragSource(tree, operations);
		    source.setTransfer(types);
		    final TreeItem[] dragSourceItem = new TreeItem[1];
		    source.addDragListener(new DragSourceListener() {
		      public void dragStart(DragSourceEvent event) {
		        TreeItem[] selection = tree.getSelection();
		        if (selection.length > 0 && selection[0].getItemCount() == 0) {
		          event.doit = true;
		          dragSourceItem[0] = selection[0];
		        } else {
		          event.doit = false;
		        }
		      };

		      public void dragSetData(DragSourceEvent event) {
		        event.data = dragSourceItem[0].getText();
		      }

		      public void dragFinished(DragSourceEvent event) {
		        if (event.detail == DND.DROP_COPY)
		          dragSourceItem[0].dispose();
		        dragSourceItem[0] = null;
		      }
		    });

		}
		
		private void applyDrop(final TableViewer tableViewerOpOuter, final List listOfFields, final boolean isSingleColumn) {
			Transfer[] types = new Transfer[] { TextTransfer.getInstance() };
			int operations = DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_LINK;
			DropTarget target = new DropTarget(tableViewerOpOuter.getTable(), operations);
			target.setTransfer(types);
			
			target.addDropListener(new DropTargetAdapter() {
			      public void dragOver(DropTargetEvent event) {
			    	  event.feedback = DND.FEEDBACK_EXPAND | DND.FEEDBACK_SCROLL; 
			      }
	
			      public void drop(DropTargetEvent event) {
			        if (event.data == null) {
			        	event.detail = DND.DROP_NONE;
			        	return;
			        }
			        if(isSingleColumn){
			        	OperationField field = new OperationField();
			        	field.setName((String) event.data);
			        	listOfFields.add(field);
			        }
			        else{
			        	System.out.println(listOfFields);
			        	NameValueProperty field = new NameValueProperty();
			        	field.setPropertyName((String) event.data);
			        	listOfFields.add(field);
			        }
			        tableViewerOpOuter.refresh(); 
			      } 
			});
		}
		
		public void applyDragFromTableViewer(Control sourceControl){
		    Transfer[] types = new Transfer[] { TextTransfer.getInstance() };

		    int operations = DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_LINK;
		    final Table table =(Table)sourceControl;
		     DragSource source = new DragSource(table, operations);
		    source.setTransfer(types);
		    final String[] columnData = new String[1];
		    source.addDragListener(new DragSourceListener() {
		      public void dragStart(DragSourceEvent event) {
		      TableItem[] selection = table.getSelection();
		      
		        if (selection[0].getText().length()>0) { 
		          event.doit = true;
		          columnData[0] = selection[0].getText();
		        } else {
		          event.doit = false;
		        }
		      }; 

		      public void dragSetData(DragSourceEvent event) {
		        event.data = columnData[0];
		      }

		      public void dragFinished(DragSourceEvent event) {
		        if (event.detail == DND.DROP_COPY){
		        	columnData[0]=null;
		        }
		      }
		    });

		}
	
		private AbstractELTWidget getButton(String displayName) {
			// Create browse button.
			AbstractELTWidget button = new ELTDefaultButton(displayName)
					.buttonWidth(20);
			return button;
		}

		
		private ListenerHelper getListenerHelper(List<? extends PropertyField> opList,TableViewer tableViewer,AbstractELTWidget fieldError,GridWidgetCommonBuilder gridWidgetBuilder) {
				ListenerHelper helper = new ListenerHelper();
				ELTGridDetails value = new ELTGridDetails(opList, tableViewer, 
						(Label) fieldError.getSWTWidgetControl(), gridWidgetBuilder);
				helper.put(HelperType.SCHEMA_GRID, value);
				return helper;
		}		
	 
		
		   public void populateWidget() {
/*		        if (!operationClassProperty.getOperationClassPath().equalsIgnoreCase("")) {
		              fileName.setText(operationClassProperty.getOperationClassPath());
		              btnCheckButton.setSelection(operationClassProperty.isParameter());
		        } 
*/
			   if(transformPropertyGrid!=null){ 
				   if(transformPropertyGrid.getOperation()!=null){
			   innerOpInputTabViewer.setInput(transformPropertyGrid.getOperation().get(0).getInputFields());
			   innerOpOutputTabViewer.setInput(transformPropertyGrid.getOperation().get(0).getOutputFields());
			   innerKeyValueTabViewer.setInput(transformPropertyGrid.getOperation().get(0).getNameValueProps());
			   fileName.setText(transformPropertyGrid.getOperation().get(0).getOpClassProperty().getOperationClassPath());
	           btnCheckButton.setSelection(transformPropertyGrid.getOperation().get(0).getOpClassProperty().isParameter());
	           
	           outerKeyValueTabViewer.setInput(transformPropertyGrid.getNameValueProps());
	           outerOpTabViewer.setInput(transformPropertyGrid.getOutputTreeFields());
			   innerOpInputTabViewer.refresh();
			   innerOpOutputTabViewer.refresh();
			   outerKeyValueTabViewer.refresh();
			   outerOpTabViewer.refresh();
				   }
			   }
		        
		  }
		   
			public void setValidationStatus(ValidationStatus validationStatus) {
				this.validationStatus = validationStatus;
			}

			public ValidationStatus getValidationStatus() {
				return validationStatus;
			}

			public TransformPropertyGrid getTransformProperty() {
				TransformPropertyGrid transformPropertyGrid = new TransformPropertyGrid();
				transformPropertyGrid.setNameValueProps(opOuterClassProperty); 
				transformPropertyGrid.setOperation(transformOperationList); 
			//	OperationClassProperty operationClassProperty = new OperationClassProperty(this.operationClassProperty.getOperationClassPath(),this.operationClassProperty.isParameter());
				transformPropertyGrid.setOutputTreeFields(opOutputOuterFields);
				return transformPropertyGrid;
			}
			
			/**
			 * Create contents of the button bar.
			 * @param parent
			 */
			@Override
			protected void createButtonsForButtonBar(Composite parent) {
				Button okButton=createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
						true);
				Button cancelButton=createButton(parent, IDialogConstants.CANCEL_ID,
						IDialogConstants.CANCEL_LABEL, false);
				
				createApplyButton(parent);	
				
				
				propertyDialogButtonBar.setPropertyDialogButtonBar(okButton, applyButton, cancelButton);
			}
			
			private void createApplyButton(Composite parent) {
				applyButton = createButton(parent, IDialogConstants.NO_ID,
						"Apply", false);
				disableApplyButton();
			}
			
			private void disableApplyButton() {
				applyButton.setEnabled(false);
			}
			
			/**
			 * Return the initial size of the dialog.
			 */
			@Override
			protected Point getInitialSize() {
				return new Point(450, 183);
			}

			private void setPropertyDialogSize() {
				container.getShell().setMinimumSize(450, 185);
			}

			@Override
			protected void cancelPressed() {
				// TODO Auto-generated method stub
				
				if(applyButton.isEnabled()){
					ConfirmCancelMessageBox confirmCancelMessageBox = new ConfirmCancelMessageBox(container);
					MessageBox confirmCancleMessagebox = confirmCancelMessageBox.getMessageBox();

					if(confirmCancleMessagebox.open() == SWT.OK){
						super.close();
					}
				}else{
					super.close();
				}
				
				//super.cancelPressed();
			}

			@Override
			protected void okPressed() {
				// TODO Auto-generated method stub
				/*operationClassProperty.setParameter(btnCheckButton.getSelection());
		        operationClassProperty.setOperationClassPath(fileName.getText());*/
//		        operationClassProperty = new OperationClassProperty(fileName.getText(), btnCheckButton.getSelection());
				transformPropertyGrid= getTransformProperty();
				super.okPressed();
			}

			@Override
			protected void buttonPressed(int buttonId) {
				if(buttonId == 3){
//					operationClassProperty = new OperationClassProperty(fileName.getText(), btnCheckButton.getSelection());
					transformPropertyGrid= getTransformProperty();
					applyButton.setEnabled(false);
				}else{
					super.buttonPressed(buttonId);
				}
			}

}
