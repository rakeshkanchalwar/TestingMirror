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

	private boolean left, right;
	private int numberOfOutGoingLinksLimit;
	private int numberOfInComingLinksLimit;
	

	public boolean isLeft() {
		return left;
	}

	

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

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
		
		//Point p = getOwner().getBounds().getCenter();
		Point p = null ;
		int ht=getOwner().getBounds().height/2;
		if(this.left)
			 p = getOwner().getBounds().getLeft();
		else if(this.right)
			 p = getOwner().getBounds().getRight();
		return p;
	}

		
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (o instanceof FixedConnectionAnchor) {
			FixedConnectionAnchor fa = (FixedConnectionAnchor) o;
			
//			if ( fa.getOwner() == this.getOwner() &&
//				 fa.getNumberOfInComingLinksLimit() == this.getNumberOfInComingLinksLimit() &&
//				 fa.getNumberOfOutGoingLinksLimit() == this.getNumberOfOutGoingLinksLimit() &&
//				 fa.getReferencePoint() == this.getReferencePoint())
//				return true;
			
			if ( fa.getOwner() == this.getOwner())
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
		return  this.getOwner().hashCode();
	}
	@Override
	public void addAnchorListener(AnchorListener listener) {
		// TODO Auto-generated method stub
		super.addAnchorListener(listener);
	}
	
	public int getNumberOfOutGoingLinksLimit() {
		return numberOfOutGoingLinksLimit;
	}

	public void setNumberOfOutGoingLinksLimit(int numberOfOutGoingLinksLimit) {
		this.numberOfOutGoingLinksLimit = numberOfOutGoingLinksLimit;
	}

	public int getNumberOfInComingLinksLimit() {
		return numberOfInComingLinksLimit;
	}

	public void setNumberOfInComingLinksLimit(int numberOfInComingLinksLimit) {
		this.numberOfInComingLinksLimit = numberOfInComingLinksLimit;
	}
}
