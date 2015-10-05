package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
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

public class ELTDelimeterWidget extends AbstractWidget{
	private Text textBox;
	private Object properties;
	private String propertyName;
	private Object txtDecorator;
	private ControlDecoration decorator;

	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {
		ListenerFactory listenerFactory = new ListenerFactory();
		

		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite = new ELTDefaultSubgroupComposite(container.getContainerControl());
		eltSuDefaultSubgroupComposite.createContainerWidget();
		
		AbstractELTWidget eltDefaultLable = new ELTDefaultLable("Delimeter ");
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultLable);
		
		AbstractELTWidget eltDefaultTextBox = new ELTDefaultTextBox().grabExcessHorizontalSpace(true).textBoxWidth(100);
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultTextBox);
		
		textBox = (Text) eltDefaultTextBox.getSWTWidgetControl();
		decorator=WidgetUtility.addDecorator(textBox, Messages.EMPTYFIELDMESSAGE);
		
		textBox.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(textBox.getText().isEmpty()){
					decorator.show();
					textBox.setBackground(new Color(Display.getDefault(), 255, 255, 204));
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				decorator.hide();
				textBox.setBackground(new Color(Display.getDefault(), 255, 255, 255));	
			}
		});
		
		txtDecorator = WidgetUtility.addDecorator(textBox, Messages.EMPTYFIELDMESSAGE);
		
		ListenerHelper helper = new ListenerHelper("decorator", txtDecorator);
		try {
			eltDefaultTextBox.attachListener(listenerFactory.getListener("ELTModifyListener"), propertyDialogButtonBar,  helper,eltDefaultTextBox.getSWTWidgetControl());
			eltDefaultTextBox.attachListener(listenerFactory.getListener("ELTEventChangeListener"), propertyDialogButtonBar,  null,eltDefaultTextBox.getSWTWidgetControl());
			
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
			textBox.setText("|");
		
	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		LinkedHashMap<String, Object> property=new LinkedHashMap<>();
		property.put(propertyName, textBox.getText());
		return property;
	}

	/*@Override
	public void setComponentName(String componentName) {
		// TODO Auto-generated method stub
		
	}*/


}
