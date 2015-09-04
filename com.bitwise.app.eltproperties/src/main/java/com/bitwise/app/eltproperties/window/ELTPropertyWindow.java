package com.bitwise.app.eltproperties.window;

import java.util.ArrayList;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.bitwise.app.eltproperties.property.Property;

public class ELTPropertyWindow {
	
	private Shell shell;
	private Display display;
	ArrayList<Property> componentProperties;
	
	public ELTPropertyWindow(ArrayList<Property> componentProperties){
		this.componentProperties = componentProperties;
	}
	
	public void createBasicWindow(){
		display = new Display ();
		shell = new Shell(display);
		shell.setText("ELT Component Property sheet");
 
		shell.setLayout(new FillLayout());
	}
	
	public void addPropertyGroupsToPropertyWindow(){
		
	}
	
	public void open(){
		
		createBasicWindow();
		
		shell.open();
		 
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
}
