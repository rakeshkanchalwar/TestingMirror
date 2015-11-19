package com.bitwise.app.parametergrid.dialog;


import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.ColumnLayoutData;

import com.bitwise.app.common.interfaces.parametergrid.DefaultGEFCanvas;
import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.parametergrid.textgridwidget.TextGrid;
import com.bitwise.app.parametergrid.textgridwidget.columns.TextGridColumnLayout;
import com.bitwise.app.parametergrid.textgridwidget.columns.TextGridRowLayout;
import com.bitwise.app.parametergrid.utils.ParameterFileManager;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class ParameterGridDialog extends Dialog {
	
	private TextGrid textGrid;
	private boolean runGraph;
	private Button headerCompositeCheckBox;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public ParameterGridDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.CLOSE | SWT.RESIZE | SWT.TITLE | SWT.WRAP | SWT.APPLICATION_MODAL);
		runGraph=false;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		final Composite container = (Composite) super.createDialogArea(parent);
		ColumnLayout cl_container = new ColumnLayout();
		cl_container.maxNumColumns = 1;
		container.setLayout(cl_container);
		
		container.getShell().setText("Parameter Grid");
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(4, false));
		ColumnLayoutData cld_composite = new ColumnLayoutData();
		cld_composite.horizontalAlignment = ColumnLayoutData.RIGHT;
		cld_composite.heightHint = 30;
		composite.setLayoutData(cld_composite);
		
		textGrid = new TextGrid(container);
	
		Label btnAdd = new Label(composite, SWT.NONE);
		GridData gd_btnAdd = getGridControlButtonLayout();
		btnAdd.setLayoutData(gd_btnAdd);
		btnAdd.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				TextGridRowLayout textGridRowLayout = new TextGridRowLayout();
				textGridRowLayout.addColumn(new TextGridColumnLayout.Builder().columnWidth(90).editable(true).build());
				textGridRowLayout.addColumn(new TextGridColumnLayout.Builder().grabHorizantalAccessSpace(true).editable(true).build());
				Composite emptyRow = textGrid.addEmptyRow(textGridRowLayout);
				headerCompositeCheckBox.setSelection(false);
				((Button)emptyRow.getChildren()[0]).addSelectionListener(new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						// TODO Auto-generated method stub
						super.widgetSelected(e);
						changeHeaderCheckboxSelection();
					}
					
				});
				
				
				textGrid.refresh();
				textGrid.scrollToLastRow();
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btnAdd.setText("");
		btnAdd.setImage(new Image(null, XMLConfigUtil.INSTANCE.CONFIG_FILES_PATH + "/icons/add.png"));
		
		Label btnRemove = new Label(composite, SWT.NONE);
		btnRemove.setLayoutData(getGridControlButtonLayout());
		btnRemove.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				textGrid.removeSelectedRows();
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnRemove.setText("");
		btnRemove.setImage(new Image(null, XMLConfigUtil.INSTANCE.CONFIG_FILES_PATH + "/icons/delete.png"));
		
		/*Label btnSelectAllRows = new Label(composite, SWT.NONE);
		btnSelectAllRows.setLayoutData(getGridControlButtonLayout());
		btnSelectAllRows.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				textGrid.selectAllRows();
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btnSelectAllRows.setText("");
		btnSelectAllRows.setImage(new Image(null, XMLConfigUtil.INSTANCE.CONFIG_FILES_PATH + "/icons/checkall.png"));
				
		Label btnDeselectAllRows = new Label(composite, SWT.NONE);
		btnDeselectAllRows.setLayoutData(getGridControlButtonLayout());
		btnDeselectAllRows.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				textGrid.clearSelections();
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btnDeselectAllRows.setText("");
		btnDeselectAllRows.setImage(new Image(null, XMLConfigUtil.INSTANCE.CONFIG_FILES_PATH + "/icons/uncheckall.png"));*/
		
				
		ParameterFileManager parameterFileManager = new ParameterFileManager(getComponentCanvas().getParameterFile());
		Map<String, String> parameterMap = parameterFileManager.getParameterMap();
		
		//List of rows, where each row is list of columns
		List<List<String>> graphGridData =  new LinkedList<>();
		List<List<String>> externalGridData =  new LinkedList<>();
		List<String> canvasParameterList = getComponentCanvas().getLatestParameterList();
		for(String key: parameterMap.keySet()){
			List<String> rowData = new LinkedList<>();
			if(canvasParameterList.contains(key)){
				rowData.add(key);
				rowData.add(parameterMap.get(key));
				graphGridData.add(rowData);
			}else{
				rowData.add(key);
				rowData.add(parameterMap.get(key));
				externalGridData.add(rowData);
			}
		}
		
		addGridHeader();
		
		for(List<String> row: graphGridData){
			TextGridRowLayout textGridRowLayout = new TextGridRowLayout();
			textGridRowLayout.addColumn(new TextGridColumnLayout.Builder().columnWidth(90).editable(false).build());
			textGridRowLayout.addColumn(new TextGridColumnLayout.Builder().grabHorizantalAccessSpace(true).editable(true).build());
			textGrid.addDisabledRow(textGridRowLayout, row);
		}
		
		for(List<String> row: externalGridData){
			TextGridRowLayout textGridRowLayout = new TextGridRowLayout();
			textGridRowLayout.addColumn(new TextGridColumnLayout.Builder().columnWidth(90).editable(true).build());
			textGridRowLayout.addColumn(new TextGridColumnLayout.Builder().grabHorizantalAccessSpace(true).editable(true).build());
			textGrid.addRow(textGridRowLayout, row);
		}
		
		
		container.getParent().addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				textGrid.setHeight(container.getParent().getBounds().height - 120);
				//textGrid.setHeight(container.getParent().getBounds().height - 133);
			}
		});
		
		textGrid.refresh();
	
		addGridRowSelectionListener();
		
		return container;
	}

	private void changeHeaderCheckboxSelection() {
		boolean allRowsSelected = true;
		for(Composite row:textGrid.getGrid()){
			if(((Button)((Composite)row).getChildren()[0]).isEnabled()){
				if(!((Button)row.getChildren()[0]).getSelection()){
					allRowsSelected = false;
					break;
				}
			}
		}
		
		if(allRowsSelected==true){
			headerCompositeCheckBox.setSelection(true);
		}else{
			headerCompositeCheckBox.setSelection(false);
		}
	}
	
	public void addGridRowSelectionListener(){
		for(Composite row: textGrid.getGrid()){
			
			//((Button)row.getChildren()[0]).
			
			((Button)row.getChildren()[0]).addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					super.widgetSelected(e);
					changeHeaderCheckboxSelection();
				}
			});
		}
	}
	
	private void addGridHeader() {
		List<String> header= new LinkedList<>();
		header.add("Name");
		header.add("Value");
		TextGridRowLayout textGridRowLayout = new TextGridRowLayout();
		textGridRowLayout.addColumn(new TextGridColumnLayout.Builder().columnWidth(90).enabled(false).build());
		textGridRowLayout.addColumn(new TextGridColumnLayout.Builder().grabHorizantalAccessSpace(true).enabled(false).build());
		textGrid.addHeaderRow(textGridRowLayout, header);
		
		
		((Button)textGrid.getHeaderComposite().getChildren()[0]).addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				super.widgetSelected(e);
				
				if(((Button)textGrid.getHeaderComposite().getChildren()[0]).getSelection()){
					textGrid.selectAllRows();
				}else{
					textGrid.clearSelections();
				}
			}
			
		});
		
		headerCompositeCheckBox = ((Button)textGrid.getHeaderComposite().getChildren()[0]);
	}

	private GridData getGridControlButtonLayout() {
		GridData gridControlButtonLayout = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gridControlButtonLayout.widthHint = 21;
		gridControlButtonLayout.heightHint = 19;
		return gridControlButtonLayout;
	}
	
	@Override
	protected void okPressed() {
		boolean error=false;
		textGrid.clearSelections();
		
		ParameterFileManager parameterFileManager = new ParameterFileManager(getComponentCanvas().getParameterFile());
		Map<String,String> dataMap = new LinkedHashMap<>();
		int rowId=0;
		for(List<String> row: textGrid.getData()){
			dataMap.put(row.get(0), row.get(1));
			if(row.get(0) == null || row.get(0).equals("")){
				textGrid.selectRow(rowId);
				error=true;
			}
			rowId++;
		}
		if(error == false){
			parameterFileManager.storeParameters(dataMap);
			super.okPressed();
		}else{
			MessageBox messageBox = new MessageBox(new Shell(), SWT.ICON_ERROR | SWT.OK | SWT.CANCEL );
	        
	        messageBox.setText("Error");
	        messageBox.setMessage("Parameter name can not be blank..please correct selected rows");
	        int buttonID = messageBox.open();
	        switch(buttonID) {
	          case SWT.OK:
	        	  runGraph = true;
	            break;
	          case SWT.CANCEL:
	        	super.okPressed();
	            break;
	        }
		}		
	}

	public boolean canRunGraph(){
		return runGraph;
	}
	
	private DefaultGEFCanvas getComponentCanvas() {		
		if(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() instanceof DefaultGEFCanvas)
			return (DefaultGEFCanvas) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		else
			return null;
	}
	
	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 423);
	}
}
