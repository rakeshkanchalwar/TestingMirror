package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import com.bitwise.app.propertywindow.factory.ListenerFactory;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.AbstractELTWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultCombo;
import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTDefaultLable;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;


public class ELTCharacterSetWidget extends AbstractWidget{

	String[] ITEMS={"True","False","Parameter"};
	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget container) {
		
		ListenerFactory listenerFactory = new ListenerFactory();
		
		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite = new ELTDefaultSubgroupComposite(container.getContainerControl());
		eltSuDefaultSubgroupComposite.createContainerWidget();
		
		AbstractELTWidget eltDefaultLable = new ELTDefaultLable("Character Set :").lableWidth(80);
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultLable);
		
		/*AbstractELTWidget eltDefaultTextBox = new ELTDefaultTextBox().defaultText("Hello").grabExcessHorizontalSpace(true).textBoxWidth(200);
		container.attachWidget(eltDefaultTextBox);*/
		
		AbstractELTWidget eltDefaultCombo = new ELTDefaultCombo().defaultText(ITEMS);
		eltSuDefaultSubgroupComposite.attachWidget(eltDefaultCombo);
		
		
		//textBox = (Text) eltDefaultTextBox.getWidgetControl();
		
		/*AbstractELTWidget eltDefaultButton = new ELTDefaultButton("Submit");
		container.attachWidget(eltDefaultButton);
		try {
			eltDefaultButton.attachListener(listenerFactory.getListener("ELTHelloTestListener"));
			eltDefaultButton.attachListener(listenerFactory.getListener("ELTHiTestListener"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
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
