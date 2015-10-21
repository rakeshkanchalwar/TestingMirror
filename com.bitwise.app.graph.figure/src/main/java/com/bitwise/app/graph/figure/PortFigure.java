package com.bitwise.app.graph.figure;


import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class PortFigure extends Figure {

	private Color portColor;
	private String terminal;
	
	public PortFigure(Color portColor, String terminal) {
		this.portColor = portColor;
		this.terminal = terminal;
		
		getBounds().setSize(new Dimension(8, 8));

	}

	@Override
	protected void paintFigure(Graphics graphics) {

		super.paintFigure(graphics);
		Rectangle r = getBounds().getCopy();
		graphics.setBackgroundColor(portColor);
		graphics.fillRectangle(getBounds().getLocation().x, getBounds()
				.getLocation().y, r.width, r.height);

	}

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
	
	public Color getPortColor() {
		return portColor;
	}

	public void setPortColor(Color portColor) {
		this.portColor = portColor;
	}

	@Override
	public String toString() {
				
		 String str="\n******************************************"+
				"\nTerminal: "+this.terminal+
				"\nParent Figure: "+this.getParent()+
				"\nHashcode: "+ hashCode()+
				"\n******************************************\n";
		 return str;
	}

}
