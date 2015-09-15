package com.bitwise.app.graph.figure;

import java.math.BigInteger;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;



public class OutputFigure extends ComponentFigure 
implements HandleBounds{
	Point labelPoint;
	Font labelFont = new Font(null, "", 10, 1);
	
	
	public OutputFigure(String componentName) {
		super(componentName);
		System.out.println("OutputFigure.componentName: " + componentName);
		FixedConnectionAnchor c;
		c = new FixedConnectionAnchor(this);
		
		c.setType("in");
		c.setTotalPortsOfThisType(1);
		c.setSequence(1);
		c.setAllowMultipleLinks(false);
		c.setLinkMandatory(true);
		
		connectionAnchors.put(c.getType()+c.getSequence(), c);
		inputConnectionAnchors.addElement(c);
		//setBorder(new ComponentBorder());
	}

	@Override
	protected void paintFigure(Graphics graphics) {

		super.paintFigure(graphics);
		Rectangle r = getBounds().getCopy();

		graphics.translate(r.getLocation());
		graphics.setBackgroundColor(new Color(null,166, 225, 245));
		graphics.setForegroundColor(ColorConstants.black);
		graphics.fillRectangle(0, 0, r.width, r.height);
		labelPoint = new Point(r.width/2-28, r.height/2-10);
		graphics.setFont(labelFont);
		graphics.drawText(getLabelName(), labelPoint);

	}

	@Override
	public void validate() {
		super.validate();
		System.out.println("OutputFigure:validate");
		if (isValid())
			return;

	}
	

	protected boolean useLocalCoordinates() {
		return true;
	}

	public Rectangle getHandleBounds() {
		
		return getBounds().getCopy();
	}
	
	
}
