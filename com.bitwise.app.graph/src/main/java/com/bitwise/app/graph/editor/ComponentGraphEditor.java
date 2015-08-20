package com.bitwise.app.graph.editor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.dnd.TransferDragSourceListener;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteToolbar;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.bitwise.app.graph.components.ComponentsEditPartFactory;
import com.bitwise.app.graph.components.ComponentsEditorContextMenuProvider;
import com.bitwise.app.graph.components.ComponentsPlugin;
import com.bitwise.app.graph.components.commands.RenameAction;
import com.bitwise.app.graph.components.model.ComponentsDiagram;
import com.bitwise.app.graph.components.model.Connection;
import com.bitwise.app.graph.components.model.GenericComponent;
import com.bitwise.app.graph.components.model.TestInputComp;
import com.bitwise.app.graph.components.model.TestOutputComp;


public class ComponentGraphEditor extends GraphicalEditorWithFlyoutPalette{
	private ComponentsDiagram diagram;
	public static final String ID = "com.bitwise.app.graph.editor.mygraphicaleditor";

	
	
	public ComponentGraphEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	@Override
	protected void configureGraphicalViewer() {
		System.out.println("configureGraphicalViewer");
		super.configureGraphicalViewer();
		final GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(new ComponentsEditPartFactory());
		ContextMenuProvider cmProvider = new ComponentsEditorContextMenuProvider(
				viewer, getActionRegistry());
		viewer.setContextMenu(cmProvider);

		getSite().registerContextMenu(cmProvider, viewer);

		//----------------------
		double[] zoomLevels;
		ArrayList<String> zoomContributions;


		ScalableFreeformRootEditPart rootEditPart = new ScalableFreeformRootEditPart();
		viewer.setRootEditPart(rootEditPart);
		ZoomManager manager = rootEditPart.getZoomManager();
		getActionRegistry().registerAction(new ZoomInAction(manager));
		getActionRegistry().registerAction(new ZoomOutAction(manager));

		zoomLevels = new double[] {0.25, 0.5, 0.75, 1.0, 1.5, 2.0, 2.5, 3.0, 4.0, 5.0, 10.0,
				20.0};
		manager.setZoomLevels(zoomLevels);

		zoomContributions = new ArrayList<String>();
		zoomContributions.add(ZoomManager.FIT_ALL);
		zoomContributions.add(ZoomManager.FIT_HEIGHT);
		zoomContributions.add(ZoomManager.FIT_WIDTH);
		manager.setZoomLevelContributions(zoomContributions);


		KeyHandler keyHandler = new KeyHandler();
		keyHandler.put(
				KeyStroke.getPressed(SWT.DEL, 127, 0),
				getActionRegistry().getAction(ActionFactory.DELETE.getId()));
		keyHandler.put(
				KeyStroke.getPressed('+', SWT.KEYPAD_ADD, 0),
				getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
		keyHandler.put(
				KeyStroke.getPressed('-', SWT.KEYPAD_SUBTRACT, 0),
				getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));

		viewer.setProperty(
				MouseWheelHandler.KeyGenerator.getKey(SWT.NONE),
				MouseWheelZoomHandler.SINGLETON);
		viewer.setKeyHandler(keyHandler);
		//----------------------
		//getActionRegistry().registerAction(new RenameAction(this));
		IAction action = new RenameAction(this);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());

	}

	//	public void createActions() {
	//		super.createActions();
	//		ActionRegistry registry = getActionRegistry();
	//		IAction action = new RenameAction(this);
	//		registry.registerAction(action);
	//		getSelectionActions().add(action.getId());
	//	}

	@Override
	protected void initializeGraphicalViewer() {
		System.out.println("initializeGraphicalViewer");
		super.initializeGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setContents(getModel());
		// listen for dropped parts
		viewer.addDropTargetListener(createTransferDropTargetListener());
		//viewer.addDragSourceListener(createDragSourceListener());
	}

	
	ComponentsDiagram getModel() {
		return diagram;
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
				//SimpleFactory tmp = new SimpleFactory((Class) template);
				//System.out.println("++++" + tmp.toString());
				return new SimpleFactory((Class) template);
			}
			
			
			
			@Override
			public void drop(DropTargetEvent event) {
				// TODO Auto-generated method stub
				ComponentsDiagram tmp = diagram;
				
				super.drop(event);
			}



			@Override
			public void dropAccept(DropTargetEvent event) {
				// TODO Auto-generated method stub
				super.dropAccept(event);
			}



			@Override
			public void dragEnter(DropTargetEvent event) {
				// TODO Auto-generated method stub
				Object tmp = event.data;
				
				super.dragEnter(event);
				tmp = event.data;
			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.ui.parts.GraphicalEditor#commandStackChanged(java.util
	 * .EventObject)
	 */
	public void commandStackChanged(EventObject event) {
		firePropertyChange(IEditorPart.PROP_DIRTY);
		super.commandStackChanged(event);
	}

	protected PaletteViewerProvider createPaletteViewerProvider() {
		System.out.println("PaletteViewerProvider");
		return new PaletteViewerProvider(getEditDomain()) {
			protected void configurePaletteViewer(PaletteViewer viewer) {
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
			}
		};
	}

	@Override
	protected void setInput(IEditorInput input) {
		super.setInput(input);

		setPartName("Components Graph");
		diagram = new ComponentsDiagram();


	}



	@Override
	public void doSave(IProgressMonitor monitor) {
		System.out.println("doSave called from MyGraph");
		//getCommandStack().markSaveLocation();
		firePropertyChange(PROP_DIRTY);


		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {

			createOutputStream(out);
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();

			file.setContents(new ByteArrayInputStream(out.toByteArray()), true, 
					false, // dont keep history
					monitor); // progress monitor

			getCommandStack().markSaveLocation();
		} catch (CoreException ce) {
			ce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void createOutputStream(OutputStream os) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(getModel());
		oos.close();
	}


	@Override
	protected PaletteRoot getPaletteRoot() {
		System.out.println("getPaletteRoot");
		PaletteRoot palette = new PaletteRoot();

		palette.addAll(createCategories(palette));

		return palette;
	}

	private List createCategories(PaletteRoot palette) {
		List categories = new ArrayList();
		palette.add(createToolsGroup(palette));
		palette.add(createShapesDrawer());
		return categories;
	}
	private static PaletteContainer createToolsGroup(PaletteRoot palette) {
		PaletteToolbar toolbar = new PaletteToolbar("Tools");

		// Add a selection tool to the group
		ToolEntry tool = new PanningSelectionToolEntry();
		toolbar.add(tool);
		palette.setDefaultEntry(tool);

		// Add a marquee tool to the group
		toolbar.add(new MarqueeToolEntry());

		// Add (solid-line) connection tool
		tool = new ConnectionCreationToolEntry("Solid connection",
				"Create a solid-line connection", new CreationFactory() {
			public Object getNewObject() {
				return null;
			}

			// see ShapeEditPart#createEditPolicies()
			// this is abused to transmit the desired line style
			public Object getObjectType() {
				return Connection.SOLID_CONNECTION;
			}
		}, ImageDescriptor.createFromFile(ComponentsPlugin.class,
				"icons/connection_s16.gif"),
				ImageDescriptor.createFromFile(ComponentsPlugin.class,
						"icons/connection_s24.gif"));
		toolbar.add(tool);

		return toolbar;
	}

	private static PaletteContainer createShapesDrawer() {
		PaletteDrawer componentsDrawer = new PaletteDrawer("Components");
		
		
		/*CombinedTemplateCreationEntry component = new CombinedTemplateCreationEntry(
				"Input", "Create an Input component", GenericComponent.class,
				new SimpleFactory(GenericComponent.class),
				ImageDescriptor.createFromFile(ComponentsPlugin.class,
						"icons/InputFile_Palette.png"), ImageDescriptor.createFromFile(
								ComponentsPlugin.class, "icons/InputFile.png"));
		componentsDrawer.add(component);
		
		CombinedTemplateCreationEntry component2 = new CombinedTemplateCreationEntry("Output",
				"Create a Output component", GenericComponent.class,
				new SimpleFactory(GenericComponent.class),
				ImageDescriptor.createFromFile(ComponentsPlugin.class,
						"icons/OutputFile_Palette.png"),
						ImageDescriptor.createFromFile(ComponentsPlugin.class,
								"icons/OutputFile.png"));
		componentsDrawer.add(component2);*/
		Class testInputClass = null;
		try {
			testInputClass=Class.forName("com.bitwise.app.graph.components.model.TestInputComp");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CombinedTemplateCreationEntry component = new CombinedTemplateCreationEntry(
				"Input", "Create an Input component", testInputClass,
				new SimpleFactory(testInputClass),
				ImageDescriptor.createFromFile(ComponentsPlugin.class,
						"icons/InputFile_Palette.png"), ImageDescriptor.createFromFile(
								ComponentsPlugin.class, "icons/InputFile.png"));
		componentsDrawer.add(component);
		
		CombinedTemplateCreationEntry component2 = new CombinedTemplateCreationEntry("Output",
				"Create a Output component", TestOutputComp.class,
				new SimpleFactory(TestOutputComp.class),
				ImageDescriptor.createFromFile(ComponentsPlugin.class,
						"icons/OutputFile_Palette.png"),
						ImageDescriptor.createFromFile(ComponentsPlugin.class,
								"icons/OutputFile.png"));
		componentsDrawer.add(component2);
		
		System.out.println("createShapesDrawer2");
		
		
		return componentsDrawer;
	}

	/*public Object getAdapter(Class type) {
		if (type == 
				org.eclipse.ui.views.properties.IPropertySheetPage.class) { 
			TabbedPropertySheetPage page = new TabbedPropertySheetPage(new 
					ITabbedPropertySheetPageContributor() 
			{public String getContributorId() 
				{ 

					return "contributorId"; 
				}
			}); 

			return page; 
		}
		return super.getAdapter(type);
		
		
	}
	 */
	
	
	/*public Object getAdapter(Class type) {
		if (type == IContentOutlinePage.class)
			return new ShapesOutlinePage(new TreeViewer());
		return super.getAdapter(type);
		
		
	}
*/
	//	//----------------------------New class-------------
	//	/**
	//	 * Creates an outline pagebook for this editor.
	//	 */
	//	public class ShapesOutlinePage extends ContentOutlinePage {
	//		/**
	//		 * Create a new outline page for the shapes editor.
	//		 * 
	//		 * @param viewer
	//		 *            a viewer (TreeViewer instance) used for this outline page
	//		 * @throws IllegalArgumentException
	//		 *             if editor is null
	//		 */
	//		public ShapesOutlinePage(EditPartViewer viewer) {
	//			super(viewer);
	//		}
	//
	//		/*
	//		 * (non-Javadoc)
	//		 * 
	//		 * @see
	//		 * org.eclipse.ui.part.IPage#createControl(org.eclipse.swt.widgets.Composite
	//		 * )
	//		 */
	//		public void createControl(Composite parent) {
	//			// create outline viewer page
	//			getViewer().createControl(parent);
	//			// configure outline viewer
	//			getViewer().setEditDomain(getEditDomain());
	//			getViewer().setEditPartFactory(new ComponentsTreeEditPartFactory());
	//			// configure & add context menu to viewer
	//			ContextMenuProvider cmProvider = new ComponentsEditorContextMenuProvider(
	//					getViewer(), getActionRegistry());
	//			getViewer().setContextMenu(cmProvider);
	//			getSite().registerContextMenu(
	//					"org.eclipse.gef.examples.shapes.outline.contextmenu",
	//					cmProvider, getSite().getSelectionProvider());
	//			// hook outline viewer
	//			getSelectionSynchronizer().addViewer(getViewer());
	//			// initialize outline viewer with model
	//			getViewer().setContents(getModel());
	//			// show outline viewer
	//		}
	//
	//		/*
	//		 * (non-Javadoc)
	//		 * 
	//		 * @see org.eclipse.ui.part.IPage#dispose()
	//		 */
	//		public void dispose() {
	//			// unhook outline viewer
	//			getSelectionSynchronizer().removeViewer(getViewer());
	//			// dispose
	//			super.dispose();
	//		}
	//
	//		/*
	//		 * (non-Javadoc)
	//		 * 
	//		 * @see org.eclipse.ui.part.IPage#getControl()
	//		 */
	//		public Control getControl() {
	//			return getViewer().getControl();
	//		}
	//
	//		/**
	//		 * @see org.eclipse.ui.part.IPageBookViewPage#init(org.eclipse.ui.part.IPageSite)
	//		 */
	//		public void init(IPageSite pageSite) {
	//			System.out.println("MyGraph:init()");
	//			super.init(pageSite);
	//			ActionRegistry registry = getActionRegistry();
	//			IActionBars bars = pageSite.getActionBars();
	//
	//			String id = ActionFactory.UNDO.getId();
	//			bars.setGlobalActionHandler(id, registry.getAction(id));
	//
	//			id = ActionFactory.REDO.getId();
	//			bars.setGlobalActionHandler(id, registry.getAction(id));
	//
	//			id = ActionFactory.DELETE.getId();
	//			bars.setGlobalActionHandler(id, registry.getAction(id));
	//
	//			//--------------------
	//
	//			id = ActionFactory.NEW.getId();
	//			bars.setGlobalActionHandler(id, registry.getAction(id));
	//
	//			id = ActionFactory.OPEN_PERSPECTIVE_DIALOG.getId();
	//			bars.setGlobalActionHandler(id, registry.getAction(id));
	//
	//		}
	//	}

}
