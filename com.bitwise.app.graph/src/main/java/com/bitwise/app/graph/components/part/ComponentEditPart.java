package com.bitwise.app.graph.components.part;

import java.awt.Label;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.bitwise.app.graph.components.commands.ConnectionCreateCommand;
import com.bitwise.app.graph.components.commands.ConnectionReconnectCommand;
import com.bitwise.app.graph.components.editpolicies.AppRenamePolicy;
import com.bitwise.app.graph.components.editpolicies.GraphComponentEditPolicy;
import com.bitwise.app.graph.components.figure.ComponentFigure;
import com.bitwise.app.graph.components.model.Component;
import com.bitwise.app.graph.components.model.Connection;
import com.bitwise.app.graph.components.model.GenericComponent;
import com.bitwise.app.graph.components.model.ModelElement;
import com.bitwise.app.graph.components.model.TestInputComp;
import com.bitwise.app.graph.components.model.TestOutputComp;


public class ComponentEditPart extends AbstractGraphicalEditPart implements
PropertyChangeListener, NodeEditPart {
	
	private ConnectionAnchor anchor;

	/**
	 * Upon activation, attach to the model element as a property change
	 * listener.
	 */
	public void activate() {
		if (!isActive()) {
			super.activate();
			((ModelElement) getModel()).addPropertyChangeListener(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		// allow removal of the associated model element
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new GraphComponentEditPolicy());
		
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());
		
		
		// allow the creation of connections and
		// and the reconnection of connections between Shape instances
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new GraphicalNodeEditPolicy() {
					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#
					 * getConnectionCompleteCommand
					 * (org.eclipse.gef.requests.CreateConnectionRequest)
					 */
					protected Command getConnectionCompleteCommand(
							CreateConnectionRequest request) {
						ConnectionCreateCommand cmd = (ConnectionCreateCommand) request
								.getStartCommand();
						cmd.setTarget((Component) getHost().getModel());
						return cmd;
					}

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#
					 * getConnectionCreateCommand
					 * (org.eclipse.gef.requests.CreateConnectionRequest)
					 */
					protected Command getConnectionCreateCommand(
							CreateConnectionRequest request) {
						Component source = (Component) getHost().getModel();
						int style = ((Integer) request.getNewObjectType())
								.intValue();
						ConnectionCreateCommand cmd = new ConnectionCreateCommand(
								source, style);
						request.setStartCommand(cmd);
						return cmd;
					}

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#
					 * getReconnectSourceCommand
					 * (org.eclipse.gef.requests.ReconnectRequest)
					 */
					protected Command getReconnectSourceCommand(
							ReconnectRequest request) {
						Connection conn = (Connection) request
								.getConnectionEditPart().getModel();
						Component newSource = (Component) getHost().getModel();
						ConnectionReconnectCommand cmd = new ConnectionReconnectCommand(
								conn);
						cmd.setNewSource(newSource);
						return cmd;
					}

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#
					 * getReconnectTargetCommand
					 * (org.eclipse.gef.requests.ReconnectRequest)
					 */
					protected Command getReconnectTargetCommand(
							ReconnectRequest request) {
						Connection conn = (Connection) request
								.getConnectionEditPart().getModel();
						Component newTarget = (Component) getHost().getModel();
						ConnectionReconnectCommand cmd = new ConnectionReconnectCommand(
								conn);
						cmd.setNewTarget(newTarget);
						return cmd;
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		IFigure f = createFigureForModel();
		f.setOpaque(true); // non-transparent figure
		f.setBackgroundColor(ColorConstants.white);
		return f;
	}

	/**
	 * Return a IFigure depending on the instance of the current model element.
	 * This allows this EditPart to be used for both sublasses of Shape.
	 */
	private IFigure createFigureForModel() {
		ComponentFigure figure;
		
		/* Shrirang: Changes
		if(getModel() instanceof GenericComponent){
			GenericComponent tempModel = (GenericComponent) getModel();
			figure = new ComponentFigure("Input");			
			
			return figure;
		}else {
			// if Shapes gets extended the conditions above must be updated
			throw new IllegalArgumentException();
		}*/
		if(getModel() instanceof TestInputComp){
			GenericComponent tempModel = (GenericComponent) getModel();
			figure = new ComponentFigure("Input");			
			
			return figure;
		}else if (getModel() instanceof TestOutputComp){
			GenericComponent tempModel = (GenericComponent) getModel();
			figure = new ComponentFigure("Output");			
			
			return figure;
		}
		else {
			// if Shapes gets extended the conditions above must be updated
			throw new IllegalArgumentException();
		}
		
	}

	
	/**
	 * Upon deactivation, detach from the model element as a property change
	 * listener.
	 */
	public void deactivate() {
		if (isActive()) {
			super.deactivate();
			((ModelElement) getModel()).removePropertyChangeListener(this);
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
