
package com.bitwise.app.graph.figure;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

import com.bitwise.app.common.component.config.PortSpecification;
import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Component.ValidityStatus;

public class ComponentFigure extends Figure implements Validator{
	
	private final XYLayout layout;
	private int height=0;
	
	private FixedConnectionAnchor c; 
	private Hashtable<String, FixedConnectionAnchor> connectionAnchors;
	private List<FixedConnectionAnchor> inputConnectionAnchors;
	private List<FixedConnectionAnchor> outputConnectionAnchors;
	private List<PortSpecification> portspecification;
	
	private int totalPortsofInType=0, totalPortsOfOutType=0;
	private PortFigure port;
	private Hashtable<String, PortFigure> ports;
	
	private String labelName;
	private Font labelFont = new Font(null, "", 8, SWT.NORMAL); 
	private Point labelPoint;
	
	private Color borderColor;
	private Color selectedBorderColor;	
	private Color componentColor;
	private Color selectedComponentColor;
	
	private String canvasIconPath;
	private Image canvasIcon;
	
	private Component.ValidityStatus status;
	
	
	public ComponentFigure(List<PortSpecification> portSpecification, String cIconPath) {
		
		this.portspecification = portSpecification;
		this.canvasIconPath = XMLConfigUtil.CONFIG_FILES_PATH + cIconPath;
		
		layout = new XYLayout();
		setLayoutManager(layout);
		
		canvasIcon = new Image(null, canvasIconPath);
		
		ports = new Hashtable<String, PortFigure>();
		
		connectionAnchors = new Hashtable<String, FixedConnectionAnchor>();
		inputConnectionAnchors = new ArrayList<FixedConnectionAnchor>();
		outputConnectionAnchors = new ArrayList<FixedConnectionAnchor>();
		

		setInitialColor();
		setComponentColorAndBorder();
		
		for(PortSpecification p:portspecification)
		{
			setPortCount(p);
			setHeight(totalPortsofInType, totalPortsOfOutType);
		}
		
		for(PortSpecification p:portspecification)
		{ 	
			initAnchors(p);	
		}
		
	}

	private void setPortCount(PortSpecification p) {
		if(p.getTypeOfPort().equalsIgnoreCase("in")){
			totalPortsofInType=p.getNumberOfPorts();
		}
		else{
			totalPortsOfOutType=p.getNumberOfPorts();
		}
		
	}

	private void setHeight(int totalPortsofInType, int totalPortsOfOutType) {
		int heightFactor=totalPortsofInType > totalPortsOfOutType ? totalPortsofInType : totalPortsOfOutType;
		this.height = (heightFactor+1)*25;
	}

	public int getHeight() {
		return height;
	}
	
	public PortFigure getPortFigure(String terminal) {
		return ports.get(terminal);
	}

	private void initAnchors(PortSpecification p) {
		c = new FixedConnectionAnchor(this, p.getTypeOfPort(), p.getNumberOfPorts(), p.getSequenceOfPort());
		connectionAnchors.put(c.getType()+c.getSequence(), c);
		if(p.getTypeOfPort().equalsIgnoreCase("out"))
			outputConnectionAnchors.add(c);
		else
			inputConnectionAnchors.add(c);
	}
	
	/**
	 * Draws the status image to right corner of the component
	 * @param graphics
	 */
	protected void drawStatus(Graphics graphics){
		Image statusImage = null;
		Rectangle rectangle = getBounds().getCopy();
		if(getStatus().equals(ValidityStatus.WARN)){
			statusImage = new Image(null, XMLConfigUtil.CONFIG_FILES_PATH + "/icons/warn.jpg");
		}
		else if (getStatus().equals(ValidityStatus.ERROR)){
			statusImage = new Image(null, XMLConfigUtil.CONFIG_FILES_PATH + "/icons/error.jpg");
		}
		if(statusImage != null){
			graphics.drawImage(statusImage, new Point(rectangle.width - 15, 8));
		}
	}
	
		
	public void setComponentColorAndBorder(){
		setBackgroundColor(componentColor);
		setBorder(new ComponentBorder(borderColor));
	}
	
	public void setSelectedComponentColorAndBorder(){
		setBackgroundColor(selectedComponentColor);
		setBorder(new ComponentBorder(selectedBorderColor,2));
	}
	
	private void setInitialColor(){
		componentColor = ELTColorConstants.bgComponent;
		borderColor = ELTColorConstants.componentBorder;
		selectedComponentColor = ELTColorConstants.bgComponentSelected;
		selectedBorderColor = ELTColorConstants.componentSelectedBorder;
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
		Rectangle r = getBounds().getCopy();
		graphics.translate(r.getLocation());
		Rectangle q = new Rectangle(4, 4, r.width-8, r.height-8);
		graphics.fillRoundRectangle(q, 5, 5);

		drawLable(r, graphics);
		
		graphics.drawImage(canvasIcon, new Point(r.width/2-16, r.height/2 - 16));
	}
	
	private void drawLable(Rectangle r, Graphics graphics){
		int x = (r.width - getLabelName().length() * 6) / 2;
		labelPoint = new Point(x, r.height / 2 + 8);
		graphics.setForegroundColor(ELTColorConstants.black);
		graphics.setFont(labelFont);
		graphics.drawText(getLabelName(), labelPoint);
	}


	public ConnectionAnchor getConnectionAnchor(String terminal) {

		return connectionAnchors.get(terminal);
	}

	public String getConnectionAnchorName(ConnectionAnchor c) {
		Enumeration<String> keys = connectionAnchors.keys();
		String key;
		while (keys.hasMoreElements()) {
			key = keys.nextElement();

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
	@Override
	public ValidityStatus getStatus() {
		return status;
	}
	
	@Override
	public void setStatus(ValidityStatus status) {
		this.status = status;
	}
}
