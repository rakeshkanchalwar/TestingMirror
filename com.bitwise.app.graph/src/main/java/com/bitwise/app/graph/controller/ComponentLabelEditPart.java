package com.bitwise.app.graph.controller;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.bitwise.app.graph.figure.ComponentLabelFigure;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.ComponentLabel;

public class ComponentLabelEditPart extends AbstractGraphicalEditPart{

	@Override
	protected IFigure createFigure() {
		
		Component component = ((ComponentEditPart) getParent()).getCastedModel();
		
		String compLabel = component.getComponentLabel().getComponentLabel();
		
		ComponentLabelFigure componentLabelFigure = new ComponentLabelFigure(compLabel);
		componentLabelFigure.setLocation(new Point(1, 1));
		return componentLabelFigure;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	protected void refreshVisuals() {
		
		System.out.println("ComponentLabelEditPart refreshVisuals called.");
		
		String label = ((ComponentLabel)getModel()).getComponentLabel();
		((ComponentLabelFigure)getFigure()).setComponentLabel(label);
		
	}

}
