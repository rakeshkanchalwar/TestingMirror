package com.bitwise.app.graph.figure;

import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;

import com.bitwise.app.common.component.config.PortSpecification;

public class DedupFigure extends ComponentFigure implements HandleBounds{

	public DedupFigure(List<PortSpecification> portSpecification) {
		super(portSpecification);
		borderColor=ELTColorConstants.black;
		setInitialColor();
		setComponentColorAndBorder();
	}
	
	private void setInitialColor(){
		componentColor = ELTColorConstants.dedupComponent;
		borderColor = ELTColorConstants.dedupComponentBorder;
		selectedComponentColor = ELTColorConstants.dedupComponentSelected;
		selectedBorderColor = ELTColorConstants.dedupComponentSelectedBorder;
	}

	@Override
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);

		Rectangle r = getBounds().getCopy();
		graphics.translate(r.getLocation());
		//graphics.setBackgroundColor(ELTColorConstants.lightRed);
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
