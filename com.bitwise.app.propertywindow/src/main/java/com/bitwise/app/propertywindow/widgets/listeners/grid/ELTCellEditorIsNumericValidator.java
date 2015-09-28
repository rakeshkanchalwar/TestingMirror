package com.bitwise.app.propertywindow.widgets.listeners.grid;

import java.util.List;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

public class ELTCellEditorIsNumericValidator implements ICellEditorValidator {

		private ControlDecoration scaleDecorator;
	public ELTCellEditorIsNumericValidator(ControlDecoration scaleDecorator) {
		super();
			this.scaleDecorator = scaleDecorator;
	}

	@Override
	public String isValid(Object value) {
		String selectedGrid=(String) value;
		if(!selectedGrid.matches("\\d+")){     
			scaleDecorator.show();   
		return "Error";   
	}else{       
		scaleDecorator.hide(); 
		    
	}
	return null; 
	}

}
