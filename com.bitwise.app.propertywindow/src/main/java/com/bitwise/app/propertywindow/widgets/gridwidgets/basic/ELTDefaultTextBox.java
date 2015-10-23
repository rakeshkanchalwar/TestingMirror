package com.bitwise.app.propertywindow.widgets.gridwidgets.basic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

// TODO: Auto-generated Javadoc
/**
 * 
 * @author Bitwise
 * Sep 18, 2015
 * 
 */

public class ELTDefaultTextBox extends AbstractELTWidget{
	
	private Text defaultELTTextBox;
	private int textboxWidth=100;
	private String defaultTextMessage = "";
	private boolean grabExcessSpace = true;
		
	/**
	 * Instantiates a new ELT default text box.
	 */
	public ELTDefaultTextBox(){}
		
	@Override
	public void attachWidget(Composite container) {
		defaultELTTextBox = new Text(container, SWT.BORDER);
		GridData gd_defaultELTTextBox = new GridData(SWT.FILL, SWT.FILL, grabExcessSpace, false, 1, 1);
		gd_defaultELTTextBox.widthHint = textboxWidth;
		defaultELTTextBox.setLayoutData(gd_defaultELTTextBox);
		defaultELTTextBox.setText(defaultTextMessage);
		
		widget = defaultELTTextBox;
	}
	
	/**
	 * Text box width.
	 * 
	 * @param textboxWidth
	 *            the textbox width
	 * @return the ELT default text box
	 */
	public ELTDefaultTextBox textBoxWidth(int textboxWidth){
		this.textboxWidth = textboxWidth;
		return this;
	}
	
	/**
	 * Default text.
	 * 
	 * @param defaultTextMessage
	 *            the default text message
	 * @return the ELT default text box
	 */
	public ELTDefaultTextBox defaultText(String defaultTextMessage){
		this.defaultTextMessage = defaultTextMessage;
		return this;
	}
	
	/**
	 * Grab excess horizontal space.
	 * 
	 * @param grabExcessSpace
	 *            the grab excess space
	 * @return the ELT default text box
	 */
	public ELTDefaultTextBox grabExcessHorizontalSpace(boolean grabExcessSpace){
		this.grabExcessSpace = grabExcessSpace;
		return this;
	}
	
	/**
	 * Visibility.
	 * 
	 * @param visible
	 *            the visible
	 * @return the ELT default text box
	 */
	public ELTDefaultTextBox visibility(boolean visible){ 
		defaultELTTextBox.setVisible(visible);
		return this;
	}
	
	/**
	 * Sets the enabled.
	 * 
	 * @param enabled
	 *            the enabled
	 * @return the ELT default text box
	 */
	public ELTDefaultTextBox setEnabled(boolean enabled){ 
		defaultELTTextBox.setEnabled(enabled);
		return this;
	}
}
