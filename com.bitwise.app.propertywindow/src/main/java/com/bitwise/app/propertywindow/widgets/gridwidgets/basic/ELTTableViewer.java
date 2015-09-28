package com.bitwise.app.propertywindow.widgets.gridwidgets.basic;

import javax.swing.text.TabExpander;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public class ELTTableViewer extends AbstractELTWidget{

	IStructuredContentProvider iStructuredContentProvider;
	ITableLabelProvider iTableLabelProvider;
	
	public ELTTableViewer(
			IStructuredContentProvider iStructuredContentProvider,
			ITableLabelProvider iTableLabelProvider) {
		super();
		this.iStructuredContentProvider = iStructuredContentProvider;
		this.iTableLabelProvider = iTableLabelProvider;
	}

	@Override
	public void attachWidget(Composite container) {
		TableViewer tableViewer = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		tableViewer.setContentProvider(iStructuredContentProvider);
		tableViewer.setLabelProvider(iTableLabelProvider);
		jfaceWidgets = tableViewer;
		widget = tableViewer.getTable();
		
	}

}
