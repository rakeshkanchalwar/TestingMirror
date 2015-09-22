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

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.AnchorListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;

public class FixedConnectionAnchor extends AbstractConnectionAnchor {

	private boolean allowMultipleLinks, linkMandatory;
	private String type;
	private int totalPortsOfThisType;
	private int sequence;

	

	public FixedConnectionAnchor(IFigure owner) {
		super(owner);
	}

	/**
	 * @see org.eclipse.draw2d.AbstractConnectionAnchor#ancestorMoved(IFigure)
	 */
	public void ancestorMoved(IFigure figure) {
		if (figure instanceof ScalableFigure)
			return;
		super.ancestorMoved(figure);
	}
	
	public Point getLocation(Point reference) {
		System.out.println("getLocation method from anchor called!!");
		Point p = null ;
		int portOffsetFactor = this.totalPortsOfThisType+1;
		int height = getOwner().getBounds().height;
		int portOffset=height/portOffsetFactor;
		
		int xLocation, yLocation;
		
		System.out.println("portOffsetFactor: "+portOffsetFactor);
		System.out.println("height: "+height);
		System.out.println("portOffset: "+portOffset);
		
		if(this.type.equals("in")){
			 xLocation=getOwner().getBounds().getTopLeft().x;
			 yLocation=getOwner().getBounds().getTopLeft().y+portOffset*this.sequence;
			 System.out.println("Returning point with xLocation, yLocation: "+xLocation+" "+yLocation);
			 p=new Point(xLocation, yLocation);
		}
		else if(this.type.equals("out")){
			 xLocation=getOwner().getBounds().getTopRight().x;
			 yLocation=getOwner().getBounds().getTopRight().y+portOffset*this.sequence;
			 System.out.println("Returning point with xLocation, yLocation: "+xLocation+" "+yLocation);
			 p=new Point(xLocation, yLocation);
		}
		return p;
	}

		
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public void setAllowMultipleLinks(boolean allowMultipleLinks) {
		this.allowMultipleLinks = allowMultipleLinks;
	}

	public void setLinkMandatory(boolean linkMandatory) {
		this.linkMandatory = linkMandatory;
	}

	public boolean isAllowMultipleLinks() {
		return allowMultipleLinks;
	}

	public boolean isLinkMandatory() {
		return linkMandatory;
	}

	public int getTotalPortsOfThisType() {
		return totalPortsOfThisType;
	}

	public void setTotalPortsOfThisType(int totalPortsOfThisType) {
		this.totalPortsOfThisType = totalPortsOfThisType;
	}

	
	@Override
	public String toString() {
				
		 String str="\n******************************************"+
				"\nOwner: "+getOwner()+
				"\nallowMultipleLinks: "+this.allowMultipleLinks+
				"\nlinkMandatory: "+this.linkMandatory+
				"\ntype: "+this.type+
				"\nsequence: "+this.sequence+
				"\ntotalPortsOfThisType: "+this.totalPortsOfThisType+
				"\n******************************************\n";
		 return str;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (o instanceof FixedConnectionAnchor) {
			FixedConnectionAnchor fa = (FixedConnectionAnchor) o;
			
			if ( fa.getOwner() == this.getOwner() &&
					fa.getType().equals(this.getType()) &&
					fa.getTotalPortsOfThisType()==this.getTotalPortsOfThisType() &&
					fa.getSequence() == this.getSequence() &&
					fa.allowMultipleLinks == this.allowMultipleLinks &&
					fa.linkMandatory == this.linkMandatory
				)
				return true;
			
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int result = 17;
		int var1 = (allowMultipleLinks?1:0);
		int var2 = (linkMandatory?1:0);
		result = 31 * result + var1;
		result = 31 * result + var2;
		
		return result;
		
		
	}
	@Override
	public void addAnchorListener(AnchorListener listener) {
		// TODO Auto-generated method stub
		super.addAnchorListener(listener);
	}
	
}
