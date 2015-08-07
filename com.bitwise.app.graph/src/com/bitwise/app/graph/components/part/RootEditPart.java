package com.bitwise.app.graph.components.part;


import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.bitwise.app.graph.components.model.Element;
import com.bitwise.app.graph.components.model.Root;

public class RootEditPart extends AbstractGraphicalEditPart {

    public RootEditPart(Root genealogyGraph) {
        setModel(genealogyGraph);
        genealogyGraph.addListener(new Runnable() {
            public void run() {
                refresh();
            }
        });
    }

    @Override
    public Root getModel() {
        return (Root) super.getModel();
    }

    @Override
    protected IFigure createFigure() {
        Figure figure = new FreeformLayer();
        figure.setLayoutManager(new FreeformLayout());
        figure.setBackgroundColor(ColorConstants.blue);
        return figure;
        
    }

    @Override
    protected List<Element> getModelChildren() {
        return getModel().getNotes();
    }

    @Override
    protected void createEditPolicies() {
    }

}