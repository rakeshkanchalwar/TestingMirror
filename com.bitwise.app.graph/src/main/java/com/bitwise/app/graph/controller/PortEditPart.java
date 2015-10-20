package com.bitwise.app.graph.controller;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.tools.ConnectionDragCreationTool;

import com.bitwise.app.common.component.config.PortSpecification;
import com.bitwise.app.graph.figure.ComponentFigure;
import com.bitwise.app.graph.figure.PortFigure;
import com.bitwise.app.graph.model.Port;

public class PortEditPart extends AbstractGraphicalEditPart {


	public Port getCastedModel() {
		return (Port) getModel();
	}

	@Override
	protected IFigure createFigure() {
		
		
		String terminal = getCastedModel().getTerminal();
		int height;
		
		ComponentFigure componentFigure = ((ComponentEditPart) getParent()).getComponentFigure();
		height = componentFigure.getHeight();
		//PortFigure portFigure = new PortFigure(ELTColorConstants.darkGrey, terminal);
		
		System.out.println("PortFigure.createFigure for terminal: "+terminal);
		
		List<PortSpecification> portSpecifications = getCastedModel().getParent().getPortSpecification();
		Point portPoint = null;
		for(PortSpecification p:portSpecifications)
			{
				String portTerminal = p.getTypeOfPort()+p.getSequenceOfPort();
				if(portTerminal.equals(terminal)){
					portPoint=getPortLocation(p.getNumberOfPorts(), p.getTypeOfPort(), p.getSequenceOfPort(), height);
					break;
				}

		}
		
		
		PortFigure portFigure = componentFigure.getPortFigure(terminal);
		portFigure.setLocation(portPoint);
		
		return portFigure;
	}

	private Point getPortLocation(int totalPortsOfThisType, String type, int sequence, int height) {
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
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public DragTracker getDragTracker(Request request) {
		getViewer().select(this);
		return new ConnectionDragCreationTool();
	}

}
