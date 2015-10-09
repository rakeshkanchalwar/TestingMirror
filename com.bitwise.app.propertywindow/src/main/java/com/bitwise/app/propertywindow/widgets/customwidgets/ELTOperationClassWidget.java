package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import com.bitwise.app.propertywindow.datastructures.filter.OperationClassProperty;
import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.property.ComponentConfigrationProperty;
import com.bitwise.app.propertywindow.property.ComponentMiscellaneousProperties;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.dialogs.ELTOperationClassDialog;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultButton;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultCheckBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultTextBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTSaparater;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper;

public class ELTOperationClassWidget extends AbstractWidget {

	

	private Text fileName;
	private Object properties;
	private String propertyName;
	private LinkedHashMap<String, Object> property = new LinkedHashMap<>();
	private Button btnCheckButton; 
	private OperationClassProperty operationClassProperty;
	private ELTOperationClassDialog eltOperationClassDialog;

	public ELTOperationClassWidget(
			ComponentConfigrationProperty componentConfigrationProperty,
			ComponentMiscellaneousProperties componentMiscellaneousProperties,
			PropertyDialogButtonBar propertyDialogButtonBar) {
		super(componentConfigrationProperty, componentMiscellaneousProperties,
				propertyDialogButtonBar);

		this.operationClassProperty = (OperationClassProperty) componentConfigrationProperty.getPropertyValue();
		if(operationClassProperty == null){
			operationClassProperty = new OperationClassProperty("", false);
		}
		this.propertyName = componentConfigrationProperty.getPropertyName();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {

		ListenerFactory listenerFactory = new ListenerFactory();

		final ELTDefaultSubgroupComposite runtimeComposite = new ELTDefaultSubgroupComposite(
				container.getContainerControl());
		runtimeComposite.createContainerWidget();
		runtimeComposite.numberOfBasicWidgets(2);
		
		ELTDefaultLable defaultLable1 = new ELTDefaultLable("Operation\nClass"); 
		runtimeComposite.attachWidget(defaultLable1);
		
		ELTDefaultButton eltDefaultButton = new ELTDefaultButton(
				"Edit").grabExcessHorizontalSpace(false);
		runtimeComposite.attachWidget(eltDefaultButton);

		//populateWidget();
		
		((Button)eltDefaultButton.getSWTWidgetControl()).addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				eltOperationClassDialog = new ELTOperationClassDialog(runtimeComposite.getContainerControl().getShell(), propertyDialogButtonBar,operationClassProperty.getCopy());
				eltOperationClassDialog.open();
				if(!eltOperationClassDialog.getOperationClassProperty().equals(operationClassProperty)){
					operationClassProperty = eltOperationClassDialog.getOperationClassProperty();
					propertyDialogButtonBar.enableApplyButton(true);
				}
				//operationClassProperty=eltOperationClassDialog.getPropertyValue();
				super.widgetSelected(e);
			}
			
		});
		
		/*try {
			eltDefaultButton.attachListener(listenerFactory
					.getListener("ELTRuntimeButtonClickListener"),
					propertyDialogButtonBar, new ListenerHelper(this.getClass()
							.getName(), (Object) this), eltDefaultButton
							.getSWTWidgetControl());

		} catch (Exception e1) {

			e1.printStackTrace();
		}*/
		
		/*ListenerFactory listenerFactory = new ListenerFactory();
		
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
			fileNameText.attachListener(listenerFactory.getListener("ELTEmptyTextModifyListener"),propertyDialogButtonBar, null,fileName,editButton.getSWTWidgetControl(),isParameterCheckbox.getSWTWidgetControl());
	//		fileNameText.attachListener(listenerFactory.getListener("ELTCheckFileExtensionListener"),propertyDialogButtonBar, null,fileName,isParameterCheckbox.getSWTWidgetControl());
			isParameterCheckbox.attachListener(listenerFactory.getListener("ELTEnableButtonListener"),propertyDialogButtonBar, null,btnCheckButton,browseButton.getSWTWidgetControl(),createButton.getSWTWidgetControl());
			} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} */
		//populateWidget();
	} 
	 

	
	/*private void populateWidget(){
		if (properties != null && properties instanceof OperationClassProperty) {
			operationClassProperty = new OperationClassProperty(((OperationClassProperty)properties).getOperationClassPath(),((OperationClassProperty)properties).isParameter());
		}
		else{
			fileName.setBackground(new Color(Display.getDefault(), 255,
					255, 204));
		}
	}*/

	@Override
	public LinkedHashMap<String, Object> getProperties() {		
		//operationClassProperty = eltOperationClassDialog.getOperationClassProperty();
		property.put(propertyName, operationClassProperty);
		
		return property;
	}

}
