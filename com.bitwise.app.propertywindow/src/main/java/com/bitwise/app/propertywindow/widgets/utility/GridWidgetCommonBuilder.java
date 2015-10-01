package com.bitwise.app.propertywindow.widgets.utility;

import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Widget;

public interface GridWidgetCommonBuilder {
	public void createDefaultSchema(List grids,TableViewer tableViewer,Label errorLabel,Widget... widgets);

}
