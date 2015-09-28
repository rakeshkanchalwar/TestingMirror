package com.bitwise.app.propertywindow.widgets.gridwidgets.basic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

public class ELTDefaultCheckBox extends AbstractELTWidget {
	private Button defaultELTCheckBox;
	private int checkBoxWidth = 75;
	private String checkBoxLable = "Checkbox";
	private boolean grabExcessSpace = false;

	@Override
	public void attachWidget(Composite container) {
		defaultELTCheckBox = new Button(container, SWT.CHECK);
		GridData gd_defaultELTCheckBox = new GridData(SWT.FILL, SWT.CENTER, grabExcessSpace, false, 1, 1);
		gd_defaultELTCheckBox.widthHint = checkBoxWidth;
		defaultELTCheckBox.setLayoutData(gd_defaultELTCheckBox);
		
		defaultELTCheckBox.setText(checkBoxLable);
		widget=defaultELTCheckBox; 
	}

	public ELTDefaultCheckBox(String checkBoxLable) {
		super();
		this.checkBoxLable = checkBoxLable;
	}

	public ELTDefaultCheckBox checkBoxLableWidth(int width){
		checkBoxWidth = width;
		return this;
	}
	
	public ELTDefaultCheckBox grabExcessHorizontalSpace(boolean grabExcessSpace){
		this.grabExcessSpace = grabExcessSpace;
		return this;
	}
}
