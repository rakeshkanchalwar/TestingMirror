package com.bitwise.app.propertywindow.widgets.filterproperty;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Item;


// TODO: Auto-generated Javadoc
/**
 * The Class ELTCellModifier.
 * 
 * @author Bitwise
 */
public class ELTCellModifier implements ICellModifier{
	
	private Viewer viewer;
	
	/**
	 * Instantiates a new ELT cell modifier.
	 * 
	 * @param viewer
	 *            the viewer
	 */
	public ELTCellModifier(Viewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public boolean canModify(Object element, String property) {
		return true;
	}

	@Override
	public Object getValue(Object element, String property) {
		ELTFilterProperties filter = (ELTFilterProperties) element;
		if(ELTFilterPropertyWizard.FilterInputFieldName.equals(property))

		return filter.getPropertyname();
		else
		return null;
	}

	@Override
	public void modify(Object element, String property, Object value) {
		if (element instanceof Item)
			element = ((Item) element).getData();

		ELTFilterProperties p = (ELTFilterProperties) element;
		
			if(ELTFilterPropertyWizard.FilterInputFieldName.equals(property))
					p.setPropertyname((String)value);
		// Force the viewer to refresh
		viewer.refresh();
		
	}

}
