package com.bitwise.app.propertywindow.widgets.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ColumnLayout;

import com.bitwise.app.propertywindow.datastructures.filter.OperationClassProperty;
import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultButton;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultCheckBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultTextBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;
import org.eclipse.ui.forms.widgets.ColumnLayoutData;

public class ELTOperationClassDialog extends Dialog {

	private Text fileName;
	private Button btnCheckButton; 
	private PropertyDialogButtonBar propertyDialogButtonBar;
	private Button applyButton;
	private Composite container;
	private OperationClassProperty operationClassProperty;
	/**
	 * Create the dialog.
	 * @param parentShell
	 * @param operationClassProperty 
	 */
	public ELTOperationClassDialog(Shell parentShell,PropertyDialogButtonBar propertyDialogButtonBar, OperationClassProperty operationClassProperty) {
		super(parentShell);
		this.propertyDialogButtonBar = propertyDialogButtonBar;
		setShellStyle(SWT.CLOSE | SWT.RESIZE | SWT.TITLE |  SWT.WRAP | SWT.APPLICATION_MODAL);
		this.operationClassProperty = operationClassProperty;
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
		
		container.getShell().setText("Operation Class");
		
		setPropertyDialogSize();
		
		Composite composite = new Composite(container, SWT.BORDER);
		ColumnLayout cl_composite = new ColumnLayout();
		cl_composite.maxNumColumns = 1;
		composite.setLayout(cl_composite);
		final ColumnLayoutData cld_composite = new ColumnLayoutData();
		cld_composite.heightHint = 82;
		composite.setLayoutData(cld_composite);
		
		
		container.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {				
               
				Rectangle containerBox = container.getShell().getBounds();
				if(containerBox.height >= 185) {
                	container.getShell().setBounds(containerBox.x, containerBox.y, containerBox.width, 185);
                }
				
				cld_composite.heightHint = container.getBounds().height - 10;
			}
			
		});
		
		//=================================
		
		ListenerFactory listenerFactory = new ListenerFactory();
		
		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite = new ELTDefaultSubgroupComposite(composite);
		eltSuDefaultSubgroupComposite.createContainerWidget();
		eltSuDefaultSubgroupComposite.numberOfBasicWidgets(4);
		
		AbstractELTWidget eltDefaultLable = new ELTDefaultLable("Operation\nClass");
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultLable);
		
		AbstractELTWidget fileNameText = new ELTDefaultTextBox().grabExcessHorizontalSpace(true).textBoxWidth(150);
		eltSuDefaultSubgroupComposite.attachWidget(fileNameText);
		
		fileName = (Text) fileNameText.getSWTWidgetControl();
		
		AbstractELTWidget browseButton = new ELTDefaultButton("...").buttonWidth(20);
		eltSuDefaultSubgroupComposite.attachWidget(browseButton);
		
		AbstractELTWidget isParameterCheckbox = new ELTDefaultCheckBox("Is Parameter").checkBoxLableWidth(100);
		eltSuDefaultSubgroupComposite.attachWidget(isParameterCheckbox);
		
		
		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite2 = new ELTDefaultSubgroupComposite(composite);
		eltSuDefaultSubgroupComposite2.createContainerWidget();
		eltSuDefaultSubgroupComposite2.numberOfBasicWidgets(3);
		
		
		ELTDefaultButton emptyButton = new ELTDefaultButton("").buttonWidth(75);
		eltSuDefaultSubgroupComposite2.attachWidget(emptyButton);
		emptyButton.visible(false);
				
		// Create new button, that use to create operational class
		AbstractELTWidget createButton = new ELTDefaultButton("Create New");
		eltSuDefaultSubgroupComposite2.attachWidget(createButton);

		// Edit new button, that use to edit operational class
		AbstractELTWidget editButton = new ELTDefaultButton("Edit");
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
		} 
		
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		
		createApplyButton(parent);	
	}
	
	private void createApplyButton(Composite parent) {
		applyButton = createButton(parent, IDialogConstants.NO_ID,
				"Apply", false);
		disableApplyButton();
	}
	
	private void disableApplyButton() {
		applyButton.setEnabled(false);
	}
	
	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 183);
	}

	private void setPropertyDialogSize() {
		container.getShell().setMinimumSize(450, 185);
	}

	public OperationClassProperty getOperationClassProperty() {
		OperationClassProperty operationClassProperty = new OperationClassProperty(this.operationClassProperty.getOperationClassPath(),this.operationClassProperty.isParameter());
		return operationClassProperty;
	}
}
