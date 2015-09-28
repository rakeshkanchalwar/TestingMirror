package com.bitwise.app.propertywindow.widgets.gridwidgets.basic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 18, 2015
 * 
 */

public class ELTDefaultButton extends AbstractELTWidget{
	private Button defaultELTButton;
	
	private String buttonText="Button";
	private int buttonWidth=80;
	private boolean grabExcessSpace=false;
	
	private ELTDefaultButton(){}
	
	public ELTDefaultButton(String buttonText){
		this.buttonText = buttonText;
	}
	
	@Override
	public void attachWidget(Composite container) {
		defaultELTButton = new Button(container, SWT.CENTER);
		GridData gd_defaultELTButton = new GridData(SWT.FILL, SWT.CENTER, grabExcessSpace, false, 1, 1);
		gd_defaultELTButton.widthHint = buttonWidth;
		defaultELTButton.setLayoutData(gd_defaultELTButton);
		defaultELTButton.setText(buttonText);
		
		widget = defaultELTButton;
	}
		
	public ELTDefaultButton buttonWidth(int buttonWidth){
		this.buttonWidth = buttonWidth;
		return this;
	}
	
	public ELTDefaultButton grabExcessHorizontalSpace(boolean grabExcessSpace){
		this.grabExcessSpace = grabExcessSpace;
		return this;
	}
	
}
