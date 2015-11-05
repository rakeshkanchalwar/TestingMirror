package com.bitwise.app.parametergrid.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.ui.forms.widgets.ColumnLayoutData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class TempParameterGridDialog extends Dialog {
	private Composite composite_1;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public TempParameterGridDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.CLOSE | SWT.RESIZE);
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
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new ColumnLayout());
		ColumnLayoutData cld_composite = new ColumnLayoutData();
		cld_composite.heightHint = 30;
		composite.setLayoutData(cld_composite);
				
		final ScrolledComposite scrolledComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		final ColumnLayoutData cld_scrolledComposite = new ColumnLayoutData();
		cld_scrolledComposite.heightHint = 267;
		scrolledComposite.setLayoutData(cld_scrolledComposite);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);
		
		composite_1 = new Composite(scrolledComposite, SWT.NONE);
		ColumnLayout cl_composite_1 = new ColumnLayout();
		cl_composite_1.maxNumColumns = 1;
		composite_1.setLayout(cl_composite_1);
		
		Button btnAdd = new Button(composite, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Text tempText = addRow(composite_1);
				composite_1.setBounds(composite_1.getBounds().x, composite_1.getBounds().y, composite_1.computeSize(SWT.DEFAULT, SWT.DEFAULT).x, composite_1.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
				scrolledComposite.setMinSize(composite_1.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				scrolledComposite.showControl(tempText);
				tempText.setFocus();
			}
		});
		btnAdd.setText("Add");
		
		
		container.getParent().addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				cld_scrolledComposite.heightHint = container.getParent().getBounds().height - 120;
			}
		});
		
		addRow(composite_1);
		addRow(composite_1);
		addRow(composite_1);
		addRow(composite_1);
		addRow(composite_1);
		
		addRow(composite_1);
		addRow(composite_1);
		addRow(composite_1);
		addRow(composite_1);
		addRow(composite_1);
		/*addRow(container);
		addRow(container);
		addRow(container);*/
				
		/*Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnNewButton.setText("+");*/
		
		scrolledComposite.setContent(composite_1);
		scrolledComposite.setMinSize(composite_1.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				
		return container;
	}
	
	
	
	private Text addRow(Composite container){
		
		Composite composite = new Composite(container, SWT.NONE);
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.horizontalSpacing = 1;
		gl_composite.marginWidth = 1;
		gl_composite.marginHeight = 0;
		gl_composite.verticalSpacing = 1;
		composite.setLayout(gl_composite);
		
		Text text = new Text(composite, SWT.BORDER);		
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text.widthHint = 90;
		text.setLayoutData(gd_text);
		Text text_1 = new Text(composite, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		return text_1;
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
