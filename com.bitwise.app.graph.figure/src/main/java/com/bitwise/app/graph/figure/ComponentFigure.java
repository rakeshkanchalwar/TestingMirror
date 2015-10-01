/*******************************************************************************
 * Copyright (c) 2000, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.bitwise.app.graph.figure;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import com.bitwise.app.common.component.config.PortSpecification;

public class ComponentFigure extends Figure {

	private String labelName;
	private FixedConnectionAnchor c; 
	protected Hashtable<String, FixedConnectionAnchor> connectionAnchors;
	protected List<FixedConnectionAnchor> inputConnectionAnchors;
	protected List<FixedConnectionAnchor> outputConnectionAnchors;
	protected List<PortSpecification> portspecification;
	
	protected Font labelFont = new Font(null, "", 10, SWT.BOLD); 
	protected Point labelPoint;
	protected Color borderColor;

	
	public ComponentFigure(List<PortSpecification> portSpecification) {

		connectionAnchors = new Hashtable<String, FixedConnectionAnchor>();
		inputConnectionAnchors = new ArrayList<FixedConnectionAnchor>();
		outputConnectionAnchors = new ArrayList<FixedConnectionAnchor>();
		this.portspecification=portSpecification;

		for(PortSpecification p:portspecification)
		{ 	
			c = new FixedConnectionAnchor(this);
			c.setType(p.getTypeOfPort());
			c.setTotalPortsOfThisType(p.getNumberOfPorts());
			c.setSequence(p.getSequenceOfPort());
			connectionAnchors.put(c.getType()+c.getSequence(), c);
			if(p.getTypeOfPort().equalsIgnoreCase("out"))
				outputConnectionAnchors.add(c);
			else
				inputConnectionAnchors.add(c);	
		}
		
	}
	@Override
	protected void paintFigure(Graphics graphics) {
		int totalPortsofInType=0, totalPortsOfOutType=0;
		for(PortSpecification p:portspecification)
		{	
			if(p.getTypeOfPort().equalsIgnoreCase("in"))
				totalPortsofInType=p.getNumberOfPorts();
			else
				totalPortsOfOutType=p.getNumberOfPorts();	
		}
		
		Rectangle r = getBounds().getCopy();
		
		int height=totalPortsofInType > totalPortsOfOutType ? totalPortsofInType : totalPortsOfOutType;
		
		Rectangle newR = new Rectangle(r.x, r.y, r.width, (height+1)*25);
		
		setBounds(newR);
	}

	protected void drawLable(Rectangle r, Graphics graphics){
		int x = (r.width - getLabelName().length() * 7) / 2;
		labelPoint = new Point(x, r.height / 2 - 10);
		graphics.setForegroundColor(ELTColorConstants.black);
		graphics.setFont(labelFont);
		graphics.drawText(getLabelName(), labelPoint);
	}

	protected void drawPorts(Rectangle r, Graphics graphics){
		PointList portPoints = new PointList();
		portPoints.addPoint(4, 4);
		portPoints.addPoint(4, -4);
		portPoints.addPoint(-4, -4);
		portPoints.addPoint(-4, 4);

		graphics.translate(r.getLocation().getNegated());
		Point portPoint;
		for(PortSpecification p:portspecification)
		{
			portPoint=getPortLocation(r, p.getNumberOfPorts(), p.getTypeOfPort(), p.getSequenceOfPort());
			graphics.translate(portPoint);
			graphics.setBackgroundColor(borderColor);
			graphics.fillPolygon(portPoints);
			graphics.translate(portPoint.getNegated());
		}
	}
	
	private Point getPortLocation(Rectangle r, int totalPortsOfThisType, String type, int sequence) {
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
			xLocation=r.getTopRight().x-4;
			yLocation=r.getTopRight().y+portOffset*sequence;

			p=new Point(xLocation, yLocation);

		}
		return p;
	}

	public ConnectionAnchor getConnectionAnchor(String terminal) {

		return (ConnectionAnchor) connectionAnchors.get(terminal);
	}

	public String getConnectionAnchorName(ConnectionAnchor c) {
		Enumeration<String> keys = connectionAnchors.keys();
		String key;
		while (keys.hasMoreElements()) {
			key = (String) keys.nextElement();

			if (connectionAnchors.get(key).equals(c))
				return key;
		}
		return null;
	}

	public ConnectionAnchor getSourceConnectionAnchorAt(Point p) {

		ConnectionAnchor closest = null;
		double min = Double.MAX_VALUE;

		for (int i = 0; i < outputConnectionAnchors.size(); i++) {

			ConnectionAnchor c = (ConnectionAnchor) outputConnectionAnchors.get(i);
			Point p2 = c.getLocation(null);
			double d = p.getDistance(p2);
			if (d < min) {
				min = d;
				closest = c;
			}
		}
		return closest;
	}


	public ConnectionAnchor getTargetConnectionAnchorAt(Point p) {

		ConnectionAnchor closest = null;
		double min = Double.MAX_VALUE;

		for (int i = 0; i < inputConnectionAnchors.size(); i++) {

			ConnectionAnchor c = (ConnectionAnchor) inputConnectionAnchors.get(i);
			Point p2 = c.getLocation(null);
			double d = p.getDistance(p2);
			if (d < min) {
				min = d;
				closest = c;
			}
		}
		return closest;
	}
	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	
	@Override
	public void validate() {
		super.validate();

		if (isValid())
			return;

	}

}
