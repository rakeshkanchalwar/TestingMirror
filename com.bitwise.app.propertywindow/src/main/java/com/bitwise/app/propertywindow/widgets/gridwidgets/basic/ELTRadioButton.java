package com.bitwise.app.propertywindow.widgets.gridwidgets.basic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

// TODO: Auto-generated Javadoc
/**
 * The Class ELTRadioButton.
 * 
 * @author Bitwise
 */
public class ELTRadioButton extends AbstractELTWidget{

	private Button defaultELTButton;
	private String buttonText="Button";
	private boolean grabExcessSpace=false;
	
	/**
	 * Instantiates a new ELT radio button.
	 * 
	 * @param buttonText
	 *            the button text
	 */
	public ELTRadioButton(String buttonText){
		this.buttonText = buttonText;
	}
	
	@Override
	public void attachWidget(Composite container) {
		defaultELTButton = new Button(container, SWT.RADIO);
		GridData gd_defaultELTButton = new GridData(SWT.FILL, SWT.FILL, grabExcessSpace, false, 1, 1);
		defaultELTButton.setLayoutData(gd_defaultELTButton);
		defaultELTButton.setText(buttonText);
		
		widget = defaultELTButton;
	}
	
	/**
	 * Grab excess horizontal space.
	 * 
	 * @param grabExcessSpace
	 *            the grab excess space
	 * @return the ELT radio button
	 */
	public ELTRadioButton grabExcessHorizontalSpace(boolean grabExcessSpace){
		this.grabExcessSpace = grabExcessSpace;
		return this;
	}
	
	/**
	 * Visible.
	 * 
	 * @param visiblity
	 *            the visiblity
	 */
	public void visible(boolean visiblity){
		defaultELTButton.setVisible(visiblity);
	}

}
