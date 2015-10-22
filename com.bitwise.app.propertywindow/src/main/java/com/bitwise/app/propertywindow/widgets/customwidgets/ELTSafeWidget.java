package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;
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
 * The Class ELTSafeWidget.
 * 
 * @author Bitwise
 */
public class ELTSafeWidget extends AbstractWidget{

	

	Combo combo;
	Text text;
	String[] ITEMS={"True","False","Parameter"};
	private LinkedHashMap<String, Object> property=new LinkedHashMap<>();
	private String propertyName;
	private String properties;
	private ComboBoxParameter comboBoxParameter=new ComboBoxParameter();
	private ControlDecoration txtDecorator;
	private ControlDecoration txtDecoratorForEmpty;
	
	/**
	 * Instantiates a new ELT safe widget.
	 * 
	 * @param componentConfigrationProperty
	 *            the component configration property
	 * @param componentMiscellaneousProperties
	 *            the component miscellaneous properties
	 * @param propertyDialogButtonBar
	 *            the property dialog button bar
	 */
	public ELTSafeWidget(
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
		
		AbstractELTWidget eltDefaultLable = new ELTDefaultLable("Safe Property ");
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultLable);
		
		AbstractELTWidget eltDefaultCombo = new ELTDefaultCombo().defaultText(ITEMS);
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultCombo);
		combo=(Combo)eltDefaultCombo.getSWTWidgetControl();
		combo.select(1);
		
		ELTDefaultTextBox eltDefaultTextBox = new ELTDefaultTextBox().grabExcessHorizontalSpace(true).grabExcessHorizontalSpace(true);
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultTextBox);
		eltDefaultTextBox.visibility(false);
		text=(Text)eltDefaultTextBox.getSWTWidgetControl();
		
		txtDecorator = WidgetUtility.addDecorator(text, Messages.CHARACTERSET);
		
		
		ListenerHelper helper = new ListenerHelper();
		helper.put(HelperType.CONTROL_DECORATION, txtDecorator);
		helper.put(HelperType.VALIDATION_STATUS, validationStatus);
		
		try {
			eltDefaultCombo.attachListener(ListenerFactory.Listners.SELECTION.getListener(),propertyDialogButtonBar, helper,eltDefaultCombo.getSWTWidgetControl(),eltDefaultTextBox.getSWTWidgetControl());
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

	private void populateWidget(){	
		if(this.properties != null){
			if(this.properties.equalsIgnoreCase("true")){
				combo.select(0);
			}else if(this.properties.equalsIgnoreCase("false")){
				combo.select(1);
			}else{
				combo.select(2);
				text.setVisible(true);
				if (StringUtils.isNotEmpty(properties)){
					text.setText(properties);
					txtDecorator.hide();
				}
				else{
					text.setText("");
					txtDecorator.show();
				}
			}
		}
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
