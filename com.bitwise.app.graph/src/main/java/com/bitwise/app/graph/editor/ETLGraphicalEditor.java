package com.bitwise.app.graph.editor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteToolbar;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.actions.ActionFactory;
import org.xml.sax.SAXException;

import com.bitwise.app.common.component.config.CategoryType;
import com.bitwise.app.common.component.config.Component;
import com.bitwise.app.common.util.ELTLoggerUtil;
import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.graph.command.ComponentCreateCommand;
import com.bitwise.app.graph.factory.ComponentsEditPartFactory;
import com.bitwise.app.graph.model.Link;
import com.bitwise.app.graph.model.Container;
import com.bitwise.app.graph.processor.DynamicClassProcessor;

/**
 * Responsible to render the pellet and container.
 * 
 */
public class ETLGraphicalEditor extends GraphicalEditorWithFlyoutPalette {

	private final class PaletteContainerListener implements MouseListener {
		private final PaletteViewer viewer;

		private PaletteContainerListener(PaletteViewer viewer) {
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
			
			eltLogger.info("Component is positioned at respective x and y location"+defaultComponentLocation.getCopy().x +20+" and "+defaultComponentLocation.getCopy().y + 20);
			eltLogger.info("Component is positioned at respective x and y location"+defaultComponentLocation.getCopy().x +20+" and "+defaultComponentLocation.getCopy().y + 20);

		}

		private CreateRequest getComponentRequest(MouseEvent mouseEvent) {
			EditPart paletteInternalController = viewer.findObjectAt(new Point(
					mouseEvent.x, mouseEvent.y));

			CombinedTemplateCreationEntry addedPaletteTool = (CombinedTemplateCreationEntry) paletteInternalController
					.getModel();


			CreateRequest componentRequest = new CreateRequest();
			componentRequest.setFactory(new SimpleFactory(
					(Class) addedPaletteTool.getTemplate()));
			
			genericComponent = (com.bitwise.app.graph.model.Component) componentRequest.getNewObject();
			
			setComponentRequestParams(componentRequest);
			
			return componentRequest;
		}

		private void setComponentRequestParams(CreateRequest componentRequest) {
			componentRequest.setSize(genericComponent.getSize());
			
			defaultComponentLocation.setLocation(
					defaultComponentLocation.getCopy().x +20,
					defaultComponentLocation.getCopy().y + 20);

			componentRequest.setLocation(defaultComponentLocation);
		}
		
		private void placeComponentOnCanvasByDoubleClickOnPalette(
				CreateRequest componentRequest) {
			GraphicalViewer graphViewer = getGraphicalViewer();
			
			ComponentCreateCommand createComponent = new ComponentCreateCommand(
					(com.bitwise.app.graph.model.Component) componentRequest
							.getNewObject(), (Container) graphViewer
							.getContents().getModel(),
					new Rectangle(componentRequest.getLocation(), componentRequest
							.getSize()));
			
			graphViewer.getEditDomain().getCommandStack()
					.execute(createComponent);
		}
	}

	ELTLoggerUtil eltLogger = new ELTLoggerUtil(getClass().getName());
	public static final String ID = "com.bitwise.app.graph.etlgraphicaleditor";
	private Container container;
	private com.bitwise.app.graph.model.Component genericComponent; 
	private Point defaultComponentLocation = new Point(0, 0);
	private Dimension defaultCompSize = new Dimension(100, 100);

	public ETLGraphicalEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	@Override
	protected void setInput(IEditorInput input) {
		super.setInput(input);
		if (getEditorInput() instanceof ETLGraphicalEditorInput) {
			setPartName(getEditorInput().getName());
		}
		setPartName(input.getName());
		container = new Container();
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		PaletteRoot palette = new PaletteRoot();
		createToolsGroup(palette);
		try {
			createShapesDrawer(palette);
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
		return palette;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		firePropertyChange(PROP_DIRTY);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(container);
			oos.close();
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();

			file.setContents(new ByteArrayInputStream(out.toByteArray()), true,
					false, // dont keep history
					monitor);

			getCommandStack().markSaveLocation();
		} catch (CoreException | IOException e) {
			// TODO add logger
			throw new RuntimeException("Could not save the file", e);
		}
	}

