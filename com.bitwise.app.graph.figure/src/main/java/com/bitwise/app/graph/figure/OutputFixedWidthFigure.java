package com.bitwise.app.graph.figure;

import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.swt.graphics.Color;

import com.bitwise.app.common.component.config.PortSpecification;

public class OutputFixedWidthFigure extends ComponentFigure implements HandleBounds
{

	public OutputFixedWidthFigure(List<PortSpecification> portSpecification) {
		super(portSpecification);
		borderColor=ELTColorConstants.black;
		//setBorder(new ComponentBorder(borderColor));
		setInitialColor();
		setComponentColorAndBorder();
	}
	
	private void setInitialColor(){
		componentColor = ELTColorConstants.outputFWComponent;
		borderColor = ELTColorConstants.outputFWComponentBorder;
		selectedComponentColor = ELTColorConstants.outputFWComponentSelected;
		selectedBorderColor = ELTColorConstants.outputFWComponentSelectedBorder;
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
		Rectangle r = getBounds().getCopy();
		graphics.translate(r.getLocation());
		//graphics.setBackgroundColor(new Color(null,220, 221, 227));
		Rectangle q = new Rectangle(4, 4, r.width-8, r.height-8);
		graphics.fillRoundRectangle(q, 5, 5);

		drawLable(r, graphics);

		drawPorts(r, graphics);
	}

	@Override
	public Rectangle getHandleBounds() {
		
		return getBounds().getCopy();
	}

	

}
