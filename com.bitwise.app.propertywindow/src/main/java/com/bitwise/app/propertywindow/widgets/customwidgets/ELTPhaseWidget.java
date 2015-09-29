package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.widgets.Text;

import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultTextBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper;
import com.bitwise.app.propertywindow.widgets.utility.WidgetUtility;

public class ELTPhaseWidget extends AbstractWidget {

	private Text textBox;
	private String properties;
	private String propertyName;
	private ControlDecoration txtDecorator;

	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {
		ListenerFactory listenerFactory = new ListenerFactory();
		
		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite = new ELTDefaultSubgroupComposite(container.getContainerControl());
		eltSuDefaultSubgroupComposite.createContainerWidget();
		
		AbstractELTWidget eltDefaultLable = new ELTDefaultLable("Phase ");
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultLable);
		
		AbstractELTWidget eltDefaultTextBox = new ELTDefaultTextBox().grabExcessHorizontalSpace(true);
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultTextBox);

		textBox = (Text) eltDefaultTextBox.getSWTWidgetControl();

		txtDecorator = WidgetUtility.addDecorator(textBox, Messages.CHARACTERSET);

		ListenerHelper helper = new ListenerHelper("decorator", txtDecorator);
		try {
			eltDefaultTextBox.attachListener(listenerFactory.getListener("ELTFocusOutListener"),
					propertyDialogButtonBar, helper, eltDefaultTextBox.getSWTWidgetControl());
			eltDefaultTextBox.attachListener(listenerFactory.getListener("ELTVerifyNumbericListener"),
					propertyDialogButtonBar, helper, eltDefaultTextBox.getSWTWidgetControl());
			eltDefaultTextBox.attachListener(listenerFactory.getListener("MyCustomWidgetTextChange"), propertyDialogButtonBar,  null,eltDefaultTextBox.getSWTWidgetControl());
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void setProperties(String propertyName, Object properties) {
		this.properties = (String) properties;
		this.propertyName = propertyName;
		if (properties != null)
			textBox.setText((String) properties);
		else
			textBox.setText("");

	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		LinkedHashMap<String, Object> property = new LinkedHashMap<>();
		property.put(propertyName, textBox.getText());
		return property;
	}

	/*@Override
	public void setComponentName(String componentName) {
		// TODO Auto-generated method stub

	}*/

}