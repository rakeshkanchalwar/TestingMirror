package com.bitwise.app.parametergrid.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.ColumnLayoutData;

import com.bitwise.app.common.interfaces.parametergrid.DefaultGEFCanvas;
import com.bitwise.app.common.interfaces.tooltip.ComponentCanvas;
import com.bitwise.app.parametergrid.textgridwidget.TextGrid;
import com.bitwise.app.parametergrid.textgridwidget.columns.TextGridColumnDataLayout;
import com.bitwise.app.parametergrid.textgridwidget.columns.TextGridColumns;
import com.bitwise.app.parametergrid.utils.ParameterFileManager;

public class ParameterGridDialog extends Dialog {
	
	private TextGrid textGrid;
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public ParameterGridDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.CLOSE | SWT.RESIZE | SWT.TITLE | SWT.WRAP | SWT.APPLICATION_MODAL);
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
		composite.setLayout(new ColumnLayout());
		ColumnLayoutData cld_composite = new ColumnLayoutData();
		cld_composite.heightHint = 30;
		composite.setLayoutData(cld_composite);
		
		final TextGridColumns textGridColumns = new TextGridColumns();
		textGridColumns.addColumn(new TextGridColumnDataLayout.Builder().columnWidth(90).editable(false).build());
		textGridColumns.addColumn(new TextGridColumnDataLayout.Builder().grabHorizantalAccessSpace(true).build());
		textGrid = new TextGrid();
		
		Button btnAdd = new Button(composite, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textGridColumns.resetColumnData(0, new TextGridColumnDataLayout.Builder().columnWidth(90).editable(true).build());
				textGrid.addRow();				
				textGrid.refresh();
				textGrid.scrollToLastRow();
			}
		});
		
		
		btnAdd.setText("Add");
		ParameterFileManager parameterFileManager = new ParameterFileManager(getComponentCanvas().getParameterFile());
		Map<String, String> parameterMap = parameterFileManager.getParameterMap();
		List<String[]> gridData =  new ArrayList<>();
		for(String key: parameterMap.keySet()){
			String[] rowData = {key,parameterMap.get(key)};
			gridData.add(rowData);
		}
		
		//textGrid.add
		
		textGrid.attachGrid(container, parameterMap.keySet().size(), textGridColumns);
		
		/*String[] row1Data = {"aaa","bbb"};
		String[] row2Data = {"ccc","ddd"};
		String[] row3Data = {"eee","fff"};
		gridData.add(row1Data);
		gridData.add(row2Data);
		gridData.add(row3Data);*/
		
		try {
			textGrid.setData(gridData);
			
			System.out.println("++++ latest param list String-   " + getComponentCanvas().getLatestParameterList().toString());
			List<String> canvasParameterList = getComponentCanvas().getLatestParameterList();
			for(Composite gridrow: textGrid.getGrid()){
				//if(((Text)gridrow.getChildren()[0]).getText())
				if(!canvasParameterList.contains(((Text)gridrow.getChildren()[0]).getText()))
					((Text)gridrow.getChildren()[0]).setEditable(true);
			}
			
			
		} catch (Exception indexOutOfBoundsException) {
			MessageDialog.openError(new Shell(), "Error", "Invaild parameter grid data -\n"+indexOutOfBoundsException.getMessage());
			getShell().dispose();
		}
		
		container.getParent().addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				textGrid.setHeight(container.getParent().getBounds().height - 120);
			}
		});
		
		textGrid.refresh();
		return container;
	}
	
	@Override
	protected void okPressed() {
		ParameterFileManager parameterFileManager = new ParameterFileManager(getComponentCanvas().getParameterFile());
		//System.out.println("+++ This is the data: " + textGrid.getData().toString());
		Map<String,String> dataMap = new LinkedHashMap<>();
		for(String[] row: textGrid.getData()){
			dataMap.put(row[0], row[1]);
		}
		
		parameterFileManager.storeParameters(dataMap);
		//System.out.println("+++ This is the data: " + dataMap.toString());
		super.okPressed();
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
