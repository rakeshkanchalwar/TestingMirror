package com.bitwise.app.eltproperties.propertydialog;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.ColumnLayout;

import com.bitwise.app.eltproperties.Messages;
import com.bitwise.app.eltproperties.property.Property;
import com.bitwise.app.eltproperties.widgets.AbstractELTWidget;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 07, 2015
 * 
 */

public class PropertyDialog extends Dialog {
	private Composite container;
	private LinkedHashMap<String, LinkedHashMap<String, ArrayList<Property>>> propertyTree;
	private LinkedHashMap<String, Object> componentProperties;
	private LinkedHashMap<String, Object> currentProperties;
	private  LinkedHashMap<String, Object> appliedProperties;
	PropertyDialogBuilder propertyDialogBuilder;
	private ArrayList<String> names = new ArrayList<>();
	private Button okButton;
	private Button applyButton;
	private int checkIfMessageBoxAlreadyThere=0;
	/**
	 * Create the dialog.
	 * @param parentShell
	 * @param propertyTree 
	 * @param ComponentProperties 
	 */
	public PropertyDialog(Shell parentShell, LinkedHashMap<String, LinkedHashMap<String, ArrayList<Property>>> propertyTree, LinkedHashMap<String, Object> ComponentProperties, ArrayList<String> names) {		
		super(parentShell);
		this.propertyTree = propertyTree;
		this.componentProperties = ComponentProperties;
		appliedProperties=new LinkedHashMap<>();
		currentProperties=new LinkedHashMap<>();
		this.names=names;
		setShellStyle(SWT.CLOSE | SWT.TITLE | SWT.WRAP | SWT.APPLICATION_MODAL);
		super.setBlockOnOpen(true);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		
		container = (Composite) super.createDialogArea(parent);
		container.getShell().setText("Component Properties");
		ColumnLayout cl_container = new ColumnLayout();
		cl_container.maxNumColumns = 1;
		container.setLayout(cl_container);
		
		
		propertyDialogBuilder = new PropertyDialogBuilder(container,propertyTree,componentProperties,names);
		propertyDialogBuilder.buildPropertyWindow();
		
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		okButton=createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		applyButton=createButton(parent,IDialogConstants.OK_ID,
				"Apply", false);
		
		for(AbstractELTWidget eltWidget : propertyDialogBuilder.getELTWidgetList()){
			eltWidget.setOkButton(okButton);
		}
		
		//initDataBindings();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(512, 505);
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		return bindingContext;
	}

	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		whenOkButtonPressed();

		whenApplyButtonPressed();


	}

	private void whenApplyButtonPressed() {
		applyButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for(AbstractELTWidget eltWidget : propertyDialogBuilder.getELTWidgetList()){
					LinkedHashMap<String, Object> tempPropert = eltWidget.getProperties();
					for(String propName : tempPropert.keySet()){
						appliedProperties.put(propName, tempPropert.get(propName));
						
					}
				}
				componentProperties.putAll(appliedProperties);
				 	Shell shell=container.getShell();
				    int style = SWT.APPLICATION_MODAL | SWT.OK;
					MessageBox messageBox = new MessageBox(shell,style);
					messageBox.setText("Information"); //$NON-NLS-1$
					messageBox.setMessage(Messages.PropertyAppliedNotification);
					messageBox.open();
			}
			
		});
	}

	private void whenOkButtonPressed() {
		okButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e){
				for(AbstractELTWidget eltWidget : propertyDialogBuilder.getELTWidgetList()){
					LinkedHashMap<String, Object> tempPropert = eltWidget.getProperties();
					for(String propName : tempPropert.keySet()){
						componentProperties.put(propName, tempPropert.get(propName));
					}
				}
				checkIfMessageBoxAlreadyThere=1;
				close();
			}
		});
	}
	
	protected void cancelPressed() {
		for(AbstractELTWidget eltWidget : propertyDialogBuilder.getELTWidgetList()){
			LinkedHashMap<String, Object> tempPropert = eltWidget.getProperties();
			for(String propName : tempPropert.keySet()){
				currentProperties.put(propName, tempPropert.get(propName));
			}
		}
		if(!currentProperties.equals(appliedProperties))
		{
			MessageBox messageBox = getMessageBox();
			if(messageBox.open()==SWT.YES)
			{
				checkIfMessageBoxAlreadyThere=1;
				close();
			}
		}
		else
		{
			checkIfMessageBoxAlreadyThere=1;
			close();
		}
	}

	private MessageBox getMessageBox() {
		Shell shell=container.getShell();
		int style = SWT.APPLICATION_MODAL | SWT.YES | SWT.NO;
		MessageBox messageBox = new MessageBox(shell,style);
		messageBox.setText("Information"); //$NON-NLS-1$
		messageBox.setMessage(Messages.MessageBeforeClosingWindow);
		return messageBox;
	}
	public boolean close() {
		
		boolean returnValue = false;
		for(AbstractELTWidget eltWidget : propertyDialogBuilder.getELTWidgetList()){
			LinkedHashMap<String, Object> tempPropert = eltWidget.getProperties();
			for(String propName : tempPropert.keySet()){
				currentProperties.put(propName, tempPropert.get(propName));
			}
		}

		if(!currentProperties.equals(appliedProperties))
		{
			MessageBox messageBox = getMessageBox();
			if(checkIfMessageBoxAlreadyThere!=1)
			{
			if(messageBox.open()==SWT.YES)

		     returnValue = super.close();
			}
			else
				returnValue = super.close();
		}
		else
			returnValue=super.close();

		return returnValue;
	}
	
	
	public ArrayList<String> getNames() {
		return names;
	}

	public void setNames(ArrayList<String> names) {
		this.names = names;
	}

}
