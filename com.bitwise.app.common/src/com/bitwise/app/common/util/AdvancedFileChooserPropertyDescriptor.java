package com.bitwise.app.common.util;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class AdvancedFileChooserPropertyDescriptor extends PropertyDescriptor {

	/**
	 * Creates a property descriptor with the given id and display name.
	 * 
	 * @param id
	 *            the id of the property
	 * @param displayName
	 *            the name to display for the property
	 */
	public AdvancedFileChooserPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	/**
	 * @see org.eclipse.ui.views.properties.Ipropertydescriptor#createPr
	 *      opertyEditor(Composite)
	 */
	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new AdvancedFileChooserCellEditor(parent);
		if (getValidator() != null)
			editor.setValidator(getValidator());
		return editor;
	}

}