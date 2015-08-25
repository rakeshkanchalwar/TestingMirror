package com.bitwise.app.graph.editor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.part.FileEditorInput;

import com.bitwise.app.graph.components.ComponentsEditPartFactory;
import com.bitwise.app.graph.components.ComponentsEditorContextMenuProvider;
import com.bitwise.app.graph.components.ComponentsPlugin;
import com.bitwise.app.graph.components.commands.RenameAction;
import com.bitwise.app.graph.components.model.ComponentsDiagram;
import com.bitwise.app.graph.components.model.Connection;
import com.bitwise.app.graph.components.model.GenericComponent;

public class ComponentGraphEditor extends GraphicalEditorWithFlyoutPalette {
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

		configureZoomSettings(viewer);

		IAction action = new RenameAction(this);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());

	}

	private void configureZoomSettings(GraphicalViewer viewer) {
		double[] zoomLevels;
		ArrayList<String> zoomContributions;

		ScalableFreeformRootEditPart rootEditPart = new ScalableFreeformRootEditPart();
		viewer.setRootEditPart(rootEditPart);
		ZoomManager manager = rootEditPart.getZoomManager();
		getActionRegistry().registerAction(new ZoomInAction(manager));
		getActionRegistry().registerAction(new ZoomOutAction(manager));

		zoomLevels = new double[] { 0.25, 0.5, 0.75, 1.0, 1.5, 2.0, 2.5, 3.0,
				4.0, 5.0, 10.0, 20.0 };
		manager.setZoomLevels(zoomLevels);

		zoomContributions = new ArrayList<String>();
		zoomContributions.add(ZoomManager.FIT_ALL);
		zoomContributions.add(ZoomManager.FIT_HEIGHT);
		zoomContributions.add(ZoomManager.FIT_WIDTH);
		manager.setZoomLevelContributions(zoomContributions);

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

	@Override
	protected void initializeGraphicalViewer() {
		System.out.println("initializeGraphicalViewer");
		super.initializeGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setContents(getModel());
		// listen for dropped parts
		viewer.addDropTargetListener(createTransferDropTargetListener());
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
				return new SimpleFactory((Class) template);
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
		if (getEditorInput() instanceof GraphEditorInput) {
			setPartName(getEditorInput().getName());
			diagram = new ComponentsDiagram();
		}
		try {
			System.out.println("SetInput Loded");
			if (input instanceof IFileEditorInput) {
				IFile file = ((IFileEditorInput) input).getFile();

				System.out.println("<<<<<<<<<<I M HERE>>>>>>");
				ObjectInputStream in = new ObjectInputStream(file.getContents());
				diagram = (ComponentsDiagram) in.readObject();
				in.close();

				setPartName(file.getName());
			}
			if (input instanceof FileStoreEditorInput) {
				File file = new File(
						((FileStoreEditorInput) input).getToolTipText());
				FileInputStream fs = new FileInputStream(file);
				ObjectInputStream in = new ObjectInputStream(fs);
				diagram = (ComponentsDiagram) in.readObject();
				in.close();

				setPartName(file.getName());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		System.out.println("doSave called from MyGraph");
		firePropertyChange(PROP_DIRTY);

		if (getEditorInput() instanceof GraphEditorInput) {
			doSaveAs();
		}

		if (getEditorInput() instanceof FileEditorInput) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try {

				createOutputStream(out);
				IFile file = ((IFileEditorInput) getEditorInput()).getFile();

				file.setContents(new ByteArrayInputStream(out.toByteArray()),
						true, false, // dont keep history
						monitor); // progress monitor

				getCommandStack().markSaveLocation();
			} catch (CoreException ce) {
				ce.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

		if (getEditorInput() instanceof FileStoreEditorInput) {
			FileOutputStream fsout;
			ObjectOutputStream objout;
			File file;
			file = new File(
					((FileStoreEditorInput) getEditorInput()).getToolTipText());
			{
				try {
					fsout = new FileOutputStream(file);
					objout = new ObjectOutputStream(fsout);
					objout.writeObject(getModel());
					objout.close();
					fsout.close();
					getCommandStack().markSaveLocation();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}

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
		CombinedTemplateCreationEntry component = new CombinedTemplateCreationEntry(
				"Input", "Create an Input component", GenericComponent.class,
				new SimpleFactory(GenericComponent.class),
				ImageDescriptor.createFromFile(ComponentsPlugin.class,
						"icons/InputFile_Palette.png"),
				ImageDescriptor.createFromFile(ComponentsPlugin.class,
						"icons/InputFile.png"));
		componentsDrawer.add(component);

		CombinedTemplateCreationEntry component2 = new CombinedTemplateCreationEntry(
				"Output", "Create a Output component", GenericComponent.class,
				new SimpleFactory(GenericComponent.class),
				ImageDescriptor.createFromFile(ComponentsPlugin.class,
						"icons/OutputFile_Palette.png"),
				ImageDescriptor.createFromFile(ComponentsPlugin.class,
						"icons/OutputFile.png"));
		componentsDrawer.add(component2);
		System.out.println("createShapesDrawer2");
		return componentsDrawer;
	}

	public void doSaveAs() {
		IFile file;
		IPath filePath;
		ByteArrayOutputStream out;
		SaveAsDialog obj = new SaveAsDialog(new Shell());
		if (getEditorInput().getName().endsWith(".graph"))
			obj.setOriginalName(getEditorInput().getName());
		else
			obj.setOriginalName(getEditorInput().getName() + ".graph");
		obj.open();
		if (obj.getReturnCode() == 0) {
			filePath = obj.getResult().removeFileExtension()
					.addFileExtension("graph");
			file = ResourcesPlugin.getWorkspace().getRoot().getFile(filePath);
			out = new ByteArrayOutputStream();
			try {
				createOutputStream(out);
				if (file.exists())
					file.setContents(
							new ByteArrayInputStream(out.toByteArray()), true,
							false, null);
				else
					file.create(new ByteArrayInputStream(out.toByteArray()),
							true, null);
				setInput(new FileEditorInput(file));
				getCommandStack().markSaveLocation();
			} catch (CoreException ce) {
				ce.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return true;
	}
}
