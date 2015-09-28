package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import com.bitwise.app.propertywindow.datastructures.filter.OperationClassProperty;
import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultButton;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultCheckBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultTextBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTSaparater;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;

public class ELTOperationClassWidget extends AbstractWidget {

	Text fileName;
	private Object properties;
	private String propertyName;
	private LinkedHashMap<String, Object> property = new LinkedHashMap<>();
	private Button btnCheckButton; 
	private OperationClassProperty operationClassProperty = new OperationClassProperty();


	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {

		ListenerFactory listenerFactory = new ListenerFactory();
		
		/*ELTDefaultSubgroupComposite separatorELTSuDefaultSubgroupComposite1 = new ELTDefaultSubgroupComposite(container.getContainerControl());
		separatorELTSuDefaultSubgroupComposite1.createContainerWidget();
		
		ELTSaparater eltSaparater1 = new ELTSaparater().grabExcessHorizontalSpace(true).visibility(true);
		separatorELTSuDefaultSubgroupComposite1.attachWidget(eltSaparater1);
		
		ELTSaparater eltSaparater2 = new ELTSaparater().grabExcessHorizontalSpace(true).visibility(true);
		separatorELTSuDefaultSubgroupComposite1.attachWidget(eltSaparater2);
		
		ELTSaparater eltSaparater3 = new ELTSaparater().grabExcessHorizontalSpace(true).visibility(true);
		separatorELTSuDefaultSubgroupComposite1.attachWidget(eltSaparater3);*/
		
		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite = new ELTDefaultSubgroupComposite(container.getContainerControl());
		eltSuDefaultSubgroupComposite.createContainerWidget();
		
		AbstractELTWidget eltDefaultLable = new ELTDefaultLable("Operation\nClass");
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultLable);
		
		AbstractELTWidget fileNameText = new ELTDefaultTextBox().grabExcessHorizontalSpace(true).textBoxWidth(200);
		eltSuDefaultSubgroupComposite.attachWidget(fileNameText);
		
		fileName = (Text) fileNameText.getSWTWidgetControl();
		
		AbstractELTWidget browseButton = new ELTDefaultButton("...").buttonWidth(20);
		eltSuDefaultSubgroupComposite.attachWidget(browseButton);
		
		
		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite2 = new ELTDefaultSubgroupComposite(container.getContainerControl());
		eltSuDefaultSubgroupComposite2.createContainerWidget();
		
		AbstractELTWidget isParameterCheckbox = new ELTDefaultCheckBox("IsParam");
		eltSuDefaultSubgroupComposite2.attachWidget(isParameterCheckbox);
		
				
		// Create new button, that use to create operational class
		AbstractELTWidget createButton = new ELTDefaultButton("Create New").grabExcessHorizontalSpace(true);
		eltSuDefaultSubgroupComposite2.attachWidget(createButton);

		// Edit new button, that use to edit operational class
		AbstractELTWidget editButton = new ELTDefaultButton("Edit").grabExcessHorizontalSpace(true);
		eltSuDefaultSubgroupComposite2.attachWidget(editButton); 
		
		btnCheckButton=(Button) isParameterCheckbox.getSWTWidgetControl();
	
		//container.attchProertySeperator();
		try { 
			editButton.attachListener(listenerFactory.getListener("ELTOpenFileEditorListener"),propertyDialogButtonBar, null,fileName);
			browseButton.attachListener(listenerFactory.getListener("ELTBrowseFileListener"),propertyDialogButtonBar, null,fileName);
			createButton.attachListener(listenerFactory.getListener("ELTCreateNewClassListener"),propertyDialogButtonBar, null,fileName);
			fileNameText.attachListener(listenerFactory.getListener("ELTEmptyTextModifyListener"),propertyDialogButtonBar, null,fileName,editButton.getSWTWidgetControl());
			fileNameText.attachListener(listenerFactory.getListener("ELTCheckFileExtensionListener"),propertyDialogButtonBar, null,fileName);
			isParameterCheckbox.attachListener(listenerFactory.getListener("ELTEnableButtonListener"),propertyDialogButtonBar, null,btnCheckButton,browseButton.getSWTWidgetControl(),createButton.getSWTWidgetControl());
			} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	} 
	 

	@Override
	public void setProperties(String propertyName, Object properties) {
		this.properties = properties;
		this.propertyName = propertyName;
		if (properties != null && properties instanceof OperationClassProperty) {
			fileName.setText(((OperationClassProperty)properties).getOperationClassPath());
			btnCheckButton.setSelection(((OperationClassProperty)properties).isParameter());
		}
		else{
			fileName.setBackground(new Color(Display.getDefault(), 255,
					255, 204));
		}
	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		operationClassProperty.setParameter(btnCheckButton.getSelection());
		operationClassProperty.setOperationClassPath(fileName.getText());
		property.put(propertyName, operationClassProperty);
		return property;
	}

	@Override
	public void setComponentName(String componentName) {
		// TODO Auto-generated method stub

	}

}
