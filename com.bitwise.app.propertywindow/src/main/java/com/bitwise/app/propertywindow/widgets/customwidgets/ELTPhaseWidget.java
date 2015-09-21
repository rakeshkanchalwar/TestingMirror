package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ColumnLayoutData;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultButton;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultTextBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;
import com.bitwise.app.propertywindow.widgets.utility.WidgetUtility;

public class ELTPhaseWidget extends AbstractWidget{

	private Text textBox;
	private Object properties;
	private String propertyName;

	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {
		ListenerFactory listenerFactory = new ListenerFactory();
		

		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite = new ELTDefaultSubgroupComposite(container.getContainerControl());
		eltSuDefaultSubgroupComposite.createContainerWidget();
		
		AbstractELTWidget eltDefaultLable = new ELTDefaultLable("Phase :");
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultLable);
		
		AbstractELTWidget eltDefaultTextBox = new ELTDefaultTextBox().defaultText("Hello").grabExcessHorizontalSpace(true).textBoxWidth(200);
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultTextBox);
		
		textBox = (Text) eltDefaultTextBox.getWidgetControl();
		AbstractELTWidget eltDefaultButton = new ELTDefaultButton("Submit");
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultButton);
		try {
			eltDefaultButton.attachListener(listenerFactory.getListener("ELTHelloTestListener"));
			eltDefaultButton.attachListener(listenerFactory.getListener("ELTHiTestListener"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
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

	@Override
	public void setComponentName(String componentName) {
		// TODO Auto-generated method stub
		
	}

}