package com.bitwise.app.graph.figure;


import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;

import com.bitwise.app.common.component.config.PortSpecification;

public class ReplicateFigure extends ComponentFigure
implements HandleBounds{
	
	public ReplicateFigure(List<PortSpecification> portSpecification) {
		super(portSpecification);
		borderColor=ELTColorConstants.black;
		//setBorder(new ComponentBorder(borderColor));
		setInitialColor();
		setComponentColorAndBorder();
	}

	private void setInitialColor(){
		componentColor = ELTColorConstants.replicateComponent;
		borderColor = ELTColorConstants.replicateComponentBorder;
		selectedComponentColor = ELTColorConstants.replicateComponentSelected;
		selectedBorderColor = ELTColorConstants.replicateComponentSelectedBorder;
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
		Rectangle r = getBounds().getCopy();
		graphics.translate(r.getLocation());
		//graphics.setBackgroundColor(ELTColorConstants.darkGrey);
		
		Rectangle q = new Rectangle(4, 4, r.width-8, r.height-8);
		graphics.fillRoundRectangle(q, 5, 5);
		
		drawLable(r, graphics);

		drawPorts(r, graphics);

	}

	public Rectangle getHandleBounds() {
		return getBounds().getCopy();
	}

}

