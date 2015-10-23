package com.bitwise.app.tooltip.launcher;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.ColumnLayout;

import com.bitwise.app.tooltip.creater.ComponentTooltipWithStatusBarCreator;
import com.bitwise.app.tooltip.creater.ComponentTooltipWithToolBarCreator;
import com.bitwise.app.tooltip.creater.IComponentTooltipCreator;
import com.bitwise.app.tooltip.informationprovider.ComponentInformationProvider;
import com.bitwise.app.tooltip.informationprovider.IInformationProvider;
import com.bitwise.app.tooltip.installer.TooltipInstaller;

public class Main {

	Main(Shell shell) throws Exception {
		Composite container = new Composite(shell, SWT.NONE);
		container.setLayout(new ColumnLayout());
		
		final Button button = new Button(container, SWT.NONE);
		button.setText("New Button");		
		hookTooltips(button);
		
	}

	private void hookTooltips(Button button) {
		// Create an information provider for our table viewer
		LinkedHashMap<String,String> data = new LinkedHashMap<>();
		data.put("Name", "Hello World");
		
		IInformationProvider informationProvider = new ComponentInformationProvider(data,button);

		// Our table viewer contains elements of type String, Person and URL.
		// Strings are handled by default. For Person and URL we need custom control creators.
		List<IComponentTooltipCreator> informationControlCreators = new ArrayList<>();
		//informationControlCreators.add(new PersonInformationControlCreator());
		informationControlCreators.add(new ComponentTooltipWithStatusBarCreator());
		informationControlCreators.add(new ComponentTooltipWithToolBarCreator());

		// Install tooltips		
		TooltipInstaller.install(button, informationProvider, informationControlCreators, false);
	}

	public static void main(String[] args) throws Exception {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setBounds(200, 200, 500, 200);
		new Main(shell);
		shell.open();

		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) {
				display.sleep();
			}
		}

		display.dispose();
	}
}