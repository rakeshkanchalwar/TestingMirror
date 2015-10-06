package com.bitwise.app.graph.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.FileEditorInput;

public class ResourceChangeListener implements IResourceChangeListener,	IResourceDeltaVisitor {
	private IEditorPart editorPart;

	public ResourceChangeListener(IEditorPart editorPart) {
		this.editorPart = editorPart;
	}

	public void resourceChanged(IResourceChangeEvent event) {
		IResourceDelta delta = event.getDelta();
		try {
			if (delta != null)
				delta.accept(this);

		}
		catch (CoreException exception) {
		}
	}

	private void closeEditorDoNotSave() {
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				editorPart.getSite().getPage()
						.closeEditor(editorPart, false);
			}
		});

		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
	}

	public boolean visit(IResourceDelta delta) {
		if (delta == null)
			return true;

		IEditorInput editorInput = editorPart.getEditorInput();
		if (editorInput instanceof FileEditorInput) {

			if (!delta.getResource().equals(
					((FileEditorInput) editorInput).getFile())) {
				return true;
			}
		} else {
			return true; // this is not an input type our editor handles
		}

		if (delta.getKind() == IResourceDelta.REMOVED) {
			if ((IResourceDelta.MOVED_TO & delta.getFlags()) == 0) {

				/*
				 *  * if the file was deleted NOTE: The case where an open,
				 * unsaved file is deleted being handled by the PartListener
				 * added to the Workbench in the initialize() method.
				 */

				if (!editorPart.isDirty()) {
					closeEditorDoNotSave();
				}
			}

			else { // else if it was moved or renamed
				final IFile newFile = ResourcesPlugin.getWorkspace()
						.getRoot().getFile(delta.getMovedToPath());

				Display display = editorPart.getSite().getShell().getDisplay();
				display.asyncExec(new Runnable() {
					public void run() {
						((ETLGraphicalEditor)editorPart).setInput(new FileEditorInput(newFile));
					}
				});
			}
		}
		return false;
	}
}
