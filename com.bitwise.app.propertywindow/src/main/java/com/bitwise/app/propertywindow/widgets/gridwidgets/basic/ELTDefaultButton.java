package com.bitwise.app.propertywindow.widgets.gridwidgets.basic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.bitwise.app.propertywindow.widgets.listeners.IELTListener;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 18, 2015
 * 
 */

public class ELTDefaultButton extends IELTWidget{
	private Button defaultELTButton;
	
	private String buttonText="Button";
	private int buttonWidth=80;
	
	private ELTDefaultButton(){}
	
	public ELTDefaultButton(String buttonText){
		this.buttonText = buttonText;
	}
	
	@Override
	public void attachWidget(Composite container) {
		defaultELTButton = new Button(container, SWT.CENTER);
		GridData gd_defaultELTButton = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_defaultELTButton.widthHint = buttonWidth;
		defaultELTButton.setLayoutData(gd_defaultELTButton);
		defaultELTButton.setText(buttonText);
		
		widget = defaultELTButton;
	}
		
	public ELTDefaultButton buttonWidth(int buttonWidth){
		this.buttonWidth = buttonWidth;
		return this;
	}
	
}
