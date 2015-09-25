package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import org.eclipse.swt.widgets.Text;

import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultTextBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;
import com.bitwise.app.propertywindow.widgets.listeners.ELTVerifyComponentNameListener;

public class ELTComponentNameWidget extends AbstractWidget {

	private Text text;
	private String oldName = "oldName";
	private String propertyName;

	private String newName = "newName";
	
	private ELTVerifyComponentNameListener listener;

	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {
		
		if (super.names != null) {
			for (String name : super.names) {
				System.out.println(name);
			}
		}
		ListenerFactory listenerFactory = new ListenerFactory();

		System.out.println("IN ELTComponentNameWidget.attachToPropertySubGroup()");

		ELTDefaultSubgroupComposite eltDefaultSubgroupComposite = new ELTDefaultSubgroupComposite(
				container.getContainerControl());
		eltDefaultSubgroupComposite.createContainerWidget();

		AbstractELTWidget eltDefaultLable = new ELTDefaultLable("Name");
		eltDefaultSubgroupComposite.attachWidget(eltDefaultLable);

		AbstractELTWidget eltDefaultTextBox = new ELTDefaultTextBox().defaultText("Hello")
				.grabExcessHorizontalSpace(true).textBoxWidth(200);
		eltDefaultSubgroupComposite.attachWidget(eltDefaultTextBox);

		text = (Text) eltDefaultTextBox.getSWTWidgetControl();

		try {
			listener = (ELTVerifyComponentNameListener)listenerFactory.getListener("ELTVerifyComponentNameListener");
			listener.setNames(super.names);
			eltDefaultTextBox.attachListener(listener,
					propertyDialogButtonBar,  null,eltDefaultTextBox.getSWTWidgetControl());
		/*	eltDefaultTextBox.attachListener(listenerFactory.getListener("MyCustomWidgetTextChange"),
					propertyDialogButtonBar,  null,eltDefaultTextBox.getSWTWidgetControl());*/
			System.out.println("ELTComponentNameWidget: added the listener");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void setProperties(String propertyName, Object properties) {
		System.out.println("ELTComponentNameWidget.setProperties():-propertyName: " + propertyName + ", properties: "
				+ properties);
		this.propertyName = propertyName;
		this.oldName = (String) properties;
		listener.setOldName(oldName);
		text.setText(oldName);

	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		newName = text.getText().trim();
		LinkedHashMap<String, Object> property = new LinkedHashMap<>();
		if (newName != null && newName != "" && isUniqueCompName(newName)) {
			property.put(propertyName, newName);
			super.names.remove(oldName);
			super.names.add(newName);
			oldName = newName;
		} else {
			// old name already should be there in the names arraylist
			property.put(propertyName, oldName);
		}

		return property;
	}

	@Override
	public void setComponentName(String componentName) {
		// TODO Auto-generated method stub

	}

	private boolean isUniqueCompName(String componentName) {
		componentName = componentName.trim();
		boolean result = true;

		for (String cname : super.names) {
			if (cname.equalsIgnoreCase(componentName)) {
				result = false;
				break;
			}

		}
		System.out.println("isUniqueCompName: result: " + result);

		return result;
	}

}
