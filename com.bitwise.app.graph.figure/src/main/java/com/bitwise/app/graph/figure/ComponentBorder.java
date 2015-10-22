package com.bitwise.app.graph.figure;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

// TODO: Auto-generated Javadoc
/**
 * The Class ComponentBorder.
 * 
 * @author Bitwise
 */
public class ComponentBorder extends AbstractBorder {

	private Insets insets;
	private Color borderColor;
	private int lineWidth = 0;
	
	/**
	 * Instantiates a new component border.
	 * 
	 * @param borderColor
	 *            the border color
	 */
	public ComponentBorder(Color borderColor){
		this.borderColor = borderColor;
		insets=new Insets();
	}
	
	/**
	 * Instantiates a new component border.
	 * 
	 * @param borderColor
	 *            the border color
	 * @param lineWidth
	 *            the line width
	 */
	public ComponentBorder(Color borderColor,int lineWidth){
		this.borderColor = borderColor;
		this.lineWidth = lineWidth;
		insets=new Insets();
	}
	@Override
	public Insets getInsets(IFigure figure) {
		return insets;
	}

	@Override
	public void paint(IFigure figure, Graphics g, Insets in) {
		
		Rectangle r = figure.getBounds().getCopy();
		
		g.setForegroundColor(borderColor);

		if(lineWidth!=0){
			g.setLineWidth(lineWidth);
		}

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

