package com.bitwise.app.graph.figure;


import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * The Class PortFigure.
 * 
 * @author Bitwise
 */
public class PortFigure extends Figure {

	private Color portColor;
	private String terminal;
	private FixedConnectionAnchor anchor;
	
	/**
	 * Instantiates a new port figure.
	 * 
	 * @param portColor
	 *            the port color
	 * @param terminal
	 *            the terminal
	 */
	public PortFigure(Color portColor, String portType, int portSeq, int totalPorts) {
		this.portColor = portColor;
		this.terminal = portType+portSeq;
		this.anchor = new FixedConnectionAnchor(this, portType, totalPorts, portSeq);
		getBounds().setSize(new Dimension(8, 8));

	}

	@Override
	protected void paintFigure(Graphics graphics) {

		super.paintFigure(graphics);
		Rectangle r = getBounds().getCopy();
		graphics.fillRectangle(getBounds().getLocation().x, getBounds()
				.getLocation().y, r.width, r.height);

	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof PortFigure) {
			PortFigure pf = (PortFigure) o;
			if ( 
					pf.getParent() == this.getParent() &&
					pf.getTerminal() == this.getTerminal()

					)
				return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		int var1 = terminal.length();
		int sequence = terminal.charAt(terminal.length() - 1);
		int var2 = portColor.hashCode();
		result = 31 * result + var1;
		result = 31 * result + sequence;
		result = 31 * result + var2;
		
		return result;
		
	}
	
	public void selectPort(){
		setBackgroundColor(ELTColorConstants.blueBrandBoder);
	}
	
	public void deSelectPort(){
		setBackgroundColor(ELTColorConstants.componentBorder);
	}
	
	@Override
	public void validate() {
		super.validate();

		if (isValid())
			return;

	}

	public Rectangle getHandleBounds() {
		return getBounds().getCopy();
	}

	public String getTerminal() {
		return terminal;
	}
	

	public FixedConnectionAnchor getAnchor() {
		return anchor;
	}


	@Override
	public String toString() {
				
		 return "\n******************************************"+
				"\nTerminal: "+this.terminal+
				"\nParent Figure: "+this.getParent()+
				"\nHashcode: "+ hashCode()+
				"\n******************************************\n";
	}

}
