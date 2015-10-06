package com.bitwise.app.graph.editor;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.graph.command.ComponentCreateCommand;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Container;

public class PaletteContainerListener implements MouseListener {

	LogFactory eltLogger = new LogFactory(getClass().getName());
	private final PaletteViewer viewer;
	private Point defaultComponentLocation = new Point(0, 0);
	private Component genericComponent;
	private GraphicalViewer graphicalViewer;
	
	public PaletteContainerListener(PaletteViewer viewer, GraphicalViewer graphicalViewer) {
		this.graphicalViewer = graphicalViewer;
		this.viewer = viewer;
	}

	@Override
	public void mouseUp(MouseEvent e) {
	}

	@Override
	public void mouseDown(MouseEvent e) {
	}

	@Override
	public void mouseDoubleClick(MouseEvent mouseEvent) {
		CreateRequest componentRequest = getComponentRequest(mouseEvent);
		placeComponentOnCanvasByDoubleClickOnPalette(componentRequest);

		eltLogger.getLogger().info(
				"Component is positioned at respective x and y location"
						+ defaultComponentLocation.getCopy().x + 20 + " and "
						+ defaultComponentLocation.getCopy().y + 20);
		eltLogger.getLogger().info(
				"Component is positioned at respective x and y location"
						+ defaultComponentLocation.getCopy().x + 20 + " and "
						+ defaultComponentLocation.getCopy().y + 20);

	}

	private CreateRequest getComponentRequest(MouseEvent mouseEvent) {
		EditPart paletteInternalController = viewer.findObjectAt(new Point(
				mouseEvent.x, mouseEvent.y));

		CombinedTemplateCreationEntry addedPaletteTool = (CombinedTemplateCreationEntry) paletteInternalController
				.getModel();

		CreateRequest componentRequest = new CreateRequest();
		componentRequest.setFactory(new SimpleFactory((Class) addedPaletteTool
				.getTemplate()));

		genericComponent = (Component) componentRequest
				.getNewObject();

		setComponentRequestParams(componentRequest);

		return componentRequest;
	}

	private void setComponentRequestParams(CreateRequest componentRequest) {
		componentRequest.setSize(genericComponent.getSize());

		defaultComponentLocation.setLocation(
				defaultComponentLocation.getCopy().x + 20,
				defaultComponentLocation.getCopy().y + 20);

		componentRequest.setLocation(defaultComponentLocation);
	}

	private void placeComponentOnCanvasByDoubleClickOnPalette(
			CreateRequest componentRequest) {
		GraphicalViewer graphViewer = graphicalViewer;

		ComponentCreateCommand createComponent = new ComponentCreateCommand(
				(com.bitwise.app.graph.model.Component) componentRequest
						.getNewObject(),
				(Container) graphViewer.getContents().getModel(),
				new Rectangle(componentRequest.getLocation(), componentRequest
						.getSize()));

		graphViewer.getEditDomain().getCommandStack().execute(createComponent);
	}
}