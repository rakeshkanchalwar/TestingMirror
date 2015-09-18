package com.bitwise.app.propertywindow.factory;

import com.bitwise.app.propertywindow.customwidgets.AbstractWidget;
import com.bitwise.app.propertywindow.widgets.ELTCharacterSetWidget;
import com.bitwise.app.propertywindow.widgets.ELTComponentNameWidget;
import com.bitwise.app.propertywindow.widgets.ELTDelimeterWidget;
import com.bitwise.app.propertywindow.widgets.ELTFilePathWidget;
import com.bitwise.app.propertywindow.widgets.ELTHasHeaderWidget;
import com.bitwise.app.propertywindow.widgets.ELTPhaseWidget;
import com.bitwise.app.propertywindow.widgets.ELTSafeWidget;
import com.bitwise.app.propertywindow.widgets.MyCustomWidget;
import com.bitwise.app.propertywindow.widgets.runtimeproperties.ELTRuntimePropertiesWidget;
import com.bitwise.app.propertywindow.widgets.schemagrid.ELTSchemaWidget;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 08, 2015
 * 
 */

public class WidgetFactory {
	public AbstractWidget getWidget(String widgetName){
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
			return new ELTComponentNameWidget();
		}else if(widgetName.equals("ELT_SAFE_PROPERTY_WIDGET")){
			return new ELTSafeWidget();
			
		}
		else if(widgetName.equals("ELT_FIELD_SEQUENCE_WIDGET")){
			return new ELTSchemaWidget(); 
			
		}
		else{
			return new MyCustomWidget();
		}
		
	}
}
