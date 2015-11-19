package com.bitwise.app.graph.controller;



import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleEvent;

import com.bitwise.app.graph.figure.LabelFigure;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.LogicLabel;

public class LogicLabelEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener{
	protected AccessibleEditPart createAccessible() {
		return new AccessibleGraphicalEditPart() {
			public void getValue(AccessibleControlEvent e) {
				e.result = getLogicLabel().getLabelContents();
			}

			public void getName(AccessibleEvent e) {
				e.result = "Label";
			}
		};
	}

	
	protected IFigure createFigure() {
		LabelFigure label = new LabelFigure(1);
		Component component = ((ComponentEditPart) getParent()).getCastedModel();
		String compLabel = component.getLogicLabel().getLabelContents();
		
		label.setText(compLabel);
		label.setLocation(new Point(1, 1));
		return label;
	}

	private LogicLabel getLogicLabel() {
		return (LogicLabel) getModel();
	}

//	private void performDirectEdit() {
//		new LogicLabelEditManager(this, new LabelCellEditorLocator(
//				(LabelFigure) getFigure())).show();
//	}
//
//	public void performRequest(Request request) {
//		if (request.getType() == RequestConstants.REQ_DIRECT_EDIT)
//			performDirectEdit();
//	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equalsIgnoreCase("labelContents"))//$NON-NLS-1$
			refreshVisuals();
		else if (evt.getPropertyName().equalsIgnoreCase("size"))
			refreshVisuals();
	}

	protected void refreshVisuals() {
		
		((LabelFigure) getFigure()).setText(getLogicLabel()
				.getLabelContents());
		super.refreshVisuals();
	}


	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		
	}

}
