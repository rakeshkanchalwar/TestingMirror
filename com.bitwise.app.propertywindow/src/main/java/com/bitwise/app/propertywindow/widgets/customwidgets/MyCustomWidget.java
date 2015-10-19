package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import org.eclipse.swt.widgets.Text;

import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.property.ComponentConfigrationProperty;
import com.bitwise.app.propertywindow.property.ComponentMiscellaneousProperties;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.utils.WordUtils;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultButton;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultTextBox;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;
import com.bitwise.app.propertywindow.widgets.listeners.ListenerHelper;


/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 08, 2015
 * 
 */

public class MyCustomWidget extends AbstractWidget{

	

	private Object properties;
	private String propertyName;
	
	private Text textBox;
	
	public MyCustomWidget(
			ComponentConfigrationProperty componentConfigrationProperty,
			ComponentMiscellaneousProperties componentMiscellaneousProperties,
			PropertyDialogButtonBar propertyDialogButtonBar) {
		super(componentConfigrationProperty, componentMiscellaneousProperties,
				propertyDialogButtonBar);

		this.properties =  componentConfigrationProperty.getPropertyValue();
		this.propertyName = componentConfigrationProperty.getPropertyName();
		
	}
	
	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container){
		
		ListenerFactory listenerFactory = new ListenerFactory();
		
		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite = new ELTDefaultSubgroupComposite(container.getContainerControl());
		eltSuDefaultSubgroupComposite.createContainerWidget();
		
		
		AbstractELTWidget eltDefaultLable = new ELTDefaultLable("Addess :");
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultLable);
		
		AbstractELTWidget eltDefaultTextBox = new ELTDefaultTextBox().defaultText("Hello").grabExcessHorizontalSpace(true).textBoxWidth(100);
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultTextBox);
		
		textBox = (Text) eltDefaultTextBox.getSWTWidgetControl();
		
		AbstractELTWidget eltDefaultButton = new ELTDefaultButton("Submit");
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultButton);
		WordUtils wordUtils= new WordUtils();
		ListenerHelper listenerHelper = new ListenerHelper("WordUtils", wordUtils);
		//enableOkButton(false);
		try {
			/*eltDefaultButton.attachListener(listenerFactory.getListener("ELTHelloTestListener"),propertyDialogButtonBar, null,eltDefaultTextBox.getSWTWidgetControl(),eltDefaultButton.getSWTWidgetControl());
			eltDefaultButton.attachListener(listenerFactory.getListener("ELTHiTestListener"), propertyDialogButtonBar,  null);
			eltDefaultTextBox.attachListener(listenerFactory.getListener("MyCustomWidgetTextChange"),propertyDialogButtonBar,  listenerHelper,eltDefaultTextBox.getSWTWidgetControl());*/
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void populateWidget(){	
		
		if(properties != null)
			textBox.setText((String) properties);
		else
			textBox.setText("");
		// TODO Auto-generated method stub
	}

	@Override
	public LinkedHashMap<String, Object> getProperties() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, Object> property=new LinkedHashMap<>();
		property.put(propertyName, textBox.getText());
		return property;
	}

	/*@Override
	public void setComponentName(String componentName) {
		// TODO Auto-generated method stub
		
	}*/

}
