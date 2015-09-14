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
import java.util.Vector;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;

public class ComponentFigure extends Figure {

	protected Hashtable<String, FixedConnectionAnchor> connectionAnchors = new Hashtable<String, FixedConnectionAnchor>(7);
	protected Vector<FixedConnectionAnchor> inputConnectionAnchors = new Vector<FixedConnectionAnchor>(2, 2);
	protected Vector<FixedConnectionAnchor> outputConnectionAnchors = new Vector<FixedConnectionAnchor>(2, 2);
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
		
		return (ConnectionAnchor) connectionAnchors.get(terminal);
	}

	public String getConnectionAnchorName(ConnectionAnchor c) {
		Enumeration<String> keys = connectionAnchors.keys();
		String key;
		while (keys.hasMoreElements()) {
			key = (String) keys.nextElement();
			System.out.println("ComponentFigure:getConnectionAnchorName-> key: "+key);
			
			if (connectionAnchors.get(key).equals(c))
				return key;
		}
		return null;
	}

	public ConnectionAnchor getSourceConnectionAnchorAt(Point p) {
		
		ConnectionAnchor closest = null;
		double min = Double.MAX_VALUE;

		Enumeration<FixedConnectionAnchor> e = getSourceConnectionAnchors().elements();
		
		while (e.hasMoreElements()) {
			
			ConnectionAnchor c = (ConnectionAnchor) e.nextElement();
			System.out.println("getSourceConnectionAnchorAt->c.toString(): "+c.toString());
			Point p2 = c.getLocation(null);
			double d = p.getDistance(p2);
			if (d < min) {
				min = d;
				closest = c;
			}
		}
		return closest;
	}

	public Vector<FixedConnectionAnchor> getSourceConnectionAnchors() {
		 
		return outputConnectionAnchors;
	}

	public ConnectionAnchor getTargetConnectionAnchorAt(Point p) {
		
		ConnectionAnchor closest = null;
		double min = Double.MAX_VALUE;

		Enumeration<FixedConnectionAnchor> e = getTargetConnectionAnchors().elements();
		while (e.hasMoreElements()) {
			ConnectionAnchor c = (ConnectionAnchor) e.nextElement();
			System.out.println("getTargetConnectionAnchorAt->c.toString(): "+c.toString());
			Point p2 = c.getLocation(null);
			
			double d =p.getDistance(p2);
			if (d < min) {
				min = d;
				closest = c;
			}
		}
		return closest;
		
	}

	public Vector<FixedConnectionAnchor> getTargetConnectionAnchors() {
		
		return inputConnectionAnchors;
	}
	


}
