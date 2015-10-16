package com.bitwise.app.propertywindow.widgets.gridwidgets.basic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class ELTRadioButton extends AbstractELTWidget{

	private Button defaultELTButton;
	private String buttonText="Button";
	private boolean grabExcessSpace=false;
	
	public ELTRadioButton(String buttonText){
		this.buttonText = buttonText;
	}
	
	@Override
	public void attachWidget(Composite container) {
		defaultELTButton = new Button(container, SWT.RADIO);
		GridData gd_defaultELTButton = new GridData(SWT.FILL, SWT.CENTER, grabExcessSpace, false, 1, 1);
		defaultELTButton.setLayoutData(gd_defaultELTButton);
		defaultELTButton.setText(buttonText);
		
		widget = defaultELTButton;
	}
	
	public ELTRadioButton grabExcessHorizontalSpace(boolean grabExcessSpace){
		this.grabExcessSpace = grabExcessSpace;
		return this;
	}
	
	public void visible(boolean visiblity){
		defaultELTButton.setVisible(visiblity);
	}

}
