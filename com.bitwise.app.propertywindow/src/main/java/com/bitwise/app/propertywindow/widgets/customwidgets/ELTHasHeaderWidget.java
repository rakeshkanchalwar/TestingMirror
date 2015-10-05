package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.messages.Messages;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultCombo;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultTextBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper;
import com.bitwise.app.propertywindow.widgets.utility.WidgetUtility;
import com.bitwise.app.propertywindow.datastructures.ComboBoxParameter;

public class ELTHasHeaderWidget extends AbstractWidget{

	Combo combo;
	Text text;
	String[] ITEMS={"True","False","Parameter"};
	private LinkedHashMap<String, Object> property=new LinkedHashMap<>();
	private String propertyName;
	private String properties;
	private ControlDecoration txtDecorator;
	
	private ComboBoxParameter comboBoxParameter=new ComboBoxParameter();
	
	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {
		ListenerFactory listenerFactory = new ListenerFactory();
		
		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite = new ELTDefaultSubgroupComposite(container.getContainerControl());
		eltSuDefaultSubgroupComposite.createContainerWidget();
		
		AbstractELTWidget eltDefaultLable = new ELTDefaultLable("Has Header");
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
		
		ListenerHelper helper = new ListenerHelper("decorator", txtDecorator);
		
		try {
			eltDefaultCombo.attachListener(listenerFactory.getListener("ELTSelectionListener"),propertyDialogButtonBar, helper,eltDefaultCombo.getSWTWidgetControl(),eltDefaultTextBox.getSWTWidgetControl());
			eltDefaultTextBox.attachListener(listenerFactory.getListener("ELTEventChangeListener"), propertyDialogButtonBar,  null,eltDefaultTextBox.getSWTWidgetControl());
			eltDefaultTextBox.attachListener(listenerFactory.getListener("ELTVerifyTextListener"), propertyDialogButtonBar,  helper,eltDefaultTextBox.getSWTWidgetControl());
			eltDefaultTextBox.attachListener(listenerFactory.getListener("ELTFocusOutListener"), propertyDialogButtonBar,  helper,eltDefaultTextBox.getSWTWidgetControl());
			
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		
	}

	@Override
	public void setProperties(String propertyName, Object properties) {
		this.propertyName = propertyName;
		this.properties =  (String) properties; 
		
		if(this.properties != null){
			if(this.properties.equalsIgnoreCase("true")){
				combo.select(0);
			}else if(this.properties.equalsIgnoreCase("false")){
				combo.select(1);
			}else{
				combo.select(2);
				text.setVisible(true);
				text.setText(this.properties);
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

	/*@Override
	public void setComponentName(String componentName) {
		// TODO Auto-generated method stub
		
	}*/
	
}
