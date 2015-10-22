package com.bitwise.app.project.structure.wizard;

import org.eclipse.e4.ui.css.swt.dom.WidgetElement;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.navigator.CommonNavigator;

/**
 * The Class ProjectExplorerView.
 * 
 * @author Bitwise
 */
public class ProjectExplorerView extends CommonNavigator {
@Override
public void createPartControl(Composite aParent) {
	super.createPartControl(aParent);
	setCSSID(getCommonViewer().getControl(), "projectExplorer");
	setCSSID(getCommonViewer().getTree(),"treeColor");
	
}
private void setCSSID(Widget widget, String name) {
	WidgetElement.setID(widget, name);
	WidgetElement.getEngine(widget).applyStyles(widget, true);
}

	
}
