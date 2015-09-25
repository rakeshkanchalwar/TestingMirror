package com.bitwise.app.propertywindow.widgets.gridwidgets.basic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

public class ELTDefaultCheckBox extends AbstractELTWidget {
	private Button defaultELTCheckBox;

	String checkBoxLable = "Checkbox";

	@Override
	public void attachWidget(Composite container) {
		defaultELTCheckBox = new Button(container, SWT.CHECK);
		defaultELTCheckBox.setText(checkBoxLable);
		widget=defaultELTCheckBox; 
	}

	public ELTDefaultCheckBox(String checkBoxLable) {
		super();
		this.checkBoxLable = checkBoxLable;
	}

}
