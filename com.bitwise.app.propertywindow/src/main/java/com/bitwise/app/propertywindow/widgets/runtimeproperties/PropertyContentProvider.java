package com.bitwise.app.propertywindow.widgets.runtimeproperties;

import java.util.List;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Item;

public class PropertyContentProvider implements IStructuredContentProvider {
	/**
	 * Returns the Person objects
	 */
	public Object[] getElements(Object inputElement) {
		return ((List) inputElement).toArray();
	}

	/**
	 * Disposes any created resources
	 */
	public void dispose() {
		// Do nothing
	}

	/**
	 * Called when the input changes
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}
}


