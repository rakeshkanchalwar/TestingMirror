package com.bitwise.app.graph.propertywindow;

import java.util.ArrayList;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.eltproperties.adapters.ELTComponentPropertyAdapter;
import com.bitwise.app.eltproperties.exceptions.EmptyComponentPropertiesException;
import com.bitwise.app.eltproperties.property.IPropertyTreeBuilder;
import com.bitwise.app.eltproperties.property.Property;
import com.bitwise.app.eltproperties.property.PropertyTreeBuilder;
import com.bitwise.app.eltproperties.propertydialog.PropertyDialog;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.processor.DynamicClassProcessor;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Sep 25, 2015
 * 
 */

public class ProdELTPropertyWindow {

	Object componenetModel;
	
	public ProdELTPropertyWindow(Object componenetModel){
		this.componenetModel = componenetModel;
	}
	
	private Component getCastedModel() {
		return (Component) componenetModel;
	}
	
	//@Override
	public void open() {
		String componentName = DynamicClassProcessor.INSTANCE.getClazzName(componenetModel.getClass());
		Object rowProperties = XMLConfigUtil.INSTANCE.getComponent(componentName).getProperty();

		Component component = (Component) componenetModel;
		// Property Window will blink if we try to click outside without closing.
		ELTComponentPropertyAdapter eltComponentPropertyAdapter = new ELTComponentPropertyAdapter(rowProperties);
		try {
			eltComponentPropertyAdapter.transform();
			Display display = Display.getDefault();
			Shell shell = new Shell(display.getActiveShell(), SWT.WRAP | SWT.APPLICATION_MODAL);//

			ArrayList<Property> componentProperties = eltComponentPropertyAdapter.getProperties();
			IPropertyTreeBuilder propertyTreeBuilder = new PropertyTreeBuilder(componentProperties);
			System.out.println(propertyTreeBuilder.toString());
			PropertyDialog testwindow = new PropertyDialog(shell, propertyTreeBuilder.getPropertyTree(),
					component.getProperties(), component.getParent().getComponentNames());
			testwindow.open();
			
			int w = ((String) getCastedModel().getPropertyValue("name")).length()*7+40;
			int defaultWidth = (getCastedModel().getBasename().length()+3)*7+40;
			Dimension newSize = new Dimension(w < defaultWidth ? defaultWidth : w, 60);
			getCastedModel().setSize(newSize);
		} catch (EmptyComponentPropertiesException e) {
			e.printStackTrace();
		}
	}

}
