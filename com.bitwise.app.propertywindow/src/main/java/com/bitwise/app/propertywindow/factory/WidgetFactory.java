package com.bitwise.app.propertywindow.factory;

import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.propertywindow.fixedwidthschema.ELTFixedWidget;
import com.bitwise.app.propertywindow.property.ComponentConfigrationProperty;
import com.bitwise.app.propertywindow.property.ComponentMiscellaneousProperties;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;
import com.bitwise.app.propertywindow.widgets.customwidgets.AbstractWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.CustomWindowOnButtonWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTCharacterSetWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTColumnWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTComponentBaseType;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTComponentNameWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTComponentType;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTDelimeterWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTFilePathWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTFilterWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTHasHeaderWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTOperationClassWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTPhaseWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTRetentionlogicWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTSafeWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.ELTStrictWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.runtimeproperty.ELTRuntimePropertiesWidget;
import com.bitwise.app.propertywindow.widgets.customwidgets.schema.ELTGenericSchemaGridWidget;

/**
 * Factory for creating Widgets
 * @author Bitwise
 * Sep 08, 2015
 * 
 */

public class WidgetFactory {
	public static final WidgetFactory INSTANCE = new WidgetFactory();
	private static final Logger logger = LogFactory.INSTANCE.getLogger(WidgetFactory.class);
	
	public enum Widgets{
		SCHEMA_WIDGET(ELTGenericSchemaGridWidget.class),
		FIELD_SEQUENCE_WIDGET(ELTGenericSchemaGridWidget.class),
		FIXED_WIDGET(ELTFixedWidget.class),
		RUNTIME_PROPERTIES_WIDGET(ELTRuntimePropertiesWidget.class),
		FILE_PATH_WIDGET(ELTFilePathWidget.class),
		CHARACTER_SET_WIDGET(ELTCharacterSetWidget.class),
		DELIMETER_WIDGET(ELTDelimeterWidget.class),
		PHASE_WIDGET(ELTPhaseWidget.class),
		HAS_HEADER_WIDGET(ELTHasHeaderWidget.class),
		COMPONENT_NAME_WIDGET(ELTComponentNameWidget.class),
		SAFE_PROPERTY_WIDGET(ELTSafeWidget.class),
		FILTER_PROPERTY_WIDGET(ELTFilterWidget.class),
		OPERATIONAL_CLASS_WIDGET(ELTOperationClassWidget.class),
		COMPONENT_BASETYPE_WIDGET(ELTComponentBaseType.class),
		COMPONENT_TYPE_WIDGET(ELTComponentType.class),
		STRICT_CLASS_WIDGET(ELTStrictWidget.class),
		RETENTION_LOGIC_WIDGET(ELTRetentionlogicWidget.class),
		COLUMN_NAME_WIDGET(ELTColumnWidget.class),
		CUSTOM_WINDOW_ON_BUTTON_WIDGET(CustomWindowOnButtonWidget.class);
		
		private Class<?> clazz = null;
		private Widgets(Class<?> clazz) {
			this.clazz = clazz;
		}
		
		public Class<?> getClazz(){
			return this.clazz;
		}
	}

	public AbstractWidget getWidget(String widgetName, ComponentConfigrationProperty componentConfigProperty, 
			ComponentMiscellaneousProperties componentMiscProperties, PropertyDialogButtonBar propertyDialogButtonBar){
		try {
			Widgets widget = Widgets.valueOf(widgetName);
			return (AbstractWidget) widget.getClazz().getDeclaredConstructor(ComponentConfigrationProperty.class,
					ComponentMiscellaneousProperties.class,	PropertyDialogButtonBar.class).
					newInstance(componentConfigProperty, componentMiscProperties, propertyDialogButtonBar);
		
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
				InvocationTargetException | NoSuchMethodException | SecurityException exception) {
			logger.error("Failed to create widget for class : {}", widgetName);
			throw new RuntimeException("Failed to instantiate the Listner {}" + widgetName);
		}
	}
}
