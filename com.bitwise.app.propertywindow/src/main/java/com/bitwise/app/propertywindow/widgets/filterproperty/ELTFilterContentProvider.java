package com.bitwise.app.propertywindow.widgets.filterproperty;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * The Class ELTFilterContentProvider.
 * 
 * @author Bitwise
 */
public class ELTFilterContentProvider implements IStructuredContentProvider{

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		
		return ((List)inputElement).toArray();
	}

}
