package com.bitwise.app.propertywindow.widgets.filterproperty;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * The Class ELTFilterLabelProvider.
 * 
 * @author Bitwise
 */
public class ELTFilterLabelProvider implements ITableLabelProvider, ITableColorProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	@Override
	public void dispose() {

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {

	}

	@Override
	public Color getForeground(Object element, int columnIndex) {
		return new Color(Display.getDefault(), new RGB(100, 0, 0));
	}

	@Override
	public Color getBackground(Object element, int columnIndex) {
		return new Color(Display.getDefault(), new RGB(255, 255, 255));
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		ELTFilterProperties filter = (ELTFilterProperties) element;
		return filter.getPropertyname();
	}

}
