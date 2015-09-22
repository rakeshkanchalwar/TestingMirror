package com.bitwise.app.graph.figure;

import java.math.BigInteger;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;



public class OutputFigure extends ComponentFigure 
implements HandleBounds{
	Point labelPoint;
	Font labelFont = new Font(null, "", 10, 1);
	
	
	public OutputFigure() {
		
		FixedConnectionAnchor c;
		c = new FixedConnectionAnchor(this);
		c.setType("in");
		c.setTotalPortsOfThisType(1);
		c.setSequence(1);
		c.setAllowMultipleLinks(true);
		c.setLinkMandatory(true);
		
		connectionAnchors.put(c.getType()+c.getSequence(), c);
		inputConnectionAnchors.addElement(c);
		setBorder(new ComponentBorder(ColorConstants.black));
	}

	@Override
	protected void paintFigure(Graphics graphics) {

		super.paintFigure(graphics);
		Rectangle r = getBounds().getCopy();
		graphics.translate(r.getLocation());
		graphics.setBackgroundColor(new Color(null,220, 221, 227));
		graphics.setForegroundColor(ColorConstants.black);
		graphics.fillRectangle(4, 4, r.width-8, r.height-8);

		labelPoint = new Point(r.width/2-28, r.height/2-10);
		graphics.setFont(labelFont);
		graphics.drawText(getLabelName(), labelPoint);

		PointList connector = new PointList();
		connector.addPoint(4, 4);
		connector.addPoint(4, -4);
		connector.addPoint(-4, -4);
		connector.addPoint(-4, 4);
		
		graphics.translate(-r.getLocation().x, -r.getLocation().y);

		//for port at left side
		Point leftPortPoint=getPortLocation(r, 1, "in", 1);
		graphics.translate(leftPortPoint);
		graphics.setBackgroundColor(ColorConstants.black);
		graphics.fillPolygon(connector);

		

	}
	public Point getPortLocation(Rectangle r, int totalPortsOfThisType, String type, int sequence) {

		System.out.println("getPortLocation method from figure called!!");
		Point p = null ;
		int portOffsetFactor = totalPortsOfThisType+1;
		int height = r.height;
		int portOffset=height/portOffsetFactor;

		int xLocation, yLocation;

		System.out.println("portOffsetFactor: "+portOffsetFactor);
		System.out.println("height: "+height);
		System.out.println("portOffset: "+portOffset);

		if(type.equals("in")){
			xLocation=r.getTopLeft().x+4;
			yLocation=r.getTopLeft().y+portOffset*sequence;
			System.out.println("IN: Returning point with xLocation, yLocation: "+xLocation+" "+yLocation);
			p=new Point(xLocation, yLocation);
		}
		else if(type.equals("out")){
			xLocation=r.getTopRight().x-5;
			yLocation=r.getTopRight().y+portOffset*sequence;
			System.out.println("OUT: Returning point with xLocation, yLocation: "+xLocation+" "+yLocation);
			p=new Point(xLocation, yLocation);
			
		}
		return p;
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
