package com.bitwise.app.propertywindow.widgets.gridwidgets.basic;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

// TODO: Auto-generated Javadoc
/**
 * The Class ELTTableViewer.
 * 
 * @author Bitwise
 */
public class ELTTableViewer extends AbstractELTWidget{

	IStructuredContentProvider iStructuredContentProvider;
	ITableLabelProvider iTableLabelProvider;
	
	/**
	 * Instantiates a new ELT table viewer.
	 * 
	 * @param iStructuredContentProvider
	 *            the i structured content provider
	 * @param iTableLabelProvider
	 *            the i table label provider
	 */
	public ELTTableViewer(
			IStructuredContentProvider iStructuredContentProvider,
			ITableLabelProvider iTableLabelProvider) {
		super();
		this.iStructuredContentProvider = iStructuredContentProvider;
		this.iTableLabelProvider = iTableLabelProvider;
	}

	@Override
	public void attachWidget(Composite container) {
		TableViewer tableViewer = new TableViewer(container, SWT.BORDER|SWT.CENTER | SWT.MULTI | SWT.FULL_SELECTION);
		tableViewer.setContentProvider(iStructuredContentProvider);
		tableViewer.setLabelProvider(iTableLabelProvider);
		jfaceWidgets = tableViewer;
		widget = tableViewer.getTable();
		
	}

}
