package com.bitwise.app.graph.policy;

import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;

import com.bitwise.app.graph.controller.ComponentLabelEditPart;
import com.bitwise.app.graph.figure.ComponentFigure;
import com.bitwise.app.graph.figure.ComponentLabelFigure;

/**
 * 
 * @author Bitwise
 * Oct 15, 2015
 * 
 */

public class ComponentSelectionPolicy extends SelectionEditPolicy {

	protected List handles;
	
	@Override
	protected void hideSelection() {		
		((ComponentFigure)getHostFigure()).setComponentColorAndBorder();
		hideLabelSelection();
	}

	@Override
	protected void showSelection() {
		((ComponentFigure)getHostFigure()).setSelectedComponentColorAndBorder();
		showLabelSelection();
		
	}

	
	private void showLabelSelection(){
		List<Figure> childrenFigures = ((ComponentFigure)getHostFigure()).getChildren();
		if (!childrenFigures.isEmpty()){
			for(Figure figure:childrenFigures)
			{
				if(figure instanceof ComponentLabelFigure)
					((ComponentLabelFigure) figure).selectLabel();
			}
		}
	}
	
	private void hideLabelSelection(){
		List<Figure> childrenFigures = ((ComponentFigure)getHostFigure()).getChildren();
		if (!childrenFigures.isEmpty()){
			for(Figure figure:childrenFigures)
			{
				if(figure instanceof ComponentLabelFigure)
					((ComponentLabelFigure) figure).deSelectlabel();
			}
		}
	}

}
