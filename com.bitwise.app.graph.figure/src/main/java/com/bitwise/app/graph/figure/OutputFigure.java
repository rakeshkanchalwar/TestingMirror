package com.bitwise.app.graph.figure;

import java.math.BigInteger;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import com.bitwise.app.common.component.config.PortSpecification;



public class OutputFigure extends ComponentFigure 
implements HandleBounds{
	Point labelPoint;
	Font labelFont = new Font(null, "", 10, 1);
	List<PortSpecification> portspecification;
	FixedConnectionAnchor c;
	
	public OutputFigure(List<PortSpecification> portSpecification) {
		
		this.portspecification=portSpecification;
		setBorder(new ComponentBorder(ColorConstants.black));
		for(PortSpecification iterator:portspecification)
        { 	
		//for port at right side
       
        c = new FixedConnectionAnchor(this);
    	c.setType(iterator.getTypeOfPort());
    	c.setTotalPortsOfThisType(iterator.getNumberOfPorts());
        c.setSequence(iterator.getSequenceOfPort());
        connectionAnchors.put(c.getType()+c.getSequence(), c);
    	inputConnectionAnchors.addElement(c);
    	}
		
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

		
		  for(PortSpecification p:portspecification)
	        {
	    Point portPoint=getPortLocation1(r, p.getNumberOfPorts(),p.getTypeOfPort(),p.getSequenceOfPort());
		graphics.translate(portPoint);
		graphics.setBackgroundColor(ColorConstants.black);
		graphics.fillPolygon(connector);
		graphics.translate(portPoint.getNegated());
	        }
    }
	public Point getPortLocation1(Rectangle r, int totalPortsOfThisType, String type, int sequence) {

		System.out.println("getPortLocation method from figure called!!");
		Point p = null ;
		int portOffsetFactor = totalPortsOfThisType+1;
		int height = r.height;
		int portOffset=height/portOffsetFactor;

		int xLocation, yLocation;

		System.out.println("portOffsetFactor: "+portOffsetFactor);
		System.out.println("height: "+height);
		System.out.println("portOffset: "+portOffset);

		if(type.equalsIgnoreCase("in")){
			xLocation=r.getTopLeft().x+4;
			yLocation=r.getTopLeft().y+portOffset*sequence;
			System.out.println("IN: Returning point with xLocation, yLocation: "+xLocation+" "+yLocation);
			p=new Point(xLocation, yLocation);
		}
		else if(type.equalsIgnoreCase("out")){
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
