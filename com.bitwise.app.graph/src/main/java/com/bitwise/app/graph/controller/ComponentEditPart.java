package com.bitwise.app.graph.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;

import com.bitwise.app.common.component.config.Policy;
import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.graph.editor.ETLGraphicalEditor;
import com.bitwise.app.graph.figure.ComponentFigure;
import com.bitwise.app.graph.figure.factory.ModelFigureFactory;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Link;
import com.bitwise.app.graph.processor.DynamicClassProcessor;
import com.bitwise.app.graph.propertywindow.ELTPropertyWindow;


// TODO: Auto-generated Javadoc
/**
 * The Class ComponentEditPart.
 */
public class ComponentEditPart extends AbstractGraphicalEditPart implements
		NodeEditPart, PropertyChangeListener {
	
	private static final Logger logger = LogFactory.INSTANCE.getLogger(ComponentEditPart.class);

	/**
	 * Upon activation, attach to the model element as a property change
	 * listener.
	 */
	public void activate() {
		if (!isActive()) {
			super.activate();
			((Component) getModel()).addPropertyChangeListener(this);
		}
	}

	/**
	 * Upon deactivation, detach from the model element as a property change
	 * listener.
	 */
	public void deactivate() {
		if (isActive()) {
			super.deactivate();
			((Component) getModel()).removePropertyChangeListener(this);
		}
	}

	@Override
	protected void createEditPolicies() {
		String componentName = DynamicClassProcessor.INSTANCE
				.getClazzName(getModel().getClass());
		try {
			for (com.bitwise.app.common.component.config.Component component : XMLConfigUtil.INSTANCE
					.getComponentConfig()) {
				if (component.getName().equalsIgnoreCase(componentName)) {
					applyGeneralPolicy(component);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} 
	}

	/**
	 * Apply general policy.
	 * 
	 * @param component
	 *            the component
	 * @throws Exception
	 *             the exception
	 */
	public void applyGeneralPolicy(
			com.bitwise.app.common.component.config.Component component)
			throws Exception {

		for (Policy generalPolicy : XMLConfigUtil.INSTANCE
				.getPoliciesForComponent(component)) {
			try {
				AbstractEditPolicy editPolicy = (AbstractEditPolicy) Class
						.forName(generalPolicy.getValue()).newInstance();
				installEditPolicy(generalPolicy.getName(), editPolicy);
			} catch (Exception exception) {
				// TODO : add logger
				logger.error(exception.getMessage());
				throw exception;
			}
		}
	}

	@Override
	protected IFigure createFigure() {
		
		IFigure f = createFigureForModel();
		f.setOpaque(true); // non-transparent figure
		f.setBackgroundColor(ColorConstants.white);
		return f;
	}

	private IFigure createFigureForModel() {
		String componentName = DynamicClassProcessor.INSTANCE
				.getClazzName(getModel().getClass());
		return new ModelFigureFactory().createFigureForComponent(componentName);
	}

	public Component getCastedModel() {
		return (Component) getModel();
	}

	/**
	 * Map connection anchor to terminal.
	 * 
	 * @param c
	 *            the c
	 * @return the string
	 */
	public final String mapConnectionAnchorToTerminal(ConnectionAnchor c) {

		return getComponentFigure().getConnectionAnchorName(c);
	}

	
	@Override
	protected List<Link> getModelSourceConnections() {
		return getCastedModel().getSourceConnections();
	}

	@Override
	protected List<Link> getModelTargetConnections() {
		return getCastedModel().getTargetConnections();
	}

	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {

		Link wire = (Link) connection.getModel();
		return getComponentFigure().getConnectionAnchor(
				wire.getSourceTerminal());
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		Point pt = new Point(((DropRequest) request).getLocation());
		return getComponentFigure().getSourceConnectionAnchorAt(pt);
		
	}

	protected ComponentFigure getComponentFigure() {
		return (ComponentFigure) getFigure();
	}

	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {

		Link wire = (Link) connection.getModel();
		return getComponentFigure().getConnectionAnchor(
				wire.getTargetTerminal());
	}

	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		Point pt = new Point(((DropRequest) request).getLocation());
		return getComponentFigure().getTargetConnectionAnchorAt(pt);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.
	 * PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {

		String prop = evt.getPropertyName();
		if (Component.Props.SIZE_PROP.eq(prop)
				|| Component.Props.LOCATION_PROP.eq(prop)) {
			refreshVisuals();
		} else if (Component.Props.OUTPUTS.eq(prop)) {
			refreshSourceConnections();
		} else if (Component.Props.INPUTS.eq(prop)) {
			refreshTargetConnections();
		}
	}

	protected void refreshVisuals() {
		// notify parent container of changed position & location
		// if this line is removed, the XYLayoutManager used by the parent
		// container
		// (the Figure of the ShapesDiagramEditPart), will not know the bounds
		// of this figure and will not draw it correctly.
		/*
		 * Component component = (Component) getModel();
		 * 
		 * Rectangle bounds = new Rectangle(component.getLocation(),
		 * component.getSize()); ((ContainerEditPart)
		 * getParent()).setLayoutConstraint(this, getFigure(), bounds);
		 */
		
		Component comp = getCastedModel();
		ComponentFigure c = getComponentFigure();
		c.setLabelName((String) comp.getPropertyValue("name"));
		logger.debug("New component/figure name :"+c.getLabelName());
		//comp.setSize(newSize);
		Rectangle bounds = new Rectangle(getCastedModel().getLocation(),
				getCastedModel().getSize());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), bounds);
	}

	@Override
	public void performRequest(Request req) {
		// Opens Property Window only on Double click.
		if (req.getType().equals(RequestConstants.REQ_OPEN)) {
			ELTPropertyWindow eltPropertyWindow = new ELTPropertyWindow(getModel());
			//ProdELTPropertyWindow eltPropertyWindow = new ProdELTPropertyWindow(getModel());
			eltPropertyWindow.open();
			
			logger.debug("Updated dimentions: " + getCastedModel().getSize().height + ":"
							+ getCastedModel().getSize().width);
			
			refreshVisuals();
			
			ETLGraphicalEditor eltGraphicalEditor=(ETLGraphicalEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			if(eltPropertyWindow.isPropertyChanged()){
				eltGraphicalEditor.setDirty(true);
			}
			
			super.performRequest(req);
		}
	}
}
