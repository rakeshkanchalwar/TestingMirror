package com.bitwise.app.graph.components.part;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;

import com.bitwise.app.common.util.ComponentCacheUtil;
import com.bitwise.app.graph.components.dynamic.DynamicClassProcessor;
import com.bitwise.app.graph.components.figure.ComponentFigure;
import com.bitwise.app.graph.components.model.Component;
import com.bitwise.app.graph.components.model.GenericComponent;


public class ComponentEditPart extends CommonPart implements
 NodeEditPart {
	
	private ConnectionAnchor anchor;

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		IFigure f = createFigureForModel();
		f.setOpaque(true); // non-transparent figure
		f.setBackgroundColor(ColorConstants.gray);
		String componentName = DynamicClassProcessor.INSTANCE.getClazzName(getModel().getClass());
		((Component) getModel()).setDescriptors(ComponentCacheUtil.INSTANCE.getPropertyDescriptors(componentName)); 
		((Component) getModel()).setProperties(ComponentCacheUtil.INSTANCE.getProperties(componentName));
		
		return f;
	}

	/**
	 * Return a IFigure depending on the instance of the current model element.
	 * This allows this EditPart to be used for both sublasses of Shape.
	 */
	private IFigure createFigureForModel() {
		ComponentFigure figure;
		
		if(DynamicClassProcessor.INSTANCE.contains(getModel().getClass())){
			//figure = new ComponentFigure(DynamicClassProcessor.INSTANCE.getClazzName(getModel().getClass()));
			figure = new ComponentFigure(getModel(), DynamicClassProcessor.INSTANCE.getClazzName(getModel().getClass()));
			return figure;
		}
		
		/*if(getModel() instanceof GenericComponent){
			GenericComponent tempModel = (GenericComponent) getModel();
			figure = new ComponentFigure("Input");			
			
			return figure;
		}*/else {
			// if Shapes gets extended the conditions above must be updated
			throw new IllegalArgumentException();
		}
	}

	private Component getCastedModel() {
		return (Component) getModel();
	}

	protected ConnectionAnchor getConnectionAnchor() {
		if (getModel() instanceof GenericComponent)
			anchor = new ChopboxAnchor(getFigure());
		else
			throw new IllegalArgumentException("unexpected model");
		return anchor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelSourceConnections
	 * ()
	 */
	protected List getModelSourceConnections() {
		return getCastedModel().getSourceConnections();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelTargetConnections
	 * ()
	 */
	protected List getModelTargetConnections() {
		return getCastedModel().getTargetConnections();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef
	 * .ConnectionEditPart)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		return getConnectionAnchor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef
	 * .Request)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return getConnectionAnchor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef
	 * .ConnectionEditPart)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		return getConnectionAnchor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef
	 * .Request)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return getConnectionAnchor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.
	 * PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		if (Component.SIZE_PROP.equals(prop) || Component.LOCATION_PROP.equals(prop)
				||Component.NAME_PROP.equals(prop)) {
			System.out.println("ComponentEditPart:refreshVisuals()");
			refreshVisuals();
		} else if (Component.SOURCE_CONNECTIONS_PROP.equals(prop)) {
			refreshSourceConnections();
		} else if (Component.TARGET_CONNECTIONS_PROP.equals(prop)) {
			refreshTargetConnections();
		}
	}

	protected void refreshVisuals() {
		// notify parent container of changed position & location
		// if this line is removed, the XYLayoutManager used by the parent
		// container
		// (the Figure of the ShapesDiagramEditPart), will not know the bounds
		// of this figure
		// and will not draw it correctly.
		Component comp = getCastedModel();
		
		System.out.println("name: "+comp.getComponentName());
		System.out.println("getCastedModel().getLocation(): "+getCastedModel().getLocation());
		System.out.println("getCastedModel().getSize(): "+getCastedModel().getSize());
		
		Rectangle bounds = new Rectangle(getCastedModel().getLocation(),
				getCastedModel().getSize());
		ComponentFigure c = (ComponentFigure) getFigure();
		c.getLabelName().setText(comp.getComponentName());
		
		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), bounds);

	}

}
