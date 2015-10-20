package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.widgets.Text;

import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.property.ComponentConfigrationProperty;
import com.bitwise.app.propertywindow.property.ComponentMiscellaneousProperties;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultTextBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper;
import com.bitwise.app.propertywindow.widgets.utility.WidgetUtility;

public class ELTPhaseWidget extends AbstractWidget {

	public ELTPhaseWidget(
			ComponentConfigrationProperty componentConfigrationProperty,
			ComponentMiscellaneousProperties componentMiscellaneousProperties,
			PropertyDialogButtonBar propertyDialogButtonBar) {
		super(componentConfigrationProperty, componentMiscellaneousProperties,
				propertyDialogButtonBar);
		
		this.properties = (String)componentConfigrationProperty.getPropertyValue();
		this.propertyName = componentConfigrationProperty.getPropertyName();
	}

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
		
		AbstractELTWidget eltDefaultTextBox = new ELTDefaultTextBox().textBoxWidth(80).grabExcessHorizontalSpace(false);
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultTextBox);

		textBox = (Text) eltDefaultTextBox.getSWTWidgetControl();

		txtDecorator = WidgetUtility.addDecorator(textBox, Messages.EMPTYFIELDMESSAGE);

		ListenerHelper helper = new ListenerHelper("decorator", txtDecorator);
		try {
			eltDefaultTextBox.attachListener(ListenerFactory.Listners.NORMAL_FOCUS_OUT.getListener(),
					propertyDialogButtonBar, helper, eltDefaultTextBox.getSWTWidgetControl());
			eltDefaultTextBox.attachListener(ListenerFactory.Listners.VERIFY_NUMERIC.getListener(),
					propertyDialogButtonBar, helper, eltDefaultTextBox.getSWTWidgetControl());
			eltDefaultTextBox.attachListener(ListenerFactory.Listners.EVENT_CHANGE.getListener(), propertyDialogButtonBar,  null,eltDefaultTextBox.getSWTWidgetControl());
			eltDefaultTextBox.attachListener(ListenerFactory.Listners.MODIFY.getListener(), propertyDialogButtonBar,  helper,eltDefaultTextBox.getSWTWidgetControl());
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		populateWidget();
	}

	
	private void populateWidget(){		
		if (properties != null)
			textBox.setText(properties);
		
	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		LinkedHashMap<String, Object> property = new LinkedHashMap<>();
		property.put(propertyName, textBox.getText());
		return property;
	}
}
