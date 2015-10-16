package com.bitwise.app.graph.figure;


import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class PortFigure extends Figure {

	private Color portColor;
	public PortFigure(Color portColor) {
		this.portColor = portColor;
		getBounds().setSize(new Dimension(8, 8));

	}

	@Override
	protected void paintFigure(Graphics graphics) {

		super.paintFigure(graphics);
		Rectangle r = getBounds().getCopy();
		graphics.setBackgroundColor(portColor);
		graphics.fillRectangle(getBounds().getLocation().x, getBounds()
				.getLocation().y, r.width, r.height);

	}

	@Override
	public void validate() {
		super.validate();

		if (isValid())
			return;

	}

//	protected boolean useLocalCoordinates() {
//		return true;
//	}

	public Rectangle getHandleBounds() {
		return getBounds().getCopy();
	}

}
