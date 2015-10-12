package com.bitwise.app.propertywindow.widgets.gridwidgets.basic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

public class ELTDefaultCombo extends AbstractELTWidget{
	private Combo defaultELTcom;
	private int textboxWidth=65;
	private String[] defaultTextMessage;
	private boolean grabExcessSpace = true;
	
	@Override
	public void attachWidget(Composite container) {
		defaultELTcom = new Combo(container,SWT.READ_ONLY);
		defaultELTcom.setItems(defaultTextMessage);
		//defaultELTcom.setItems(new String[] {"True","false"});
		//defaultELTcom.setItem(0, "");
		GridData gd_defaultELTTextBox = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_defaultELTTextBox.widthHint = textboxWidth;
		defaultELTcom.setLayoutData(gd_defaultELTTextBox);
		
		widget = defaultELTcom;
	}

	public ELTDefaultCombo defaultText(String[] defaultTextMessage){
		this.defaultTextMessage = defaultTextMessage;
		return this;
	}
	
	public ELTDefaultCombo grabExcessHorizontalSpace(boolean grabExcessSpace){
		this.grabExcessSpace = grabExcessSpace;
		return this;
	}
	
	public ELTDefaultCombo comboBoxWidth(int textboxWidth){
		this.textboxWidth = textboxWidth;
		return this;
	}
}
