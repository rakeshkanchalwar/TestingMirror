package com.bitwise.app.propertywindow.messagebox;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.bitwise.app.propertywindow.messages.Messages;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 24, 2015
 * 
 */

public class ConfirmCancelMessageBox {
	private Composite container;
	private MessageBox messageBox;
	
	public ConfirmCancelMessageBox(Composite container){
		this.container =container;
		createMessageBox();
	}
	
	private void createMessageBox(){
		Shell shell=container.getShell();
		int style = SWT.APPLICATION_MODAL | SWT.OK | SWT.CANCEL;
		messageBox = new MessageBox(shell,style);
		
		messageBox.setText("Information"); //$NON-NLS-1$
		messageBox.setMessage(Messages.MessageBeforeClosingWindow);
	}
	
	public MessageBox getMessageBox(){
		return messageBox;
	}
}
