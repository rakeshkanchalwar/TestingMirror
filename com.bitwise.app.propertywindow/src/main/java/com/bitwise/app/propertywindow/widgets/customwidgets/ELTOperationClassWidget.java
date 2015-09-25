package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import org.eclipse.swt.widgets.Text;

import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultButton;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultCheckBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultTextBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;

public class ELTOperationClassWidget extends AbstractWidget {

	Text fileName;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {

		ListenerFactory listenerFactory = new ListenerFactory();
		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite = new ELTDefaultSubgroupComposite(container.getContainerControl());
		eltSuDefaultSubgroupComposite.createContainerWidget();
		eltSuDefaultSubgroupComposite.numberOfBasicWidgets(5);

		// Operational class label.
		AbstractELTWidget oprationClassLable = new ELTDefaultLable("Oprational Class").lableWidth(95);
		eltSuDefaultSubgroupComposite.attachWidget(oprationClassLable);

		// Browse file text box.
		final ELTDefaultTextBox fileNameText = new ELTDefaultTextBox().grabExcessHorizontalSpace(true).textBoxWidth(150).grabExcessHorizontalSpace(false);
		eltSuDefaultSubgroupComposite.attachWidget(fileNameText);
		fileName=(Text) fileNameText.getSWTWidgetControl();

		// Create browse button.
		AbstractELTWidget browseButton = new ELTDefaultButton("...");
		eltSuDefaultSubgroupComposite.attachWidget(browseButton);

		// Create new button, that use to create operational class
		AbstractELTWidget createButton = new ELTDefaultButton("Create New");
		eltSuDefaultSubgroupComposite.attachWidget(createButton);

		// Edit new button, that use to edit operational class
		AbstractELTWidget editButton = new ELTDefaultButton("Edit");
		eltSuDefaultSubgroupComposite.attachWidget(editButton); 
		AbstractELTWidget isParameterCheckbox = new ELTDefaultCheckBox("Is_Parameter");
		eltSuDefaultSubgroupComposite.attachWidget(isParameterCheckbox); 

		try { 
			editButton.attachListener(listenerFactory.getListener("ELTOpenFileEditorListener"),propertyDialogButtonBar, null,fileName);
			browseButton.attachListener(listenerFactory.getListener("ELTBrowseFileListener"),propertyDialogButtonBar, null,fileName);
			createButton.attachListener(listenerFactory.getListener("ELTCreateNewClassListener"),propertyDialogButtonBar, null,fileName);
			fileNameText.attachListener(listenerFactory.getListener("ELTEmptyTextModifyListener"),propertyDialogButtonBar, null,fileName,editButton.getSWTWidgetControl());
			fileNameText.attachListener(listenerFactory.getListener("ELTCheckFileExtensionListener"),propertyDialogButtonBar, null,fileName);
			isParameterCheckbox.attachListener(listenerFactory.getListener("ELTEnableButtonListener"),propertyDialogButtonBar, null,isParameterCheckbox.getSWTWidgetControl(),browseButton.getSWTWidgetControl(),createButton.getSWTWidgetControl());
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
