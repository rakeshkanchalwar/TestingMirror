package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

import com.bitwise.app.propertywindow.datastructures.ComboBoxParameter;
import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultCombo;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultTextBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper;


public class ELTCharacterSetWidget extends AbstractWidget{
	
	Combo combo;
	Text text;
	String[] ITEMS={"True","False","Parameter"};
	private LinkedHashMap<String, Object> property=new LinkedHashMap<>();
	private String propertyName;
	private String properties;
	
	
	private ComboBoxParameter comboBoxParameter=new ComboBoxParameter();
	
	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {
		
		ListenerFactory listenerFactory = new ListenerFactory();
		
		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite = new ELTDefaultSubgroupComposite(container.getContainerControl());
		eltSuDefaultSubgroupComposite.createContainerWidget();
		
		AbstractELTWidget eltDefaultLable = new ELTDefaultLable("Character Set").lableWidth(80);
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultLable);
		
		
		AbstractELTWidget eltDefaultCombo = new ELTDefaultCombo().defaultText(ITEMS).grabExcessHorizontalSpace(true);
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultCombo);
		
		combo=(Combo)eltDefaultCombo.getSWTWidgetControl();
		combo.select(1);
		
		ELTDefaultTextBox eltDefaultTextBox = new ELTDefaultTextBox().grabExcessHorizontalSpace(true).textBoxWidth(150).grabExcessHorizontalSpace(false);
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultTextBox);
		eltDefaultTextBox.visibility(false);
		text=(Text)eltDefaultTextBox.getSWTWidgetControl();
		
		
		try {
			
			eltDefaultCombo.attachListener(listenerFactory.getListener("ELTSelectionListener"), propertyDialogButtonBar, null,eltDefaultCombo.getSWTWidgetControl(),eltDefaultTextBox.getSWTWidgetControl());
			eltDefaultTextBox.attachListener(listenerFactory.getListener("MyCustomWidgetTextChange"), propertyDialogButtonBar,  null,eltDefaultTextBox.getSWTWidgetControl());
			eltDefaultTextBox.attachListener(listenerFactory.getListener("ELTVerifyTextListener"), propertyDialogButtonBar,  null,eltDefaultTextBox.getSWTWidgetControl());
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
	}

	@Override
	public void setProperties(String propertyName, Object properties) {
		// TODO Auto-generated method stub
		
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
		
		/*if(properties != null && properties instanceof ComboBoxParameter){
			
			if(!combo.getText().equalsIgnoreCase("Parameter"))
			{
				text.setVisible(true);
				combo.setText(((ComboBoxParameter)properties).getOption());
				if(((ComboBoxParameter)properties).getOptionValue()!=null)
					text.setText(((ComboBoxParameter)properties).getOptionValue());
				
			}
			else
			{
				text.setVisible(false);
				combo.setText(((ComboBoxParameter)properties).getOption());
			}	
		}*/
		
		this.properties = text.getText();
	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
	/*	property.put(propertyName, combo.getText());
		property.put("text_value", text.getText());

		return property;*/

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

	@Override
	public void setComponentName(String componentName) {
		// TODO Auto-generated method stub
		
	}

	
}
