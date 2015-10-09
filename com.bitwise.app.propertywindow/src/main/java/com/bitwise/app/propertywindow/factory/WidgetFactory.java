package com.bitwise.app.propertywindow.factory;

import com.bitwise.app.propertywindow.property.ComponentConfigrationProperty;
import com.bitwise.app.propertywindow.property.ComponentMiscellaneousProperties;
import com.bitwise.app.propertywindow.property.ELTComponenetProperties;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
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
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTStrictWidget;
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
	public static AbstractWidget getWidget(String widgetName, ComponentConfigrationProperty componentConfigrationProperty, ComponentMiscellaneousProperties componentMiscellaneousProperties, PropertyDialogButtonBar propertyDialogButtonBar){
		if(widgetName.equals("ELT_SCHEMA_WIDGET")){
			return new ELTSchemaGridWidget(componentConfigrationProperty,componentMiscellaneousProperties,propertyDialogButtonBar);
		}else if(widgetName.equals("ELT_RUNTIME_PROPERTIES_WIDGET")){
			return new ELTRuntimePropertiesWidget(componentConfigrationProperty,componentMiscellaneousProperties,propertyDialogButtonBar);
		}else if(widgetName.equals("ELT_FILE_PATH_WIDGET")){
			return new ELTFilePathWidget(componentConfigrationProperty,componentMiscellaneousProperties,propertyDialogButtonBar);
		}else if(widgetName.equals("ELT_CHARACTER_SET_WIDGET")){
			return new ELTCharacterSetWidget(componentConfigrationProperty,componentMiscellaneousProperties,propertyDialogButtonBar);
		}else if(widgetName.equals("ELT_DELIMETER_WIDGET")){
			//return new ELTDelimeterWidget();
			return new ELTOperationClassWidget(componentConfigrationProperty,componentMiscellaneousProperties,propertyDialogButtonBar);
		}else if(widgetName.equals("ELT_PHASE_WIDGET")){
			return new ELTPhaseWidget(componentConfigrationProperty,componentMiscellaneousProperties,propertyDialogButtonBar);
		}else if(widgetName.equals("ELT_HAS_HEADER_WIDGET")){
			return new ELTHasHeaderWidget(componentConfigrationProperty,componentMiscellaneousProperties,propertyDialogButtonBar);
		}else if(widgetName.equals("ELT_COMPONENT_NAME_WIDGET")){
			return new ELTComponentNameWidget(componentConfigrationProperty,componentMiscellaneousProperties,propertyDialogButtonBar);
		}else if(widgetName.equals("ELT_SAFE_PROPERTY_WIDGET")){
			return new ELTSafeWidget(componentConfigrationProperty,componentMiscellaneousProperties,propertyDialogButtonBar);
		}else if(widgetName.equals("ELT_FILTER_PROPERTY_WIDGET")){
			return new ELTFilterWidget(componentConfigrationProperty,componentMiscellaneousProperties,propertyDialogButtonBar);
		}else if(widgetName.equals("ELT_OPERATIONAL_CLASS_WIDGET")){
			return new ELTOperationClassWidget(componentConfigrationProperty,componentMiscellaneousProperties,propertyDialogButtonBar);
		}else if(widgetName.equals("ELT_FIELD_SEQUENCE_WIDGET")){
			return new ELTSchemaGridWidget(componentConfigrationProperty,componentMiscellaneousProperties,propertyDialogButtonBar);   
		}else if(widgetName.equals("ELT_COMPONENT_BASETYPE_WIDGET")){
			return new ELTComponentBaseType(componentConfigrationProperty,componentMiscellaneousProperties,propertyDialogButtonBar);  
		}else if(widgetName.equals("ELT_COMPONENT_TYPE_WIDGET")){
			return new ELTComponentType(componentConfigrationProperty,componentMiscellaneousProperties,propertyDialogButtonBar); 
		}else if(widgetName.equals("ELT_STRICT_CLASS_WIDGET")){
			return new ELTStrictWidget(componentConfigrationProperty,componentMiscellaneousProperties,propertyDialogButtonBar);
		}
		else{ 
			return new MyCustomWidget(componentConfigrationProperty,componentMiscellaneousProperties,propertyDialogButtonBar);
		}
	}
}
