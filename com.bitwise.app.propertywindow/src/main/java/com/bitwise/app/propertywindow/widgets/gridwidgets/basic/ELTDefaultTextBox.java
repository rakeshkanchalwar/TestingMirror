package com.bitwise.app.propertywindow.widgets.gridwidgets.basic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 18, 2015
 * 
 */

public class ELTDefaultTextBox extends AbstractELTWidget{
	
	private Text defaultELTTextBox;
	private int textboxWidth=300;
	private String defaultTextMessage = "";
	private boolean grabExcessSpace = true;
		
	public ELTDefaultTextBox(){}
		
	@Override
	public void attachWidget(Composite container) {
		defaultELTTextBox = new Text(container, SWT.BORDER);
		GridData gd_defaultELTTextBox = new GridData(SWT.FILL, SWT.CENTER, grabExcessSpace, false, 1, 1);
		gd_defaultELTTextBox.widthHint = textboxWidth;
		defaultELTTextBox.setLayoutData(gd_defaultELTTextBox);
		defaultELTTextBox.setText(defaultTextMessage);
		
		widget = defaultELTTextBox;
	}
	
	public ELTDefaultTextBox textBoxWidth(int textboxWidth){
		this.textboxWidth = textboxWidth;
		return this;
	}
	
	public ELTDefaultTextBox defaultText(String defaultTextMessage){
		this.defaultTextMessage = defaultTextMessage;
		return this;
	}
	
	public ELTDefaultTextBox grabExcessHorizontalSpace(boolean grabExcessSpace){
		this.grabExcessSpace = grabExcessSpace;
		return this;
	}
	
	public ELTDefaultTextBox visibility(boolean visible){ 
		defaultELTTextBox.setVisible(visible);
		return this;
	}
}
