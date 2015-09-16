package com.bitwise.app.graph.editor;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

public class ComponentsEditorContextMenuProvider extends ContextMenuProvider {
	/** The editor's action registry. */
	private ActionRegistry actionRegistry;
	
	/**
	 * Instantiate a new menu context provider for the specified EditPartViewer
	 * and ActionRegistry.
	 * @param viewer the editor's graphical viewer
	 * @param registry the editor's action registry
	 * @throws IllegalArgumentException if registry is <tt>null</tt>.
	 */
	public ComponentsEditorContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
		super(viewer);
		if (registry == null) {
			throw new IllegalArgumentException();
		}
		actionRegistry = registry;
	}
	
	/**
	 * Called when the context menu is about to show. Actions, whose state is
	 * enabled, will appear in the context menu.
	 */
	@Override
	public void buildContextMenu(IMenuManager menu) {
		// Add standard action groups to the menu
		GEFActionConstants.addStandardActionGroups(menu);

		// Add actions to the menu
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, // target group id
				getAction(ActionFactory.UNDO.getId())); // action to add
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, getAction(ActionFactory.REDO.getId()));
		
		menu.appendToGroup(GEFActionConstants.GROUP_EDIT, getAction(ActionFactory.DELETE.getId()));
		menu.appendToGroup(GEFActionConstants.GROUP_EDIT, getAction(ActionFactory.SAVE.getId()));
	}

	private IAction getAction(String actionId) {
		return actionRegistry.getAction(actionId);
	}
}
