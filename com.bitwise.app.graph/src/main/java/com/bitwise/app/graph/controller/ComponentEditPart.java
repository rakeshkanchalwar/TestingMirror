package com.bitwise.app.graph.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
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
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.xml.sax.SAXException;

import com.bitwise.app.common.component.config.Policy;
import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.eltproperties.adapters.ELTComponentPropertyAdapter;
import com.bitwise.app.eltproperties.exceptions.EmptyComponentPropertiesException;
import com.bitwise.app.eltproperties.property.IPropertyTreeBuilder;
import com.bitwise.app.eltproperties.property.Property;
import com.bitwise.app.eltproperties.property.PropertyTreeBuilder;
import com.bitwise.app.eltproperties.propertydialog.PropertyDialog;
import com.bitwise.app.graph.figure.ComponentFigure;
import com.bitwise.app.graph.figure.CopyToManyFigure;
import com.bitwise.app.graph.figure.FilterFigure;
import com.bitwise.app.graph.figure.FixedConnectionAnchor;
import com.bitwise.app.graph.figure.GatherFigure;
import com.bitwise.app.graph.figure.InputFigure;
import com.bitwise.app.graph.figure.OutputFigure;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.ComponentConnection;
import com.bitwise.app.graph.model.InputComponent;
import com.bitwise.app.graph.model.OutputComponent;
import com.bitwise.app.graph.processor.DynamicClassProcessor;

