package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import org.eclipse.swt.widgets.Text;

import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultButton;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultTextBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper;
import com.bitwise.app.propertywindow.widgets.utility.WidgetUtility;


public class ELTFilePathWidget extends AbstractWidget{

	private Text textBox;
	private Object properties;
	private String propertyName;
	private Object txtDecorator;
	

	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {
		ListenerFactory listenerFactory = new ListenerFactory();
		
		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite = new ELTDefaultSubgroupComposite(container.getContainerControl());
		eltSuDefaultSubgroupComposite.createContainerWidget();

		AbstractELTWidget eltDefaultLable = new ELTDefaultLable("File Path");
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultLable);
		
		AbstractELTWidget eltDefaultTextBox = new ELTDefaultTextBox().grabExcessHorizontalSpace(true).textBoxWidth(200);
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultTextBox);
		
		textBox = (Text) eltDefaultTextBox.getSWTWidgetControl();
		
		AbstractELTWidget eltDefaultButton = new ELTDefaultButton("...").buttonWidth(20);
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultButton);
		
		txtDecorator = WidgetUtility.addDecorator(textBox, Messages.CHARACTERSET);
		
		ListenerHelper helper = new ListenerHelper("decorator", txtDecorator);
		
		try {
			//eltDefaultButton.attachListener(listenerFactory.getListener("ELTHelloTestListener"),propertyDialogButtonBar, null,eltDefaultTextBox.getSWTWidgetControl());
			//eltDefaultTextBox.attachListener(listenerFactory.getListener("MyCustomWidgetTextChange"), propertyDialogButtonBar,  null,eltDefaultTextBox.getSWTWidgetControl());
			eltDefaultButton.attachListener(listenerFactory.getListener("ELTFileDialogSelectionListener"), propertyDialogButtonBar, null, eltDefaultButton.getSWTWidgetControl(),eltDefaultTextBox.getSWTWidgetControl());
			eltDefaultTextBox.attachListener(listenerFactory.getListener("ELTFocusOutListener"), propertyDialogButtonBar,  helper,eltDefaultTextBox.getSWTWidgetControl());
			} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}

	@Override
	public void setProperties(String propertyName, Object properties) {
		this.properties =  properties;
		this.propertyName = propertyName;
		if(properties != null)
			textBox.setText((String) properties);
		else
			textBox.setText("");
		
	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		LinkedHashMap<String, Object> property=new LinkedHashMap<>();
		property.put(propertyName, textBox.getText());
		return property;
		
	}

	/*@Override
	public void setComponentName(String componentName) {
	//
		
	}*/
}
