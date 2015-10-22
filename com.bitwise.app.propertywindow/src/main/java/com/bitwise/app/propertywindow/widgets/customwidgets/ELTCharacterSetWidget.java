package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

import com.bitwise.app.propertywindow.datastructures.ComboBoxParameter;
import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.property.ComponentConfigrationProperty;
import com.bitwise.app.propertywindow.property.ComponentMiscellaneousProperties;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultCombo;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultTextBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper.HelperType;
import com.bitwise.app.propertywindow.widgets.utility.WidgetUtility;


// TODO: Auto-generated Javadoc
/**
 * The Class ELTCharacterSetWidget.
 * 
 * @author Bitwise
 */
public class ELTCharacterSetWidget extends AbstractWidget{
	private Combo combo;
	private Text text;
	private String[] ITEMS={"UTF-8","US-ASCII","ISO-8859-1","IUTF-16BE","UTF-16LE","UTF-16","Parameter"};
	private LinkedHashMap<String, Object> property=new LinkedHashMap<>();
	private String propertyName;
	private String properties;
	
	private ControlDecoration txtDecorator; //=WidgetUtility.addDecorator((Text)widgetList[0], Messages.CHARACTERSET);
	private ComboBoxParameter comboBoxParameter=new ComboBoxParameter();
	
	/**
	 * Instantiates a new ELT character set widget.
	 * 
	 * @param componentConfigrationProperty
	 *            the component configration property
	 * @param componentMiscellaneousProperties
	 *            the component miscellaneous properties
	 * @param propertyDialogButtonBar
	 *            the property dialog button bar
	 */
	public ELTCharacterSetWidget(
			ComponentConfigrationProperty componentConfigrationProperty,
			ComponentMiscellaneousProperties componentMiscellaneousProperties,
			PropertyDialogButtonBar propertyDialogButtonBar) {
		super(componentConfigrationProperty, componentMiscellaneousProperties,
				propertyDialogButtonBar);

		this.propertyName = componentConfigrationProperty.getPropertyName();
		this.properties =  (String) componentConfigrationProperty.getPropertyValue(); 
	}
	
	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {
		
		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite = new ELTDefaultSubgroupComposite(container.getContainerControl());
		eltSuDefaultSubgroupComposite.createContainerWidget();
		
		AbstractELTWidget eltDefaultLable = new ELTDefaultLable("Character Set");
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultLable);
		
		
		AbstractELTWidget eltDefaultCombo = new ELTDefaultCombo().defaultText(ITEMS).grabExcessHorizontalSpace(true);
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultCombo);
		
		combo=(Combo)eltDefaultCombo.getSWTWidgetControl();
		combo.select(0);
		
		ELTDefaultTextBox eltDefaultTextBox = new ELTDefaultTextBox().grabExcessHorizontalSpace(true).grabExcessHorizontalSpace(true);
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultTextBox);
		eltDefaultTextBox.visibility(false);
		text=(Text)eltDefaultTextBox.getSWTWidgetControl();
		
		txtDecorator = WidgetUtility.addDecorator(text, Messages.CHARACTERSET);
		
		ListenerHelper helper = new ListenerHelper();
		helper.put(HelperType.CONTROL_DECORATION, txtDecorator);
		helper.put(HelperType.VALIDATION_STATUS, validationStatus);
		
		try {
			
			eltDefaultCombo.attachListener(ListenerFactory.Listners.SELECTION.getListener(), propertyDialogButtonBar, helper,eltDefaultCombo.getSWTWidgetControl(),eltDefaultTextBox.getSWTWidgetControl());
			eltDefaultTextBox.attachListener(ListenerFactory.Listners.EVENT_CHANGE.getListener(), propertyDialogButtonBar,  null,eltDefaultTextBox.getSWTWidgetControl());
			eltDefaultTextBox.attachListener(ListenerFactory.Listners.VERIFY_TEXT.getListener(), propertyDialogButtonBar,  helper,eltDefaultTextBox.getSWTWidgetControl());
			eltDefaultTextBox.attachListener(ListenerFactory.Listners.FOCUS_OUT.getListener(), propertyDialogButtonBar,  helper,eltDefaultTextBox.getSWTWidgetControl());
			eltDefaultTextBox.attachListener(ListenerFactory.Listners.FOCUS_IN.getListener(), propertyDialogButtonBar,  helper,eltDefaultTextBox.getSWTWidgetControl());
			eltDefaultTextBox.attachListener(ListenerFactory.Listners.MODIFY.getListener(), propertyDialogButtonBar,  helper,eltDefaultTextBox.getSWTWidgetControl());
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		
		populateWidget();
	}

	private void populateWidget() {
		
		if(this.properties != null){
			if(this.properties.equalsIgnoreCase("UTF-8")){
				combo.select(0);
			}else if(this.properties.equalsIgnoreCase("US-ASCII")){
				combo.select(1);
			}else if(this.properties.equalsIgnoreCase("ISO-8859-1")){
				combo.select(2);
			}else if(this.properties.equalsIgnoreCase("IUTF-16BE")){
				combo.select(3);
			}else if(this.properties.equalsIgnoreCase("UTF-16LE")){
				combo.select(4);
			}else if(this.properties.equalsIgnoreCase("UTF-16")){
				combo.select(5);
			}else{
				combo.select(6);
				text.setVisible(true);
				text.setText(this.properties);
			}
		}
		this.properties = text.getText();
	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {

		if( combo.getText().equalsIgnoreCase("Parameter"))
		{
			comboBoxParameter.setOption(text.getText());
			comboBoxParameter.setOptionValue(text.getText());
		}else{
			comboBoxParameter.setOption(combo.getText());
		}
		property.put(propertyName,comboBoxParameter.getOption());
		return property;
	}


	
}
