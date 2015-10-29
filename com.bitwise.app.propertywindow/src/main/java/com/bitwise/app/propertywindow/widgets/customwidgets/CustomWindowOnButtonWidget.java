package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import org.eclipse.swt.widgets.Shell;

import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.property.ComponentConfigrationProperty;
import com.bitwise.app.propertywindow.property.ComponentMiscellaneousProperties;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultButton;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;

public class CustomWindowOnButtonWidget extends AbstractWidget {
	private Shell shell;
	
	public CustomWindowOnButtonWidget(ComponentConfigrationProperty componentConfigProperty,	
			ComponentMiscellaneousProperties componentMiscProperties,	PropertyDialogButtonBar propertyDialogButtonBar) {
		super(componentConfigProperty, componentMiscProperties,	propertyDialogButtonBar);
		validationStatus.setIsValid(true);
	}

	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {
		ELTDefaultSubgroupComposite runtimeComposite = new ELTDefaultSubgroupComposite(container.getContainerControl());
		runtimeComposite.createContainerWidget();
		shell = runtimeComposite.getContainerControl().getShell();

		
		ELTDefaultLable label = new ELTDefaultLable("Operation");  
		runtimeComposite.attachWidget(label);
		
		
		ELTDefaultButton button = new ELTDefaultButton(Messages.CustomWindowOnButtonWidget_EDIT);
		
		runtimeComposite.attachWidget(button);

		try {
			/*button.attachListener(ListenerFactory.Listners.RUNTIME_BUTTON_CLICK.getListener(),
					propertyDialogButtonBar, new ListenerHelper(this.getClass()
							.getName(), this), button.getSWTWidgetControl());*/

		} catch (Exception e1) {

			e1.printStackTrace();
		}

	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		return null;
	}
}
