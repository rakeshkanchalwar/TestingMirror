package com.bitwise.app.propertywindow.widgets.listeners;


import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.widgets.FileDialog;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;

public class ELTFileDialogSelectionListener implements IELTListener{

	Shell shell;
	@Override
	public int getListenerType() {
		return SWT.Selection;
	}

	@Override
	public Listener getListener(final PropertyDialogButtonBar propertyDialogButtonBar,
			ListenerHelper helpers, final Widget... widgets) {
		final Button aaa = ((Button)widgets[0]);
		aaa.getShell();
		
		Listener listener=new Listener() {
			@Override
			public void handleEvent(Event event) {
				if(event.type==SWT.Selection){
				FileDialog filedialog=new FileDialog(aaa.getShell(),SWT.None);
				String path=filedialog.open();
				if(path!=null){
					File file= new File(path);
					((Text)widgets[1]).setText(file.getAbsolutePath());
					propertyDialogButtonBar.enableApplyButton(true);
				} 
			}
			}
		};
		return listener;
	}

}
