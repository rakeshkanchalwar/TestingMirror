package com.bitwise.app.graph.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class ETLGraphicalEditorInput implements IEditorInput {

	private String name = null;

	public ETLGraphicalEditorInput(String name) {
		this.name = name;
	}

	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	@Override
	public boolean exists() {
		return (this.name != null);
	}

	public boolean equals(Object o) {
		if (!(o instanceof ETLGraphicalEditorInput))
			return false;
		return ((ETLGraphicalEditorInput) o).getName().equals(getName());
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return ImageDescriptor.getMissingImageDescriptor();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return this.name;
	}

}
