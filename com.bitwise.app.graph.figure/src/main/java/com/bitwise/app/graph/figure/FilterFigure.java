package com.bitwise.app.graph.figure;


import java.math.BigInteger;

import org.eclipse.draw2d.ColorConstants;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

public class FilterFigure extends ComponentFigure
implements HandleBounds{
	Point labelPoint;
	Font labelFont = new Font(null, "", 10, 1); 
	FixedConnectionAnchor c_in1, c_out1, c_out2;
	
	
	public FilterFigure(String componentName) {
		super(componentName);
		System.out.println("FilterFigure.componentName: " + componentName);
		
		c_in1 = new FixedConnectionAnchor(this);
		c_in1.setType("in");
		c_in1.setTotalPortsOfThisType(1);
		c_in1.setSequence(1);
		c_in1.setAllowMultipleLinks(true);
		c_in1.setLinkMandatory(true);
		
		connectionAnchors.put("in1", c_in1);
		inputConnectionAnchors.addElement(c_in1);
		//-------------------------------------
		
		c_out1 = new FixedConnectionAnchor(this);
		c_out1.setType("out");
		c_out1.setTotalPortsOfThisType(2);
		c_out1.setSequence(1);
		c_out1.setAllowMultipleLinks(true);
		c_out1.setLinkMandatory(true);
		
		connectionAnchors.put("out1", c_out1);
		outputConnectionAnchors.addElement(c_out1);
		//-------------------------------------
		
		c_out2 = new FixedConnectionAnchor(this);
		c_out2.setType("out");
		c_out2.setTotalPortsOfThisType(2);
		c_out2.setSequence(2);
		c_out2.setAllowMultipleLinks(true);
		c_out2.setLinkMandatory(false);
		
		connectionAnchors.put("out2", c_out2);
		outputConnectionAnchors.addElement(c_out2);
		//-------------------------------------
	}

	@Override
	protected void paintFigure(Graphics graphics) {

		super.paintFigure(graphics);
		Rectangle r = getBounds().getCopy();

		graphics.translate(r.getLocation());

		graphics.setBackgroundColor(new Color(null,166, 225, 245));
		
		graphics.setForegroundColor(ColorConstants.black);

		graphics.fillRectangle(0, 0, r.width, r.height);
		
		labelPoint = new Point(r.width/2-25, r.height/2-10);
	
		graphics.setFont(labelFont);
		graphics.drawText(getLabelName(), labelPoint);

		// ---------------------------------

	}

	@Override
	public void validate() {
		super.validate();
		System.out.println("FilterFigure:validate");
		if (isValid())
			return;

	}
	

	protected boolean useLocalCoordinates() {
		return true;
	}

	public Rectangle getHandleBounds() {
		//return getBounds().getCropped(new Insets(2, 0, 2, 0));
		return getBounds().getCopy();
	}

	
	
}
