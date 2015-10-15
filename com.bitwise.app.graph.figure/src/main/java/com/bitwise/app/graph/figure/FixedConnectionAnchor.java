
package com.bitwise.app.graph.figure;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.AnchorListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.geometry.Point;

public class FixedConnectionAnchor extends AbstractConnectionAnchor {

	private boolean allowMultipleLinks, linkMandatory;
	private String type;
	private int totalPortsOfThisType;
	private int sequence;

	

	public FixedConnectionAnchor(IFigure owner, String type, int totalPortsOfThisType, int sequence) {
		super(owner);
		this.type=type;
		this.totalPortsOfThisType=totalPortsOfThisType;
		this.sequence=sequence;
	}

	/**
	 * @see org.eclipse.draw2d.AbstractConnectionAnchor#ancestorMoved(IFigure)
	 */
	public void ancestorMoved(IFigure figure) {
		if (figure instanceof ScalableFigure)
			return;
		super.ancestorMoved(figure);
	}
	
	@Override
	public Point getLocation(Point reference) {
		
		Point p = null ;
		int portOffsetFactor = this.totalPortsOfThisType+1;
		int height = getOwner().getBounds().height;
		int portOffset=height/portOffsetFactor;
		
		int xLocation =0, yLocation = 0;
		
		
		if(this.type.equalsIgnoreCase("in")){
			 xLocation=getOwner().getBounds().getTopLeft().x;
			 yLocation=getOwner().getBounds().getTopLeft().y+portOffset*this.sequence;
		}
		else if(this.type.equalsIgnoreCase("out")){
			 xLocation=getOwner().getBounds().getTopRight().x;
			 yLocation=getOwner().getBounds().getTopRight().y+portOffset*this.sequence;
		}
		Point point= new Point(xLocation, yLocation);
		getOwner().translateToAbsolute(point);
		return point;
	}

		
	public String getType() {
		return type;
	}

	public int getSequence() {
		return sequence;
	}

	public void setAllowMultipleLinks(boolean allowMultipleLinks) {
		this.allowMultipleLinks = allowMultipleLinks;
	}

	public void setLinkMandatory(boolean linkMandatory) {
		this.linkMandatory = linkMandatory;
	}

	public int getTotalPortsOfThisType() {
		return totalPortsOfThisType;
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
		super.addAnchorListener(listener);
	}
	
}
