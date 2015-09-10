package com.bitwise.app.eltproperties.factory;

import com.bitwise.app.eltproperties.widgets.ELTCharacterSetWidget;
import com.bitwise.app.eltproperties.widgets.ELTDelimeterWidget;
import com.bitwise.app.eltproperties.widgets.ELTFilePathWidget;
import com.bitwise.app.eltproperties.widgets.ELTHasHeaderWidget;
import com.bitwise.app.eltproperties.widgets.ELTPhaseWidget;
import com.bitwise.app.eltproperties.widgets.ELTSafeWidget;
import com.bitwise.app.eltproperties.widgets.IELTWidget;
import com.bitwise.app.eltproperties.widgets.MyCustomWidget;

import com.bitwise.app.eltproperties.widgets.runtimeproperties.ELTRuntimePropertiesWidget;
import com.bitwise.app.eltproperties.widgets.schemagrid.ELTSchemaWidget;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 08, 2015
 * 
 */

public class WidgetFactory {
	public IELTWidget getWidget(String widgetName){
		if(widgetName.equals("ELTSchemaWidget")){
			return new ELTSchemaWidget();
		}else if(widgetName.equals("ELTRuntimePropertiesWidget")){
			return new ELTRuntimePropertiesWidget();
			
		}else if(widgetName.equals("ELTFilePathWidget")){
			return new ELTFilePathWidget();
		}else if(widgetName.equals("ELTCharacterSetWidget")){
			return new ELTCharacterSetWidget();
		}else if(widgetName.equals("ELTDelimeterWidget")){
			return new ELTDelimeterWidget();
		}else if(widgetName.equals("ELTPhaseWidget")){
			return new ELTPhaseWidget();
		}else if(widgetName.equals("ELTHasHeaderWidget")){
			return new ELTHasHeaderWidget();
		}else if(widgetName.equals("ELTComponentNameWidget")){
			return new MyCustomWidget();
		}else if(widgetName.equals("ELTSafePropertyWidget")){
			return new ELTSafeWidget();
		}else{
			return null;
		}
	}
}
