package com.bitwise.app.propertywindow.widgets.gridwidgets.basic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.bitwise.app.propertywindow.widgets.listeners.IELTListener;

public class ELTDefaultLable extends IELTWidget{
	
	Label defaultELTLable;
		
	private int lableWidth=50;
	private String lableText="Lable : ";
	
	private ELTDefaultLable(){}
	
	public ELTDefaultLable(String lableText){
		this.lableText = lableText;
	}
	
	@Override
	public void attachWidget(Composite container) {
		defaultELTLable = new Label(container, SWT.NONE);
		GridData gd_defaultELTLable = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_defaultELTLable.widthHint = lableWidth;
		defaultELTLable.setLayoutData(gd_defaultELTLable);		
		defaultELTLable.setText(lableText);
		
		widget = defaultELTLable;
	}

	public ELTDefaultLable lableWidth(int lableWidth){
		this.lableWidth = lableWidth;
		return this;
	}
	
}
