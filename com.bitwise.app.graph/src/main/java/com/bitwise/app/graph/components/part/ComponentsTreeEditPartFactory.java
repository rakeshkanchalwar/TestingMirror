package com.bitwise.app.graph.components.part;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.bitwise.app.graph.components.model.Component;
import com.bitwise.app.graph.components.model.ComponentsDiagram;


/**
 * Factory that maps model elements to TreeEditParts. TreeEditParts are used in
 * the outline view of the ShapesEditor.
 * 
 */
public class ComponentsTreeEditPartFactory implements EditPartFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,
	 * java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof Component) {
			return new ComponentTreeEditPart((Component) model);
		}
		if (model instanceof ComponentsDiagram) {
			return new DiagramTreeEditPart((ComponentsDiagram) model);
		}
		return null; // will not show an entry for the corresponding model
						// instance
	}
}
