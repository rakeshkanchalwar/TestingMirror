package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.eclipse.swt.widgets.Text;
import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.property.ComponentConfigrationProperty;
import com.bitwise.app.propertywindow.property.ComponentMiscellaneousProperties;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultTextBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;
import com.bitwise.app.propertywindow.widgets.listeners.ELTVerifyComponentNameListener;

public class ELTComponentNameWidget extends AbstractWidget {

	private static final Logger logger = LogFactory.INSTANCE.getLogger(ELTComponentNameWidget.class);

	public ELTComponentNameWidget(
			ComponentConfigrationProperty componentConfigrationProperty,
			ComponentMiscellaneousProperties componentMiscellaneousProperties,
			PropertyDialogButtonBar propertyDialogButtonBar) {
		super(componentConfigrationProperty, componentMiscellaneousProperties,
				propertyDialogButtonBar);

		this.propertyName = componentConfigrationProperty.getPropertyName();
		this.oldName = (String) componentConfigrationProperty.getPropertyValue();
	}

	private Text text;
	private String oldName = "oldName";
	private String propertyName;

	private String newName = "newName";
	
	private ELTVerifyComponentNameListener listener;

	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {
		
		ListenerFactory listenerFactory = new ListenerFactory();


		ELTDefaultSubgroupComposite eltDefaultSubgroupComposite = new ELTDefaultSubgroupComposite(
				container.getContainerControl());
		eltDefaultSubgroupComposite.createContainerWidget();

		AbstractELTWidget eltDefaultLable = new ELTDefaultLable("Name");
		eltDefaultSubgroupComposite.attachWidget(eltDefaultLable);

		AbstractELTWidget eltDefaultTextBox = new ELTDefaultTextBox().defaultText("Hello")
				.grabExcessHorizontalSpace(true).textBoxWidth(200);
		eltDefaultSubgroupComposite.attachWidget(eltDefaultTextBox);

		text = (Text) eltDefaultTextBox.getSWTWidgetControl();
		
		text.setTextLimit(50);

		try {
			listener = (ELTVerifyComponentNameListener)ListenerFactory.Listners.VERIFY_COMPONENT_NAME.getListener();
			listener.setNames((ArrayList<String>) super.componentMiscellaneousProperties.getComponentMiscellaneousProperty("componentNames"));
			eltDefaultTextBox.attachListener(listener,
					propertyDialogButtonBar,  null,eltDefaultTextBox.getSWTWidgetControl());
		/*	eltDefaultTextBox.attachListener(listenerFactory.getListener("MyCustomWidgetTextChange"),
					propertyDialogButtonBar,  null,eltDefaultTextBox.getSWTWidgetControl());*/
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		populateWidget();

	}


	private void populateWidget() {
		listener.setOldName(oldName);
		text.setText(oldName);

	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		newName = text.getText().trim();
		LinkedHashMap<String, Object> property = new LinkedHashMap<>();
		if (newName != null && newName != "" && isUniqueCompName(newName)) {
			property.put(propertyName, newName);
			//super.names.remove(oldName);
			((ArrayList<String>) super.componentMiscellaneousProperties.getComponentMiscellaneousProperty("componentNames")).remove(oldName);			
			//super.names.add(newName);
			((ArrayList<String>) super.componentMiscellaneousProperties.getComponentMiscellaneousProperty("componentNames")).add(newName);
			oldName = newName;
		} else {
			// old name already should be there in the names arraylist
			property.put(propertyName, oldName);
		}

		return property;
	}

	/*@Override
	public void setComponentName(String componentName) {
		// TODO Auto-generated method stub

	}*/

	private boolean isUniqueCompName(String componentName) {
		componentName = componentName.trim();
		boolean result = true;

		for (String cname : ((ArrayList<String>) super.componentMiscellaneousProperties.getComponentMiscellaneousProperty("componentNames"))) {
			if (cname.equalsIgnoreCase(componentName)) {
				result = false;
				break;
			}

		}
		logger.debug("isUniqueCompName: result: " + result);

		return result;
	}

}
