package com.bitwise.app.eltproperties.factory;

import com.bitwise.app.eltproperties.widgets.ELTCharacterSetWidget;
import com.bitwise.app.eltproperties.widgets.ELTDelimeterWidget;
import com.bitwise.app.eltproperties.widgets.ELTFilePathWidget;
import com.bitwise.app.eltproperties.widgets.ELTHasHeaderWidget;
import com.bitwise.app.eltproperties.widgets.ELTPhaseWidget;
import com.bitwise.app.eltproperties.widgets.ELTSafeWidget;
import com.bitwise.app.eltproperties.widgets.AbstractELTWidget;
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
	public AbstractELTWidget getWidget(String widgetName){
		if(widgetName.equals("ELT_SCHEMA_WIDGET")){
			return new ELTSchemaWidget();
		}else if(widgetName.equals("ELT_RUNTIME_PROPERTIES_WIDGET")){
			return new ELTRuntimePropertiesWidget();
		}else if(widgetName.equals("ELT_FILE_PATH_WIDGET")){
			return new ELTFilePathWidget();
		}else if(widgetName.equals("ELT_CHARACTER_SET_WIDGET")){
			return new ELTCharacterSetWidget();
		}else if(widgetName.equals("ELT_DELIMETER_WIDGET")){
			return new ELTDelimeterWidget();
		}else if(widgetName.equals("ELT_PHASE_WIDGET")){
			return new ELTPhaseWidget();
		}else if(widgetName.equals("ELT_HAS_HEADER_WIDGET")){
			return new ELTHasHeaderWidget();
		}else if(widgetName.equals("ELT_COMPONENT_NAME_WIDGET")){
			return new MyCustomWidget();
		}else if(widgetName.equals("ELT_SAFE_PROPERTY_WIDGET")){
			return new ELTSafeWidget();
		}else{
			return new MyCustomWidget();
		}
		
	}
}
