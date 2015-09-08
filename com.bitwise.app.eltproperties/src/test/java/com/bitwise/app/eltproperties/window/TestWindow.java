package com.bitwise.app.eltproperties.window;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.bitwise.app.eltproperties.property.IPropertyTreeBuilder;
import com.bitwise.app.eltproperties.property.Property;
import com.bitwise.app.eltproperties.property.PropertyTreeBuilder;
import com.bitwise.app.eltproperties.propertydialog.PropertyDialog;
import com.bitwise.app.eltproperties.testdata.ComponentModel;
import com.bitwise.app.eltproperties.testdata.PropertyStore;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 07, 2015
 * 
 */

public class TestWindow extends ApplicationWindow {

	LinkedHashMap<String, Object> inputCompProps;
	
	/**
	 * Create the application window.
	 */
	public TestWindow() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		
		ComponentModel componentModel = new ComponentModel(); 
		inputCompProps = componentModel.getProperties("Input");
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Hello");
				//PropertyWindow propertyWindow = new PropertyWindow(e.display.getActiveShell());
				//propertyWindow.open();
				
				//CopyOfPropertyWindowPOC2 copyOfPropertyWindowPOC2 = new CopyOfPropertyWindowPOC2(e.display.getActiveShell());
				//copyOfPropertyWindowPOC2.open();
				PropertyStore propertyStore = new PropertyStore();
				
				ArrayList<Property> inputComponentProperties = propertyStore.getProperties("Input");
				
				IPropertyTreeBuilder propertyTreeBuilder = new PropertyTreeBuilder(inputComponentProperties);
				
				
				
				PropertyDialog testDialog = new PropertyDialog(e.display.getActiveShell(),propertyTreeBuilder.getPropertyTree(),inputCompProps);
				
				testDialog.open();
				
				System.out.println("In Test Window: " + inputCompProps);
				
			}
			
		});
		btnNewButton.setBounds(0, 0, 75, 25);
		btnNewButton.setText("New Button");

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			TestWindow window = new TestWindow();
			window.setBlockOnOpen(true);
			
			window.open();
			Display.getCurrent().dispose();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("New Application");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
}
