package com.bitwise.app.graph.editor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.ViewportAwareConnectionLayerClippingStrategy;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.commands.CommandStack;
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
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.commands.ActionHandler;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.statushandlers.StatusManager;
import org.slf4j.Logger;
import org.xml.sax.SAXException;

import com.bitwise.app.common.component.config.CategoryType;
import com.bitwise.app.common.component.config.Component;
import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.engine.exceptions.EngineException;
import com.bitwise.app.engine.util.ConverterUtil;
import com.bitwise.app.graph.action.CopyAction;
import com.bitwise.app.graph.action.CutAction;
import com.bitwise.app.graph.action.PasteAction;
import com.bitwise.app.graph.editorfactory.GenrateContainerData;
import com.bitwise.app.graph.factory.ComponentsEditPartFactory;
import com.bitwise.app.graph.factory.CustomPaletteEditPartFactory;
import com.bitwise.app.graph.model.Container;
import com.bitwise.app.graph.model.Link;
import com.bitwise.app.graph.processor.DynamicClassProcessor;
import com.thoughtworks.xstream.XStream;

// TODO: Auto-generated Javadoc
/**
 * Responsible to render the palette and container.
 * 
 */
public class ETLGraphicalEditor extends GraphicalEditorWithFlyoutPalette {

	private boolean dirty=false;
	private final Color palatteBackgroundColor= new Color(null,82,84,81);


	Logger logger = LogFactory.INSTANCE.getLogger(ETLGraphicalEditor.class);
	public static final String ID = "com.bitwise.app.graph.etlgraphicaleditor";
	private Container container;
	private final Point defaultComponentLocation = new Point(0, 0);

