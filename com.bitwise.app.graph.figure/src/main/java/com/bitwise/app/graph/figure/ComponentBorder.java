package com.bitwise.app.graph.figure;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class ComponentBorder extends AbstractBorder {

	protected static Insets insets = new Insets(8, 6, 8, 6);
		

	public Insets getInsets(IFigure figure) {
		return insets;
	}

	public void paint(IFigure figure, Graphics g, Insets in) {
		
		Rectangle r = figure.getBounds().getCopy();
		
		g.setForegroundColor(ColorConstants.black);
		g.setBackgroundColor(new Color(null,166, 225, 245));


		// Outline the border
		g.setForegroundColor(ColorConstants.black);
		g.drawLine(r.x, r.y, r.right() - 1, r.y);
		g.drawLine(r.x, r.bottom()-1, r.right() - 1, r.bottom()-1);
		g.drawLine(r.x, r.y + 1, r.x, r.bottom() - 1);
		g.drawLine(r.right() - 1, r.bottom() - 1, r.right() - 1, r.y + 1);

		
		r.getExpanded(new Insets(0, 0, 0, 0));
		r.expand(1, 1);
		
		
	}

}

