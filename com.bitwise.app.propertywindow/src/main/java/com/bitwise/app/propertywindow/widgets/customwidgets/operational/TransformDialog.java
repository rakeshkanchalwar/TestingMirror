package com.bitwise.app.propertywindow.widgets.customwidgets.operational;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
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
import com.bitwise.app.propertywindow.datastructures.filter.OperationClassProperty;
import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.customwidgets.AbstractWidget.ValidationStatus;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultButton;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
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

	
	private static final String ADD_ICON = "C:\\BHS\\bhs\\add.png";
	private Composite container;
	public static final String PROPERTYNAME = "Property Name";
	public static final String PROPERTYVALUE = "Property Values";
	public static final String OPERATIONALINPUTFIELD = "Operation Input Fields";
	public static final String OPERATIONALOUTPUTFIELD = "Operation Output Fields";
	private ELTTransforAddSelectionListener eltTransforAddSelectionListener = new ELTTransforAddSelectionListener();
	private ELTTransforAddPropValueListener eltTransforAddPropValueListener = new ELTTransforAddPropValueListener();
    final ArrayList<PropertyField> propertyFields = new ArrayList<>();
	public Tree tree_1;
	private Text fileName;
	private ExpandBar expandBar = null;
	private OperationClassProperty operationClassProperty;
	private PropertyDialogButtonBar propertyDialogButtonBar;
	private Button btnCheckButton;
	private ListenerHelper helperOpIn;
	private ListenerHelper helperOpOut;
	private ValidationStatus validationStatus;
	// Operational class label.
	AbstractELTWidget fieldError = new ELTDefaultLable(Messages.FIELDNAMEERROR)
			.lableWidth(250);
	private TableViewer tableViewer;
	private TableViewer tableViewerOutput;
	private TableViewer tableViewerInnerPropValue;
	private TableViewer tableViewerPropOuter;
	/**
	 * Create the dialog.
	 * @param parentShell
	 * @param operationClassProperty 
	 */
	public TransformDialog(Shell parentShell,PropertyDialogButtonBar propertyDialogButtonBar,OperationClassProperty operationClassProperty) {
		super(parentShell);
		setShellStyle(SWT.CLOSE | SWT.RESIZE | SWT.TITLE |  SWT.WRAP | SWT.APPLICATION_MODAL);
		this.operationClassProperty = operationClassProperty;
		this.propertyDialogButtonBar=propertyDialogButtonBar;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		container = (Composite) super.createDialogArea(parent);
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
		
		tree_1 = new Tree(rightContainerComposite, SWT.CHECK | SWT.BORDER);
		tree_1.setBounds(10, 10, 89, 424);
		
		tree.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event event) {
		       if(((TreeItem)event.item).getChecked()){
		        TreeItem item = new TreeItem(tree_1, SWT.NONE);
			      item.setText(((TreeItem)event.item).getText()); 
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
		addExpandItem(container, expandBarScrolledComposite);
		
		Composite nameValueComposite = new Composite(middleContainerComposite, SWT.NONE);
		nameValueComposite.setBounds(10, 267, 767, 171);

		tableViewerPropOuter = createTableViewer(nameValueComposite, new String[]{"Property Name","Property Values"},new TransformGridContentProvider(),new PropertyLabelProvider());
		tableViewerPropOuter.setCellModifier(new PropertyGridCellModifier(tableViewerPropOuter));
		applyDrop(tableViewerPropOuter.getTable(), tableViewerPropOuter,new NameValueProperty());
		
		ELTDefaultSubgroupComposite defaultnameValueComposite = new ELTDefaultSubgroupComposite(nameValueComposite);
		defaultnameValueComposite.createContainerWidget();

		ELTTable eltPropOuterTable = new ELTTable(tableViewerPropOuter);
		defaultnameValueComposite.attachWidget(eltPropOuterTable);
	
		
		btnAddOperation.addSelectionListener(new SelectionAdapter() {
			@Override 
			public void widgetSelected(SelectionEvent e) {
				addExpandItem(container, expandBarScrolledComposite);
			}
		});
		
		ListenerHelper helperPropertyValue=getListenerHelper(propertyFields, tableViewerPropOuter, fieldError,eltTransforAddPropValueListener);

		/*
		 * Listener attached for property name value Outer main grid
		 */
		//addButton.attachListener(ListenerFactory.Listners.GRID_ADD_SELECTION.getListener(),propertyDialogButtonBar, helperPropertyValue, tableViewerInnerPropValue.getTable());
		//deleteButton.attachListener(ListenerFactory.Listners.GRID_DELETE_SELECTION.getListener(),propertyDialogButtonBar, helperPropertyValue, tableViewerInnerPropValue.getTable());
			try {
				eltPropOuterTable.attachListener(ListenerFactory.Listners.GRID_MOUSE_DOUBLE_CLICK.getListener(),	propertyDialogButtonBar, helperPropertyValue, tableViewerInnerPropValue.getTable());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		//eltTable.attachListener(ListenerFactory.Listners.GRID_MOUSE_DOWN.getListener(),propertyDialogButtonBar, helperOpOut, editors[0].getControl());

		
		
		return container;
	}


	private void addExpandItem(Composite shell, ScrolledComposite expandBarScrolledComposite) {
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
		
		ExpandItem expandItem = new ExpandItem(expandBar, SWT.NONE);
		expandItem.setExpanded(true);
		expandItem.setText("Operation");
		expandItem.setHeight(150);
		
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
		
		tableViewer = createTableViewer(OpInputFieldComposite, new String[]{"Operation Input Fields"},new TransformGridContentProvider(),new OperationLabelProvider());
		tableViewer.setCellModifier(new OperationGridCellModifier(tableViewer));
		applyDrop(tableViewer.getTable(), tableViewer,new OperationField());
		
		ELTTable eltOpInTable = new ELTTable(tableViewer);
		
		eltSuDefaultSubgroupComposite2.attachWidget(eltOpInTable);
		


		
		
		Composite composite_4 = new Composite(expandItemContainerComposite, SWT.NONE);
		composite_4.setLayoutData(new RowData(336, SWT.DEFAULT));
		composite_4.setVisible(true);
		composite_4.setLayout(new RowLayout(SWT.VERTICAL));
		
		Composite fileSelectionComposite = new Composite(composite_4, SWT.NONE);
		fileSelectionComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
		fileSelectionComposite.setLayoutData(new RowData(331, 63));
	
		FilterOperationClassUtility.createOperationalClass(fileSelectionComposite, propertyDialogButtonBar, fileName, btnCheckButton, validationStatus);
	
		
		Composite nameValueInnerComposite = new Composite(composite_4, SWT.NONE);
		tableViewerInnerPropValue = createTableViewer(nameValueInnerComposite, new String[]{"Property Name","Property Values"},new TransformGridContentProvider(),new PropertyLabelProvider());
		tableViewerInnerPropValue.setCellModifier(new PropertyGridCellModifier(tableViewerInnerPropValue));
	
		ELTDefaultSubgroupComposite defaultnameValueInnerComposite = new ELTDefaultSubgroupComposite(nameValueInnerComposite);
		defaultnameValueInnerComposite.createContainerWidget();
		
		ELTTable eltPropValueTable = new ELTTable(tableViewerInnerPropValue);
		
		defaultnameValueInnerComposite.attachWidget(eltPropValueTable);

		
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
		

		
		
		tableViewerOutput = createTableViewer(opOutputFieldComposite, new String[]{"Operation Output Fields"},new TransformGridContentProvider(),new OperationLabelProvider());
		tableViewerOutput.setCellModifier(new OperationGridCellModifier(tableViewerOutput));
		
		ELTTable eltOpOutTable = new ELTTable(tableViewerOutput);
		
		buttonOprationOutputComposite.attachWidget(eltOpOutTable);

		
		applyDragFromTableViewer(tableViewerOutput.getTable());
//		applyDropToTree(tree_1, tableViewerOutput,new OperationField()); 
		applyDrop(tableViewerOutput.getTable(), tableViewerOutput, new OperationField());
		
		expandBarScrolledComposite.setContent(expandBar);
		expandBarScrolledComposite.setMinSize(expandBar.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		ListenerHelper helperOpIn=getListenerHelper(propertyFields, tableViewer, fieldError,eltTransforAddSelectionListener);  
		ListenerHelper helperOpOut=getListenerHelper(propertyFields, tableViewerOutput, fieldError,eltTransforAddSelectionListener);
		ListenerHelper helperPropertyValue=getListenerHelper(propertyFields, tableViewerInnerPropValue, fieldError,eltTransforAddPropValueListener);
		try {
			
			/*
			 * Listener attached for operational input and operation output grid
			 */
			addButton.attachListener(ListenerFactory.Listners.GRID_ADD_SELECTION.getListener(),propertyDialogButtonBar, helperOpIn, tableViewer.getTable());
			deleteButton.attachListener(ListenerFactory.Listners.GRID_DELETE_SELECTION.getListener(),propertyDialogButtonBar, helperOpIn, tableViewer.getTable());
			addOpOutButton.attachListener(ListenerFactory.Listners.GRID_ADD_SELECTION.getListener(),propertyDialogButtonBar, helperOpOut, tableViewerOutput.getTable());
			deleteOpOutButton.attachListener(ListenerFactory.Listners.GRID_DELETE_SELECTION.getListener(),propertyDialogButtonBar, helperOpOut, tableViewerOutput.getTable());  
			eltOpInTable.attachListener(ListenerFactory.Listners.GRID_MOUSE_DOUBLE_CLICK.getListener(),	propertyDialogButtonBar, helperOpIn, tableViewer.getTable());
			eltOpOutTable.attachListener(ListenerFactory.Listners.GRID_MOUSE_DOUBLE_CLICK.getListener(),	propertyDialogButtonBar, helperOpOut, tableViewerOutput.getTable());
			//eltTable.attachListener(ListenerFactory.Listners.GRID_MOUSE_DOWN.getListener(),propertyDialogButtonBar, helperOpOut, editors[0].getControl());

			/*
			 * Listener attached for property name value inner grid
			 */
			addButton.attachListener(ListenerFactory.Listners.GRID_ADD_SELECTION.getListener(),propertyDialogButtonBar, helperPropertyValue, tableViewerInnerPropValue.getTable());
			deleteButton.attachListener(ListenerFactory.Listners.GRID_DELETE_SELECTION.getListener(),propertyDialogButtonBar, helperPropertyValue, tableViewerInnerPropValue.getTable());
			eltPropValueTable.attachListener(ListenerFactory.Listners.GRID_MOUSE_DOUBLE_CLICK.getListener(),	propertyDialogButtonBar, helperPropertyValue, tableViewerInnerPropValue.getTable());
			//eltTable.attachListener(ListenerFactory.Listners.GRID_MOUSE_DOWN.getListener(),propertyDialogButtonBar, helperOpOut, editors[0].getControl());
			
			
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
		
		public void applyDrop(Control desControl,final TableViewer tableViewer,final PropertyField propertyField){
		    Transfer[] types = new Transfer[] { TextTransfer.getInstance() };
		    int operations = DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_LINK;
		    Table table = (Table)desControl ;
		    DropTarget target = new DropTarget(table, operations);
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
		        String text = (String) event.data;
		        ArrayList<PropertyField> propertyFieldsTemp=  addPropertyToGrid(propertyField, text,propertyFields);
		        tableViewer.setInput(propertyFieldsTemp);
		        tableViewer.refresh(); 
		      } 
		    });
		}
		
		public ArrayList<PropertyField> addPropertyToGrid(PropertyField propertyField,String data,ArrayList<PropertyField> propertyFields){
			if(propertyField instanceof NameValueProperty)
			{
		        NameValueProperty pFields = new NameValueProperty();
		        pFields.setPropertyName(data);
		        pFields.setPropertyValue("");
		        propertyFields.add(pFields);
		        return propertyFields;
			}
			else if(propertyField instanceof OperationField)
			{
				OperationField pFields = new OperationField();
				pFields.setOperationField(data);
		        propertyFields.add(pFields);
		        return propertyFields;
			}
			return (ArrayList<PropertyField>) Collections.EMPTY_LIST;
		}
		
		public void applyDropToTree(Control desControl,final TableViewer tableViewer,final PropertyField propertyField){
		    Transfer[] types = new Transfer[] { TextTransfer.getInstance() };
		    int operations = DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_LINK;
		    desControl.setData(DND.DROP_TARGET_KEY,this);
		    Tree tree = (Tree)desControl ;
		    DropTarget target = new DropTarget(tree, operations);
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
		        String text = (String) event.data;
		        TreeItem item = new TreeItem(tree_1, SWT.NONE);
			      item.setText(text); 
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

		
		private ListenerHelper getListenerHelper(List<PropertyField> opList,TableViewer tableViewer,AbstractELTWidget fieldError,GridWidgetCommonBuilder gridWidgetBuilder) {
				ListenerHelper helper = new ListenerHelper();
				ELTGridDetails value = new ELTGridDetails(opList, tableViewer, 
						(Label) fieldError.getSWTWidgetControl(), gridWidgetBuilder);
				helper.put(HelperType.SCHEMA_GRID, value);
				return helper;
		}		
	
}
