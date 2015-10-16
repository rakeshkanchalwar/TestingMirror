package com.bitwise.app.graph.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ManhattanConnectionRouter;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;

import com.bitwise.app.graph.model.Container;
import com.bitwise.app.graph.policy.ShapesXYLayoutEditPolicy;

/**
 * The Class ContainerEditPart.
 */
public class ContainerEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener{
	
	/**
	 * Upon activation, attach to the model element as a property change
	 * listener.
	 */
	public void activate() {
		if (!isActive()) {
			super.activate();
			((Container) getModel()).addPropertyChangeListener(this);
		}
	}
	
	/**
	 * Upon deactivation, detach from the model element as a property change
	 * listener.
	 */
	public void deactivate() {
		if (isActive()) {
			super.deactivate();
			((Container) getModel()).removePropertyChangeListener(this);
		}
	}
	
	@Override
	protected IFigure createFigure() {
		Figure f = new FreeformLayer();
		f.setBorder(new MarginBorder(3));
		f.setLayoutManager(new FreeformLayout());

		// Create the static router for the connection layer
		ConnectionLayer connLayer = (ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER);
		connLayer.setConnectionRouter(new ManhattanConnectionRouter());
		return f;
	}
	@Override
	protected void createEditPolicies() {
		// disallows the removal of this edit part from its parent
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
		// handles constraint changes (e.g. moving and/or resizing) of model elements
		// and creation of new model elements
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new ShapesXYLayoutEditPolicy());
	}

	@Override
	protected List getModelChildren() {
		// return a list of models
		return ((Container)getModel()).getChildren();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.beans.PropertyChangeListener#propertyChange(PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		// these properties are fired when model are added into or removed from
		// the Container instance and must cause a call of refreshChildren()
		// to update the diagram's contents.
		if (Container.CHILD_ADDED_PROP.equals(prop)	|| Container.CHILD_REMOVED_PROP.equals(prop)) {
			refreshChildren();
		}
	}
}