	/**
	 * Instantiates a new ETL graphical editor.
	 */
	public ETLGraphicalEditor() {
		setEditDomain(new DefaultEditDomain(this));
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
				viewer.setEditPartFactory(new CustomPaletteEditPartFactory(palatteBackgroundColor,palatteBackgroundColor));
				// create a drag source listener for this palette viewer
				// together with an appropriate transfer drop target listener,
				// this will enable
				// model element creation by dragging a
				// CombinatedTemplateCreationEntries
				// from the palette into the editor
				// @see ShapesEditor#createTransferDropTargetListener()
				viewer.addDragSourceListener(new TemplateTransferDragSourceListener(
						viewer));
				viewer.getControl().addMouseListener(
						new PaletteContainerListener(viewer, getGraphicalViewer()));
			}
		};
	}

	public void commandStackChanged(EventObject event) {		
		setDirty(true);
		firePropertyChange(IEditorPart.PROP_DIRTY);
		super.commandStackChanged(event);
	}

	/**
	 * Creates the palette container.
	 * 
	 * @param CategoryName
	 *            the category name
	 * @return the palette drawer
	 */
	public PaletteDrawer createPaletteContainer(String CategoryName) {
		String name = CategoryName.substring(0, 1).toUpperCase()
				+ CategoryName.substring(1).toLowerCase();
		PaletteDrawer p = new PaletteDrawer(name);
		p.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
		return p;
	}

	/**
	 * Adds the container to palette.
	 * 
	 * @param p1
	 *            the p1
	 * @param p
	 *            the p
	 */
	public void addContainerToPalette(PaletteRoot p1, PaletteDrawer p) {
		p1.add(p);
	}

	private void createShapesDrawer(PaletteRoot palette)
			throws RuntimeException, SAXException, IOException {
		Map<String, PaletteDrawer> categoryPaletteConatiner = new HashMap<>();
		for (CategoryType category : CategoryType.values()) {
			PaletteDrawer p = createPaletteContainer(category.name());
			addContainerToPalette(palette, p);
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
			categoryPaletteConatiner.get(componentConfig.getCategory().name())
			.add(component);

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

				defaultComponentLocation.setLocation(selectedEditPart
						.getFigure().getBounds().x, selectedEditPart
						.getFigure().getBounds().y);

			}
		};
	}

	private void handleKeyStrokes(GraphicalViewer viewer) {
		KeyHandler keyHandler = new KeyHandler();
		keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0),
				getActionRegistry().getAction(ActionFactory.DELETE.getId()));
		keyHandler.put(KeyStroke.getPressed((char) ('z' - 'a' + 1),'z',SWT.CTRL), getActionRegistry().getAction(ActionFactory.UNDO.getId()));
		keyHandler.put(KeyStroke.getPressed((char) ('y' - 'a' + 1), 'y', SWT.CTRL), getActionRegistry().getAction(ActionFactory.REDO.getId()));
		keyHandler.put(KeyStroke.getPressed((char) ('a' - 'a' + 1), 'a', SWT.CTRL), getActionRegistry().getAction(ActionFactory.SELECT_ALL.getId()));
		keyHandler.put(KeyStroke.getPressed((char) ('c' - 'a' + 1), 'c', SWT.CTRL), getActionRegistry().getAction(ActionFactory.COPY.getId()));
		keyHandler.put(KeyStroke.getPressed((char) ('v' - 'a' + 1), 'v', SWT.CTRL), getActionRegistry().getAction(ActionFactory.PASTE.getId()));
		keyHandler.put(KeyStroke.getPressed((char) ('x' - 'a' + 1), 'x', SWT.CTRL), getActionRegistry().getAction(ActionFactory.CUT.getId()));
		viewer.setKeyHandler(keyHandler);
	}
	
	public void createActions() {
		super.createActions();
		ActionRegistry registry = getActionRegistry();
		// ...
		IAction pasteAction;
		pasteAction = new PasteAction(this);
		 registry.registerAction(pasteAction);
		 getSelectionActions().add(pasteAction.getId());
		 
		 IAction action;
		 action=new CopyAction(this, pasteAction);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		
		 action=new CutAction(this, pasteAction);
		 registry.registerAction(action);
		getSelectionActions().add(action.getId());
	}
		
		

	private void configureViewer(GraphicalViewer viewer) {
		viewer.setEditPartFactory(new ComponentsEditPartFactory());
		ContextMenuProvider cmProvider = new ComponentsEditorContextMenuProvider(
				viewer, getActionRegistry());
		viewer.setContextMenu(cmProvider);
		getSite().registerContextMenu(cmProvider, viewer);
	}

	private void prepareZoomContributions(GraphicalViewer viewer) {

		ScalableFreeformRootEditPart root = new ScalableFreeformRootEditPart();

		// set clipping strategy for connection layer
		ConnectionLayer connectionLayer = (ConnectionLayer) root
				.getLayer(LayerConstants.CONNECTION_LAYER);
		connectionLayer
		.setClippingStrategy(new ViewportAwareConnectionLayerClippingStrategy(
				connectionLayer));

		List zoomLevels = new ArrayList(3);
		zoomLevels.add(ZoomManager.FIT_ALL);
		zoomLevels.add(ZoomManager.FIT_WIDTH);
		zoomLevels.add(ZoomManager.FIT_HEIGHT);
		root.getZoomManager().setZoomLevelContributions(zoomLevels);

		IAction zoomIn = new ZoomInAction(root.getZoomManager());
		IAction zoomOut = new ZoomOutAction(root.getZoomManager());
		viewer.setRootEditPart(root);
		getActionRegistry().registerAction(zoomIn);
		getActionRegistry().registerAction(zoomOut);

		//zoom on key strokes: ctrl++ and ctrl--
		IHandlerService service = 
				(IHandlerService)getEditorSite().getService(IHandlerService. class);

		service.activateHandler(zoomIn.getActionDefinitionId(),
				new ActionHandler(zoomIn));

		service.activateHandler(zoomOut.getActionDefinitionId(),
				new ActionHandler(zoomOut));

		// Scroll-wheel Zoom
		getGraphicalViewer().setProperty(
				MouseWheelHandler.KeyGenerator.getKey(SWT.MOD1),
				MouseWheelZoomHandler.SINGLETON);
	}


	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return dirty;
	}

	public void setDirty(boolean dirty){
		this.dirty = dirty;
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}


	@Override
	public void setInput(IEditorInput input) {
		super.setInput(input);
		String METHOD_NAME = "setInput - ";
		logger.debug(METHOD_NAME);
		try {
			GenrateContainerData genrateContainerData = new GenrateContainerData();
			genrateContainerData.setEditorInput(input, this);
			container = genrateContainerData.getContainerData();
		} catch (CoreException | IOException ce) {
			logger.error(METHOD_NAME , ce);
		}
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		String METHOD_NAME = "doSave -";
		logger.debug(METHOD_NAME);
		firePropertyChange(PROP_DIRTY);
		try {
			GenrateContainerData genrateContainerData = new GenrateContainerData();
			genrateContainerData.setEditorInput(getEditorInput(), this);
			genrateContainerData.storeContainerData();

		} catch (CoreException | IOException ce) {
			logger.error(METHOD_NAME , ce);
			MessageDialog.openError(new Shell(), "Error", "Exception occured while saving the graph -\n"+ce.getMessage());
		}
	}

	/**
	 * Creates the output stream.
	 * 
	 * @param out
	 *            the out
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void createOutputStream(OutputStream out) throws IOException {
		String METHOD_NAME = "createOutputStream - ";
		logger.debug(METHOD_NAME);
		out.write(fromObjectToXML(getModel()).getBytes());
	}

	public Container getModel() {
		return container;
	}

	public void doSaveAs() {
		String METHOD_NAME = "ETLGraphicalEditor. doSaveAs()";
		logger.debug(METHOD_NAME);
		IFile file=opeSaveAsDialog();
		if(file!=null){
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try {
				createOutputStream(out);
				if (file.exists())
					file.setContents(new ByteArrayInputStream(out.toByteArray()), true,	false, null);
				else
					file.create(new ByteArrayInputStream(out.toByteArray()),true, null);
				setInput(new FileEditorInput(file));
				initializeGraphicalViewer();
				genrateTargetXml(file);
				getCommandStack().markSaveLocation();
			} catch (CoreException  | IOException ce) {
				logger.error(METHOD_NAME,ce);
				MessageDialog.openError(new Shell(), "Error", "Exception occured while saving the graph -\n"+ce.getMessage());
			}
			setDirty(false);
		}
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	private IFile opeSaveAsDialog() {
		logger.debug("opeSaveAsDialog - Opening SaveAs dialog box.");
		SaveAsDialog obj = new SaveAsDialog(new Shell());
		IFile file=null;
		if (getEditorInput().getName().endsWith(".job"))
			obj.setOriginalName(getEditorInput().getName());
		else
			obj.setOriginalName(getEditorInput().getName() + ".job");
		obj.open();

		if (obj.getReturnCode() == 0) {
			IPath filePath = obj.getResult().removeFileExtension().addFileExtension("job");
			file= ResourcesPlugin.getWorkspace().getRoot().getFile(filePath);
		}
		return file;
	}


	/**
	 * From xml to object.
	 * 
	 * @param xml
	 *            the xml
	 * @return the object
	 */
	public Object fromXMLToObject(InputStream xml) {
		String METHOD_NAME = "ETLGraphicalEditor.fromXMLToJava(InputStream xml)";
		Object obj = null;
		logger.debug(METHOD_NAME);
		XStream xs = new XStream();
		try {

			obj = xs.fromXML(xml);
			logger.debug(METHOD_NAME
					+ "Sucessfully converted JAVA Object from XML Data");
			xml.close();
		} catch (Exception e) {
			logger.error(METHOD_NAME,e);
			MessageDialog
			.openError(new Shell(), "Error", "Invalid graph file.");

		}
		return obj;
	}

	/**
	 * From object to xml.
	 * 
	 * @param object
	 *            the object
	 * @return the string
	 */
	public String fromObjectToXML(Serializable object) {
		String METHOD_NAME = "ETLGraphicalEditor.fromObjectToXML(Serializable object)";
		String str = "<!-- It is recommended to avoid changes to xml data -->\n\n";
		logger.debug(METHOD_NAME);
		XStream xs = new XStream();
		try {
			str = str + xs.toXML(object);
			logger.debug(
					METHOD_NAME + "Sucessfully converted XML from JAVA Object");
		} catch (Exception e) {
			logger.error(METHOD_NAME,e);
		}
		return str;
	}

	/**
	 * Genrate target xml.
	 * 
	 * @param ifile
	 *            the ifile
	 */
	public void genrateTargetXml(IFile ifile) {
		String METHOD_NAME="genrateTargetXml - ";
		logger.debug(METHOD_NAME+"genrating target XML");
		IFile outPutFile = ResourcesPlugin.getWorkspace().getRoot().getFile(ifile.getFullPath().removeFileExtension().addFileExtension("xml"));
		try {
			ConverterUtil.INSTANCE.convertToXML(container, false, outPutFile);
		} catch (EngineException eexception) {
			logger.warn("Failed to create the engine xml", eexception);
			MessageDialog.openError(Display.getDefault().getActiveShell(), "Failed to create the engine xml", eexception.getMessage());
			//			
		}catch (Exception exception) {
			logger.error("Failed to create the engine xml", exception);
			Status status = new Status(IStatus.ERROR, "com.bitwise.app.graph",
					"Failed to create Engine XML " + exception.getMessage());
			StatusManager.getManager().handle(status, StatusManager.SHOW);
		}

	}

	@Override
	public void setPartName(String partName) {
		super.setPartName(partName);
	}

	public CommandStack getCommandStack() {
		return super.getCommandStack();
	}


	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);
		ResourcesPlugin.getWorkspace().addResourceChangeListener(new ResourceChangeListener(this), IResourceChangeEvent.POST_CHANGE);
	}

	public void dispose() {
		super.dispose();
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(new ResourceChangeListener(this));
	}




}