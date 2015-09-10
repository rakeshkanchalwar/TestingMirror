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

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;

public class ComponentFigure extends Figure {

	protected Hashtable connectionAnchors = new Hashtable(7);
	protected Vector inputConnectionAnchors = new Vector(2, 2);
	protected Vector outputConnectionAnchors = new Vector(2, 2);
	//private Label labelName = new Label();
	protected String labelName;

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public ComponentFigure(String componentName) {
		this.labelName = componentName;
		System.out.println("ComponentFigure.labelName: " + componentName);
	}
	
	public ConnectionAnchor getConnectionAnchor(String terminal) {
		System.out.println("terminal: "+terminal);
		return (ConnectionAnchor) connectionAnchors.get(terminal);
	}

	public String getConnectionAnchorName(ConnectionAnchor c) {
		Enumeration keys = connectionAnchors.keys();
		String key;
		while (keys.hasMoreElements()) {
			key = (String) keys.nextElement();
			System.out.println("getConnectionAnchorName: key"+key);
			if (connectionAnchors.get(key).equals(c))
				return key;
		}
		return null;
	}

	public ConnectionAnchor getSourceConnectionAnchorAt(Point p) {
		System.out.println("ComponentFigure:getSourceConnectionAnchorAt");
		ConnectionAnchor closest = null;
		double min = Double.MAX_VALUE;

		Enumeration e = getSourceConnectionAnchors().elements();
		while (e.hasMoreElements()) {
			ConnectionAnchor c = (ConnectionAnchor) e.nextElement();
			Point p2 = c.getLocation(null);
			double d = p.getDistance(p2);
			if (d < min) {
				min = d;
				closest = c;
			}
		}
		return closest;
	}

	public Vector getSourceConnectionAnchors() {
		System.out.println("outputConnectionAnchors.size(): "+outputConnectionAnchors.size()); 
		return outputConnectionAnchors;
	}

	public ConnectionAnchor getTargetConnectionAnchorAt(Point p) {
		System.out.println("NodeFigure:getTargetConnectionAnchorAt");
		ConnectionAnchor closest = null;
		double min = Double.MAX_VALUE;

		Enumeration e = getTargetConnectionAnchors().elements();
		while (e.hasMoreElements()) {
			ConnectionAnchor c = (ConnectionAnchor) e.nextElement();
			Point p2 = c.getLocation(null);
			
			double d =p.getDistance(p2);
			if (d < min) {
				min = d;
				closest = c;
			}
		}
		return closest;
	}

	public Vector getTargetConnectionAnchors() {
		System.out.println("inputConnectionAnchors.size(): "+inputConnectionAnchors.size());
		return inputConnectionAnchors;
	}
	
//	public ConnectionAnchor connectionAnchorAt(Point p) {
//		System.out.println("ComponentFigure:connectionAnchorAtttttttt");
//		ConnectionAnchor closest = null;
//		long min = Long.MAX_VALUE;
//
//		Enumeration e = getSourceConnectionAnchors().elements();
//	
//		while (e.hasMoreElements()) {
//			System.out.println("ComponentFigure:SourceConnectionAnchors iterating through enum");
//			
//			ConnectionAnchor c = (ConnectionAnchor) e.nextElement();
//			Point p2 = c.getLocation(null);
//			long d=(long) p.getDistance(p2);
//			//long d = p.getDistance2(p2);
//			if (d < min) {
//				min = d;
//				closest = c;
//				
//			}
//		}
//		e = getTargetConnectionAnchors().elements();
//		while (e.hasMoreElements()) {
//			System.out.println("ComponentFigure:TargetConnectionAnchors iterating through enum");
//			ConnectionAnchor c = (ConnectionAnchor) e.nextElement();
//			Point p2 = c.getLocation(null);
//			long d = (long) p.getDistance(p2);
//			//long d = p.getDistance2(p2);
//			if (d < min) {
//				min = d;
//				closest = c;
//			}
//		}
//		return closest;
//	}

}