public class ComponentEditPart extends AbstractGraphicalEditPart implements
		NodeEditPart, PropertyChangeListener {

	private ConnectionAnchor anchor;

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
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void applyGeneralPolicy(
			com.bitwise.app.common.component.config.Component component)
			throws RuntimeException, SAXException, IOException {

		for (Policy generalPolicy : XMLConfigUtil.INSTANCE
				.getPoliciesForComponent(component)) {
			try {
				AbstractEditPolicy editPolicy = (AbstractEditPolicy) Class
						.forName(generalPolicy.getValue()).newInstance();
				installEditPolicy(generalPolicy.getName(), editPolicy);
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException exception) {
				// TODO : add logger
				throw new RuntimeException();
			}
		}
	}

	@Override
	protected IFigure createFigure() {
		IFigure f = createFigureForModel();
		f.setOpaque(true); // non-transparent figure
		f.setBackgroundColor(ColorConstants.white);
//		f.setForegroundColor(new Color(null,6, 105, 138));
//		f.setBorder(new LineBorder(2));
		return f;
	}

	private IFigure createFigureForModel() {
		String componentName = DynamicClassProcessor.INSTANCE
				.getClazzName(getModel().getClass());
		com.bitwise.app.common.component.config.Component component = XMLConfigUtil.INSTANCE
				.getComponent(componentName);
		System.out.println("componentName in createFigureForModel: "+componentName );
		
		if(componentName.equals("Input")){
			System.out.println("in instanceof InputComponent");
//			BigInteger outputPorts = component.getOutputPort()
//					.getNumberOfPorts();
//			if (component.getOutputPort().isAllowMultipleLinks() != null) {
//				boolean isAllowedMultipleLinks = component.getOutputPort()
//						.isAllowMultipleLinks();
//				((Component) getModel())
//						.setAllowMultipleLinks(isAllowedMultipleLinks);
//			}
			return new InputFigure( componentName);
		
		} else	if(componentName.equals("Output")){
			System.out.println("in instanceof OutputComponent");
			return new OutputFigure(componentName);
		
		} else	if(componentName.equals("Gather")){
			System.out.println("in instanceof GatherComponent");
			((Component) getModel())
			.setAllowMultipleLinks(true);
			return new GatherFigure(componentName);
		
		}else	if(componentName.equals("CopyToMany")){
			System.out.println("in instanceof CopyToManyComponent");
			((Component) getModel())
			.setAllowMultipleLinks(true);
			return new CopyToManyFigure(componentName);
		
		}else	if(componentName.equals("Filter")){
			System.out.println("in instanceof FilterComponent");
			((Component) getModel())
			.setAllowMultipleLinks(true);
			return new FilterFigure(componentName);
		} 
		
		else {
			throw new IllegalArgumentException();
		}
	}

	public Component getCastedModel() {
		return (Component) getModel();
	}

	public final String mapConnectionAnchorToTerminal(ConnectionAnchor c) {
		System.out.println("ComponentEditPart:mapConnectionAnchorToTerminal");
		System.out.println("ConnectionAnchor.toString()"+c.toString());;
		return getComponentFigure().getConnectionAnchorName(c);
	}

	protected ConnectionAnchor getConnectionAnchor() {
		
		if (anchor == null) {
			if (getModel() instanceof InputComponent) {
				anchor = new FixedConnectionAnchor(getFigure());
			} else if (getModel() instanceof OutputComponent)
				anchor = new FixedConnectionAnchor(getFigure());

			else
				// if Components gets extended the conditions above must be
				// updated
				throw new IllegalArgumentException("unexpected model");

		}
		return anchor;
	}

	protected List<ComponentConnection> getModelSourceConnections() {
		return getCastedModel().getSourceConnections();
	}

	protected List<ComponentConnection> getModelTargetConnections() {
		return getCastedModel().getTargetConnections();
	}

	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {

		ComponentConnection wire = (ComponentConnection) connection.getModel();
		return getComponentFigure().getConnectionAnchor(
				wire.getSourceTerminal());
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {

		System.out.println("ComponentEditPart:getSourceConnectionAnchor");
		Point pt = new Point(((DropRequest) request).getLocation());
		return getComponentFigure().getSourceConnectionAnchorAt(pt);

	}

	protected ComponentFigure getComponentFigure() {
		return (ComponentFigure) getFigure();
	}

	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {

		ComponentConnection wire = (ComponentConnection) connection.getModel();
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
		if (Component.SIZE_PROP.equals(prop)
				|| Component.LOCATION_PROP.equals(prop)) {
			refreshVisuals();
		} else 
			
		if (Component.OUTPUTS.equals(prop)) {
			refreshSourceConnections();
		} else if (Component.INPUTS.equals(prop)) {
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
		c.setLabelName((String) comp.getPropertyValue("Name"));
		Rectangle bounds = new Rectangle(getCastedModel().getLocation(),
				getCastedModel().getSize());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), bounds);
	}

	@Override
	public void performRequest(Request req) {
		// TODO Auto-generated method stub
		if (req.getType().equals(RequestConstants.REQ_OPEN))// Opens Property
															// Window only on
															// Double click.
		{
			String componentName = DynamicClassProcessor.INSTANCE
					.getClazzName(getModel().getClass());
			Object rowProperties = XMLConfigUtil.INSTANCE.getComponent(
					componentName).getProperty();

			Component component = (Component) getModel();

			ELTComponentPropertyAdapter eltComponentPropertyAdapter = new ELTComponentPropertyAdapter(
					rowProperties);// Property Window will blink if we try to
									// click outside without closing.
			try {
				eltComponentPropertyAdapter.transform();
				Display display = Display.getDefault();
				Shell shell = new Shell(display.getActiveShell(), SWT.WRAP
						| SWT.APPLICATION_MODAL);//

				ArrayList<Property> componentProperties = eltComponentPropertyAdapter
						.getProperties();
				IPropertyTreeBuilder propertyTreeBuilder = new PropertyTreeBuilder(
						componentProperties);
				System.out.println(propertyTreeBuilder.toString());
				PropertyDialog testwindow = new PropertyDialog(shell,
						propertyTreeBuilder.getPropertyTree(),
						component.getProperties());
				testwindow.open();

				System.out.println("GetModel::"+((InputComponent)getModel()).getProperties());
			} catch (EmptyComponentPropertiesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			super.performRequest(req);
		}
	}
}
