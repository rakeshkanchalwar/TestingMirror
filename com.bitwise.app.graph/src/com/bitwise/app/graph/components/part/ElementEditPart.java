package com.bitwise.app.graph.components.part;


import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.bitwise.app.graph.components.model.Element;
import com.bitwise.app.graph.components.model.Root;

public class ElementEditPart extends AbstractGraphicalEditPart {

    public ElementEditPart(Element note) {
        setModel(note);
    }

    @Override
    protected void refreshVisuals() {
    	System.out.println("ElementEditPart:refreshVisuals()");
        Element m = getModel();
        Rectangle bounds = new Rectangle(m.x, m.y, m.w, m.h);
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), bounds);
        super.refreshVisuals();
    }

    @Override
    public RootEditPart getParent() {
        return (RootEditPart) super.getParent();
    }

    @Override
    public Element getModel() {
        return (Element) super.getModel();
    }

    @Override
    protected IFigure createFigure() {
        RectangleFigure f = new RectangleFigure();
        f.setBackgroundColor(ColorConstants.green);
        return f;
    }

    @Override
    protected void createEditPolicies() {
        NonResizableEditPolicy selectionPolicy = new NonResizableEditPolicy();
        selectionPolicy.setDragAllowed(false);
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, selectionPolicy);
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentEditPolicy() {
            @Override
            protected Command createDeleteCommand(GroupRequest deleteRequest) {
                final Root root = getParent().getModel();
                final Element element = getModel();
                return new Command() {
                    @Override public void execute() {
                        System.out.println("Removing...");
                        root.removeElement(element);
                    }
                    @Override public void undo() {
                        root.addElement(element);
                    }
                };
            }
        });
    }

}
