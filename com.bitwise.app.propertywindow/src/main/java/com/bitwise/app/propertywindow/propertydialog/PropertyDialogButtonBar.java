package com.bitwise.app.propertywindow.propertydialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 22, 2015
 * 
 */

public class PropertyDialogButtonBar {
	private Button okButton;
	private Button applyButton;
	private Button cancelButton;
	
	public PropertyDialogButtonBar(Composite composite){
		okButton = new Button(composite, SWT.PUSH);
		applyButton = new Button(composite, SWT.PUSH);
		cancelButton = new Button(composite, SWT.PUSH);
	}
	
	public void setPropertyDialogButtonBar(Button okButton,Button applyButton,Button cancelButton){
		disposePropertyDialogButtonBar();
		this.okButton=okButton;
		this.applyButton=applyButton;
		this.cancelButton=cancelButton;
	}

	private void disposePropertyDialogButtonBar() {
		this.okButton.dispose();
		this.applyButton.dispose();
		this.cancelButton.dispose();
	}

	public void enableOKButton(boolean status){
		okButton.setEnabled(status);
	}
	
	public void enableApplyButton(boolean status){
		applyButton.setEnabled(status);
	}
	
	public void enableCancelButton(boolean status){
		cancelButton.setEnabled(status);
	}
}
