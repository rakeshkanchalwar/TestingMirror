package com.bitwise.app.propertywindow.widgets.listeners.grid;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.ICellEditorValidator;

import com.bitwise.app.propertywindow.propertydialog.PropertyDialogButtonBar;

public class ELTCellEditorIsNumericValidator implements ICellEditorValidator {

		private ControlDecoration scaleDecorator;
		private PropertyDialogButtonBar propertyDialogButtonBar;
	public ELTCellEditorIsNumericValidator(ControlDecoration scaleDecorator,PropertyDialogButtonBar propertyDialogButtonBar) {
		super();
			this.scaleDecorator = scaleDecorator;
			this.propertyDialogButtonBar=propertyDialogButtonBar;
	}

	@Override
	public String isValid(Object value) {
		String selectedGrid=(String) value;
		if(!selectedGrid.matches("\\d+") && !selectedGrid.isEmpty()){     
			scaleDecorator.show();   
		return "Error";   
	}else{  
		scaleDecorator.hide(); 
		    
	}
	return null; 
	}

}
