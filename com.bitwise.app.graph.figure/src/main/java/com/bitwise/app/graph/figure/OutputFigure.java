package com.bitwise.app.graph.figure;

import java.math.BigInteger;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.swt.graphics.Font;



public class OutputFigure extends ComponentFigure 
implements HandleBounds{
	Point labelPoint = new Point(15, 16);
	Font labelFont = new Font(null, "", 12, 0); 
	//FixedConnectionAnchor in, out;
	
	
	public OutputFigure(BigInteger inPorts) {

		
		FixedConnectionAnchor c;
		c = new FixedConnectionAnchor(this);
		c.setLeft(true);
		c.setNumberOfInComingLinksLimit(1);
		connectionAnchors.put(inPorts.toString(), c);
		inputConnectionAnchors.addElement(c);
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
	
		super.paintFigure(graphics);
		Rectangle r = getBounds().getCopy();
			
		System.out.println("OutputFigure:paintFigure");
		
		graphics.translate(r.getLocation());
		graphics.setBackgroundColor(ColorConstants.lightGray);
		graphics.setForegroundColor(ColorConstants.black);
		graphics.fillRectangle(0, 0, r.width, r.height);
		//setBorder(new LineBorder(1));
		graphics.setFont(labelFont);
		graphics.drawText("Output", labelPoint);
		
		
		
		
	}
	
	@Override
	public void validate() {
		System.out.println("OutputFigure:validate");
		if (isValid())
			return;
		
		
		super.validate();
	}
	

	protected boolean useLocalCoordinates() {
		return true;
	}

	public Rectangle getHandleBounds() {
		
		return getBounds().getCopy();
	}

	
	
}
