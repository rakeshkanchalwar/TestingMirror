package com.bitwise.app.graph.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class GraphEditorInput implements IEditorInput{
	public String name = null;
	public GraphEditorInput(String name) {
		this.name = name;
	}
	public boolean exists() {
		return (this.name != null);
	}
	public boolean equals(Object o) {
		if (!(o instanceof GraphEditorInput))
			return false;
		return ((GraphEditorInput)o).getName().equals(getName());
	}
	public ImageDescriptor getImageDescriptor() {
		return ImageDescriptor.getMissingImageDescriptor();
	}
	public String getName() {
		return this.name;
	}
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getToolTipText() {
		return this.name;
	}
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		
		return null;
	}
}
