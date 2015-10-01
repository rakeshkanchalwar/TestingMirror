package com.bitwise.app.propertywindow.factory;

import com.bitwise.app.propertywindow.widgets.customwidgets.AbstractWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTCharacterSetWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTComponentBaseType;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTComponentNameWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTComponentType;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTDelimeterWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTFilePathWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTFilterWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTHasHeaderWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTOperationClassWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTPhaseWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTSafeWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.MyCustomWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.runtimeproperty.ELTRuntimePropertiesWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.schema.ELTFieldSequenceWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.schema.ELTSchemaGridWidget;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 08, 2015
 * 
 */

public class WidgetFactory {
	public AbstractWidget getWidget(String widgetName){
		if(widgetName.equals("ELT_SCHEMA_WIDGET")){
			return new ELTSchemaGridWidget();
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
		}else if(widgetName.equals("ELT_FILTER_PROPERTY_WIDGET")){
			return new ELTFilterWidget();
		}else if(widgetName.equals("ELT_OPERATIONAL_CLASS_WIDGET")){
			return new ELTOperationClassWidget();
		}else if(widgetName.equals("ELT_FIELD_SEQUENCE_WIDGET")){
			return new ELTSchemaGridWidget();   
		}else if(widgetName.equals("ELT_COMPONENT_BASETYPE_WIDGET")){
			return new ELTComponentBaseType();  
		}else if(widgetName.equals("ELT_COMPONENT_TYPE_WIDGET")){
			return new ELTComponentType(); 
		}
		else{ 
			return new MyCustomWidget();
		}
	}
}
