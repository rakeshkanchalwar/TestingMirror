package com.bitwise.app.eltproperties.factory;

import com.bitwise.app.eltproperties.widgets.IELTWidget;
import com.bitwise.app.eltproperties.widgets.MyCustomWidget;
import com.bitwise.app.eltproperties.widgets.runtimeproperties.RuntimeWidgetButton;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 08, 2015
 * 
 */

public class WidgetFactory {
	public IELTWidget getWidget(String widgetName){
		if(widgetName.equals("ELTSchemaWidget")){
			return new MyCustomWidget();
		}else if(widgetName.equals("ELTRuntimePropertiesWidget")){
			//return new RuntimeWidgetButton();
			return new MyCustomWidget();
		}else if(widgetName.equals("ELTFilePathWidget")){
			return new MyCustomWidget();
		}else if(widgetName.equals("ELTCharacterSetWidget")){
			return new MyCustomWidget();
		}else if(widgetName.equals("ELTDelimeterWidget")){
			return new MyCustomWidget();
		}else if(widgetName.equals("ELTPhaseWidget")){
			return new MyCustomWidget();
		}else if(widgetName.equals("ELTHasHeaderWidget")){
			return new MyCustomWidget();
		}else if(widgetName.equals("ELTComponentNameWidget")){
			return new MyCustomWidget();
		}else if(widgetName.equals("ELTSafePropertyWidget")){
			return new MyCustomWidget();
		}else{
			return null;
		}
	}
}
