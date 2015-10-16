
package com.bitwise.app.graph.figure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.XYLayout;
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
	protected Color selectedBorderColor;	
	protected Color componentColor;
	protected Color selectedComponentColor;
	private int height=0;
	private int totalPortsofInType=0, totalPortsOfOutType=0;
	private XYLayout layout;
	private PortFigure port;
	
	
	public ComponentFigure(List<PortSpecification> portSpecification) {
		
		layout = new XYLayout();
		setLayoutManager(layout);
		
		connectionAnchors = new Hashtable<String, FixedConnectionAnchor>();
		inputConnectionAnchors = new ArrayList<FixedConnectionAnchor>();
		outputConnectionAnchors = new ArrayList<FixedConnectionAnchor>();
		this.portspecification=portSpecification;

		setInitialColor();
		setComponentColorAndBorder();
		
		for(PortSpecification p:portspecification)
		{ 	
			c = new FixedConnectionAnchor(this, p.getTypeOfPort(), p.getNumberOfPorts(), p.getSequenceOfPort());
			connectionAnchors.put(c.getType()+c.getSequence(), c);
			if(p.getTypeOfPort().equalsIgnoreCase("out"))
				outputConnectionAnchors.add(c);
			else
				inputConnectionAnchors.add(c);	
			
		}
		
		
		for(PortSpecification p:portspecification)
		{	
			if(p.getTypeOfPort().equalsIgnoreCase("in")){
				totalPortsofInType=p.getNumberOfPorts();
				System.out.println("totalPortsofInType: "+totalPortsofInType);
			}
			else{
				totalPortsOfOutType=p.getNumberOfPorts();
				System.out.println("totalPortsOfOutType: "+totalPortsOfOutType);
			}
		}
		
		int heightFactor=totalPortsofInType > totalPortsOfOutType ? totalPortsofInType : totalPortsOfOutType;
		this.height = (heightFactor+1)*25;
		System.out.println("height: "+height);
		Point portPoint;
		for(PortSpecification p:portspecification)
		{
			port =  new PortFigure(borderColor);
			portPoint=getPortLocation(p.getNumberOfPorts(), p.getTypeOfPort(), p.getSequenceOfPort());
			add(port);
			setConstraint(port, new Rectangle(portPoint.x, portPoint.y, -1, -1));
			
		}
		
	}
	
	public void setComponentColorAndBorder(){
		setBackgroundColor(componentColor);
		setBorder(new ComponentBorder(borderColor));
	}
	
	public void setSelectedComponentColorAndBorder(){
		setBackgroundColor(selectedComponentColor);
		setBorder(new ComponentBorder(selectedBorderColor,3));
	}
	
	private void setInitialColor(){
		componentColor = ELTColorConstants.bgComponent;
		borderColor = ELTColorConstants.componentBorder;
		selectedComponentColor = ELTColorConstants.bgComponentSelected;
		selectedBorderColor = ELTColorConstants.componentSelectedBorder;
	}
	private Point getPortLocation(int totalPortsOfThisType, String type, int sequence) {
		Point p = null ;
		int width = 100;
		int portOffsetFactor = totalPortsOfThisType+1;
		int portOffset=height/portOffsetFactor;
		int xLocation, yLocation;

		if(type.equalsIgnoreCase("in")){
			xLocation=0;
			yLocation=portOffset*sequence - 4;
			p=new Point(xLocation, yLocation);
		}
		else if(type.equalsIgnoreCase("out")){
			xLocation=width-7;
			yLocation=portOffset*sequence - 4;
			p=new Point(xLocation, yLocation);
		}
		return p;
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {

	}
	
	protected void drawLable(Rectangle r, Graphics graphics){
		int x = (r.width - getLabelName().length() * 7) / 2;
		labelPoint = new Point(x, r.height / 2 - 10);
		graphics.setForegroundColor(ELTColorConstants.black);
		graphics.setFont(labelFont);
		graphics.drawText(getLabelName(), labelPoint);
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

	private ConnectionAnchor closestAnchor(Point p, List<FixedConnectionAnchor> connectionAnchors) {
		ConnectionAnchor closest = null;
		double min = Double.MAX_VALUE;
		for(ConnectionAnchor c : connectionAnchors){
			double d = p.getDistance(c.getLocation(null));
			if (d < min) {
				min = d;
				closest = c;
			}
		}
		return closest;
	}
	
	public ConnectionAnchor getSourceConnectionAnchorAt(Point p) {
		return closestAnchor(p, outputConnectionAnchors);
	}

	public ConnectionAnchor getTargetConnectionAnchorAt(Point p) {
		return closestAnchor(p, inputConnectionAnchors);
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
