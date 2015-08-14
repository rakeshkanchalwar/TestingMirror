package com.bitwise.app.graph.components;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.bitwise.app.graph.components.model.Component;
import com.bitwise.app.graph.components.model.ComponentsDiagram;
import com.bitwise.app.graph.components.model.Connection;
import com.bitwise.app.graph.components.part.ComponentEditPart;
import com.bitwise.app.graph.components.part.ConnectionEditPart;
import com.bitwise.app.graph.components.part.DiagramEditPart;


public class ComponentsEditPartFactory implements EditPartFactory{
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,
	 * java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object modelElement) {
		// get EditPart for model element
		
		EditPart part = getPartForElement(modelElement);
		// store model element in EditPart
		part.setModel(modelElement);
		return part;
	}

	/**
	 * Maps an object to an EditPart.
	 * 
	 * @throws RuntimeException
	 *             if no match was found (programming error)
	 */
	private EditPart getPartForElement(Object modelElement) {
		if (modelElement instanceof ComponentsDiagram) {
			System.out.println("modelElement:"+modelElement.getClass());
			return new DiagramEditPart();
		}
		if (modelElement instanceof Component) {
			System.out.println("modelElement:"+modelElement.getClass());
			return new ComponentEditPart();
		}
		if (modelElement instanceof Connection) {
			System.out.println("modelElement:"+modelElement.getClass());			
			return new ConnectionEditPart();
		}
		throw new RuntimeException("Can't create part for model element: "
				+ ((modelElement != null) ? modelElement.getClass().getName()
						: "null"));
	}
}
