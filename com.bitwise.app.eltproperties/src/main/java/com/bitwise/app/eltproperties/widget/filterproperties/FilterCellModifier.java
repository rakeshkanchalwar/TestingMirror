package com.bitwise.app.eltproperties.widget.filterproperties;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Item;

public class FilterCellModifier implements ICellModifier{

	private Viewer viewer;

	public FilterCellModifier(Viewer viewer) {
		this.viewer = viewer;
	}
	
	@Override
	public boolean canModify(Object element, String property) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object getValue(Object element, String property) {
		FilterProperties filter = (FilterProperties) element;
			if(ELTFilterPropertyWizard.FilterInputFieldName.equals(property))

			return filter.getPropertyname();
			return filter;
	
				
	}

	@Override
	public void modify(Object element, String property, Object value) {
		if (element instanceof Item)
			element = ((Item) element).getData();

		FilterProperties p = (FilterProperties) element;
		
			if(ELTFilterPropertyWizard.FilterInputFieldName.equals(property))
					p.setPropertyname((String)value);
		// Force the viewer to refresh
		viewer.refresh();
		
	}

}
