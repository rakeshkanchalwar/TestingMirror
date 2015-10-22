package com.bitwise.app.propertywindow.propertydialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

// TODO: Auto-generated Javadoc
/**
 * 
 * @author Bitwise
 * Sep 22, 2015
 * 
 */

public class PropertyDialogButtonBar {
	private Button okButton;
	private Button applyButton;
	private Button cancelButton;
	
	
	/**
	 * Instantiates a new property dialog button bar.
	 * 
	 * @param composite
	 *            the composite
	 */
	public PropertyDialogButtonBar(Composite composite){
		okButton = new Button(composite, SWT.PUSH);
		applyButton = new Button(composite, SWT.PUSH);
		cancelButton = new Button(composite, SWT.PUSH);
	}
	
	/**
	 * Sets the property dialog button bar.
	 * 
	 * @param okButton
	 *            the ok button
	 * @param applyButton
	 *            the apply button
	 * @param cancelButton
	 *            the cancel button
	 */
	public void setPropertyDialogButtonBar(Button okButton,Button applyButton,Button cancelButton){
		disposePropertyDialogButtonBar();
		this.okButton=okButton;
		if(applyButton != null)
		this.applyButton=applyButton;
		this.cancelButton=cancelButton;
	}

	private void disposePropertyDialogButtonBar() {
		this.okButton.dispose();
		this.applyButton.dispose();
		this.cancelButton.dispose();
	}

	/**
	 * Enable ok button.
	 * 
	 * @param status
	 *            the status
	 */
	public void enableOKButton(boolean status){
		okButton.setEnabled(status);
	}
	
	/**
	 * Enable apply button.
	 * 
	 * @param status
	 *            the status
	 */
	public void enableApplyButton(boolean status){
		applyButton.setEnabled(status);
	}
	
	/**
	 * Enable cancel button.
	 * 
	 * @param status
	 *            the status
	 */
	public void enableCancelButton(boolean status){
		cancelButton.setEnabled(status);
	}
	
	public boolean isApplyEnabled(){
		return applyButton.isEnabled();
	}
}