	/**
	 * Initialize the viewer with container
	 */
	@Override
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setContents(container);
		// listen for dropped parts
		viewer.addDropTargetListener(createTransferDropTargetListener());
		// listener for selection on canvas
		viewer.addSelectionChangedListener(createISelectionChangedListener());
	}

	/**
	 * Configure the graphical viewer with
	 * <ul>
	 * <li>ComponentEditPartFactory</li>
	 * <li>Zoom Contributions</li>
	 * <li>HandleKeyStrokes</li>
	 * </ul>
	 */
	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		final GraphicalViewer viewer = getGraphicalViewer();
		configureViewer(viewer);
		prepareZoomContributions(viewer);
		handleKeyStrokes(viewer);
	}

	protected PaletteViewerProvider createPaletteViewerProvider() {
		return new PaletteViewerProvider(getEditDomain()) {
			protected void configurePaletteViewer(final PaletteViewer viewer) {
				super.configurePaletteViewer(viewer);
				// create a drag source listener for this palette viewer
				// together with an appropriate transfer drop target listener,
				// this will enable
				// model element creation by dragging a
				// CombinatedTemplateCreationEntries
				// from the palette into the editor
				// @see ShapesEditor#createTransferDropTargetListener()
				viewer.addDragSourceListener(new TemplateTransferDragSourceListener(
						viewer));
				viewer.getControl().addMouseListener(new PaletteContainerListener(viewer));
			}
		};
	}

	public void commandStackChanged(EventObject event) {
		firePropertyChange(IEditorPart.PROP_DIRTY);
		super.commandStackChanged(event);
	}
	
	public  PaletteDrawer createPaletteContainer(String CategoryName)
    { 
    String name=CategoryName.substring(0, 1).toUpperCase()+CategoryName.substring(1).toLowerCase();
   	PaletteDrawer p=new PaletteDrawer(name);
   	p.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
    return p;
    }
    
    public  void addContainerToPalette(PaletteRoot p1,PaletteDrawer p){
    p1.add(p);
    }

	private void createShapesDrawer(PaletteRoot palette) throws RuntimeException, SAXException, IOException {
		Map<String, PaletteDrawer> categoryPaletteConatiner = new HashMap<>();
		for (CategoryType category : CategoryType.values()) {
			PaletteDrawer p=createPaletteContainer(category.name());
			addContainerToPalette(palette,p);
			categoryPaletteConatiner.put(category.name(), p);
		}
		List<Component> componentsConfig = XMLConfigUtil.INSTANCE
				.getComponentConfig();
		for (Component componentConfig : componentsConfig) {
			Class<?> clazz = DynamicClassProcessor.INSTANCE
					.createClass(componentConfig);

			CombinedTemplateCreationEntry component = new CombinedTemplateCreationEntry(
					componentConfig.getName(), "Custom components", clazz,
					new SimpleFactory(clazz),
					ImageDescriptor
							.createFromURL(prepareIconPathURL(componentConfig
									.getIconPath())),
					ImageDescriptor
							.createFromURL(prepareIconPathURL(componentConfig
									.getIconPath())));
			 categoryPaletteConatiner.get(componentConfig.getCategory().name()).add(component);
			
		}
		
	}

	private void createToolsGroup(PaletteRoot palette) {
		PaletteToolbar toolbar = new PaletteToolbar("Tools");

		// Add a selection tool to the group
		ToolEntry tool = new PanningSelectionToolEntry();
		toolbar.add(tool);
		palette.setDefaultEntry(tool);

		// Add a marquee tool to the group
		toolbar.add(new MarqueeToolEntry());

		// Add (solid-line) connection tool
		tool = new ConnectionCreationToolEntry(
				"Solid connection",
				"Create a solid-line connection",
				new CreationFactory() {
					public Object getNewObject() {
						return null;
					}

					// see ShapeEditPart#createEditPolicies()
					// this is abused to transmit the desired line style
					public Object getObjectType() {
						return Link.SOLID_CONNECTION;
					}
				},
				ImageDescriptor
						.createFromURL(prepareIconPathURL("/icons/connection_s24.gif")),
				ImageDescriptor
						.createFromURL(prepareIconPathURL("/icons/connection_s24.gif")));
		toolbar.add(tool);

		palette.add(toolbar);
	}

	private URL prepareIconPathURL(String iconPath) {
		URL iconUrl = null;

		try {
			iconUrl = new URL("file", null,
					(XMLConfigUtil.CONFIG_FILES_PATH + iconPath));
		} catch (MalformedURLException e) {
			// TODO : Add Logger
			throw new RuntimeException();
		}
		return iconUrl;
	}

	/**
	 * Create a transfer drop target listener. When using a
	 * CombinedTemplateCreationEntry tool in the palette, this will enable model
	 * element creation by dragging from the palette.
	 * 
	 * @see #createPaletteViewerProvider()
	 */
	private TransferDropTargetListener createTransferDropTargetListener() {
		return new TemplateTransferDropTargetListener(getGraphicalViewer()) {
			protected CreationFactory getFactory(Object template) {
				return new SimpleFactory((Class) template);
			}
		};
	}

	private ISelectionChangedListener createISelectionChangedListener() {
		return new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection sSelection = (StructuredSelection) event
						.getSelection();

				AbstractGraphicalEditPart selectedEditPart = (AbstractGraphicalEditPart) sSelection
						.getFirstElement();

				defaultComponentLocation.setLocation(selectedEditPart.getFigure()
						.getBounds().x, selectedEditPart.getFigure()
						.getBounds().y);

			}
		};
	}

	private void handleKeyStrokes(GraphicalViewer viewer) {
		KeyHandler keyHandler = new KeyHandler();
		keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0),
				getActionRegistry().getAction(ActionFactory.DELETE.getId()));
		keyHandler.put(KeyStroke.getPressed('+', SWT.KEYPAD_ADD, 0),
				getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
		keyHandler.put(KeyStroke.getPressed('-', SWT.KEYPAD_SUBTRACT, 0),
				getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));

		viewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.NONE),
				MouseWheelZoomHandler.SINGLETON);
		viewer.setKeyHandler(keyHandler);
	}

	private void configureViewer(GraphicalViewer viewer) {
		viewer.setEditPartFactory(new ComponentsEditPartFactory());
		ContextMenuProvider cmProvider = new ComponentsEditorContextMenuProvider(
				viewer, getActionRegistry());
		viewer.setContextMenu(cmProvider);
		getSite().registerContextMenu(cmProvider, viewer);
	}

	private void prepareZoomContributions(GraphicalViewer viewer) {
		ScalableFreeformRootEditPart rootEditPart = new ScalableFreeformRootEditPart();
		viewer.setRootEditPart(rootEditPart);
		ZoomManager manager = rootEditPart.getZoomManager();
		getActionRegistry().registerAction(new ZoomInAction(manager));
		getActionRegistry().registerAction(new ZoomOutAction(manager));

		double[] zoomLevels = new double[] { 0.25, 0.5, 0.75, 1.0, 1.5, 2.0,
				2.5, 3.0, 4.0, 5.0, 10.0, 20.0 };
		manager.setZoomLevels(zoomLevels);

		ArrayList<String> zoomContributions = new ArrayList<>();
		zoomContributions.add(ZoomManager.FIT_ALL);
		zoomContributions.add(ZoomManager.FIT_HEIGHT);
		zoomContributions.add(ZoomManager.FIT_WIDTH);
		manager.setZoomLevelContributions(zoomContributions);
	}
}
