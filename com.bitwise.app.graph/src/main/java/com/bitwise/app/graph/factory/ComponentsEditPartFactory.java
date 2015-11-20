package com.bitwise.app.graph.factory;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.bitwise.app.graph.controller.ComponentEditPart;
import com.bitwise.app.graph.controller.ContainerEditPart;
import com.bitwise.app.graph.controller.LinkEditPart;
import com.bitwise.app.graph.controller.ComponentLabelEditPart;
import com.bitwise.app.graph.controller.PortEditPart;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Container;
import com.bitwise.app.graph.model.Link;
import com.bitwise.app.graph.model.ComponentLabel;
import com.bitwise.app.graph.model.Port;

/**
 * Factory class to create the edit part for the given model.
 */
public class ComponentsEditPartFactory implements EditPartFactory{
	
	/**
	 * Creates edit parts for given model.
	 */
	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		// get EditPart for model element
		EditPart part = null;
		if (model instanceof Container) {
			part = new ContainerEditPart();
		}
		else if (model instanceof Component) {
			part = new ComponentEditPart();
		}
		else if (model instanceof Link) {
			part = new LinkEditPart();
		}
		else if (model instanceof Port){
			part = new PortEditPart();
		}
		else if (model instanceof ComponentLabel){
			part = new ComponentLabelEditPart();
		}
		else{
			//TODO : Add logger
			throw new RuntimeException("Can't create edit part for model element: "	+ 
						((model != null) ? model.getClass().getName() : "null"));
		}
		
		// store model element in EditPart
		part.setModel(model);
		return part;
	}
}
