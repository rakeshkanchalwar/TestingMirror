package com.bitwise.app.eltproperties.widget;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.forms.widgets.ColumnLayoutData;
import org.eclipse.swt.custom.ScrolledComposite;

import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultButton;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultTextBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.IELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroup;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.IELTContainerWidget;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 18, 2015
 * 
 */

public class PropertyDialog extends Dialog {
	private Text text;
	private Text txtHello;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public PropertyDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.CLOSE | SWT.RESIZE | SWT.TITLE);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	protected Control createDialogArea(Composite parent) {
		
		ListenerFactory listenerFactory = new ListenerFactory();
		
		final Composite container = (Composite) super.createDialogArea(parent);
		ColumnLayout cl_container = new ColumnLayout();
		cl_container.maxNumColumns = 1;
		container.setLayout(cl_container);
		
		container.getShell().setMinimumSize(600, 500);
		container.getShell().setText("Property Dialog");
		
		
		
		TabFolder tabFolder = new TabFolder(container, SWT.NONE);
		final ColumnLayoutData cld_tabFolder = new ColumnLayoutData();
		cld_tabFolder.heightHint = 303;
		tabFolder.setLayoutData(cld_tabFolder);
		
		container.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				cld_tabFolder.heightHint = container.getBounds().height - 50;
			}
		});
		
		TabItem tbtmTab = new TabItem(tabFolder, SWT.NONE);
		tbtmTab.setText("Tab1");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setAlwaysShowScrollBars(true);
		tbtmTab.setControl(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		ColumnLayout cl_composite = new ColumnLayout();
		cl_composite.maxNumColumns = 1;
		composite.setLayout(cl_composite);
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		//------------------------------------------------------------------------
		
		IELTContainerWidget eltDefaultSubgroup= new ELTDefaultSubgroup(composite).subGroupName("Address");
		eltDefaultSubgroup.createContainerWidget();
		
		IELTWidget eltDefaultLable = new ELTDefaultLable("Addess :");
		eltDefaultSubgroup.attachWidget(eltDefaultLable);
		
		IELTWidget eltDefaultTextBox = new ELTDefaultTextBox().defaultText("Hello").grabExcessHorizontalSpace(true).textBoxWidth(200);
		eltDefaultSubgroup.attachWidget(eltDefaultTextBox);
		
		IELTWidget eltDefaultButton = new ELTDefaultButton("Submit");
		eltDefaultSubgroup.attachWidget(eltDefaultButton);
		try {
			eltDefaultButton.attachListener(listenerFactory.getListener("ELTHelloTestListener"));
			eltDefaultButton.attachListener(listenerFactory.getListener("ELTHiTestListener"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		
		createButton(parent, IDialogConstants.NO_ID,
				"Apply", false);
		
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		
		
	}

	/**
	 * Return the initial size of the dialog.
	 */
	protected Point getInitialSize() {
		return new Point(548, 429);
	}
}
