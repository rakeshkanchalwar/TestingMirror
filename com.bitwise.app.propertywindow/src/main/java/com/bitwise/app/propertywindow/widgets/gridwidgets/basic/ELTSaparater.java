package com.bitwise.app.propertywindow.widgets.gridwidgets.basic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

// TODO: Auto-generated Javadoc
/**
 * 
 * @author Bitwise
 * Sep 28, 2015
 * 
 */

public class ELTSaparater extends AbstractELTWidget{

	private Label label;
	private boolean visible = false;
	private int lableWidth=75;
	private boolean grabExcessSpace = true;
	
	@Override
	public void attachWidget(Composite container) {
		// TODO Auto-generated method stub
		label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gd_defaultELTLable = new GridData(SWT.LEFT, SWT.CENTER, grabExcessSpace, false, 1, 1);
		gd_defaultELTLable.widthHint = lableWidth;
		label.setLayoutData(gd_defaultELTLable);	
		label.setVisible(visible);
		
		widget = label;
	}

	/**
	 * Lable width.
	 * 
	 * @param lableWidth
	 *            the lable width
	 * @return the ELT saparater
	 */
	public ELTSaparater lableWidth(int lableWidth){
		this.lableWidth = lableWidth;
		return this;
	}
	
	/**
	 * Grab excess horizontal space.
	 * 
	 * @param grabExcessSpace
	 *            the grab excess space
	 * @return the ELT saparater
	 */
	public ELTSaparater grabExcessHorizontalSpace(boolean grabExcessSpace){
		this.grabExcessSpace = grabExcessSpace;
		return this;
	}
	
	/**
	 * Visibility.
	 * 
	 * @param visible
	 *            the visible
	 * @return the ELT saparater
	 */
	public ELTSaparater visibility(boolean visible){ 
		this.visible =visible;
		return this;
	}
}
