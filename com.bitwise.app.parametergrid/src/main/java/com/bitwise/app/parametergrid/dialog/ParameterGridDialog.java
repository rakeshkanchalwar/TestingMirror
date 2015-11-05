package com.bitwise.app.parametergrid.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.ColumnLayoutData;

import com.bitwise.app.parametergrid.textgridwidget.TextGrid;
import com.bitwise.app.parametergrid.textgridwidget.columns.ColumnData;
import com.bitwise.app.parametergrid.textgridwidget.columns.TextGridColumns;

public class ParameterGridDialog extends Dialog {
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
		textGridColumns.addColumn(new ColumnData.Builder().columnWidth(90).editable(false).build());
		textGridColumns.addColumn(new ColumnData.Builder().grabHorizantalAccessSpace(true).build());
		final TextGrid textGrid = new TextGrid();
		
		Button btnAdd = new Button(composite, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textGridColumns.resetColumnData(0, new ColumnData.Builder().columnWidth(90).editable(true).build());
				textGrid.addRow();				
				textGrid.refresh();
				textGrid.scrollToLastRow();
			}
		});
		btnAdd.setText("Add");
		
		textGrid.attachGrid(container, 10, textGridColumns);
		
		container.getParent().addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				textGrid.setHeight(container.getParent().getBounds().height - 120);
			}
		});
		
		textGrid.refresh();
				
		return container;
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
