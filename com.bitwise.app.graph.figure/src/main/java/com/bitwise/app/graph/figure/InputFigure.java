package com.bitwise.app.graph.figure;


import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;

import com.bitwise.app.common.component.config.PortSpecification;

public class InputFigure extends ComponentFigure
implements HandleBounds{
	

	public InputFigure(List<PortSpecification> portSpecification) {
		super(portSpecification);
		borderColor=ELTColorConstants.black;
		//setBorder(new ComponentBorder(borderColor));
		setInitialColor();
		setComponentColorAndBorder();
	}

	private void setInitialColor(){
		componentColor = ELTColorConstants.inputComponent;
		borderColor = ELTColorConstants.inputComponentBorder;
		selectedComponentColor = ELTColorConstants.inputComponentSelected;
		selectedBorderColor = ELTColorConstants.inputComponentSelectedBorder;
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {		
		super.paintFigure(graphics);
		Rectangle rectangle = getBounds().getCopy();
		graphics.translate(rectangle.getLocation());
		//graphics.setBackgroundColor(ELTColorConstants.lightGrey);
		Rectangle fillRectangleArea = new Rectangle(4, 4, rectangle.width-8, rectangle.height-8);
		graphics.fillRoundRectangle(fillRectangleArea, 5, 5);
		//TODO : Enable once the Error mapping for property window is done
		//drawStatus(graphics);
		drawLable(rectangle, graphics);
		drawPorts(rectangle, graphics);
	}

	public Rectangle getHandleBounds() {
		return getBounds().getCopy();
	}

}
