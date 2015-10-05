package com.bitwise.app.propertywindow.propertydialog;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.ColumnLayout;

import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.propertywindow.constants.ELTProperties;
import com.bitwise.app.propertywindow.messagebox.ConfirmCancelMessageBox;
import com.bitwise.app.propertywindow.property.ELTComponenetProperties;
import com.bitwise.app.propertywindow.property.Property;
import com.bitwise.app.propertywindow.widgets.customwidgets.AbstractWidget;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 07, 2015
 * 
 */

public class PropertyDialog extends Dialog {
	private Composite container;
	private LinkedHashMap<String, LinkedHashMap<String, ArrayList<Property>>> propertyTree;
	private LinkedHashMap<String, Object> ComponentProperties;
	private LinkedHashMap<String, Object> componentMiscellaneousProperties;
	private PropertyDialogBuilder propertyDialogBuilder;
	private PropertyDialogButtonBar propertyDialogButtonBar;
	String componentName;
	private Button applyButton;
	private boolean propertyChanged=false;		
	/**
	 * Create the dialog.
	 * @param parentShell
	 * @param propertyTree 
	 * @param ComponentProperties 
	 */
	public PropertyDialog(Shell parentShell, LinkedHashMap<String, LinkedHashMap<String, ArrayList<Property>>> propertyTree,ELTComponenetProperties eltComponenetProperties) {		
		super(parentShell);
		this.propertyTree = propertyTree;
		this.ComponentProperties = eltComponenetProperties.getComponentConfigurationProperties();
		this.componentMiscellaneousProperties = eltComponenetProperties.getComponentMiscellaneousProperties();
		this.componentName = (String) ComponentProperties.get(ELTProperties.NAME_PROPERTY.getPropertyName());
		
		setShellStyle(SWT.CLOSE | SWT.RESIZE | SWT.TITLE | SWT.WRAP | SWT.APPLICATION_MODAL);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		
		container = (Composite) super.createDialogArea(parent);
		ColumnLayout cl_container = new ColumnLayout();
		cl_container.maxNumColumns = 1;
		container.setLayout(cl_container);
		
		container.getShell().setMinimumSize(400, 500);
		container.getShell().setText(componentName + " - Properties");
		propertyDialogButtonBar = new PropertyDialogButtonBar(container);
		
		propertyDialogBuilder = new PropertyDialogBuilder(container,propertyTree,ComponentProperties,componentMiscellaneousProperties,propertyDialogButtonBar);
		propertyDialogBuilder.buildPropertyWindow();
		
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		
		Button okButton=createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		
		
		Button cancelButton = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		
		
		applyButton = createButton(parent, IDialogConstants.NO_ID,
				"Apply", false);
		
		propertyDialogButtonBar.setPropertyDialogButtonBar(okButton, applyButton, cancelButton);
		
		for(AbstractWidget eltWidget : propertyDialogBuilder.getELTWidgetList()){
			eltWidget.setpropertyDialogButtonBar(propertyDialogButtonBar);
		}
		applyButton.setEnabled(false);
		
		
		applyButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				for(AbstractWidget eltWidget : propertyDialogBuilder.getELTWidgetList()){
					if(eltWidget.getProperties() != null){
						LinkedHashMap<String, Object> tempPropert = eltWidget.getProperties();
						System.out.println(tempPropert.keySet().toString());
						for(String propName : tempPropert.keySet()){
							ComponentProperties.put(propName, tempPropert.get(propName));
						}
					}
				}
				propertyChanged=true;
				applyButton.setEnabled(false);
				super.widgetSelected(e);
			}
			
		});
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(400, 500);
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		return bindingContext;
	}

	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		System.out.println("Prop saved");
		for(AbstractWidget eltWidget : propertyDialogBuilder.getELTWidgetList()){
			if(eltWidget.getProperties() != null){
				LinkedHashMap<String, Object> tempPropert = eltWidget.getProperties();
				System.out.println(tempPropert.keySet().toString());
				for(String propName : tempPropert.keySet()){
					ComponentProperties.put(propName, tempPropert.get(propName));
				}	
			}
			
		}
		if(applyButton.isEnabled())
			propertyChanged=true;
			
		//System.out.println(ComponentProperties);
		super.okPressed();
	}
	
	@Override
	protected void cancelPressed(){
		if(applyButton.isEnabled()){
			ConfirmCancelMessageBox confirmCancelMessageBox = new ConfirmCancelMessageBox(container);
			MessageBox confirmCancleMessagebox = confirmCancelMessageBox.getMessageBox();
			
			if(confirmCancleMessagebox.open() == SWT.OK){
				//System.out.println("Hiiii");
				super.close();
			}
		}else{
			super.close();
		}
		
	}
	

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);		
		String imagePath = null;
		//TODO Please un comment below code before build.
		try{
			imagePath = XMLConfigUtil.CONFIG_FILES_PATH + "/icons/property_window_icon.png" ;  
			Image shellImage = new Image(newShell.getDisplay(), imagePath);
			newShell.setImage(shellImage);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	public boolean isPropertyChanged(){
		return propertyChanged;
		/*if(applyButton.isEnabled())
			return true;
		else
			return false;*/
		
	}

}
