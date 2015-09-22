package com.bitwise.app.propertywindow.widgets.customwidgets;

import java.util.LinkedHashMap;

import org.eclipse.jface.viewers.TableViewer;

import com.bitwise.app.propertywindow.widgets.gridwidgets.basic.ELTTableViewer;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.AbstractELTContainerWidget;
import com.bitwise.app.propertywindow.widgets.gridwidgets.container.ELTDefaultSubgroupComposite;

public class ELTSchemaWidget extends AbstractWidget{

	@Override
	public void attachToPropertySubGroup(AbstractELTContainerWidget subGroup) {
		// TODO Auto-generated method stub
		
		ELTDefaultSubgroupComposite eltSuDefaultSubgroupComposite = new ELTDefaultSubgroupComposite(subGroup.getContainerControl());
		eltSuDefaultSubgroupComposite.createContainerWidget();
		
		
		ELTTableViewer eltTableViewer = new ELTTableViewer();
		
		eltTableViewer.getSWTWidgetControl();
		TableViewer tableViewer=(TableViewer) eltTableViewer.getJfaceWidgetControl();
		
		eltSuDefaultSubgroupComposite.attachWidget(eltTableViewer);
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
