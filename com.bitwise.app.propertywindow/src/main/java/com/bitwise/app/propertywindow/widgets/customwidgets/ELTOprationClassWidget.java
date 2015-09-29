package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.widgets.Text;

import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultButton;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultCheckBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultTextBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;
import com.bitwise.app.propertywindow.widgets.utility.WidgetUtility;

public class ELTOprationClassWidget extends AbstractWidget {
	
	private ControlDecoration fieldNameDecorator;
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {
		// TODO Auto-generated method stub
		ListenerFactory listenerFactory = new ListenerFactory();
		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite = new ELTDefaultSubgroupComposite(container.getContainerControl());
		eltSuDefaultSubgroupComposite.createContainerWidget();
		eltSuDefaultSubgroupComposite.numberOfBasicWidgets(5);
		AbstractELTWidget oprationClassLable = new ELTDefaultLable("Oprational Class").lableWidth(95);
		eltSuDefaultSubgroupComposite.attachWidget(oprationClassLable);
		ELTDefaultTextBox fileName = new ELTDefaultTextBox().grabExcessHorizontalSpace(true).textBoxWidth(150).grabExcessHorizontalSpace(false);
		eltSuDefaultSubgroupComposite.attachWidget(fileName);
		fieldNameDecorator = WidgetUtility.addDecorator((Text)fileName.getSWTWidgetControl(),
				Messages.OperationClassBlank);
		AbstractELTWidget browseButton = new ELTDefaultButton("Browse...");
		eltSuDefaultSubgroupComposite.attachWidget(browseButton);
		AbstractELTWidget createButton = new ELTDefaultButton("Create New");
		eltSuDefaultSubgroupComposite.attachWidget(createButton);
		AbstractELTWidget isParameterCheckbox = new ELTDefaultCheckBox("Is_Parameter");
		eltSuDefaultSubgroupComposite.attachWidget(isParameterCheckbox);
		
		try { 
			browseButton.attachListener(listenerFactory.getListener("ELTSelectionBrowseListener"),propertyDialogButtonBar,fileName.getSWTWidgetControl());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}

	@Override
	public void setProperties(String propertyName, Object properties) {
		// TODO Auto-generated method stub

	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setComponentName(String componentName) {
		// TODO Auto-generated method stub

	}

}
