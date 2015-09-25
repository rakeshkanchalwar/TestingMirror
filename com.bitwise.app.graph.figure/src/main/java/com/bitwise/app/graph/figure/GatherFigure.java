package com.bitwise.app.graph.figure;


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

public class GatherFigure extends ComponentFigure
implements HandleBounds{
	Point labelPoint;
	Font labelFont = new Font(null, "", 10, 1); 
	FixedConnectionAnchor c;
	List<PortSpecification> portspecification;

	public GatherFigure(List<PortSpecification> portSpecification) {
		this.portspecification=portSpecification;
		setBorder(new ComponentBorder(ColorConstants.black));
		for(PortSpecification iterator:portspecification)
		{ 	
			c = new FixedConnectionAnchor(this);
			c.setType(iterator.getTypeOfPort());
			c.setTotalPortsOfThisType(iterator.getNumberOfPorts());
			c.setSequence(iterator.getSequenceOfPort());
			connectionAnchors.put(c.getType()+c.getSequence(), c);
			if(iterator.getTypeOfPort().equalsIgnoreCase("out"))
				outputConnectionAnchors.addElement(c);
			else
				inputConnectionAnchors.addElement(c);	
		}


	}

	@Override
	protected void paintFigure(Graphics graphics) {

		super.paintFigure(graphics);
		Rectangle r = getBounds().getCopy();
		graphics.translate(r.getLocation());
		graphics.setBackgroundColor(new Color(null,188, 190, 196));
		graphics.setForegroundColor(ColorConstants.black);
		graphics.fillRectangle(4, 4, r.width-8, r.height-8);

		int x = (r.width - getLabelName().length() * 7) / 2;
		labelPoint = new Point(x, r.height / 2 - 10);
		graphics.setFont(labelFont);
		graphics.drawText(getLabelName(), labelPoint);

		PointList connector = new PointList();
		connector.addPoint(4, 4);
		connector.addPoint(4, -4);
		connector.addPoint(-4, -4);
		connector.addPoint(-4, 4);

		graphics.translate(-r.getLocation().x, -r.getLocation().y);

		//for port at left side
		for(PortSpecification p:portspecification)
		{
			Point portPoint=getPortLocation(r,p.getNumberOfPorts(),p.getTypeOfPort(),p.getSequenceOfPort());
			graphics.translate(portPoint);
			graphics.setBackgroundColor(ColorConstants.black);
			graphics.fillPolygon(connector);
			graphics.translate(portPoint.getNegated());
		}
	}

	public Point getPortLocation(Rectangle r, int totalPortsOfThisType, String type, int sequence) {


		Point p = null ;
		int portOffsetFactor = totalPortsOfThisType+1;
		int height = r.height;
		int portOffset=height/portOffsetFactor;

		int xLocation, yLocation;

		if(type.equalsIgnoreCase("in")){
			xLocation=r.getTopLeft().x+4;
			yLocation=r.getTopLeft().y+portOffset*sequence;

			p=new Point(xLocation, yLocation);
		}
		else if(type.equalsIgnoreCase("out")){
			xLocation=r.getTopRight().x-5;
			yLocation=r.getTopRight().y+portOffset*sequence;

			p=new Point(xLocation, yLocation);

		}
		return p;
	}

	@Override
	public void validate() {
		super.validate();

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

