package com.bitwise.app.graph.policy;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;

import com.bitwise.app.graph.figure.ComponentBorder;
import com.bitwise.app.graph.figure.ComponentFigure;
import com.bitwise.app.graph.figure.ELTColorConstants;

/**
 * 
 * @author Shrirang S. Kumbhar
 * Oct 15, 2015
 * 
 */

public class ComponentSelectionPolicy extends SelectionEditPolicy {

	protected List handles;
	
	@Override
	protected void hideSelection() {		
		((ComponentFigure)getHostFigure()).setComponentColorAndBorder();
	}

	@Override
	protected void showSelection() {
		((ComponentFigure)getHostFigure()).setSelectedComponentColorAndBorder();	
	}

	
	

}
