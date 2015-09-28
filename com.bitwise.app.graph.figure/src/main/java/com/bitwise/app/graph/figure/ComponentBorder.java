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
	private Color borderColor;

	ComponentBorder(Color borderColor){
		this.borderColor = borderColor;
	}
	
	public Insets getInsets(IFigure figure) {
		return insets;
	}

	public void paint(IFigure figure, Graphics g, Insets in) {
		
		Rectangle r = figure.getBounds().getCopy();
		
		g.setForegroundColor(borderColor);
		//g.setBackgroundColor(new Color(null,191, 52, 114));


		// Outline the border
		//r.getShrinked(4, 4);

		//top
		g.drawLine(r.x+4+4, r.y+4, r.right() - 5-4, r.y+4);
		
		//Bottom
		g.drawLine(r.x+4+4, r.bottom()-5, r.right() - 5-4, r.bottom()-5);
		
		//Left
		g.drawLine(r.x+4, r.y + 4+4, r.x+4, r.bottom() - 5-4);
		
		//right
		g.drawLine(r.right() - 5, r.bottom() - 5-4, r.right() - 5, r.y + 4+4);
		
		//----------Arcs at corners---------------------------
		
		//top right
		g.drawArc(r.right() - 5-4-4, r.y + 4, 8, 8, 0, 90);
		
		//bottom left
		g.drawArc(r.x+4, r.bottom()-5-4-4, 8, 8, 180, 90);
		
		//bottom right
		g.drawArc(r.right() - 5-4-4, r.bottom()-5-4-4, 8, 8, 0, -90);
		
		//top left
		g.drawArc(r.x+4, r.y + 4, 8, 8, 180, -90);
		
		
		r.getExpanded(new Insets(0, 0, 0, 0));
		r.expand(1, 1);
		
		
	}

}

