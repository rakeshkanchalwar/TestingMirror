package com.bitwise.app.graph.figure;

import java.math.BigInteger;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.swt.graphics.Font;

public class InputFigure extends ComponentFigure
implements HandleBounds{
	Point labelPoint = new Point(10, 8);
	Font labelFont = new Font(null, "", 10, 0); 
	protected static PointList connector = new PointList();
	private Label labelName = new Label();
	
	FixedConnectionAnchor c;
	
	
	public InputFigure(BigInteger outPorts, String componentName) {
		super(componentName);
		System.out.println("InputFigure.componentName: " + componentName);
		c = new FixedConnectionAnchor(this);
		c.setNumberOfOutGoingLinksLimit(1);
		c.setRight(true);
		connectionAnchors.put(outPorts.toString(), c);
		outputConnectionAnchors.addElement(c);

	}

	@Override
	protected void paintFigure(Graphics graphics) {

		super.paintFigure(graphics);
		Rectangle r = getBounds().getCopy();
		// setBorder(SimpleEtchedBorder.singleton);

		System.out.println("InputFigure:paintFigure");

		graphics.translate(r.getLocation());

		// graphics.setBackgroundColor(ColorConstants.lightBlue);
		graphics.setBackgroundColor(ColorConstants.lightGray);
		graphics.setForegroundColor(ColorConstants.black);

		graphics.fillRectangle(0, 0, r.width, r.height);

		graphics.setFont(labelFont);
		graphics.drawText(getLabelName(), labelPoint);

		// ---------------------------------

	}

	@Override
	public void validate() {
		super.validate();
		System.out.println("InputFigure:validate");
		if (isValid())
			return;

	}
	

	protected boolean useLocalCoordinates() {
		return true;
	}

	public Rectangle getHandleBounds() {
		//return getBounds().getCropped(new Insets(2, 0, 2, 0));
		return getBounds().getCopy();
	}

	
	
}
