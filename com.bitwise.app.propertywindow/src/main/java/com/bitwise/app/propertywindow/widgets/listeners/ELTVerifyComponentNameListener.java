package com.bitwise.app.propertywindow.widgets.listeners;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;

public class ELTVerifyComponentNameListener implements IELTListener {
	
	private ArrayList<String> names;
	private String oldName;

	@Override
	public int getListenerType() {
		// TODO Auto-generated method stub
		return SWT.Verify;
	}

	@Override
	public Listener getListener(final PropertyDialogButtonBar propertyDialogButtonBar, Widget... widgets) {
		Widget[] widgetList = widgets;
		final Text text = (Text) widgetList[0];

		Listener listener = new Listener() {

			@Override
			public void handleEvent(Event e) {
				if (e.type == SWT.Verify) {

					String currentText = ((Text) e.widget).getText();
					String newName = (currentText.substring(0, e.start) + e.text + currentText.substring(e.end)).trim();

					// System.out.println("addVerifyListener(): current text: " + oldName + ", new text: " + newName);
					System.out.println("addVerifyListener():  new text: " + newName);
					if (newName == null || newName.equals("")) {
						// e.doit=false;
						Color listBackground = Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
						text.setBackground(listBackground);
						text.setToolTipText("Enter valid name");
						text.setMessage("Valid Name required");
						propertyDialogButtonBar.enableOKButton(false);

					} 
					  else if (!newName.equalsIgnoreCase(oldName) && !isUniqueCompName(newName)) { Color listBackground
					  = Display.getDefault().getSystemColor(SWT.COLOR_GRAY); text.setBackground(listBackground);
					  text.setToolTipText("Enter unique name for component."); text.setMessage("Unique Name required");
					  propertyDialogButtonBar.enableOKButton(false);
					  
					  }
					 else {
						text.setBackground(null);
						text.setToolTipText("");
						text.setMessage("");
						propertyDialogButtonBar.enableOKButton(true);
					}
				}
			}
		};
		return listener;
	}

	public ArrayList<String> getNames() {
		return names;
	}

	public void setNames(ArrayList<String> names) {
		this.names = names;
	}
	
	private boolean isUniqueCompName(String componentName) {
		componentName = componentName.trim();
		boolean result = true;

		for (String cname : names) {
			if (cname.equalsIgnoreCase(componentName)) {
				result = false;
				break;
			}

		}
		System.out.println("isUniqueCompName: result: " + result);

		return result;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

}
