package com.bitwise.app.graph.propertywindow;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.processor.DynamicClassProcessor;
import com.bitwise.app.propertywindow.adapters.ELTComponentPropertyAdapter;
import com.bitwise.app.propertywindow.exceptions.EmptyComponentPropertiesException;
import com.bitwise.app.propertywindow.property.ELTComponenetProperties;
import com.bitwise.app.propertywindow.property.IPropertyTreeBuilder;
import com.bitwise.app.propertywindow.property.Property;
import com.bitwise.app.propertywindow.property.PropertyTreeBuilder;
import com.bitwise.app.propertywindow.propertydialog.PropertyDialog;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 25, 2015
 * 
 */

public class ELTPropertyWindow implements IELTPropertyWindow{

	Object componenetModel;
	ELTComponenetProperties eltComponenetProperties;
	Component component;
	private boolean propertyChanged = false;
	private ELTPropertyWindow(){
		
	}
	
	public ELTPropertyWindow(Object componenetModel){
		this.componenetModel = componenetModel;
		component = getCastedModel();
		eltComponenetProperties = getELTComponenetProperties();
	}

	private Component getCastedModel() {
		return (Component) componenetModel;
	}
	
	private ELTComponenetProperties getELTComponenetProperties(){
		LinkedHashMap<String, Object> componentConfigurationProperties = component.getProperties();
		LinkedHashMap<String, Object> ComponentMiscellaneousProperties = getComponentMiscellaneousProperties();
		
		ELTComponenetProperties eltComponenetProperties = new ELTComponenetProperties(componentConfigurationProperties, ComponentMiscellaneousProperties);
		return eltComponenetProperties;
	}

	private LinkedHashMap<String, Object> getComponentMiscellaneousProperties() {
		LinkedHashMap<String, Object> ComponentMiscellaneousProperties = new LinkedHashMap<>();
		
		ComponentMiscellaneousProperties.put("componentNames", component.getParent().getComponentNames());
		ComponentMiscellaneousProperties.put("componentBaseType", component.getCategory());
		ComponentMiscellaneousProperties.put("componentType", component.getBasename());
		return ComponentMiscellaneousProperties;
	}
	
	private Property getComponentBaseTypeProperty(){
		Property property = new Property("String", "Base Type", "ELT_COMPONENT_BASETYPE_WIDGET").group("GENERAL").subGroup("DISPLAY");
		return property;
	}
	
	private Property getComponentTypeProperty(){
		Property property = new Property("String", "Type", "ELT_COMPONENT_TYPE_WIDGET").group("GENERAL").subGroup("DISPLAY");
		return property;
	}
	
	//@Override
	public void open() {
		Object rowProperties = getComponentPropertiesFromComponentXML();		
		try {			
			Shell shell = getParentShellForPropertyWindow();
				
			ArrayList<Property> componentProperties = transformComponentPropertiesToPropertyWindowUnderstantableFormat(rowProperties);
			componentProperties.add(getComponentBaseTypeProperty());
			componentProperties.add(getComponentTypeProperty());
			
			IPropertyTreeBuilder propertyTreeBuilder = new PropertyTreeBuilder(componentProperties);

			PropertyDialog propertyDialog = new PropertyDialog(shell, propertyTreeBuilder.getPropertyTree(),
					eltComponenetProperties);
			propertyDialog.open();

			component.setSize(getNewComponentSize());
			
			propertyChanged = propertyDialog.isPropertyChanged();
			
		} catch (EmptyComponentPropertiesException e) {
			e.printStackTrace();
		}
	}

	public boolean isPropertyChanged(){
			return propertyChanged;
	}
	
	private Shell getParentShellForPropertyWindow() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display.getActiveShell(), SWT.WRAP | SWT.APPLICATION_MODAL);
		return shell;
	}

	private Dimension getNewComponentSize() {
		int w = ((String) component.getPropertyValue("name")).length()*7+40;
		int defaultWidth = (component.getBasename().length()+3)*7+30;
		int defaultHeight = (defaultWidth * 6)/8;
		System.out.println("defaultHeight:"+defaultHeight);
		Dimension newSize = new Dimension(w < defaultWidth ? defaultWidth : w, defaultHeight);
		return newSize;
	}

	private ArrayList<Property> transformComponentPropertiesToPropertyWindowUnderstantableFormat(
			Object rowProperties) throws EmptyComponentPropertiesException {
		ELTComponentPropertyAdapter eltComponentPropertyAdapter = new ELTComponentPropertyAdapter(rowProperties);
		eltComponentPropertyAdapter.transform();
		ArrayList<Property> componentProperties = eltComponentPropertyAdapter.getProperties();
		return componentProperties;
	}

	private Object getComponentPropertiesFromComponentXML() {
		String componentName = DynamicClassProcessor.INSTANCE.getClazzName(componenetModel.getClass());
		Object rowProperties = XMLConfigUtil.INSTANCE.getComponent(componentName).getProperty();
		return rowProperties;
	}
}
