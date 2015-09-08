package com.bitwise.app.eltproperties.propertydialog;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.ColumnLayoutData;

import com.bitwise.app.eltproperties.property.IPropertyTreeBuilder;
import com.bitwise.app.eltproperties.property.Property;
import com.bitwise.app.eltproperties.property.PropertyTreeBuilder;
import com.bitwise.app.eltproperties.testdata.PropertyStore;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 07, 2015
 * 
 */

public class PropertyDialog extends Dialog {
	private Composite container;

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
	@Override
	protected Control createDialogArea(Composite parent) {
		
		container = (Composite) super.createDialogArea(parent);
		
		ColumnLayout cl_container = new ColumnLayout();
		cl_container.maxNumColumns = 1;
		container.setLayout(cl_container);
		
		
		PropertyStore propertyStore = new PropertyStore();
		
		ArrayList<Property> inputComponentProperties = propertyStore.getProperties("Input");
		
		IPropertyTreeBuilder propertyTreeBuilder = new PropertyTreeBuilder(inputComponentProperties);
		
		PropertyDialogBuilder propertyDialogBuilder = new PropertyDialogBuilder(container,propertyTreeBuilder.getPropertyTree());
		
		propertyDialogBuilder.buildPropertyWindow();
		
		
		
		
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
		//initDataBindings();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(479, 505);
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		return bindingContext;
	}
}
